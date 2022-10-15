<!-- 댓글입력창 -->
<template>
  <!-- 프로필 -->
  <div>
    <div class="profile" v-for="cmt in comments" :key="cmt.commId" >
      <v-avatar class="ml-10 my-5 mr-4" color="grey darken-1" size="30">
          <v-img aspect-ratio="30" :src="cmt.profileImg" />
      </v-avatar>
      <div class="user text-overline">{{cmt.memberId}}
        <small class="date">{{this.$moment(cmt.writeDate).fromNow()}}</small>
        <div class="btn">
          <v-btn x-small outlined color="success" class="mr-3">수정</v-btn>
          <v-btn x-small outlined color="error">삭제</v-btn>
        </div>
        <v-card-actions class="mt-10">
        <div class="content"> {{cmt.content}} </div>
        </v-card-actions>
      </div>
    </div>
  </div>
</template>
<script>

export default {
    name:"CmtList",
    props: {
    postid: String,
  },
    data() {
      return{
        comments: [],
      }
    },
    created() {
    this.memId = this.$store.state.id;
    this.getCmtList();
  },
    methods:{
    //댓글리스트
    getCmtList() {
      this.axios('/sns/cmt/' + this.postid, {
      }).then(res => {
        this.comments = res.data;
        console.log("댓글리스트 가져오기 성공!");
      }).catch(err => {
        console.log(err);
      });
    },
  }
}
</script>
