<template>

    <!-- The content half -->
    <div id="login">
        <!-- Demo content-->
            <v-app 
                id="inspire" 
                style="margin-top: -70px;"
            >
                <!-- login component -->
                <v-container 
                    style="max-width: 300px" 
                    fill-height
                >
                    <v-flex>
                    <v-card outlined>
                        <div class="pa-10">
                        <h1 style="text-align: center">
                            Login
                        </h1>
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
                            color="#2ac187"
                            large
                            block
                            dark
                            class="my-3"
                            id="loginbtn"
                            @click="checkMember()"
                        >
                            Login
                        </v-btn>
                        <p style="text-align: center;">
                        <a id="custom-login-btn" @click="kakaoLogin()">
                        <img
                            src="https://www.gb.go.kr/Main/Images/ko/member/certi_kakao_login.png"
                            style="height: 50px; width: 195px;"
                        />
                        </a>
                        </p>
                        <!-- <p style="text-align: center;"> -->
                        <div id="naverIdLogin"></div>
                        </div>
                    </v-card>
                    </v-flex>
                </v-container>
            </v-app>
        <!-- End -->
    </div>
    <!-- End -->
</template>

<script>
export default {
    data() {
        return {
            id: this.$route.query.id,
            // 1 : 출석
            // 2 : 외출
            // 3 : 복귀
            // 4 : 조퇴
            // 5 : 퇴실
            type: this.$route.query.type,
            memberId: '',
            password: '',
            loginInfo: null,
            email: '',
            token: '',
            attdType: {
                1: 'ATTD_DATE',
                2: 'OUT_DATE',
                3: 'COMEBACK_DATE',
                4: 'LEAVE_DATE',
                5: 'EXIT_DATE'
            },
        }
    },
    mounted() {
        // let initBody = document.body.innerHTML;
        // document.body.innerHTML = document.getElementById('login').innerHTML;
        // console.log('페이지 로딩');
    },
    created() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function(pos) {
                var latitude = pos.coords.latitude;
                var longitude = pos.coords.longitude;
                alert("현재 위치는 : " + latitude + ", "+ longitude);
                alert(pos.coords.accuracy);
            });
        } else {
            alert("이 브라우저에서는 Geolocation이 지원되지 않습니다.")
        }
    },
    methods: {
        kakaoLogin() {
            const vm = this;
            window.Kakao.Auth.loginForm({
                scope: "profile_nickname, account_email, gender, birthday", //동의항목 페이지에 있는 개인정보 보호 테이블의 활성화된 ID값을 넣습니다.
                success: function (response) {
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
        checkMember() {
            if(this.memberId == '' || this.password == '') {
                this.$swal({
                    icon: "info",
                    title: "아이디, 비밀번호를 입력하세요.",
                });
            } else {
                const vm = this;
                this.axios({
                    url: "/membercheck",
                    method: "post",
                    data: {
                    memberId: vm.memberId,
                    password: vm.password,
                    },
                }).then(function (response) {
                    if (response.data != "" && response.data.memberId != "admin") {
                        if (response.data.delDate != null) {
                            vm.$swal.fire("탈퇴한 회원입니다.");
                            return;
                        }
                        
                        if(vm.memberId == vm.id) {
                            vm.setAttdStatus();
                        } else {
                            vm.$swal.fire({
                                icon: "error",
                                title: "출석 정보가 일치하지 않습니다.",
                            });
                        }
    
                        vm.memberId = '';
                        vm.password = '';
    
                    } else {
                        vm.$swal.fire({
                            icon: "error",
                            title: "아이디, 비밀번호가 일치하지 않습니다.",
                        });
                    }
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            }

        },
        checkMemberByEmail() {
            const vm = this;
            this.axios({
                url: "/memberEmail/" + this.email,
                method: "get",
            }).then(function (response) {
                if (response.data !== "" && response.data.constructor === Object) {
                    //this.$store.commit("setId", this.memberId);
                    if (response.data.delDate != null) {
                        vm.$swal.fire("탈퇴한 회원입니다.");
                        return;
                    }
                    this.$router.push("/");
                } else {
                    vm.$swal.fire({
                        icon: "error",
                        title: "등록되지 않은 계정입니다.",
                    });
                }
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        setAttdStatus() {
            let res;
            if(this.type == 1) {
                res = this.axios.post('/attd', {
                    params: {
                        memberId: '',
                        currId: '',
                        type: '',
                    }
                })
            } else {
                res = this.axios.put('/attd', {
                    params: {
                        memberId: '',
                        currId: '',
                        type: '',
                    }
                })
            }
            console.log(res);
            console.log(res.data);
        }
    }
}
</script>

<style>

</style>