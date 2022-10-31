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
            currId: this.$route.query.currId,
            type: this.$route.query.type,
            connect: this.$route.query.connect,
            time: this.$route.query.time,
            memberId: '',
            password: '',
            loginInfo: null,
            email: '',
            token: '',
            now: this.$moment().format('YYYY-MM-DD HH:mm:ss'),
            attdType: {
                0: 'ATTD_DATE',     //출석
                1: 'LEAVE_DATE',    //조퇴
                2: 'OUT_DATE',      //외출
                3: 'RECORD_TYPE',   //결석
                4: 'EXIT_DATE',     //퇴실
                5: 'COMEBACK_DATE'  //복귀
            },
        }
    },
    mounted() {
    },
    created() {
        this.checkTimeLimit();
    },
    methods: {
        kakaoLogin() {
            const vm = this;
            window.Kakao.Auth.loginForm({
                scope: "profile_nickname, account_email, gender, birthday",
                success: function (response) {
                window.Kakao.API.request({
                    // 사용자 정보 가져오기
                    url: "/v2/user/me",
                    success: (res) => {
                    const kakao_account = res.kakao_account;
                    console.log(kakao_account);

                    vm.token = kakao_account.access_token;
                    vm.email = kakao_account.email;

                    vm.checkMemberByEmail();
                    },
                });
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
            let req = {
                memberId: this.id,
                currId: this.currId,
                type: this.type,
                column: this.attdType[`${this.type}`],
                value: this.type == 3 ? '3' : this.now,
            };
            let time = this.time + ':00';
            let nowTime = this.$moment(this.now).format('HH:mm');
            if(this.type == 0 && time < nowTime) {
                req.lateCheck=1;
                this.$swal('출석이 완료되었습니다!', '지각이지만.. 화이팅!', 'success');
            } else if (this.type == 1) {
                this.$swal('조퇴가 완료되었습니다!', '', 'success');
            } else if (this.type == 2) {
                this.$swal('외출이 완료되었습니다!', '', 'success');
            } else if (this.type == 4) {
                this.$swal('퇴실이 완료되었습니다!', '', 'success');
            } else if (this.type == 5) {
                this.$swal('복귀가 완료되었습니다!', '', 'success');
            }

            this.send(req);
        },
        send(req) {
            this.axios.post('/class/attd', req);
        },
        checkTimeLimit() {
            let now = this.now;
            let connect = this.connect.substr(0, 4)+'-'+this.connect.substr(4, 2)+'-'+this.connect.substr(6, 2)+' '
                            +this.connect.substr(8, 2)+':'+this.connect.substr(10, 2)+':'+this.connect.substr(12, 2);
            let income = this.$moment(connect).format('YYYY-MM-DD HH:mm:ss');
            let limit = this.$moment(income).add(5, "m").format('YYYY-MM-DD HH:mm:ss');
            if(!(limit>now)) {
                document.body.innerHTML = '';
                this.$swal('시간이 초과되었습니다!', '출결QR을 재발급 받으세요.', 'error');
            };
        },
    }
}
</script>

<style>

</style>