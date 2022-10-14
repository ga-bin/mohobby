<template>
  <v-navigation-drawer app>
    <div class="text-center mt-4 pa-10">
      <v-avatar class="mb-4" color="grey darken-1" size="64">
        <v-img
          aspect-ratio="30"
          :src="require(`@/assets/image/user/${profileImg}`)"
        ></v-img>
      </v-avatar>
      <h4 class="white--text">{{ memberId }}</h4>
    </div>

    <v-divider></v-divider>

    <v-list>
      <v-list-item
        v-for="link in links"
        :key="link.text"
        @click="$router.push({ path: link.route })"
        link
      >
        <v-list-item-icon>
          <v-icon>{{ link.icon }}</v-icon>
        </v-list-item-icon>

        <v-list-item-content>
          <v-list-item-title>{{ link.text }}</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </v-list>
  </v-navigation-drawer>
</template>
<script>
export default {
  data() {
    return {
      links: [
        { icon: "mdi-account", text: "나의 프로필", route: "/mypageprofile" },
        { icon: "mdi-instagram", text: "나의 SNS", route: "/mypagesns" },
        { icon: "mdi-human-male-board", text: "나의 강의", route: "/mypageclass" },
        { icon: "mdi-account-group", text: "나의 소모임", route: "/mypagemoim" },
        { icon: "mdi-stairs-up", text: "나의 챌린지", route: "/mypagechallenge" },
      ],
      memberId : "",
      profileImg : "",
    };
  },
  created() {
    this.setMemberInfo();
  },
   methods: {
    setMemberInfo() {
      const vm = this;
      this.memberId = this.$store.state.id;
      if (this.memberId == "") {
        this.memberId = "비회원";
        this.profileImg = "comfuck.jpg";
        return;
      }
      this.axios({
        url: "http://localhost:8088/java/member/" + this.memberId,
        method: "get",
      })
        .then(function (response) {
          if (response.data != "") {
            console.log(response.data);
            vm.memberId = response.data.memberId;
            vm.profileImg = response.data.profileImg;
            console.log(vm.profileImg);
          }
        })
        .catch(function (error) {
          console.log(error);
        });
    },
   }
};
</script>
<style scoped>
.text-center {
  background-color: #2ac187;
}
</style>
