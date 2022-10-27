<template>
  <v-navigation-drawer app>
    <div class="text-center pa-10">
      <v-avatar class="mb-4" color="grey darken-1" size="64">
        <v-img aspect-ratio="30" :src="require(`@/assets/image/user/${profileImg}`)" />
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
      memberId: this.$store.state.id,
      profileImg: this.$store.state.user.profileImg,
      links: [
        { icon: "mdi-account", text: "내피드", route: "snsUserFeed?userId=" + this.$store.state.id },
        { icon: "mdi-equal-box", text: "내피드관리", route: "snsUserFeed?userId=" + this.$store.state.id }, //관리페이지 추가 필요
        { icon: "mdi-face-man", text: "팔로우관리", route: "/snsmain" },
        { icon: "mdi-label-multiple", text: "저장된 게시글", route: "/snsBookmark" },
      ],
    };
  },
  created() {
    this.setMemberInfo();
  },
  methods: {
    setMemberInfo() {
      if (this.memberId == "") {
        this.memberId = "비회원";
        this.profileImg = "comfuck.jpg";
        return;
      } else if (this.memberId == "admin") {
        this.profileImg = "female.png";
      } else if (this.memberId != "" && this.memberId != "admin") {
        this.getMemberInfo();
      }
    },
    // 로그인 회원 정보
    getMemberInfo() {
      const vm = this;
      this.axios({
        url: "member/" + this.memberId,
        method: "get",
      })
        .then(function (response) {
          if (response.data != "") {
            console.log(response.data);
            vm.memberId = response.data.memberId;
            vm.profileImg = response.data.profileImg;
          }
        })
        .catch(function (error) {
          console.log(error);
        });
    },
  },
};
</script>
<style scoped>
.text-center {
  background-color: #2ac187;
}
</style>
