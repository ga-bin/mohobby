<template>
        <v-container id="main">


                <!-- 검색창 끝 -->
                <div class="mx-auto" style="width:500px;">
                    <v-text-field class="rounded-xl mx-auto" v-model="keyword" label="해시태그나 유저 아이디를 검색해보세요!" dense
                        outlined append-icon="mdi-magnify" @keydown.enter="search(keyword)" style="height:50px" />

                    <!-- 상단바 HOT해시태그 (키워드검색) -->
                    <div id="chip" style="width:500px;">
                        <v-sheet ref="getHashtag">
                            <v-chip-group active-class="primary--text">
                                <v-chip @click="searchHashtag(item.hashtag)" justify="space-around"
                                    v-for="(item, i) in items" :key="i" color="#1862C9"
                                    class="mx-auto white--text font-weight-bold">
                                    #{{ item.hashtag }}
                                </v-chip>
                            </v-chip-group>
                        </v-sheet>
                    </div>
                </div>
                <div v-show = "member" style="float:right">
                        <!-- 내 피드 버튼 -->
                        <v-btn  @click="goMyFeed(member)"
                                title="내 프로필로"
                                icon
                                style="position:relative; font-size: 1px;" 
                                class="mr-5 mx-auto black--text font-weight-bold">
                                <v-icon color="#2255b1"
                                        class="mx-auto mb-1"
                                        style="position:absolute;"
                                        outline
                                        left>mdi-robot-love
                                </v-icon>
                            </v-btn>
                            
                            <!-- 글쓰기 버튼 -->
                            <v-btn @click="regFeedForm(member)"
                                    title="글쓰기"
                                    icon 
                                    class="mx-auto black--text font-weight-bold">
                                <v-icon color="#2255b1" left>mdi-lead-pencil</v-icon>
                            </v-btn>
                </div>

                <!-- 피드 -->
                <div v-if="randomFeeds == true" id="nonuserFeeds">
                    <h3 v-if="!member">재주 견습생들 피드</h3>
                    <h3 v-else>나의 팔로워 피드</h3>
                    <NoneUser :feeds="feeds" />
                    <!-- 팔로워 제외 피드 갖다붙일방법,, -->
                    <!-- <NoneUser :feeds="feeds" /> --> 
                </div>
            </div>

            <!-- 전체 피드
        <h3>전체 피드</h3> -->


            <!-- 해시태그 검색페이지 : 해시태그 검색-->
            <div v-if="tagSearch == true">
                <div>
                    <h3>검색페이지입니다</h3>
                    <NoneUser :feeds="hashResult" :keyword="temp" />
                </div>
            </div>


                <!-- 유저 검색 페이지-->
                <div v-if="userSearch == true">
                    <UserResult :userResult="userResult" :followType = "followType" :keyword="temp" />
                </div>

            <div v-if="noResult == true">
                <NoResult :keyword="temp" />
            </div>
     
    </v-container>


</template>
<script>

import SnsSidebar from "@/components/sns/Common/SnsSidebar.vue";
import HotLecturer from "@/components/sns/Main/HotLecturer.vue";
import NoneUser from "@/components/sns/Main/Noneuser.vue";
import UserResult from "@/components/sns/Management/User.vue";
import NoResult from "@/components/sns/Search/NoResult.vue";


export default {

name: "snsMain",

components: { SnsSidebar, HotLecturer, NoneUser, UserResult, NoResult },

data() {
    return {
        offsetTop: 0,
        firstIdx: 0,
        feeds: [],
        allFeed: [],
        hashResult: [],
        userResult: [],
        items: [], //HOT해시태그

        // searchResult: "",//검색창에서 받아온 결과

            //컴포넌트 v-if조건
            tagSearch: false, //해시태그검색페이지
            userSearch: false, //유저검색페이지
            hotLectureFeeds: false, //HOT LIST 페이지
            randomFeeds: false, //ALL LIST 페이지
            noResult: false, //검색결과 없음 페이지

            followType: Number,
        keyword: "", //v-model키워드값
        temp: "", //임시 키워드 저장소
        member: this.$store.state.id,


        //팔로우관리
        followerLists: [],
        followingLists: [],
        followerCheck: Number,
        check: false,

    }
},

watch: {
    //검색창
    // userInput(val) {
    //     if (!val) {
    //         return
    //     }
    //     this.fetchEntriesDebounced()
    // },
},

created() {
    this.getHotHashtags();// 상단바 Hot해시태그
    this.hotLectureFeeds = true;
    this.randomFeeds = true;

    //로그인 아이디 있으면 팔로워리스트, 없으면 전체랜덤리스트
    if (this.member) {

        this.getFollowingList();
        this.getNoFollowingList();
        console.log("회원 팔로잉리스트호출");

    } else {

        this.getAllList();
        console.log("비회원 전체리스트 호출");

    }



    //디테일에서 들어온 해시태그 키워드
    if (this.$route.params.detailHashtag) {
        this.keyword = this.$route.params.detailHashtag;
        this.search(this.keyword);

        this.tagSearch = true;
        this.hotLectureFeeds = false;
        this.randomFeeds = false;
        this.userSearch = false;
    }

},

methods: {
   
    //AllList조회
    getAllList() {

        this.axios('/sns/main/allFeeds')
            .then(res => {
                console.log(res);
                this.feeds = res.data;
                console.log("전체랜덤피드 호출 성공");
            }).catch(err => {
                console.log(err);
            });

    },

    //전체 피드조회
    getAllListPaging() {
        let vm = this
        this.axios.get("/sns/main/allFeed", {
            params: {
                firstIdx: this.firstIdx
            }
        }).then(function (res) {
            console.log(res.data)
            for (let i = 0; i < res.data.length; i++) {
                vm.feeds.push(res.data[i])
            }
            vm.firstIdx = vm.firstIdx + 20
        }).catch(function (err) {
            console.log(err)
        })

            this.temp = "'" + keyword + "'에 대한 검색결과입니다";
            this.axios('/sns/search/user', {
                params: {
                    memberId: keyword
                }
            }).then(res => {
               
                this.userResult = res.data;
                this.followType = 3;
                this.keyword = "";

                //페이지 노출여부 컨트롤
                if (this.userResult.length < 1) {
                    this.noResult = true;
                    this.userSearch = false;
                    this.tagSearch = false;
                    this.hotLectureFeeds = false;
                    this.randomFeeds = false;

                } else {
                    this.userSearch = true;
                    this.tagSearch = false;
                    this.hotLectureFeeds = false;
                    this.randomFeeds = false;
                    this.noResult = false;
                }
                console.log("팔로잉목록 호출 성공");
            }).catch(err => {
                console.log(err);
            });

    },


    //No팔로잉 조회
    getNoFollowingList(){

        this.axios('/sns/main/nofollowingFeeds/' + this.member)
            .then(res => {
                console.log(res.data);
                this.feeds = res.data;
                if (this.feeds.length < 1) { //피드값이 없으면 전체리스트 호출
                    this.getAllList();
                }
                console.log("팔로잉목록 호출 성공");
            }).catch(err => {
                console.log(err);
            });

    },


    //상단바 - top6해시태그 리스트
    getHotHashtags() {

        this.axios('/sns/main/hashtag')
            .then(res => {
                this.items = res.data;
            }).catch(err => {
                console.log(err);
            });

    },


    

    /*
        검색창
          1. '#'가 포함된 검색어 -> 해시태그 검색
          2. 그 외 유저검색
    */
    search(keyword) {

        if (keyword.includes("#")) {

            let hashtag = keyword.slice(1);
            this.searchHashtag(hashtag);

        } else { 
            this.searchMem(keyword); 
        }
    },


    //1. 해시태그 검색
    searchHashtag(hashtag) {
        this.temp = "'" + hashtag + "'에 대한 검색결과입니다";

        this.axios('/sns/search/hashtag', {
            params: {
                hashtag: hashtag
            }
        }).then(res => {

            this.hashResult = res.data;
            this.keyword = "";

            //페이지 노출여부 컨트롤
            if (this.hashResult.length < 1) {
                this.noResult = true;
                this.tagSearch = false;
                this.userSearch = false;
                this.hotLectureFeeds = false;
                this.randomFeeds = false;

            } else {
                this.tagSearch = true;
                this.noResult = false;
                this.userSearch = false;
                this.hotLectureFeeds = false;
                this.randomFeeds = false;
            }

        }).catch(err => {
            console.log(err);
        });
    },


    //2. 유저검색
    searchMem(keyword) {

        this.temp = "'" + keyword + "'에 대한 검색결과입니다";
        this.axios('/sns/search/user', {
            params: {
                memberId: keyword
            }
        }).then(res => {
           
            this.userResult = res.data; 
            this.keyword = "";

            //페이지 노출여부 컨트롤
            if (this.userResult.length < 1) {
                this.noResult = true;
                this.userSearch = false;
                this.tagSearch = false;
                this.hotLectureFeeds = false;
                this.randomFeeds = false;

            } else {
                this.userSearch = true;
                this.tagSearch = false;
                this.hotLectureFeeds = false;
                this.randomFeeds = false;
                this.noResult = false;
            }

        }).catch(err => {
            console.log(err);
        });
    },


    //팔로우 상태 조회
    checkFollowState(){
    this.followerCheck= []; //쌓이지 않게 초기화
    
    for(let i=0; i<this.followerLists.length; i++){
      
      for(let j=0; j<this.followingLists.length; j++){
       
        if(this.followerLists[i].followingId==this.followingLists[j].followerId){

          this.followerCheck.push(1); // 맞팔이면 1
          this.check=true; // 위의 조건을 만족하면 check값을 true로
          
         break;
        }
      }

      if(!this.check){ // check가 true로 바뀌면 나머지는 0
        this.followerCheck.push(0);
      }

      this.check=false //check값 초기화
    }
  },


  //팔로워 목록조회
  getFollowerList() {
    let vm = this;

    this.axios('/sns/follow/search/follower/'+ this.memberId)
    .then(res => {
    
      for(let i=0; i<res.data.length; i++){

        this.followerLists = res.data;
        vm.getFollowingList();
      }
    }).catch(err =>{
        console.log(err);
    });
},

  //팔로잉 목록조회
  getFollowingList() {
    let vm = this;

    this.axios('/sns/follow/search/following/' + this.memberId)
    .then(res => {

        this.followingLists = res.data;

        console.log("팔로잉목록 호출 성공");
        vm.checkFollowState();

    }).catch(err =>{
        console.log(err);
    });

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
    select: function () {
        if (this.member) {
            this.$router.push({ path: 'snsFeedRegister' })
        }
    },

    //글등록버튼
    regFeedForm(member) {
        if (member == "" || member == null) {
            this.$swal('로그인부터 부탁드립니다🙏');
            this.$router.push({ path: 'login' });
        } else {
            this.$router.push({ path: 'snsFeedRegister' });
        }
    },

    //내 피드로 이동
    goMyFeed(member) {
        if (member == undefined || member == null || member == "") {
            return;
        }
        this.$router.push({ name: 'snsUserFeed', query: { userId: member } });
    },

    // 내려오면 api를 호출하여 아래에 더 추가,


}
};
</script>





<style scoped lang="css" src="@/assets/css/sns/SnsMain.css" />