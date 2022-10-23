<template>
  
  <div class="cards-container" >
    <!-- FIRST CARD -->
    <v-slide-group class="pa-2">
        <v-slide-item  v-for="(info,i) in listInfo" :key="i">
          <div class="card card-first">
            <div class="card-header-wrapper" @click="goMyFeed(info.memberId)">
                <h2 class="card-title"></h2>
                <h4 class="card-subtitle">{{info.memberId}}</h4>
                <div class="card-avatar-wrapper" style="width:200px;">
                    <img class="card-avatar" :src="require(`@/assets/image/user/${info.profileImg}`)" alt="프로필사진">
                </div>
            </div>
            <div class="card-photo-wrapper" @click="getFeedDetail(info.memberId, info.postId)">
                <img class="card-photo" :src="require(`@/assets/image/sns/${info.postId}/${info.thumbnail}`)" alt="썸네일">
            </div>
            <p class="card-text">{{ info.content }}</p>
            <div class="card-like" height="32" width="32">
                <div style="width:130px;"><v-icon color="#2ac187">mdi-heart</v-icon> {{ info.likes }} likes</div>       
            </div>
            <button class="card-button">More</button>
          </div>
      </v-slide-item>
    </v-slide-group>
  </div>
</template>
<script>
export default {
  name: "HotLecturer",
  props:{
      items : []
  },
  data() {
    return {
      listInfo: [],
      memberId: this.$store.state.id, //세션아이디
    };
  },
  setup() {
    
  },
  created() {
    this.getHotLecturerList();
    console.log(this.memberId);
  },
  mounted() {

  },
  unmounted() {

  },
  methods: {
    //피드상세로 이동
    getFeedDetail(memberId, postId) {
        this.$router.push({ path: '/snsFeedDetail', query: {writer : memberId, postId : postId} });
      },
      getHotLecturerList() {
        //hotLectureList조회
        this.axios('/sns/main/top20LecturerFeeds')
        .then(res => {
            console.log(res);
            console.log(res.data);
            console.log(res.data);
            console.log(res.data);
            console.log(res.data);
            console.log(res.data);
            this.listInfo = res.data;
            console.log("getHotLecturerList받아오기 성공")
          }).catch(err =>{
            console.log(err);
          });
      },
    //유저피드로 이동
    goMyFeed(userId) {
      this.$router.push({ path: "/snsUserFeed", query: { userId: userId } });
    },
  },
};
</script>

<style scoped lang="css" src="@/assets/css/sns/HotLecturer.css" />
