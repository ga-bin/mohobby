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
 
  },
  created() {
  
  }
};
</script>
