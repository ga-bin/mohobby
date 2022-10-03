<template>
    <v-app>
        <v-content>
            <v-container class="fill-height" fluid>
                <v-row align="center" justify="center">
                    <v-col col="12" sm="8" md="8">
                        <v-card class="elevation-12">
                            <v-window v-model="step">
                                 <v-window-item :value="2">
                                    <v-row cl.css="fill-height">
                                        <v-col cols="12" md="4" class="teal accent-3">
                                            <v-card-text class="white--text mt-12">
                                                <h1 class="text-center display-1">Welcome Back !</h1>
                                                <h5 class="text-center">To keep connected with us please login with your personnel information</h5>
                                            </v-card-text>
                                            <div class="text-center">
                                                <v-btn rounded outlined dark @click="$router.push('/login')">로그인</v-btn>
                                            </div>
                                        </v-col>
                                        <v-col cols="12" md="8">
                                            <v-card-text class="mt-12">
                                                <h1 class="text--center display-2 teal--text text--accent-3">Create Account</h1>
                                                <div class="text-center mt-4">
                                                    <v-btn class="mx-2" fab color="black" outlined>
                                                        <v-icon>fab fa-facebook-f</v-icon>
                                                          <h6>카카오</h6>
                                                    </v-btn>
                                                     <v-btn class="mx-2" fab color="black" outlined>
                                                        <v-icon>fab fa-google-plus-g</v-icon>
                                                        <h6>네이버</h6>
                                                    </v-btn>
                                                </div>
                                                <v-form>

                                                    <v-text-field label="Name" name="Name" prepend-icon="person" type="text" color="teal accent-3"></v-text-field>
                                                    <v-text-field label="Email" name="Email" prepend-icon="email" type="text" color="teal accent-3"></v-text-field>
                                                    <v-text-field label="Password" name="Password" prepend-icon="password" type="text" color="teal accent-3"></v-text-field>
                                                    <v-radio-group row prepend-icon="password">
                                                        <v-radio label="남성" value="radio-1"></v-radio>
                                                        <v-radio label="여성" value="radio-2"></v-radio>
                                                        <v-radio label="선택안함" value="radio-2"></v-radio>
                                                    </v-radio-group>

                                                </v-form>
                                                <div class="text-center mt-n5">
                                                    <v-btn rounded color="teal accent-3" dark>회원가입</v-btn>
                                                </div>
                                            </v-card-text>
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