<template>
    <div id = "container">
        <div id="searchbar">
            <SnsSidebar></SnsSidebar>
            <div>
                <v-btn color="red" class="mx-auto white--text font-weight-bold" @click="goMyFeed(member)">내 피드가기</v-btn>  
            </div>
            <div class="regFeed">
                <!-- 로그인버튼-->
                <v-btn text @click="regFeedForm(member)">
                    <v-chip color="#2ac187" class="mx-auto white--text font-weight-bold">
                        <v-icon left>mdi-plus</v-icon>글쓰기
                    </v-chip>
                </v-btn>
                <!-- 로그인버튼 끝 -->
                <!-- 검색창 -->
                <div>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                            <v-autocomplete 
                                    v-model="search" 
                                    :items="ctg" 
                                    item-text="tag" 
                                    item-value="tag" 
                                    label="해시태그나 유저 아이디를 검색해보세요"
                                    dense 
                                    outlined
                                    :search-input.sync="userInput" 
                                    @input="userInput=null"
                                    menu-props="{'closeOnContentClick': true}"
                                    class="rounded-xl mx-auto"
                                    append-icon="mdi-magnify"
                                    @change="search()"
                                    @keydown.enter="enter(search)"
                                    style="height:50px"
                            />
                    </v-card-actions>
                    <!-- 검색창 끝 -->
                    <!-- 상단바 HOT해시태그 -->
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
              feeds: [],//해시검색에 받아온
              word: "",
            //   noneuser : false,
              items: [], //HOT해시태그
              member : this.$store.state.id,
              show: false, //1:검색 결과 페이지
              noResult: false, //1:검색결과 없음
              main: true,
              showHashtag : "",
              //자동검색
              ctg: [
                { tag: '운동' },
                { tag: '오공완' },
                { tag: '다이어트' },
                { tag: '건강' },
                { tag: '공예' },
                { tag: '연극' },
                { tag: '취향' },
                { tag: '메이크업' },
                { tag: '오운완' },

            ],
            userInput: null,
            search:"", //검색한 단어
          }
      },
      watch: {
        //검색창
        userInput(val) {
            if (!val) {
                return
            }
            this.fetchEntriesDebounced()
        },
      },
      created() {
          this.getHotHashtags();//함수실행
          this.feeds=this.$route.params.hashtagResult; //피드디테일에서 받아옴 -> searchPage
          console.log(this.$route.params.hashtagResult);//(없을시 undefined)
          console.log(this.$store.state.id);
          this.show=this.$route.params.showing
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
         //댓글 enter등록
         enter(search){
            if (window.event.keyCode == 13) {
            this.search(search);
            }
        },
          //검색
          search(temp) {
            //#을 붙여 검색해보세요 -> 해시태그
            console.log(temp.indexOf('#'))
            // if(temp.indexOf('#') == true)
            //유저검색

            // this.axios('/sns/search/hashtag', {
            //       params : {
            //           hashtag : getHashtag
            //       }
            //   }).then(res => {
            //       this.feeds = res.data;
            //       console.log("검색 성공!");
            //       this.showHashtag = getHashtag;
            //       this.show = true;
            //       this.main = false;
            //       if (this.feeds.length === 0){
            //         this.noResult = true;
            //         this.main = false;
            //       }
                  
            //   }).catch(err =>{
            //       console.log(err);
            //   });
          },

          //글 등록 이동
          select : function() {
              if (this.member) {
              this.$router.push({ path: 'snsFeedRegister' })
              }
          },
          //글등록버튼
          regFeedForm(member) {
            if(member == "" || member == null){
                this.$swal('로그인부터 부탁드립니다🙏');
                this.$router.push({ path: 'login' });
            } else{
                this.$router.push({ path: 'snsFeedRegister' });
            }
          },
          //내 피드로 이동
          goMyFeed(member){
            if (member == undefined || member == null || member =="") {
                return;
            }
            this.$router.push({ name: 'snsUserFeed', query: {userId : member} });
          },
          //검색창
          fetchEntriesDebounced() {
            // cancel pending call
            clearTimeout(this._timerId)

            // delay new call 500ms
            this._timerId = setTimeout(() => {
                // maybe : this.fetch_data()
                this.people = this.itemData ? this.itemData : []
            }, 500)
        },
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