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

      <!-- 소모임 회원 -->
      <div class="right" v-if="right == 1">
        <v-btn text @click="invite()">
          <v-icon small color="white">mdi-plus-circle-outline</v-icon>
          <div style="color: white">초대하기</div>
        </v-btn>
      </div>

      <!-- 가입클릭 시 회원 -->
      <div class="right" v-if="right == 0 && member == 0">
        <v-btn text @click="checkForJoin()">
          <v-icon small color="white">mdi-plus-circle-outline</v-icon>
          <div style="color: white">가입하기</div>
        </v-btn>
      </div>
      <!-- <div class="right" v-if="right == 0 && member == 0">
        <div class="text-center">
          <v-dialog v-model="dialog" width="500">
            <template v-slot:activator="{ on, attrs }">
              <v-btn text v-bind="attrs" v-on="on">
                <v-icon small color="white">mdi-plus-circle-outline</v-icon>
                <div style="color: white">가입하기</div>
              </v-btn>
            </template>

            <v-card>
              <v-card-title class="text-h5 grey lighten-2">
                가입하기
              </v-card-title>

              <v-card-text>
                <br />
                이 소모임은 가입조건이 설정되어 있습니다.
                <br />
                마이페이지에서 성별과 나이를 설정해주십시오.
              </v-card-text>

              <v-divider></v-divider>

              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="primary" text @click="goMypage()"> 확인 </v-btn>

                <v-btn
                  class="ml-3"
                  color="primary"
                  text
                  @click="dialog = false"
                >
                  취소
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </div>
      </div> -->

      <!-- 설정 되어있는 모하비 회원 -->
      <div class="right" v-if="right == 0 && member == 1">
        <div class="text-center">
          <v-dialog v-model="dialog" width="500">
            <template v-slot:activator="{ on, attrs }">
              <v-btn text v-bind="attrs" v-on="on">
                <v-icon small color="white">mdi-plus-circle-outline</v-icon>
                <div style="color: white">가입하기</div>
              </v-btn>
            </template>

            <v-card>
              <v-card-title class="text-h5 grey lighten-2">
                가입하기
              </v-card-title>
              <v-card-text>
                <br />
                이 소모임에 가입하기를 원하시면 아래 가입버튼을 눌러주십시오!
              </v-card-text>

              <v-divider></v-divider>

              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="primary" text @click="goMain()"> 가입 </v-btn>

                <v-btn
                  class="ml-3"
                  color="primary"
                  text
                  @click="dialog = false"
                >
                  취소
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </div>
      </div>
      <div class="right" v-if="memberId != '비회원'">
        <v-btn text @click="invite()">
          <v-icon small color="white">mdi-plus-circle-outline</v-icon>
          <div style="color: white" @click="flagging">신고하기</div>
        </v-btn>
      </div>
    </div>

    <v-divider></v-divider>

    <v-list>
      <v-list-item
        v-for="link in links"
        :key="link.text"
        @click="$router.push({ name: link.route })"
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
  props: {
    moimInfo: Object,
  },
  data() {
    return {
      memberId: "",
      profileImg: "comfuck.jpg",
      dialog: false,
      member: 0,
      right: 0,
      links: [
        { icon: "mdi-plus-box", text: "관리하기", route: "/" },
        { icon: "mdi-note-text", text: "게시글", route: "moimBoard" },
        { icon: "mdi-image", text: "사진첩", route: "moimPhoto" },
        { icon: "mdi-clipboard-check", text: "공지사항", route: "moimNotice" },
        { icon: "mdi-calendar-text", text: "일정", route: "moimSchedule" },
        { icon: "mdi-chart-gantt", text: "투표", route: "moimVote" },
        { icon: "mdi-chart-pie", text: "N빵", route: "moimNbbang" },
      ],
      memberInfo: [],
    };
  },
  created() {
    this.setMemberInfo();
    this.getMemberInfo();
  },
  mounted() {},
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
        this.profileImg = this.$store.state.user.profileImg;
      }
    },
    getMemberInfo() {
      const vm = this;
      this.axios({
        url: "http://localhost:8088/java/member/" + this.memberId,
        method: "get",
      })
        .then(function (response) {
          console.log(response.data);
          vm.memberInfo = response.data;
          console.log(vm.memberInfo);
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    goMypage() {
      //this.$route.push(name:"my")
    },
    goMain() {
      this.$router.push({ name: "moimBoard" });
    },
    checkForJoin() {
      const vm = this;
      if (this.memberId == "비회원") {
        this.$swal
          .fire({
            title: "로그인이 먼저 필요합니다.",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "로그인하러 가기",
            cancelButtonText: "취소",
          })
          .then((result) => {
            if (result.isConfirmed) {
              this.$swal.fire(
                "로그인 화면으로 이동",
                vm.$router.push("/login")
              );
            }
          });
      } else if (
        this.moimInfo.maxAge != null ||
        this.moimInfo.minAge != null ||
        this.moimInfo.gender != null
      ) {
        if (this.memberInfo.gender == null || this.memberInfo.birth == null) {
          this.$swal
            .fire({
              title: "회원정보 설정이 먼저 필요합니다.",
              icon: "warning",
              showCancelButton: true,
              confirmButtonColor: "#3085d6",
              cancelButtonColor: "#d33",
              confirmButtonText: "회원정보 설정하러 가기",
              cancelButtonText: "취소",
            })
            .then((result) => {
              vm.$router.push("/mypageprofile");
              // if (result.isConfirmed) {
              //   this.$swal.fire("회원정보 수정 화면으로 이동"),
              //     vm.$router.push("/mypageprofile");
              // }
            });
        }
      }
    },
  },
};
</script>
<style scoped>
.text-center {
  background-color: #2ac187;
}

.mb-4 {
  margin-top: 0;
}

.right {
  float: right;
  margin-right: -30px;
}

.dialogBox {
  width: 300px;
  margin: 0;
}

.title {
  text-align: center;
  padding: 3%;
}
</style>
