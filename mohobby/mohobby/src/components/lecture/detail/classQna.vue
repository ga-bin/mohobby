<template>
  <v-container fluid>
    <!-- qna 내역 존재 -->
    <div v-if="qnaList.length != 0">
      <div>
        <v-card class="d-flex justify-end align-center" flat tile>
            <!-- 문의하기 버튼 -->
            <div class="text-center" style="padding-right: 20px;">
              <v-bottom-sheet
                v-model="sheet"
              >
                <template v-slot:activator="{ attrs }">
                  <v-btn
                    outlined
                    color="#2b2b2b"
                    v-bind="attrs"
                    @click="openSheet"
                  >
                    문의하기
                  </v-btn>
                </template>
                <v-sheet
                  class="text-center"
                  height="300px"
                >
                
                <div class="d-flex justify-end align-center">

                  <v-btn
                    class="mt-6"
                    text
                    color="success"
                    @click="addContent"
                  >
                    완료
                  </v-btn>
                  <v-btn
                    class="mt-6"
                    text
                    color="error"
                    @click="sheet = false"
                  >
                    취소
                  </v-btn>
                </div>
                <v-row style="padding: 0px 0px 15px 30px;" class="new-content-lock" @click="changeLock">
                  <v-icon style="padding-right: 5px;">
                    {{ this.newLock.icon }}
                  </v-icon>
                  {{ this.newLock.text }}
                </v-row>
                  <div class="my-3">
                    <v-textarea
                      filled
                      auto-grow
                      label="문의 내용을 입력하세요."
                      rows="5"
                      row-height="32"
                      shaped
                      v-model="newContent"
                    ></v-textarea>
                  </div>
                  
                </v-sheet>
              </v-bottom-sheet>
            </div>
          <v-card tile flat>
            <!-- 정렬 방식 -->
            <v-select :items="listStd" item-text="title" item-value="value"
              :menu-props="{ bottom: true, offsetY: true }" attach style="width: 160px;" v-model="defaultSelect" />
          </v-card>
        </v-card>
      </div>
      <!-- qna 목록 -->
      <div style="padding-top: 20px" v-for="(rv,i) in qnaList" :key="i">
        <v-card class="mx-auto" outlined>
          <v-card-text>
            <v-row>
              <v-avatar style="margin: 10px 0px 30px 5px;" flat size="30">
                <v-icon style="color: #2ac187">mdi-message-question</v-icon>
              </v-avatar>
              <v-col>
                <span 
                  class="qnaNickname"
                  @click="$router.push({ path: '/snsUserFeed?memId='+rv.memberId}).catch(()=>{$router.go(0)})"
                >
                  {{ rv.nickname }}
                </span>
                <span style="font-size: 1em; color: gray; padding-left: 7px;">{{ replaceDate(rv.writeDate) }}</span>
                <span v-if="rv.secret == 1 && rv.memberId == $store.state.id" style="padding-left: 10px; color: gray;"><v-icon size="17">mdi-lock</v-icon> {{ ' 비밀글입니다.'}}</span>
                <div v-if="rv.secret == 1 && rv.memberId != $store.state.id" style="font-size: 1.2em; padding-top: 14px; color: #8f8f8f;"><v-icon>mdi-lock</v-icon> {{ ' 비밀글입니다.'}}</div>
                <div v-if="rv.secret == 0 || rv.memberId == $store.state.id" style="font-size: 1.3em; padding-top: 14px">{{ rv.title }}</div>
              </v-col>
               <!-- 수정 / 삭제 -->
              <div style="padding: 12px 17px 0px 0px;">
                <v-row>
                  <div v-if="rv.replyCheck == 0 && rv.memberId == $store.state.id" class="modBtn" @click="updateContent(i)">수정</div>
                  <div v-if="rv.memberId == $store.state.id" class="delBtn">삭제</div>
                  <div v-if="rv.replyCheck == 0" style="color: gray;">미답변</div>
                  <div v-if="rv.replyCheck == 1" style="color: #2ac187;">답변완료</div>
                </v-row>
              </div>
            </v-row>
            <!--문의 답변-->
            <div 
              v-if="rv.replyCheck == 1 && rv.secret == 0" 
              style="padding-top: 20px;"
            >
              <v-divider></v-divider>
              <v-row style="padding-top: 20px;">
                <v-avatar style="margin: 10px 0px 30px 5px;" flat size="30">
                  <v-icon style="color: #2ac187">mdi-alpha-a-circle</v-icon>
                </v-avatar>
                <v-col>
                  <span 
                    class="qnaNickname"
                    @click="$router.push({ path: '/snsUserFeed?memId='+rv.memberId}).catch(()=>{$router.go(0)})"
                  >
                    {{ '관리자' }}
                  </span>
                  <div v-if="rv.secret == 0" style="font-size: 1.3em; padding-top: 14px">{{ rv.content }}</div>
                </v-col>
              </v-row>
            </div>
           
          </v-card-text>
        </v-card>
      </div>
    </div>
    <!-- qna 내역 없음 -->
    <div v-if="qnaList.length == 0">
      <v-card flat justify="center" align="center" style="padding-top: 50px">
        <h1>🙇</h1>
        <h1>등록된 QnA가 없습니다</h1>
      </v-card>
    </div>
  </v-container>
</template>

<script>
export default {
  name: "classQna",
  props: {
    classId: {
      type: String,
      default: 1
    }
  },
  data() {
    return {
      newLock: {
        value: 0,
        icon: 'mdi-lock-open-variant',
        text: '공개',
      },
      newContent: '',
      sheet: '',
      classInfo: {},
      qnaList: [],
      listStd: [
        { title: "최신순", value: "0" },
        { title: "답변완료순", value: "1" }
      ],
      defaultSelect: {
        value: "0"
      }
    };
  },
  created() {
    this.initInfo();
    this.getQnaList();
  },
  methods: {
    initInfo() {
      let p = this.$parent;
      if (typeof p == "undefined") {
        p.initInfo();
      }
      this.classInfo = p.$data.classInfo;
    },
    getQnaList() {
      this.axios("/class/board", {
        params: {
          classId: this.classId,
          boardType: 1
        }
      })
        .then(res => {
          if (res.data.length > 0) {
            this.qnaList = res.data;
          }
        })
        .catch(err => console.log(err));
    },
    replaceDate(date) {
      return this.$moment(date).fromNow();
    },
    selectSort() {
      let listSort = this.qnaList;
      if (this.defaultSelect == 0) {
        listSort.sort((a, b) => b.boardId - a.boardId);
      } else if (this.defaultSelect == 1) {
        listSort.sort((a, b) => b.replyCheck - a.replyCheck);
      }
      this.qnaList = listSort;
    },
    openSheet() {
      if (!this.$store.state.id) {
          this.$swal('로그인 후 이용하세요!', '', 'info');
          return;
      }
      this.sheet = !this.sheet;
    },
    addContent() {
      console.log(event.currenttarget);
      if (this.newContent == '') {
        this.$swal('내용을 입력하세요!', '', 'info');
      } else {
        // this.axios('/class/board', {
        //   method: "POST",
        //   headers: {
        //       "Content-Type": "application/json; charset=utf-8",
        //   },
        //   data: JSON.stringify({
        //       memberId: this.$store.state.id,
        //       classId: this.classId,
        //       boardType: 1,
        //       title: this.newContent,
        //       secret: this.newLock.value,
        //       nickname: this.$store.state.user.nickName,
        //   })
        // }).then( res => {
        //   if(res.status == 200) {
        //     this.sheet = false;
        //     this.qnaList.unshift(res.data);
        //   }
        // }).catch( err => console.log(err) )
        console.log(event.currenttarget);
      }
    },
    initContent() {
      this.newContent = '';
      this.newLock.secret = 0;
      this.sheet = false;
      this.newLock.text = '공개';
      this.newLock.icon = 'mdi-lock-open-variant';
      this.newLock.value = 0;
    },
    changeLock() {
      if(this.newLock.text == '공개') {
        this.newLock.text = '비공개';
        this.newLock.icon = 'mdi-lock';
        this.newLock.value = 1;
      } else if(this.newLock.text == '비공개') {
        this.newLock.text = '공개';
        this.newLock.icon = 'mdi-lock-open-variant';
        this.newLock.value = 0;
      }
    },
    updateContent(idx) {
      this.newLock.value = this.qnaList[idx].secret;
      this.newLock.icon = this.qnaList[idx].secret == 0 ? 'mdi-lock-open-variant' : 'mdi-lock';
      this.newLock.text = this.qnaList[idx].secret == 0 ? '공개' : '비공개';
      this.newContent = this.qnaList[idx].title;
      this.sheet = !this.sheet;
    }
  },
  watch: {
    defaultSelect: function () {
      this.selectSort();
    },
    sheet: function() {
      if(!this.sheet) {
        this.initContent();
      }
    }
  }
};
</script>

<style scoped>
* {
  color: #2b2b2b;
}

.qnaTitle {
  font-weight: bold;
  font-size: 1.2em;
  padding-right: 8px;
}

.qnaNickname {
  font-size: 1.2em;
  cursor: pointer;
  font-weight: bold;
}

.mdi-lock {
  color: #8f8f8f;
}

.new-content-lock {
  cursor: pointer;
}

.modBtn, .delBtn {
  margin-right: 10px;
  padding: 0px 5px;
  cursor: pointer;
  border-radius: 6px;
}

.modBtn {
  border: 1px solid #229c6e;
  color: #229c6e;
}

.delBtn {
  border: 1px solid #7a2a1c;
  color: #7a2a1c;
}
</style>