<template>
    <div id = "container" align-center>
        <v-container>
            <v-spacer></v-spacer>
                <!-- 로그인버튼-->
                <v-btn text @click="regFeedForm(member)">
                    <v-chip color="#2ac187" class="mx-auto white--text font-weight-bold">
                        <v-icon left>mdi-plus</v-icon>글쓰기
                    </v-chip>
                </v-btn>
                <!-- 로그인버튼 끝 -->


                    <!-- 검색창 끝 -->
                    <div class="mx-auto" style="width:500px;">
                    <v-text-field class="rounded-xl mx-auto"
                            v-model="keyword"
                            :items="ctg" 
                            item-text="tag" 
                            item-value="tag" 
                            label="해시태그나 유저 아이디를 검색해보세요!"
                            dense
                            outlined
                            :search-input.sync="userInput" 
                            @input="userInput=null"
                            menu-props="{'closeOnContentClick': true}"
                            append-icon="mdi-magnify"
                            @change="searchMem(keyword)"
                            @keydown.enter="searchMem(keyword)"
                            style="height:50px" />
                            

                    <!-- 상단바 HOT해시태그 (키워드검색) -->
                    <div id="chip" style="width:500px;">
                        <v-sheet ref="getHashtag">
                            <v-chip-group active-class="primary--text">
                                <v-chip @click="searchHashtag(item.hashtag)"
                                        justify="space-around"
                                        v-for="(item,i) in items" 
                                        :key="i"
                                        color="#2ac187"
                                        class="mx-auto white--text font-weight-bold">
                                    {{ item.hashtag }}
                                </v-chip>
                            </v-chip-group>
                        </v-sheet>
                    </div>
                </div>

                
            <!-- 검색컴포넌트 
                검색결과가 있을땐 show를 트루로 바꿔서 HotList가 안보이게되도록.
            -->





            <div id="searchResult" v-if="show == true">
                <div id="nonuserFeeds">
                    <h3>검색페이지입니다</h3>
                    <NoneUser :feeds="feeds" />
                </div>
            </div>


            <div v-if="showHot == true">
                <div id="hotLecturers">
                    <h3>추천 만능 재주꾼들 피드</h3>
                    <HotLecturer name="this.items" />
                </div>

                <div id="nonuserFeeds">
                    <h3>재주 견습생들 피드</h3>
                    <NoneUser />
                </div>
            </div>
            <!-- 유저 검색 페이지 -->
            <div v-if="showResult == true">
                <Follow :searchResult="searchResult" />
            </div>
        </v-container> 
    </div>
  </template>
  <script>
    import SnsSearchbar from "@/components/sns/Common/Searchbar.vue"
    import SnsSidebar from "@/components/sns/Common/SnsSidebar.vue";
    import HotLecturer from "@/components/sns/Main/HotLecturer.vue";
    import NoneUser from "@/components/sns/Main/Noneuser.vue";
    import Follow from "@/components/sns/Management/Follower.vue";
  
    export default {
      name: "snsMain",
      components: { SnsSidebar, SnsSearchbar, HotLecturer, NoneUser, Follow },
     
      data() {
          return {
            feeds: [],//해시검색에 받아온
            searchResult: "",//검색창에서 받아온 결과
            keyword: "",
            //   noneuser : false,
            items: [], //HOT해시태그
            member : this.$store.state.id,
            show: false, //1:검색 페이지
            showHot: true, //howLectureList
            showResult: false, //user검색 페이지
            // showHashtag : "",
              //자동검색
            ctg: [
                { tag: '운동' },
                { tag: '공예' },
                { tag: '연극' },
                { tag: '독서' },
            ],
            userInput: null,
            temp:"", //검색한 단어
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
          this.searchResult=this.$route.params.hashtagResult; //피드디테일에서 받아옴 -> searchPage
          console.log(this.$route.params.hashtagResult);//(없을시 undefined)
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

          //키워드 해시태그 검색
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
                //   this.showHashtag = getHashtag;
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

          searchMem(keyword){
            this.searchResult = this.keyword; //props로 보낼 값 바인딩
            console.log(this.searchResult);
            this.show = false;
            this.showHot = false;
            this.showResult = true;
            // this.keyword = "";
          },



            //데이터 저장시 배열 선언하여 각각의 정보들을 배열의 요소로 추가하고 한 이름에 대한 정보들은 여러 항목이 있기에 객체로 저장한다.
            
            //1. 데이터 저장하기
            //enter를 하게 되면 or 등록 버튼을 click을 하게되면 저장이 되어야 함.
            // localStorage.setItem(key, value); //저장할이름, 저장할 내용
            // localStorage.setItem("searchKeyword", JSON.stringify(this.search));


            // objArr = [{num:1, title:'test', contents:'blahblah'}, ...];

            //반복가능한 객체가 아니어서 키와 값 전체를 얻으려면 배열처럼 다뤄야한다.
            // for(let i=0; i<localStorage.length; i++) {
            //     let key = localStorage.key(i);
            //     alert(`${key}: ${localStorage.getItem(key)}`);
            // }

            //데이터 불러오기. 데이터 조회
            //검색창 바디를 클릭하면 기록리스트를 불러온다. 해당 키에 값이 없는 경우 ""로 할당한다.
            // localStorage.getItem(key);
            // userInfo: JSON.parse(localStorage.getItem("searchKeyword")) || "",

            // 키에 해당되는 데이터 삭제
            // localStorage.removeItem(key); 

            // 저장소 데이터 전체 삭제
            // localStorage.clear(); 


            //자바코드를 이용해 삭제해야함.
            //자동으로 삭제되지 않으나 캐시를 사용하는경우 삭제됨

            //window객체의 localStorage에 위치한다.

            //#을 붙여 검색해보세요 -> 해시태그
            // console.log(temp.indexOf('#'))
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

    }
};
  </script>
  
  <style scoped lang="css" src="@/assets/css/sns/SnsMain.css" />