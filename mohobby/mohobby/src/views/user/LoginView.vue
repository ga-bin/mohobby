<template>
  <div class="container-fluid">
    <div class="row no-gutter">
      <!-- The image half -->
      <div class="col-md-6 d-none d-md-flex bg-image"></div>

      <!-- The content half -->
      <div class="col-md-6 bg-light">
        <div class="login d-flex align-items-center py-5">
          <!-- Demo content-->
          <div class="container">
            <v-app id="inspire">
              <v-main class="lighten-4">
                <!-- login component -->
                <v-container style="max-width: 450px" fill-height>
                  <v-layout align-center row wrap>
                    <v-flex xs12>
                      <v-card>
                        <div class="pa-10">
                          <h1 style="text-align: center" class="mb-10">
                            Login
                          </h1>
                          <form v-on:submit.prevent="checkMember">
                            <v-text-field
                              label="아이디"
                              prepend-inner-icon="mdi-account"
                              color="#2ac187"
                              v-model="memberId"
                            ></v-text-field>
                            <v-text-field
                              color="#2ac187"
                              prepend-inner-icon="mdi-lock"
                              type="password"
                              label="비밀번호"
                              v-model="password"
                            >
                            </v-text-field>
                            <v-btn
                              type="submit"
                              color="#2ac187"
                              depressed
                              large
                              block
                              dark
                              class="mb-3"
                            >
                              Login
                            </v-btn>
                            <v-btn color="#2ac187" depressed large block dark>
                              Sign Up
                            </v-btn>
                            <br />
                            <br />
                            <!-- <p style="text-align: center;"> -->
                            <a id="custom-login-btn" @click="kakaoLogin()">
                              <img
                                src="https://www.gb.go.kr/Main/Images/ko/member/certi_kakao_login.png"
                                style="
                                  height: 40px;
                                  width: 170px;
                                  margin-right: 30px;
                                "
                              />
                            </a>
                            <!-- </p> -->
                            <!-- <p style="text-align: center;"> -->
                            <div id="naverIdLogin"></div>
                          </form>
                        </div>
                      </v-card>
                    </v-flex>
                  </v-layout>
                </v-container>
              </v-main>
            </v-app>
          </div>
          <!-- End -->
        </div>
      </div>
      <!-- End -->
    </div>
  </div>
</template>

<style>
.login,
.image {
  min-height: 100vh;
}

.bg-image {
  background-image: url("@/assets/image/user/logo.jpeg");
  background-size: contain;
  background-repeat: no-repeat;
  background-position: center center;
}

.btn-primary {
  background-color: #2ac187;
  border: #2ac187;
}
</style>
<script>
import router from "@/router";
export default {
  data: () => ({
    step: 1,
    naverLogin: null,
    memberId: "",
    password: "",
    loginInfo: null,
    email: "",
    token: "",
  }),
  props: {
    source: String,
  },
  beforeCreate() {},
  created() {},
  beforeMount() {},
  mounted() {
    // login페이지 띄울때 무조건 로그아웃
    this.$store.commit("setIsLoginFalse");
    this.$store.commit("logout");
    this.$store.commit("setUserData", null);

    this.naverLogin = new window.naver.LoginWithNaverId({
      clientId: "qtK4gDKw7gcdHhTwYZpV",
      callbackUrl: "http://localhost:8080/login",
      isPopup: false, // 팝업을 통한 연동 처리여부
      loginButton: {
        color: "green",
        type: 3,
        height: 40,
        width: 150,
      }, // 로그인 버튼의 타입을 지정
    });

    // 설정 정보를 초기화하고 연동을 준비
    this.naverLogin.init();

    this.naverLogin.getLoginStatus((status) => {
      const vm = this;
      if (status) {
        console.log(status);
        console.log(this.naverLogin.user);

        // 필수적으로 받아야하는 프로필 정보가 있다면 callback처리 시점에 체크
        var email = this.naverLogin.user.getEmail();
        if (email == "undifined" || email == null) {
          alert("이메일은 필수 정보입니다. 정보 제공을 동의해주세요.");
          // 사용자 정보 재동의를 위해 다시 네이버 동의 페이지로 이동함
          this.naverLogin.reprompt();
          return;
        }
        vm.email = email;
        vm.checkMemberByEmail();
      } else {
        console.log("callback 처리에 실패하였습니다.");
      }
    });
  },
  beforeUpdate() {},
  updated() {},
  beforeUnmount() {},
  unmounted() {},
  methods: {
    kakaoLogin() {
      const vm = this;
      window.Kakao.Auth.loginForm({
        scope: "profile_nickname, account_email, gender, birthday", //동의항목 페이지에 있는 개인정보 보호 테이블의 활성화된 ID값을 넣습니다.
        success: function (response) {
          console.log(response); // 로그인 성공하면 받아오는 데이터
          window.Kakao.API.request({
            // 사용자 정보 가져오기
            url: "/v2/user/me",
            success: (res) => {
              const kakao_account = res.kakao_account;
              console.log(kakao_account);
              // console.log(kakao_account.profile.nickname);
              // console.log(kakao_account.email);
              // console.log(kakao_account.gender);
              // console.log(kakao_account.birthday);

              vm.token = kakao_account.access_token;
              vm.email = kakao_account.email;

              vm.checkMemberByEmail();
            },
          });
          // window.location.href='/' //리다이렉트 되는 코드
        },
        fail: function (error) {
          console.log(error);
        },
      });
    },
    kakaoLogout() {
      if (!Kakao.Auth.getAccessToken()) {
        console.log("Not logged in.");
        return;
      }
      Kakao.Auth.logout(function (response) {
        alert(response + " logout");
        // window.location.href='/'
      });
    },

    // 멤버 check해서 일반로그인
    checkMember() {
      console.log(this.memberId, this.password);

      const vm = this;
      this.axios({
        url: "http://localhost:8088/java/membercheck",
        method: "post",
        data: {
          memberId: this.memberId,
          password: this.password,
        },
      })
        .then(function (response) {
          if (response.data != "" && response.data.memberId != "admin") {
            console.log("if문 안에" + response.data);
            // 유저 id, 유저 정보 넣기
            vm.$store.state.id = vm.memberId;
            vm.$store.commit("setUserData", response.data);
            // 메인으로 이동(로그인성공)
            vm.$router.push("/");
          } else if(response.data != "" && response.data.memberId == "admin") {
            console.log("else if문 안에" + response.data);
            vm.$store.state.id = vm.memberId;
            vm.$store.commit("setUserData", response.data);
            // 메인으로 이동(로그인성공)
            vm.$router.push("/adminuser");
          }
          else {
            vm.$swal.fire({
              icon: 'error',
              title: '아이디, 비밀번호가 일치하지 않습니다.',
        })
          }
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    // 네이버, 카카오 로그인 시 이메일 있는지 검사
    checkMemberByEmail() {
      console.log(this.email);
      const vm = this;
      this.axios({
        url: "http://localhost:8088/java/memberEmail/" + this.email,
        method: "get",
      })
        .then(function (response) {
          console.log(response);
          if (response.data !== "" && response.data.constructor === Object) {
            //this.$store.commit("setId", this.memberId);
            console.log(response.data);
            vm.$store.state.id = vm.memberId;
            vm.$store.commit("setIsLoginTrue");
            vm.$store.commit("setUserData", response.data);
            // vm.$store.state.user=response.data;
            // 로그인 성공시 메인으로 이동
            this.$router.push("/");
          } else {
            vm.$router.push({
              name: "register",
              params: { email: vm.email },
            });
            // vm.$router.push("/register");
          }
        })
        .catch(function (error) {});
    },
  },
};
</script>
