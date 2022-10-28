<template>
  <v-app id="inspire">
    <Header></Header>
    <v-main>
      <v-container fluid style="padding: 0px;">
        <router-view></router-view>
      </v-container>
    </v-main>
  </v-app>
</template>
<script>

import Header from "./components/common/Header";
export default {

  components: { Header },
  data: () => ({
    cards: ["Today", "Yesterday"],
    menu5: "asd",
  }),
  mounted() {
  },
  methods: {
    //채팅방 inout 감지
    CheckOut() {
      this.axios
      .get("/updateCheckOut", {
        params: {
          roomId: this.$store.state.isRoomNo,
          memberId: this.$store.state.id
        },
      }).then(function(res){console.log("res : " +res)})
    },
  },
  created() {
    this.stompClient.connect(
        {},
        frame => {
          // 소켓 연결 성공
          this.connected = true;
          console.log('소켓 연결 성공', frame);
        },
        error => {
          // 소켓 연결 실패
          console.log('소켓 연결 실패', error);
          this.connected = false;
        }
      );      
  
  },
  watch: {
    '$route' (to, from) {
      if(from.name=='chat'){
      this.CheckOut();
      this.stompClient.unsubscribe(this.$store.state.isRoomNo);
      console.log("this.$store.state.isUser : " + this.$store.state.isUser)
      this.stompClient.unsubscribe(this.$store.state.isUser);
    }
    }
  },
};
</script>
