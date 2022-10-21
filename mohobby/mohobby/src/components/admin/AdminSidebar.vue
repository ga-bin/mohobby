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
      memberId: "admin",
      profileImg: "female.png",
      links: [
        { icon: "mdi-microsoft-windows", text: "sns관리", route: "/adminsns" },
        { icon: "mdi-account", text: "강의 관리", route: "/adminclass" },
        {
          icon: "mdi-clipboard-list-outline",
          text: "소모임 관리",
          route: "/adminmoim",
        },
        {
          icon: "mdi-clipboard-list",
          text: "챌린지 관리",
          route: "/adminchallenge",
        },
        { icon: "mdi-alert-octagon", text: "유저 관리", route: "/adminuser" },
      ],
    };
  },
  created() {
    this.setMemberInfo();
  },
  methods: {
    setMemberInfo() {
      this.memberId = this.$store.state.id;
      if (!this.memberId) {
        this.memberId = "비회원";
        this.profileImg = "comfuck.jpg";
        return;
      } else if (this.memberId == "admin") {
        this.profileImg = "female.png";
      } else if (this.memberId != "" && this.memberId != "admin") {
        this.getMemberInfo();
      }
    },
    // 로그인 회원 정보 가져와서 셋팅
    getMemberInfo() {
      const vm = this;
      this.axios({
        url: "http://localhost:8088/java/member/" + this.memberId,
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

.text-xs-center {
  text-align: center;
}
</style>
