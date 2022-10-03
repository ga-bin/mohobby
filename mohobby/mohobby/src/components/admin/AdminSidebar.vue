<template>
  <v-navigation-drawer app>
    <v-card class="text-xs-center">
      <v-btn class="ma-2" outlined color="indigo">메인 페이지로 가기</v-btn>
    </v-card>
    <v-spacer></v-spacer>
    <v-divider></v-divider>

    <v-list>
      <v-list-item v-for="link in links" :key="link.text" @click="$router.push({ path: link.route })" link>
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
        { icon: "mdi-microsoft-windows", text: "sns관리", route: "/adminsns" },
        { icon: "mdi-account", text: "강의 관리", route: "/adminclass" },
        { icon: "mdi-clipboard-list-outline", text: "소모임 관리", route: "/adminmoim" },
        { icon: "mdi-clipboard-list", text: "챌린지 관리", route: "/adminchallenge" },
        { icon: "mdi-alert-octagon", text: "유저 관리", route: "/adminuser" },
      ],
    };
  },
};
</script>
<style scoped>
.text-center {
  background-color: #2ac187;
}

.text-xs-center  {
  text-align: center;
}

</style>
