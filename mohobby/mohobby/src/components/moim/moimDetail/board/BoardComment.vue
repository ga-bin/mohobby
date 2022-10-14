<template>
  <!-- 프로필 -->
  <div>
  <div class="profile" v-for="item in items" :key="item.user" >
    <v-avatar class="ml-10 my-5 mr-4" color="grey darken-1" size="30">
        <v-img aspect-ratio="30" :src="item.src" />
    </v-avatar>
    <div class="user text-overline">{{item.commentWriter}}
      <small class="date">{{item.commentDate | yyyyMMdd}}</small>
      <div class="btn">
        <v-btn x-small outlined color="success" class="mr-3">수정</v-btn>
        <v-btn x-small outlined color="error">삭제</v-btn>
      </div>
      <v-card-actions class="mt-10">
      <div class="content"> {{item.content}} </div>
      </v-card-actions>
    </div>
  </div>
</div>
</template>
<script>
export default {
  data() {
    return {
      boardId : this.$route.query.boardId,
      moimId : this.$route.query.moimId,
      items : [],
    }
  },
  getBoard() {
      this.axios.get("/detailBoard", {
        params : {
          moimId : this.Id,
          boardType : 1,
          boardId : this.boardId
        }
      })
      .then((resp)=> {
        console.log(resp)
        console.log(this.items)
        this.items = resp.data;
      })
      .catch((err) => {
        console.log(this.items)
        console.log(err)
      })
  },
  created(){
    this.getBoard
  },
  filters: {
    // filter로 쓸 filter ID 지정
    yyyyMMdd: function (value) {
      // 들어오는 value 값이 공백이면 그냥 공백으로 돌려줌
      if (value == "") return "";
      // 현재 Date 혹은 DateTime 데이터를 javaScript date 타입화
      var js_date = new Date(value);
      // 연도, 월, 일 추출
      var year = js_date.getFullYear();
      var month = js_date.getMonth() + 1;
      var day = js_date.getDate();
      // 월, 일의 경우 한자리 수 값이 있기 때문에 공백에 0 처리
      if (month < 10) {
        month = "0" + month;
      }
      if (day < 10) {
        day = "0" + day;
      }
      // 최종 포맷 (ex - '2021/10/08')
      return year + "/" + month + "/" + day;
    },
  }
}
</script>
<style scoped>
 .profile{
  position: relative;
  display : flex;
  margin-left: 30px;
  width: 90%;
 }
 .profile::after {
  content : "";
  position : absolute;
  left:30px;
  height: 1px;
  width : 100%;
  border-bottom : 1px solid rgba(184, 189, 190, 0.8);
 }
 .user {
  margin-top : 20px;
 }

 .date {
  position: absolute;
  right: 0;
 }

 .btn{
  position: absolute;
  right: 0;
 }
</style>