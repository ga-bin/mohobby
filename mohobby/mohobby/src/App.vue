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
    // this.logout();
  },
  methods: {
    //  logout() {
    //   this.$store.commit("setIsLoginFalse");
    //   this.$store.commit("logout");
    //   this.$store.commit("setUserData", null);
    //   this.$router.push("/");
    // },
    //채팅방 inout 감지
    CheckOut() {
      console.log("isRoomNo" + this.$store.state.isRoomNo),
      console.log("isId" + this.$store.state.id)
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
  
  },
  watch: {
    '$route' (to, from) {
      if(from.name=='chat')this.CheckOut();
    }
  },
};
</script>
