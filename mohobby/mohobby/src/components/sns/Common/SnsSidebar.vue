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
      <v-list-group
        v-for="link in links"
        :key="link.text"
        :prepend-icon="link.icon"
        no-action
      >
        <template v-slot:activator>
          <v-list-item-content>
            <v-list-item-title v-text="link.text"></v-list-item-title>
          </v-list-item-content>
        </template>

        <v-list
          v-for="subheader in link.subheaders"
          :key="subheader.text"
          shaped
          subheader
        >
          <v-list-item-group v-model="selectedItem" color="primary">
            <v-subheader v-if="subheader.text != ''">{{
              subheader.text
            }}</v-subheader>
            <v-list-item
              v-for="child in subheader.items"
              :key="child.text"
              @click="$router.push({ path: child.route })"
              link
            >
              <v-list-item-icon>
                <v-icon></v-icon>
              </v-list-item-icon>
              <v-list-item-content>
                <v-list-item-title v-text="child.text"></v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list-item-group>
        </v-list>
      </v-list-group>
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
        {
          icon: "mdi-account",
          text: "내 피드",
          subheaders: [
            {
              text: "",
              items: [{ text: "내 피드로 이동", route: "/sns/userFeed" }],
            },
          ],
        },
        {
          icon: "mdi-ab-testing",
          text: "내 피드관리",
          subheaders: [
            { text: "", items: [{ text: "프로필 설정", route: "snsmain" }] },
            {
              text: "프로필 노출 설정",
              items: [
                // { text: "내 강의 관리", route: "/management/snsLecture" },
                // { text: "내 모임 관리", route: "/management/snsMoim" },
                // { text: "내 챌린지 관리", route: "/management/test/iamportTest" },
              ],
            },
          ],
        },
        {
          icon: "mdi-crown",
          text: "팔로우 관리",
          subheaders: [
            {
              text: "팔로워 관리",
              items: [{ text: "팔로워 목록", route: "" }],
            },
            {
              text: "팔로잉 관리",
              items: [{ text: "팔로잉 목록", route: "" }],
            },
          ],
        },
        {
          icon: "mdi-format-list-bulleted",
          text: "저장한 게시글 관리",
          subheaders: [
            {
              text: "북마크 찜꽁빵꽁",
              items: [{ text: "저장게시글", route: "/snsBookmark" }],
            },
          ],
        },
      ],
      selectedItem: "",
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
    // 로그인 회원 정보 가져와서 셋팅
    getMemberInfo() {
      const vm = this;
      this.axios({
        url: "/member/" + this.memberId,
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
