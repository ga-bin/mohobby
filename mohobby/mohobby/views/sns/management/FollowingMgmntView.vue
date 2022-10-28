<template>
    <div>
      <SnsSidebar></SnsSidebar>
      <h1>sns 관리 화면</h1>
      <Following :followingLists = "followingLists" />

      <!-- <Lecture />
      <Moim />
      <Challenge />
      <Following /> -->

    </div>
  </template>
<script>
    import SnsSidebar from "@/components/sns/Common/SnsSidebar";
    import Following from "@/components/sns/Management/User";
    // import Follower from "@/components/sns/Management/Following";
    import Lecture from "@/components/sns/Management/Lecture";
    import Moim from "@/components/sns/Management/Moim";

  export default {
    name: "Follower",
    components: { SnsSidebar, Following },
    // Following, Lecture, Moim, Lecture
    data() {
      return {
        followingLists: [],
        memberId : this.$store.state.id,
      };
    },
    setup() {
      
    },
    created() {
  
    },
    mounted() {
  
    },
    unmounted() {
  
    },
    methods: {
      //FollowingList조회
      getFollowingList() {

        this.axios('/sns/main/followingFeeds/' + this.memberId)
        .then(res => {
            console.log(res.data);
            this.followingLists = res.data;
            if (this.feeds.length < 1){ //피드값이 없으면 전체리스트 호출
                // 결과 없음 페이지 ON
            }
            console.log("팔로잉목록 호출 성공");
        }).catch(err =>{
            console.log(err);
        });

      },
    },
  };
  </script>
  
  <style></style>
  