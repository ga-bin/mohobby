<template>
    <div>
        <SnsSidebar></SnsSidebar>
            <div id = "container">
                <div id="searchbar">
                    <div>
                      <v-btn color="red" @click="goMyFeed(member)">내 피드가기</v-btn>  
                    </div>
                    <div class="regFeed">
                        <!-- 로그인폼: 비회원일때-->
                        <v-card-actions  v-if="!this.member" >
                            <v-spacer></v-spacer>
                            <div class="text-center">
                                <v-dialog
                                        v-model="noneuser"
                                        width="500"
                                >
                                <template v-slot:activator="{ on, attrs }">
                                    <v-btn text>
                                        <v-chip
                                        color="success"
                                        outlined
                                        v-bind="attrs"
                                        v-on="on"
                                        >
                                        <v-icon left>mdi-plus</v-icon>글쓰기
                                        </v-chip>
                                    </v-btn>
                                </template>
                                    <v-card>
                                    <br><br>
                                    <v-card-text class="font-weight-bold center">
                                        로그인이 필요합니다 !
                                    </v-card-text>
                                    <v-divider></v-divider>
                                    <v-card-actions>
                                        <v-spacer></v-spacer>
                                        <v-btn
                                        color="success"
                                        depressed
                                        @click="login()"
                                        >로그인하러 가기</v-btn>
                                        <v-btn
                                        depressed
                                        @click="noneuser=false"
                                        >닫기</v-btn>
                                    </v-card-actions>
                                </v-card>
                            </v-dialog>
                        </div>
                    </v-card-actions>

                    <!-- 글쓰기폼: 회원일때 -->
                    <v-card-actions v-else>
                        <v-spacer></v-spacer>
                        <v-btn text>
                            <v-chip
                            color="success"
                            outlined
                            @click="select"
                            >
                            <v-icon left>mdi-plus</v-icon>
                            글쓰기
                            </v-chip>
                        </v-btn>
                    </v-card-actions>
                </div>

            <!-- 검색창 -->
            <div>
            <!-- 키워드 검색(해시태그) -->
            <v-card-actions>
                <v-spacer></v-spacer>
                <div id="chip">
                    <v-row justify="space-around">
                        <v-col cols="12">
                            <v-sheet ref="getHashtag">
                                <v-chip-group active-class="primary--text">
                                    <v-chip v-for="(item,i) in items" 
                                            :key="i"
                                            @click="searchHashtag(item.hashtag)"
                                            color="#2ac187"
                                            class="mx-auto white--text font-weight-bold">
                                        {{ item.hashtag }}
                                    </v-chip>
                                </v-chip-group>
                            </v-sheet>
                        </v-col>
                    </v-row>
                </div>
            </v-card-actions>
        </div>
    </div>

        <!-- 검색컴포넌트 -->
        <div id="searchResult" v-if="show">
            <SearchPage :feeds="feeds" />
            </div>
            <div v-else>
                <!-- 메인 컴포넌트 -->
                <div id="hotLecturers">
                <!-- 인기 피드리스트 -->
                    <h3>추천 만능 재주꾼들 피드</h3>
                    <HotLecturer name="this.items" />
                </div>

                <div id="nonuserFeeds">
                    <h3>재주 견습생들 피드</h3>
                    <!-- 랜덤피드 무한스크롤링 -->
                    <NoneUser />
                </div>
            </div> 
        </div>
    </div>
  </template>
  <script>
    import SnsSearchbar from "@/components/sns/Common/Searchbar.vue"
    import SnsSidebar from "@/components/sns/Common/SnsSidebar.vue";
    import SearchPage from "@/views/sns/SnsSearchPage.vue";
    import HotLecturer from "@/components/sns/Main/HotLecturer.vue";
    import NoneUser from "@/components/sns/Main/Noneuser.vue";
  
    export default {
      name: "snsMain",
      components: { SnsSidebar, SnsSearchbar, HotLecturer, NoneUser, SearchPage },
     
      data() {
          return {
              feeds: [],
              word: "",
              noneuser : false,
              items: [],
              member : this.$store.state.id,
              show: false, //1:검색 결과 페이지
              noResult: false, //1:검색결과 없음
              main: true,
              showHashtag : "",
          }
      },
      watch: {},
      created() {
          this.getHotHashtags();//함수실행
          this.feeds=this.$route.params.hashtagResult; //피드디테일에서 받아옴
          console.log(this.$route.params.hashtagResult);//(없을시 undefined)
          console.log(this.$store.state.id);
          this.show=this.$route.params.showing
        //   if(this.$route.params.mainn != undefined || this.$route.params.mainn != ""){
        //     this.main = this.$route.params.mainn;
        //       console.log(this.$route.params.mainn);
        //   }
      },
      methods: {
          //상단바에 표시되는 top6해시태그
          getHotHashtags() {
              this.axios('/sns/main/hashtag').then(res => {
              this.items = res.data;
            }).catch(err =>{
              console.log(err);
            });
          },

          //해시태그 검색
          searchHashtag(getHashtag){
              console.log("받아온 해시태그 ->");
              console.log(getHashtag);
              this.axios('/sns/search/hashtag', {
                  params : {
                      hashtag : getHashtag
                  }
              }).then(res => {
                  this.feeds = res.data;
                  console.log("피드받기 성공!");
                  this.showHashtag = getHashtag;
                  this.show = true;
                  this.main = false;
                  if (this.feeds.length === 0){
                    this.noResult = true;
                    this.main = false;
                  }
                  
              }).catch(err =>{
                  console.log(err);
              });
          },
          
          //글 등록 이동
          select : function() {
              if (this.member) {
              this.$router.push({ path: 'snsFeedRegister' })
              }
          },
          //로그인폼으로 이동
          login() {
              this.$router.push({ path: 'login' })
          },
          //내 피드로 이동
          goMyFeed(member){
            if (member == undefined) {
                member = 'user11'
                this.$router.push({ name: 'snsUserFeed', query: {memId : member} });
            }
            this.$router.push({ name: 'snsUserFeed', query: {memId : member} });
          }
        }
    };
  </script>
  
  <style scoped>
      * {
        list-style:none;
      }
      
      #container {
        width : 80%;
        list-style:none;
        margin: 0 auto;
      }
  
      #searchbar{
          width:80%;
          margin: 0 auto;
      }
  
      label {
          margin-right: 3px;
      }

  </style>