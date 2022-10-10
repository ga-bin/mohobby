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
                        <v-main class=" lighten-4">
                            <!-- login component -->
      <v-container style="max-width: 450px" fill-height>
        <v-layout align-center row wrap>
          <v-flex xs12>
            <v-card>
              <div class="pa-10">
                <h1 style="text-align: center" class="mb-10">회원가입</h1>
                <form>
                  <v-text-field
                    label="아이디"
                    prepend-inner-icon="mdi-account"
                    color="#2ac187"
                    v-model="memberId"
                    @input="checkId()"
                    :messages="idCheckMessage"
                  ></v-text-field>
                  <v-text-field
                    color="#2ac187"
                    prepend-inner-icon="mdi-lock"
                    type="password"
                    label="비밀번호"
                    v-model="password"
                    @input="checkPassword()"
                    :messages="passwordCheckMessage"
                  ></v-text-field>
                  <v-text-field
                    color="#2ac187"
                    prepend-inner-icon="mdi-lock"
                    type="password"
                    label="비밀번호 확인"
                    v-model="password2"
                    @input="checkPassword2()"
                    :messages="password2CheckMessage"
                  >
                  </v-text-field>
                  <v-radio-group label="성별" row>
                      <v-radio
                        label="여성"
                        value="f"
                      ></v-radio>
                      <v-radio
                        label="남성"
                        value="radio-2"
                      ></v-radio>
                       <v-radio
                        label="선택안함"
                        value="radio-2"
                      ></v-radio>
                    </v-radio-group>
                  <v-btn
                    type="submit"
                    color="#2ac187"
                    depressed
                    large
                    block
                    dark
                    class="mb-3"
                  >
                    회원가입
                  </v-btn>
                </form>
              </div>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
    </v-main>
  </v-app>
</div><!-- End -->
</div>
</div><!-- End -->
</div>
</div>
</template>

<style>
.login,
.image {
  min-height: 100vh;
}

.bg-image {
  background-image: url('@/assets/image/user/logo.jpeg');
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
export default {
    name: '',
    components: {},
    data() {
        return {
            memberId: '',
            idCheckMessage: '',
            allMember: null,
            password: '',
            password2: '',
            passwordCheckMessage: '',
            password2CheckMessage: '',
            canRegister: ''
    }
},
    beforeCreate() {},
    created() {},
    beforeMount() {},
    mounted() {
      const vm = this;
      this.axios({
                url: 'http://localhost:8088/java/memberAll',
                method : 'get',
            })
            .then(function (response) {
                console.log(response);
                if (response.data != "") {
                
                //this.$store.commit("setId", this.memberId);
                console.log(response.data)
                vm.allMember = response.data;

                }
            })
            .catch(function(error) {
              console.log(error);
            })
    },
    beforeUpdate() {},
    updated() {},
    beforeUnmount() {},
    unmounted() {},
    methods: {
      checkId() {
        for(let i = 0; i < this.allMember.length; i++) {
          console.log("this.allMember[i].memberId : " + this.allMember[i].memberId);
          console.log(this.memberId);
          if(this.memberId === '') {
            this.idCheckMessage = '';
            return;
          } else if(this.memberId !== this.allMember[i].memberId) {
            this.idCheckMessage = '사용 가능한 아이디입니다.';
            this.canRegister = 'true';
          } else if (this.memberId === this.allMember[i].memberId) {
            this.idCheckMessage = '사용 불가능한 아이디입니다.';
            this.canRegister = 'false';
            return;
          }
        }
      },
      checkPassword() {
        let special_pattern = /[`~!@#$%^&*|\\\'\";:\/?]/gi;

        if(this.password.search(/\s/) != -1) {
          this.passwordCheckMessage = '공백은 포함할 수 없습니다';
          this.canRegister = 'false';
        } else if (special_pattern.test(this.password) == false) {
          this.passwordCheckMessage = '한글자 이상의 특수문자를 포함해 주세요';
          this.canRegister = 'false';
	      } else if (this.password == this.password.toLowerCase()){
          this.passwordCheckMessage = '한 글자 이상의 대문자를 포함해 주세요';
          this.canRegister = 'false';
        } else if (this.password.length < 8) {
          this.passwordCheckMessage = '8자리 이상을 입력하세요';
          this.canRegister = 'false';
        } else {
          this.passwordCheckMessage = '사용 가능한 비밀번호 입니다.';
          this.canRegister = 'true';
        }
      },

      checkPassword2() {
        if (this.password !== this.password2) {
          this.password2CheckMessage = '비밀번호가 일치하지 않습니다.';
          this.canRegister = 'false';
        } else {
          this.password2CheckMessage = '비밀번호가 일치합니다.';
          this.canRegister = 'true';
        }
      }
    }
}
</script>