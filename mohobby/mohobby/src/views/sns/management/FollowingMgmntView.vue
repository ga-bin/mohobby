<template>
  <div class="mx-auto">
    <SnsSidebar></SnsSidebar>
    <h1>sns 관리 화면</h1>

      <v-tabs color="#2255b1" fixed-tabs>
        <v-tab @click="show=true">팔로워 목록</v-tab>
        <v-tab @click="show=false">팔로잉 목록</v-tab>
      </v-tabs>

    <Follow v-if="show == true" :userResult = "followerLists" :keyword = "follower" :followerCheck ="followerCheck" :followType = "followerType" @updateFollow="updateFollow" />
      <Follow v-else :userResult = "followingLists" :keyword = "following"  :followType = "followingType" @updateFollow="updateFollow" />

      <NoFollow v-show="noShow == true" :keyword="keyword" />

    <!-- <Lecture />
    <Moim />
    <Challenge />-->

  </div>
</template>
<script>
    import SnsSidebar from "@/components/sns/Common/SnsSidebar";
    import Follow from "@/components/sns/Management/User";
    import NoFollow from "@/components/sns/Management/NoFollow";
    // import Lecture from "@/components/sns/Management/Lecture";
    // import Moim from "@/components/sns/Management/Moim";

  export default {
    name: "Follower",
    components: { SnsSidebar, Follow, NoFollow },
    // Following, Lecture, Moim
    data() {
      return {
        followingLists: [], //내가 구독하는 사람들
        followerLists: [], //나를 구독하는 사람들
        follower:"",
        following:"",

        followerType: Number,
        followingType: Number,
        
        show: Boolean,

        followers: [],
        followings: [],

        followerCheck: [],

        keyword:"",

        noShow: false,
        check :false,
        memberId : this.$store.state.id,
      };
    },
    setup() {
      
    },
    created() {
      this.getFollowerList();
      this. show = true;
    },
    mounted() {
  
    },
    unmounted() {
  
    },
    
    watch() {
      if (this.show == false) {
        this.getFollowingList();
      }
    },


    methods: {
      updateFollow(){
        if( this.show == true ) {
          this.show = false;
          this.getFollowingList();
        } else {

          this.show =true;
          this.getFollowerList();
        }
      },
      //손절 체크
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
        console.log("followCheck : " +this.followerCheck);
      },

      //팔로워 목록조회
      getFollowerList() {
        let vm = this;

        this.axios('/sns/follow/search/follower/'+ this.memberId).then(res => {
        
          for(let i=0;i<res.data.length;i++){
            console.log("follower : " + i + " :"+res.data[i].followerId)
            console.log("following : " + i + " :"+res.data[i].followingId)};

            this.followerLists = res.data;
            this.followerType = 1;

            this.followers = this.followerLists.followerId;
            this.follower= this.memberId + " 님의 팔로워 목록입니다"

            //팔로워 값이 없을 때 노쇼페이지 노출
            if(this.followerLists.length === 0){
              this.noShow = true;
              this.show = false;
              this.keyword="팔로워";
            }
            console.log("팔로워목록 호출 성공");
            // vm.getFollowingList();
           
        }).catch(err =>{
            console.log(err);
        });

      },
      

      //팔로잉 목록조회
      getFollowingList() {
        let vm = this;

        this.axios('/sns/follow/search/following/' + this.memberId)
        .then(res => {
          for(let i=0;i<res.data.length;i++){
            console.log("Cfollower : " + i + " :"+res.data[i].followerId)
            console.log("Cfollowing : " + i + " :"+res.data[i].followingId)};

            this.followingLists = res.data;
            this.followingType = 2;
            this.followings = this.followingLists.followingId;
            this.following= this.memberId + " 님의 팔로잉 목록입니다"

            //팔로잉 값이 없을 때 노쇼페이지 노출
            if(this.followingLists.length === 0){
              this.noShow = true;
              this.show = false;
              this.keyword="팔로잉";
            }
            console.log("팔로잉목록 호출 성공");
            vm.checkFollowState();
        }).catch(err =>{
            console.log(err);
        });

    },




   //게시글 상세 로드
  showDetail(postId, writer) {
    this.axios("/sns/user/feed_detail/" + postId, {
      params: {
        memberId: writer,
      },
    })
      .then((res) => {
        if(this.confirmMember(this.memberId) == true ){
          console.log("로그인세션을 확인합니다");
          this.getBookmarkStatus(postId);
          this.getCollectionList(this.memberId);
        }
        console.log();
        this.items = res.data;
        console.log("비밀글여부: "+ this.items.secPost);

        //자신의 게시물이면 dot list 세팅
        this.lists.push({title: "수정"});
        this.lists.push({title: "삭제"});
        if(this.items.secPost == 0){
          this.lists.push({title: "비밀글로"});
        } else {
          this.lists.push({title: "비밀글 해제"});
        }


        if (this.items.hashtag != null) {
          let str = this.items.hashtag; //%%,%%,%% 형태
          let hashtag = str.split(","); //해시태그 자르기
          this.hashtags = hashtag; //자른 해시태그들 hashtags에 담기
        }
        console.log("상세페이지 접근 성공!");
      })
      .catch((err) => {
        alert("게시글호출 실패" + err);
      });
  },

    

  },
};
</script>

<style></style>