<template>
  <div>
    <UserSidebar></UserSidebar>
    <h1>회원정보 수정하기</h1>
    <!-- Demo content-->
          <div class="container">
            <v-app id="inspire">
              <v-main class="lighten-4">
                <!-- login component -->
                <v-container style="max-width: 600px" fill-height>
                  <v-layout align-center row wrap>
                    <v-flex xs12>
                      <v-card>
                        <div class="pa-10">
                          <h1 style="text-align: center" class="mb-10">
                            회원 정보 수정
                          </h1>
                          <h6 style="text-align: center">
                            모든 요소는 필수적으로 입력해 주셔야합니다.
                          </h6>
                          <!-- 아이디입력 -->
                          <v-text-field
                            label="아이디"
                            prepend-inner-icon="mdi-account"
                            color="#2ac187"
                            v-model="memberId"
                            @input="checkId()"
                            :messages="idCheckMessage"
                            oninput="javascript: this.value= this.value.replace(/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/g, '');"
                          ></v-text-field>
                          <!-- 비밀번호입력 -->
                          <v-text-field
                            color="#2ac187"
                            prepend-inner-icon="mdi-lock"
                            type="password"
                            label="비밀번호"
                            v-model="password"
                            @input="checkPassword()"
                            :messages="passwordCheckMessage"
                            oninput="javascript: if (this.value.length > 12) this.value = this.value.slice(0, 12);"
                          ></v-text-field>
                          <!-- 비밀번호확인입력 -->
                          <v-text-field
                            color="#2ac187"
                            prepend-inner-icon="mdi-lock"
                            type="password"
                            label="비밀번호 확인"
                            v-model="password2"
                            @input="checkPassword2()"
                            :messages="password2CheckMessage"
                            oninput="javascript: if (this.value.length > 12) this.value = this.value.slice(0, 12);"
                          >
                          </v-text-field>
                          <!-- 이름입력 -->
                          <v-text-field
                            color="#2ac187"
                            prepend-inner-icon="mdi-account-outline"
                            label="이름"
                            v-model="memberName"
                          >
                          </v-text-field>
                          <!-- 닉네임입력 -->
                          <v-text-field
                            color="#2ac187"
                            prepend-inner-icon="mdi-account-outline"
                            label="닉네임"
                            v-model="nickName"
                          >
                          </v-text-field>
                          <!-- 생년월일입력 -->
                          <v-col cols="24" sm="20" md="20">
                            <v-dialog
                              ref="dialog"
                              color="#2ac187"
                              v-model="modal"
                              :return-value.sync="birth"
                              persistent
                              width="290px"
                            >
                              <template v-slot:activator="{ on, attrs }">
                                <v-text-field
                                  color="#2ac187"
                                  v-model="birth"
                                  label="Picker in dialog"
                                  prepend-icon="mdi-calendar"
                                  readonly
                                  v-bind="attrs"
                                  v-on="on"
                                ></v-text-field>
                              </template>
                              <v-date-picker
                                color="#2ac187"
                                v-model="birth"
                                scrollable
                              >
                                <v-spacer></v-spacer>
                                <v-btn
                                  text
                                  color="#2ac187"
                                  @click="modal = false"
                                >
                                  Cancel
                                </v-btn>
                                <v-btn
                                  text
                                  color="#2ac187"
                                  @click="$refs.dialog.save(birth)"
                                >
                                  OK
                                </v-btn>
                              </v-date-picker>
                            </v-dialog>
                          </v-col>
                          <!-- 성별입력 -->
                          <v-col>
                            <v-radio-group label="성별" row v-model="gender">
                              <v-radio
                                label="여성"
                                value="f"
                                color="#2ac187"
                              ></v-radio>
                              <v-radio
                                label="남성"
                                value="m"
                                color="#2ac187"
                              ></v-radio>
                              <v-radio
                                label="선택안함"
                                value="null"
                                color="#2ac187"
                              ></v-radio>
                            </v-radio-group>
                          </v-col>
                          
                          <!-- 지역 입력 -->
                          <v-col>
                            <v-select
                              v-if="regionList"
                              :items="regionList"
                              :item-text="'keywordName'"
                              :item-value="'keywordId'"
                              v-model="regionId"
                              label="지역"
                              dense
                            >
                            </v-select>
                          </v-col>

                          <!-- 이메일입력 -->
                          <v-text-field
                            id="email"
                            color="#2ac187"
                            prepend-inner-icon="mdi-email"
                            type="text"
                            label="이메일"
                            v-model="email"
                            @input="checkEmail()"
                            :messages="emailCheckMessage"
                          ></v-text-field>
                          <!-- 휴대전화번호 입력 -->
                          <v-text-field
                            color="#2ac187"
                            prepend-inner-icon="mdi-cellphone"
                            type="text"
                            label="휴대전화"
                            v-model="phoneNum"
                            @input="inputPhoneNumber()"
                            :messages="'-를 제외하고 입력해주세요'"
                          ></v-text-field>
                          <!-- 회원 소개하기 정보-->
                          <br>
                           <v-textarea
          name="input-7-1"
          label="소개글"
          v-model="intro"
          hint="Hint text"
        ></v-textarea>
                          <!-- 관심사 정보 -->
                          
      <v-row justify="space-around">
    <v-col
      cols="12"
      sm="30"
      md="30"
    >
      <v-sheet
        class="py-4 px-1"
      >
      <v-slide-group class="pa-2">
        <v-slide-item v-for="catg in catg" :key="catg.keywordId">
          <div class="displayflex">
        <v-chip-group
          active-class="primary--text"
        >
          <v-chip
            :value="catg.keywordId"
            @click="chipclick(catg.keywordId)"
          >
            {{ catg.keywordName }}
          </v-chip>
        </v-chip-group>
                 </div>
        </v-slide-item>
      </v-slide-group>
      </v-sheet>
    </v-col>
  </v-row>
                          <!-- 회원가입버튼 -->
                          <v-btn
                            type="submit"
                            color="#2ac187"
                            depressed
                            large
                            block
                            dark
                            class="mb-3"
                            @click="register()"
                          >
                            회원가입
                          </v-btn>
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
</template>

<script>
import UserSidebar from "../../components/user/UserSidebar.vue";
const { IMP } = window;
IMP.init("imp46541776");

export default {
  name: "",
  props: {
    email: {
      type: String,
      default: "",
    },
  },
  components: {UserSidebar},
  data() {
    return {
      catg: [
          
        ],
      memberId: "",
      idCheckMessage: "",
      allMember: null,
      password: "",
      password2: "",
      passwordCheckMessage: "",
      password2CheckMessage: "",
      canRegister: "",
      memberName: "",
      nickName: "",
      birth: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
        .toISOString()
        .substr(0, 10),
      menu: false,
      modal: false,
      menu2: false,
      emailCheckMessage: "",
      email: "",
      phoneNum: "",
      regionList: [],
      regionId: "",
      gender: "",
      checkCert: false,
      profileImg : "",
      intro : "",
      memberPickKeyword : "",
      clicked : false,
    };
  },
  beforeCreate() {},
  created() {
    this.getAllRegion();
    this.getAllCatg();
    
  },
  beforeMount() {},
  mounted() {
  this.setMemberInfo();
   this.getAllMember();
  },
  beforeUpdate() {},
  updated() {},
  beforeUnmount() {},
  unmounted() {},
  methods: {
    setMemberInfo() {
      const vm = this;
      this.memberId = this.$store.state.id;
       this.axios({
      url: "http://localhost:8088/java/member/" + this.memberId,
      method: "get",
    })
      .then(function (response) {
        if (response.data != "") {
          console.log(response.data);
          vm.memberId = response.data.memberId;
          vm.password = response.data.password;
          vm.password2 = response.data.password;
          vm.nickName = response.data.nickName;
          vm.email = response.data.email;
          vm.regionId = response.data.resionId;
          vm.phoneNum = response.data.phoneNum;
          vm.intro = response.data.intro;
          vm.gender = response.data.gender;
          vm.memberName = response.data.memberName;
          vm.profileImg = response.data.profileImg;
        }
        vm.getMemberPickKeyword();
      })
      .catch(function (error) {
        console.log(error);
      });
    },
    // 관심사 전체 목록 가져오기
    getAllCatg() {
    const vm = this;
    this.axios({
      url: "http://localhost:8088/java/allCatg",
      method: "get",
    })
      .then(function (response) {
        console.log(response.data);
        if (response.data != "") {
          vm.catg = response.data;
        }
      })
      .catch(function (error) {
        console.log(error);
      });
    },
    // 회원이 선택한 관심사 가져오기
    getMemberPickKeyword() {
  const vm = this;
    this.axios({
      url: "http://localhost:8088/java/memPickKeyword/" + this.memberId,
      method: "get",
    })
      .then(function (response) {
        console.log(response);
        if (response.data != "") {
          console.log(response.data);
          vm.memberPickKeyword = response.data;
        }
      })
      .catch(function (error) {
        console.log(error);
      });
    },
    // 유효성 검사 위해 회원 전체 정보 가져오기
    getAllMember() {
      const vm = this;
    this.axios({
      url: "http://localhost:8088/java/memberAll",
      method: "get",
    })
      .then(function (response) {
        console.log(response);
        if (response.data != "") {
          //this.$store.commit("setId", this.memberId);
          console.log(response.data);
          vm.allMember = response.data;
        }
      })
      .catch(function (error) {
        console.log(error);
      });
    },
    // 지역 정보 전체 가져오기
    getAllRegion() {
      const vm = this;
    this.axios({
      url: "http://localhost:8088/java/regionAll",
      method: "get",
    })
      .then(function (response) {
        console.log(response);
        if (response.data != "") {
          console.log(response.data);
          vm.regionList = response.data;
        }
      })
      .catch(function (error) {
        console.log(error);
      });
    }
    ,checkId() {
      for (let i = 0; i < this.allMember.length; i++) {
        console.log(
          "this.allMember[i].memberId : " + this.allMember[i].memberId
        );
        console.log(this.memberId);
        if (this.memberId === "") {
          this.idCheckMessage = "";
          return;
        } else if (this.memberId !== this.allMember[i].memberId) {
          this.idCheckMessage = "사용 가능한 아이디입니다.";
          this.canRegister = "true";
        } else if (this.memberId === this.allMember[i].memberId) {
          this.idCheckMessage = "사용 불가능한 아이디입니다.";
          this.canRegister = "false";
          return;
        }
      }
    },
    checkPassword() {
      let special_pattern = /[`~!@#$%^&*|\\\'\";:\/?]/gi;

      if (this.password.search(/\s/) != -1) {
        this.passwordCheckMessage = "공백은 포함할 수 없습니다";
        this.canRegister = "false";
      } else if (special_pattern.test(this.password) == false) {
        this.passwordCheckMessage = "한글자 이상의 특수문자를 포함해 주세요";
        this.canRegister = "false";
      } else if (this.password == this.password.toLowerCase()) {
        this.passwordCheckMessage = "한 글자 이상의 대문자를 포함해 주세요";
        this.canRegister = "false";
      } else if (this.password.length < 8) {
        this.passwordCheckMessage = "8자리 이상을 입력하세요";
        this.canRegister = "false";
      } else {
        this.passwordCheckMessage = "사용 가능한 비밀번호 입니다.";
        this.canRegister = "true";
      }
    },

    checkPassword2() {
      if (this.password !== this.password2) {
        this.password2CheckMessage = "비밀번호가 일치하지 않습니다.";
        this.canRegister = "false";
      } else {
        this.password2CheckMessage = "비밀번호가 일치합니다.";
        this.canRegister = "true";
      }
    },

    checkEmail() {
      let reg_email =
        /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
      for (let i = 0; i < this.allMember.length; i++) {
        if (this.allMember[i].email === this.email) {
          this.emailCheckMessage = "이미 사용중인 이메일입니다.";
          this.canRegister = false;
          return;
        }
      }
      if (!reg_email.test(this.email)) {
        this.emailCheckMessage = "이메일 형식이 아닙니다.";
        this.canRegister = false;
      } else {
        this.emailCheckMessage = "사용 가능한 이메일 입니다.";
        this.canRegister = true;
      }
    },

    inputPhoneNumber() {
      var number = this.phoneNum.replace(/[^0-9]/g, "");
      var phone = "";
      if (number.length < 4) {
        return number;
      } else if (number.length < 7) {
        phone += number.substr(0, 3);
        phone += "-";
        phone += number.substr(3);
      } else if (number.length < 11) {
        phone += number.substr(0, 3);
        phone += "-";
        phone += number.substr(3, 3);
        phone += "-";
        phone += number.substr(6);
      } else {
        phone += number.substr(0, 3);
        phone += "-";
        phone += number.substr(3, 4);
        phone += "-";
        phone += number.substr(7);
      }
      this.phoneNum = phone;
    },
    register() {
      const vm = this;

      if (
        this.memberId === "" ||
        this.password === "" ||
        this.regionId === "" ||
        this.memberName === "" ||
        this.nickName === "" ||
        this.gender === "" ||
        this.birth === "" ||
        this.email === "" ||
        this.phoneNum === ""
      ) {
        alert("필수항목이 입력되지 않았습니다.");
        return;
      }
      this.axios({
        url: "http://localhost:8088/java/member",
        method: "post",
        data: {
          memberId: this.memberId,
          password: this.password,
          regionId: this.regionId,
          memberName: this.memberName,
          nickName: this.nickName,
          gender: this.gender,
          birth: this.birth,
          email: this.email,
          phoneNum: this.phoneNum,
        },
      })
        .then(function (response) {
          console.log("회원등록 성공");
          vm.$router.push("/login");
        })
        .catch(function (error) {
          console.log("회원등록 실패");
        });
    },
      chipclick(keywordId) {
        if (this.clicked == true) {
          this.clicked = false;
          this.deleteMemKeyword(keywordId);
        } if(this.clicked == false) {
          this.clicked = true;
          this.insertMemKeyword(keywordId);
        }
    },
    insertMemKeyword(keywordId) {
         this.axios({
        url: "http://localhost:8088/java/memPickKeyword",
        method: "post",
        data: {
         memberId : this.memberId,
         keywordId : keywordId,
        }, 
      })
        .then(function (response) {
          console.log("키워드 등록 성공");
        })
        .catch(function (error) {
          console.log("키워드 등록 실패");
        }); 
    },
    deleteMemKeyword(keywordId) {
       this.axios({
        url: "http://localhost:8088/java/memPickKeyword",
        method: "delete",
        data: {
         memberId : this.memberId,
         keywordId : keywordId,
        },
      })
        .then(function (response) {
          console.log("키워드 삭제 성공");
        })
        .catch(function (error) {
          console.log("키워드 삭제 실패");
        });
    },
    onlyAlphabet(ele) {
      ele.value = ele.value.replace(/[^\\!-z]/gi, "");
    },
    
  },

};
</script>

<style scoped>
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