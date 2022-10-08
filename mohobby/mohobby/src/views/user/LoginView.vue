<template>
    <v-app>
        <v-content>
            <v-container class="fill-height" fluid>
                <v-row align="center" justify="center">
                    <v-col col="12" sm="8" md="8">
                        <v-card class="elevation-12">
                            <v-window v-model="step">
                                <v-window-item :value="1">
                                    <v-row>
                                        <v-col cols="12" md="8">
                                            <v-card-text class="mt-12">
                                                <h1 class="text-center dispaly-2 teal--text text--accent-3">Mohobby</h1>
                                                <div class="text-center" mt-4>
                                                 <a id="custom-login-btn" @click="kakaoLogin()">
                                                    <img src="https://www.gb.go.kr/Main/Images/ko/member/certi_kakao_login.png" style ="height:60px; width:auto;"/> </a> 
                                                </a>
                                                    <v-btn class="mx-2" fab color="black" outlined>
                                                        <span class="mdi-google-plus"></span>
                                                    </v-btn>
                                                    <v-btn class="mx-2" fab color="black" outlined>
                                                        <span class="mdi-linkedin"></span>
                                                    </v-btn>
                                                </div>
                                                <h4 class="text-center mlt-4">Ensure your email for registratior</h4>
                                                <v-form>
                                                    <v-text-field label="Id" name="Id" prepend-icon="email" type="text" color="teal accent-3"></v-text-field>
                                                    <v-text-field id="password" label="Password" name="Password" prepend-icon="lock" type="password" color="teal accent-3"></v-text-field>
                                                </v-form>
                                            </v-card-text>
                                            <div id="buttonContainer" class="text-center mt-3">
                                                <v-btn rounded color="teal accent-3" dark>로그인</v-btn>
                                                <v-btn rounded color="teal accent-3" dark>아이디/비밀번호 찾기</v-btn>
                                            </div>
                                        </v-col>
                                        <v-col cols="12" md="4" class="teal accent-3">
                                            <v-card-text class="white--text mt-12">
                                                <h1 class="text-center display-1">Hello, Friends !</h1>
                                                <h5 class="text-center">Enter your personnel details and start journay with us</h5>
                                            </v-card-text>
                                            <div class="text-center">
                                                <v-btn rounded outlined="" dark @click="$router.push('/register')">회원가입</v-btn>
                                            </div>
                                        </v-col>
                                    </v-row>
                                </v-window-item>
                            </v-window>
                        </v-card>
                    </v-col>
                </v-row>
            </v-container>
        </v-content>
    </v-app>
</template>
<script>
export default {
    data : () => ({
        step: 1
    }),
    props : {
        source : String
    },
    beforeCreate() {},
    created() {},
    beforeMount() {},
    mounted() {},
    beforeUpdate() {},
    updated() {},
    beforeUnmount() {},
    unmounted() {},
    methods: {}
}
</script>
<style scoped>
#buttonContainer {
    height: 80px;
}

#buttonContainer button {
    margin-right: 20px;
}
.teal--text {
    padding-bottom: 30px;
}
</style>