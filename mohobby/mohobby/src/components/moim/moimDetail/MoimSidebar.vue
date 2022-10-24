<template>
  <v-navigation-drawer app>
    <div class="text-center pa-10">
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
      <!-- 신고하기 -->
     
      <v-dialog
      v-model="moimFlagModal"
      scrollable
      max-width="300px"
    >
      <template v-slot:activator="{ on, attrs }">
        <div class="right" v-if="memberId != '비회원'" v-bind="attrs"
                v-on="on">
              <v-btn text @click="moimFlagging">
                <v-icon small color="white">mdi-plus-circle-outline</v-icon>
                <div style="color: white">신고하기</div>
              </v-btn>
            </div>
      </template>
      <v-card>
        <v-card-title>Select Country</v-card-title>
        <v-divider></v-divider>
        <v-card-text style="height: 300px;">
          <v-radio-group
            v-model="selectedCode"
            column
          >
            <span>{{ selectedCode }}</span>
            <v-radio
              label="부적절한 게시물 개시"
              value="mo1"
            ></v-radio>
            <v-radio
              label="홍보성 초대 반복"
              value="mo2"
            ></v-radio>
            <v-radio
              label="청소년에게 음란성 초대글 유포"
              value="mo3"
            ></v-radio>
            <v-radio
              label="다른 소모임에 대한 욕설, 비방"
              value="mo4"
            ></v-radio>
            <v-radio
              label="사이트 목적과 맞지 않는 소모임"
              value="mo5"
            ></v-radio>
            <v-radio
              label="기타"
              value="mo6"
            ></v-radio>
          </v-radio-group>
        </v-card-text>
        <v-divider></v-divider>
        <v-card-actions>
          <v-btn
            color="blue darken-1"
            text
            @click="moimFlagModal = false"
          >
            Close
          </v-btn>
          <v-btn
            color="blue darken-1"
            text
            @click="moimFlagging()"
          >
            Save
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
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
    moimId: Number,
  },
  data() {
    return {
      memberId: "",
      profileImg: "comfuck.jpg",
      dialog: false,
      member: 0,
      right: 0,
      links: [
        { icon: "mdi-plus-box", text: "프로필 관리", route: "mypageprofile" },
        { icon: "mdi-note-text", text: "게시글", route: "moimBoard" },
        { icon: "mdi-image", text: "사진첩", route: "moimPhoto" },
        { icon: "mdi-clipboard-check", text: "공지사항", route: "moimNotice" },
        { icon: "mdi-calendar-text", text: "일정", route: "moimSchedule" },
        { icon: "mdi-chart-gantt", text: "투표", route: "moimVote" },
        { icon: "mdi-chart-pie", text: "N빵", route: "moimNbbang" },
      ],
      memberInfo: [],
      moimMemberCount: 0,
      // 신고
      selectedCode: '',
      moimFlagModal: false,
      flagedMoim : "",
      flagReason : "",
      flagedMoimName : "",
    };
  },
  created() {
    this.setMemberInfo();
    this.getMemberInfo();
    this.getMoimOneInfo();
    this.getMoimMemberCount();
  },
  methods: {
    async setMemberInfo() {
      this.memberId = this.$store.state.id;
      if (!this.memberId) {
        this.memberId = "비회원";
        this.profileImg = "comfuck.jpg";
        return;
      } else if (this.memberId == "admin") {
        this.profileImg = "female.png";
      } else if (this.memberId != "" && this.memberId != "admin") {
        await this.getMemberInfo();
        this.profileImg = this.memberInfo.profileImg;
      }
    },
    async getMemberInfo() {
      const vm = this;
      await this.axios({
        url: "/member/" + this.memberId,
        method: "get",
      })
        .then(function (response) {
          vm.memberInfo = response.data;
          console.log(vm.memberInfo);
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    getMoimOneInfo() {
      const vm = this;
      this.axios({
        url: "/moimOneInfo/" + this.moimId,
        method: "get",
      })
        .then(function (response) {
          console.log(response.data);
          vm.moimInfo = response.data;
          console.log(vm.moimInfo);
          vm.flagedMoim = vm.moimInfo.moimId;
          vm.flagedMoimName = vm.moimInfo.moimName;
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    getMoimMemberCount() {
      const vm = this;
      this.axios({
        url: "/moimMemberCount/" + this.moimId,
        method: "get",
      })
        .then(function (response) {
          console.log(response.data);
          vm.moimMemberCount = response.data;
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
      console.log(this.moimInfo.maxAge);
      console.log(this.moimInfo.maxAge);
      console.log(this.moimInfo.gender);
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
        // 모임 정원 수 초과 여부 검사
      } else if (this.moimInfo.maxPeople <= this.moimMemberCount) {
        this.$swal.fire({
          icon: "error",
          title: "모임의 정원 수가 다차서 가입할 수 없습니다.",
        });
        // 모임 가입조건 없는 경우
      } else if (
        this.moimInfo.maxAge == 0 &&
        this.moimInfo.minAge == 0 &&
        this.moimInfo.gender == 0
      ) {
        this.$swal
          .fire({
            title: "모임에 가입하시겠습니까?",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "가입하기",
            cancelButtonText: "취소하기",
          })
          .then((result) => {
            if (result.isConfirmed) {
              this.$swal.fire(vm.insertMoim(), "가입이 완료되었습니다.");
            }
          });
      }
      // 모입 가입조건 있는 경우
      else if (
        this.moimInfo.maxAge != 0 ||
        this.moimInfo.minAge != 0 ||
        this.moimInfo.gender != 0
      ) {
        // 회원 정보 설정이 안되있는 경우
        if (this.memberInfo.gender == 0 || this.memberInfo.birth == null) {
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
              if (result.isConfirmed) {
                vm.$router.push("/mypageprofile");
              }
            });
          // 회원 정보 설정이 되어 있는데, 모임 조건과 일치하지 않는 경우
        } else if (
          this.memberInfo.gender != this.moimInfo.gender ||
          this.memberInfo.birth > this.moimInfo.maxAge ||
          this.memberInfo.birth < this.moimInfo.minAge
        ) {
          this.$swal.fire({
            icon: "error",
            title: "모임의 가입 기준에 충족하지 않아 가입할 수 없습니다.",
          });
        }
      }
    },
    // 모임 가입하기 로직(axios만들자)
    insertMoim() {},
    // 모임 신고하기
    moimFlagging() {
          const vm = this;
          this.moimFlagModal = false;
          console.log(this.selectedCode);
          console.log(this.selectedCode);
          console.log(this.selectedCode);
          console.log(this.selectedCode);
          console.log(this.selectedCode);
          if (this.selectedCode == "mo6") {
            this.$swal.fire({
                title: '신고 이유를 입력하세요',
                html: `<input type="text" id="flagReason" class="swal2-input" placeholder="신고 이유">`,
                confirmButtonText: '제출하기',
                focusConfirm: false,
                preConfirm: () => {
                  const flagReason = this.$swal.getPopup().querySelector('#flagReason').value
                  if (!flagReason) {
                    this.$swal.showValidationMessage(`신고이유를 입력해 주세요`)
                  }
                  return { flagReason: flagReason }
                }
              }).then((result) => {
                vm.flagReason = result.value.flagReason;
                this.insertFlag();
              })
          } else {
            this.insertFlag();
          }
        },
        async insertFlag() {
          const vm = this;
          await this.axios({
            url: "/flagging",
            method: "post",
            data : {
              flagFrom : this.memberId,
              flagTo : this.flagedMoim,
              flagCode : this.selectedCode,
              flagReason : this.flagReason,
              moimName : this.flagedMoimName,
            }
          })
            .then(function (response) {
              console.log(response);
              vm.$swal.fire('모임 신고가 완료되었습니다.');

            })
            .catch(function (error) {
              console.log(error);
            });
        },
        invite() {

        }
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
