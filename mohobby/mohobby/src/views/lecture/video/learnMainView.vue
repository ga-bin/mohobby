<template>
  <div id="learn-page-box">

    <v-sheet class="overflow-hidden" style="position: relative; background-color: #1d202e;">
      <v-container class="fill-height">
        <v-row align="center" justify="start" style="padding-top: 2%;">
          <v-icon color="white" @click.stop="drawer = !drawer" style="padding: 0px;">
            mdi-menu
          </v-icon>
          <span class="currName">제목</span>
        </v-row>
        <Artplayer @get-instance="getInstance" :option="option" :style="style" />
      </v-container>

      <v-navigation-drawer v-model="drawer" width="600" color="#1d202e" absolute temporary>


        <v-expansion-panels v-model="panel" multiple flat>
          <h1 class="panel-header-title">
            제목
          </h1>
          <v-expansion-panel v-for="(item,i) in items" :key="i">
            <v-expansion-panel-header class="panel-header" @click="changePanelHeader">
              <v-row justify="start" align="center">
                <v-chip label color="white">{{ i+1 }}</v-chip>
                <div>
                  <h3 class="panel-header-text">{{ item.header }}</h3>
                </div>
              </v-row>
            </v-expansion-panel-header>
            <v-expansion-panel-content class="panel-content">
              <v-list dense nav>
                <v-list-item v-for="(child,j) in item.content" :key="j" link @click.stop="pushCheck(child)"
                  class="panel-list-item">
                  <div style="font-weight: bold; color:#AAABB7; padding-right: 30px;">{{ j+1 | idx }}</div>
                  <div v-if="child.preview == 1"
                    style="margin-right:20px; padding:3px 7px; background-color: #dfeff1; color:#188ba7; border-radius: 5px; font-size: 0.9em;">
                    {{ '미리보기' }}</div>
                  <v-list-item-content>
                    <h3 class="panel-list-item-title">{{ child.title }}</h3>
                  </v-list-item-content>
                  <div style="font-weight: bold; color:#AAABB7;">{{ child.runtime | runtime }}</div>
                </v-list-item>
              </v-list>
            </v-expansion-panel-content>
          </v-expansion-panel>
        </v-expansion-panels>
      </v-navigation-drawer>
    </v-sheet>

    <v-progress-linear :value="knowledge" color="#AAABB7" height="20" readonly>
      <strong style="font-size: 0.9em;">전체 진도율 {{ Math.ceil(knowledge) }}%</strong>
    </v-progress-linear>
    <v-bottom-navigation color="primary" horizontal class="bottom-nav">
      <v-row>
        <div class="d-flex justify-start align-center">
          <v-btn>
            <v-icon color="white" style="margin-left: 15px;">mdi-chevron-left</v-icon>
            <span class="bottom-nav-text" style="margin-left: 20px; font-size: 1.1em;">이전학습</span>

          </v-btn>
        </div>
      </v-row>
      <v-row>
        <div>
          <v-tabs class="d-flex justify-center align-center" v-model="tab" background-color="transparent">
            <v-tab key="2">
              <v-btn @click="questForm">
                <span class="bottom-nav-text-2">질문하기</span>
    
                <v-icon color="white" class="bottom-nav-text-2">mdi-chat-question</v-icon>
              </v-btn>
            </v-tab>
            <v-tab key="3">
              <v-btn @click="noteForm">
                <span class="bottom-nav-text-2">학습노트</span>
                <v-icon color="white" class="bottom-nav-text-2">mdi-notebook-edit</v-icon>
              </v-btn>
            </v-tab>
          </v-tabs>
        </div>
      </v-row>
      <div class="d-flex justify-center align-center">
        <v-btn>
          <span class="bottom-nav-text" style="margin-right: 20px; font-size: 1.1em;">다음학습</span>
          <v-icon color="white">mdi-chevron-right</v-icon>
        </v-btn>
      </div>


    </v-bottom-navigation>
    <v-bottom-sheet v-model="sheet">
      <v-sheet class="text-center" height="300px">
        <div class="d-flex justify-center align-center">
          <span class="mt-2 mr-3">{{ currentTime | runtime }}</span>

          <v-btn class="mt-2" text color="success" @click="clickSubmit">
            {{ form.submit }}
          </v-btn>
          <v-btn class="mt-2" text color="error" @click="sheet = false">
            취소
          </v-btn>
        </div>
        <div class="my-3">
          <div v-if="form.type == 3">
            <v-textarea filled auto-grow label="내용을 입력하세요." rows="5" row-height="32" shaped v-model="newContent">
            </v-textarea>
          </div>
          <div v-if="form.type == 2">
            <v-textarea filled auto-grow label="질문 내용을 입력하세요." rows="5" row-height="32" shaped v-model="newContent">
            </v-textarea>
          </div>
        </div>

      </v-sheet>
    </v-bottom-sheet>
      
      <v-tabs-items v-model="tab" style="padding: 20px 0px">
        <v-tab-item key="2">
          <!-- 질문 내역 존재 -->
          <div v-if="questList.length != 0">
            <div>
              <v-card class="d-flex justify-end align-center" flat tile>
                <!-- 작성하기 버튼 -->
                <div class="text-center mb-3" style="padding-right: 20px;">
                  <v-btn
                    outlined
                    color="#2b2b2b"
                    @click="clickWriteBtn"
                  >
                    질문 작성하기
                  </v-btn>
                </div>
                <v-card tile flat>
                  <!-- 정렬 방식 -->
                  <v-select :items="listFilter" item-text="title" item-value="value"
                    :menu-props="{ bottom: true, offsetY: true }" attach style="width: 160px;" v-model="defaultFilter" />
                </v-card>
              </v-card>
            </div>
            <!-- 질문 게시글 목록 -->
            <div style="padding: 5px 20px" v-for="(rv,i) in questList" :key="i">
              <v-card class="mx-auto" outlined>
                <v-card-text>
                  <v-row>
                    <v-chip
                      class="mt-2 ml-2"
                      @click="moveTime(rv.title)"
                      color="#AAABB7"
                      dark
                    >
                      #{{ rv.title | runtime }}
                    </v-chip>
                    <v-col>
                      <span 
                        class="nickname"
                        @click="$router.push({ path: '/snsUserFeed?memId='+rv.memberId}).catch(()=>{$router.go(0)})"
                      >
                        {{ rv.nickname }}
                      </span>
                      <span style="font-size: 1em; color: gray; padding-left: 7px;">{{ replaceDate(rv.writeDate) }}</span>
                      <div style="font-size: 1.3em; color: #2b2b2b; padding-top: 14px">{{ rv.content }}</div>
                    </v-col>
                    <!-- 수정 / 삭제 -->
                    <div style="padding: 20px 17px 0px 0px;">
                      <v-row v-if="rv.memberId == $store.state.id">
                        <div class="modBtn" @click="clickUpdateBtn(i)">수정</div>
                        <div class="delBtn" @click="clickDelete(i)">삭제</div>
                      </v-row>
                    </div>
                  </v-row>
                  <!--댓글-->
                  <v-divider class="mt-5 mb-5" inset></v-divider>
                  <details>
                    <summary style="font-size: 1.2em;">댓글({{ rv.commentTotal }})</summary>
                    <div v-if="rv.commentTotal > 0" class="output ql-snow">
                      ㅇㅇ
                    </div>
                  </details>
                
                </v-card-text>
              </v-card>
            </div>
          </div>
          <div v-if="questList.length == 0">
            <v-card flat justify="center" align="center" style="padding-top: 50px">
              <h1>🙇</h1>
              <h1>등록된 질문이 없습니다</h1>
            </v-card>
          </div>
        </v-tab-item>
        <v-tab-item key="3">
          <!-- 노트 내역 존재 -->
          <div v-if="questList.length != 0">
            <div>
              <v-card class="d-flex justify-end align-center" flat tile>
                <!-- 작성하기 버튼 -->
                <div class="text-center mb-3" style="padding-right: 20px;">
                  <v-btn
                    outlined
                    color="#2b2b2b"
                    @click="sheet = true"
                  >
                    노트 작성하기
                  </v-btn>
                </div>
                <v-card tile flat>
                  <!-- 정렬 방식 -->
                  <v-select :items="listFilter" item-text="title" item-value="value"
                    :menu-props="{ bottom: true, offsetY: true }" attach style="width: 160px;" v-model="defaultFilter" />
                </v-card>
              </v-card>
            </div>
            <!-- 노트 게시글 목록 -->
            <div style="padding: 5px 20px" v-for="(rv,i) in noteList" :key="i">
              <v-card class="mx-auto" outlined>
                <v-card-text>
                  <v-row>
                    <v-chip
                      class="mt-2 ml-2"
                      @click="moveTime(rv.title)"
                      color="#AAABB7"
                      dark
                    >
                      #{{ rv.title | runtime }}
                    </v-chip>
                    <v-col>
                      <span style="font-size: 1em; color: gray;">{{ replaceDate(rv.writeDate) }}</span>
                      <div style="font-size: 1.3em; color: #2b2b2b; padding-top: 14px">{{ rv.content }}</div>
                    </v-col>
                    <!-- 수정 / 삭제 -->
                    <div style="padding: 20px 17px 0px 0px;">
                      <v-row v-if="rv.memberId == $store.state.id">
                        <div class="modBtn" @click="clickUpdateBtn(i)">수정</div>
                        <div class="delBtn" @click="clickDelete(i)">삭제</div>
                      </v-row>
                    </div>
                  </v-row>
                </v-card-text>
              </v-card>
            </div>
          </div>
          <div v-if="questList.length == 0">
            <v-card flat justify="center" align="center" style="padding-top: 50px">
              <h1>🙇</h1>
              <h1>작성한 학습노트가 없습니다</h1>
            </v-card>
          </div>
        </v-tab-item>
      </v-tabs-items>




  </div>
</template>

<script>
import Artplayer from "artplayer/examples/vue/Artplayer";

export default {
  name: 'classDetail',
  props: {
    currId: {
      type: String,
      default: 1,
    },
  },
  data() {
    return {
      defaultFilter: 0,
      listFilter: [
        {
          title: '최신순',
          value: 0,
        },
        {
          title: '댓글순',
          value: 1,
        },
      ],
      tab: '2',
      classInfo: {},
      questList: [],
      panel: [],
      items: [
        {
          header: '프로그래밍 혁명',
          content: [
            {
              title: '코딩배우기',
              runtime: '142',
              preview: 1,
              currId: 1,
            },
            {
              title: '얏호',
              runtime: '1680',
              preview: 1,
              currId: 1,
            },
            {
              title: '코딩이 뭘까?',
              runtime: '239',
              preview: 1,
              currId: 1,
            },
            {
              title: '프로그래밍 혁명에 참여하는 방법',
              runtime: '237',
              preview: 1,
              currId: 1,
            },
          ],
        },
        {
          header: '입문자가 알기 힘든 두 가지',
          content: [
            {
              title: '코딩배우기',
              runtime: '142',
              preview: 1,
              currId: 1,
            },
            {
              title: '얏호',
              runtime: '1680',
              preview: 0,
            },
            {
              title: '코딩이 뭘까?',
              runtime: '239',
              preview: 0,
            },
            {
              title: '프로그래밍 혁명에 참여하는 방법',
              runtime: '237',
              preview: 0,
            },
          ],
        },
        {
          header: '프로그래밍 분야들',
          content: [
            {
              title: '코딩배우기',
              runtime: '142',
              preview: 0,
            },
            {
              title: '얏호',
              runtime: '1680',
              preview: 0,
            },
            {
              title: '코딩이 뭘까?',
              runtime: '239',
              preview: 0,
            },
            {
              title: '프로그래밍 혁명에 참여하는 방법',
              runtime: '237',
              preview: 0,
            },
          ],
        },
        {
          header: '공부하기!',
          content: [
            {
              title: '코딩배우기',
              runtime: '142',
              preview: 0,
            },
            {
              title: '얏호',
              runtime: '1680',
              preview: 0,
            },
            {
              title: '코딩이 뭘까?',
              runtime: '239',
              preview: 0,
            },
            {
              title: '프로그래밍 혁명에 참여하는 방법',
              runtime: '237',
              preview: 0,
            },
          ],
        },
        {
          header: '퀴즈',
          content: [
            {
              title: '코딩배우기',
              runtime: '142',
              preview: 0,
            },
            {
              title: '얏호',
              runtime: '1680',
              preview: 0,
            },
            {
              title: '코딩이 뭘까?',
              runtime: '239',
              preview: 0,
            },
            {
              title: '프로그래밍 혁명에 참여하는 방법',
              runtime: '237',
              preview: 0,
            },
          ],
        },
      ],
      drawer: false,
      option: {
        url: require('@/assets/video/class/curriculum/1.mp4'),
        volume: 0.5,
        isLive: false,
        muted: false,
        autoplay: false,
        pip: false,
        autoSize: true,
        autoMini: false,
        screenshot: true,
        setting: true,
        loop: false,
        rotate: true,
        playbackRate: true,
        fullscreen: true,
        fullscreenWeb: true,
        localVideo: false,
        autoPlayback: true,
        theme: '#ffad00',
        quality: [
          {
            url: require('@/assets/video/class/curriculum/1.mp4'),
            html: 'SD 480P',
          },
          {
            url: require('@/assets/video/class/curriculum/1.mp4'),
            default: true,
            html: 'HD 720P',
          },
          {
            url: require('@/assets/video/class/curriculum/1.mp4'),
            html: 'HD 1080P',
          },
        ],
        // controls: [
        //   {
        //     position: 'right',
        //     html: 'Control',
        //     tooltip: 'Control Tooltip',
        //     click: function () {
        //       console.log('You clicked on the custom control');
        //       console.log(document.querySelector(".art-video").currentTime);
        //       this.option.quality.pop();
        //       console.log(this.option.quality);
        //     },
        //   },
        // ],
        highlight: [

        ],
        icons: {
          loading: '',
          state: '',
          indicator: '',
        },
      },
      style: {
        width: "70%",
        height: "729px",
        margin: "0 auto",
        padding: "3% 0",
      },
      knowledge: 82.65,
      sheet: false,
      newContent: '',
      form: {
        type: 2, //2:질문, 3:노트
        submit: '질문등록',

      },
      currentTime: 0,
      noteList: [],
      updateObj: {},

    };
  },
  components: {
    Artplayer,
  },
  methods: {
    getQuestList() {
      this.axios("/class/board", {
        params: {
          classId: this.currId,
          boardType: 2
        }
      })
      .then(res => {
        console.log(res.data);
        if (res.data.length > 0) {
          this.questList = res.data;
        }
      })
      .catch(err => console.log(err));
    },
    getNoteList() {
      this.axios("/class/board", {
        params: {
          classId: this.currId,
          boardType: 3
        }
      })
      .then(res => {
        if(res.data.length != 0) {
          this.noteList = res.data;
        }
      })
      .catch(err => console.log(err));
    },
    getControls() {},
    changePanelHeader() {
      console.log(event.currentTarget.style);
    },
    getInstance(art) {
      console.log(art);
      console.log(art.playing);
      console.log(art.getControls());
    },
    questForm() {
      if (!this.$store.state.id) {
          this.$swal('로그인 후 이용하세요!', '', 'info');
          return;
      }
      //document.querySelector(".art-video").pause();
      this.currentTime = document.querySelector(".art-video").currentTime;
      this.form.type = 2;
      this.form.submit = '질문등록';
      //this.sheet = true;
    },
    noteForm() {
      if (!this.$store.state.id) {
          this.$swal('로그인 후 이용하세요!', '', 'info');
          return;
      }
      //document.querySelector(".art-video").pause();
      this.currentTime = document.querySelector(".art-video").currentTime;
      this.form.type = 3;
      this.form.submit = '노트작성';
      //this.sheet = true;
    },
    clickSubmit() {
      if(this.form.submit.includes('수정')) {
        this.updateContent(this.updateObj);
      } else {
        this.insertContent();
      }
    },
    updateContent(obj) {
      if(this.newContent == obj.content) {
        this.$swal('변경된 내용이 없습니다!', '', 'info');
      } else {
        this.axios.put('/class/board', {
            boardId: obj.boardId,
            title: obj.title,
            content: this.newContent,
            boardType: obj.boardType,
        }).then( res => {
          if(res.status == 200) {
            obj.content = this.newContent;
            this.sheet = false;
          }
        }).catch( err => console.log(err) )
      }
    },
    insertContent() {
      if(this.newContent == '') {
        this.$swal('내용을 입력하세요!', '', 'info');
        return;
      }

      if(this.form.type == 2) {
        this.insertQuest();
      } else if(this.form.type == 3) {
        this.insertNote();
      }
    },
    insertQuest() {
      this.axios('/class/board', {
        method: "POST",
        headers: {
            "Content-Type": "application/json; charset=utf-8",
        },
        data: JSON.stringify({
            memberId: this.$store.state.id,
            classId: this.currId,
            boardType: 2,
            title: Math.ceil(document.querySelector(".art-video").currentTime),
            content: this.newContent,
            nickname: this.$store.state.user.nickName,
        })
      }).then( res => {
        if(res.status == 200) {
          this.sheet = false;
          this.newContent = '';
          this.questList.unshift(res.data);
        }
      }).catch( err => console.log(err) )
    },
    insertNote() {
      this.axios('/class/board', {
        method: "POST",
        headers: {
            "Content-Type": "application/json; charset=utf-8",
        },
        data: JSON.stringify({
            memberId: this.$store.state.id,
            classId: this.currId,
            boardType: 3,
            title: Math.ceil(document.querySelector(".art-video").currentTime),
            content: this.newContent,
            nickname: this.$store.state.user.nickName,
        })
      }).then( res => {
        if(res.status == 200) {
          this.sheet = false;
          this.newContent = '';
          this.noteList.unshift(res.data);
        }
      }).catch( err => console.log(err) )
    },
    replaceDate(date) {
      return this.$moment(date).fromNow();
    },
    moveTime(time) {
      document.querySelector(".art-video").currentTime = time;
    },
    clickUpdateBtn(idx) {
      if(this.form.type == 2) {
        this.newContent = this.questList[idx].content;
        this.currentTime = this.questList[idx].title;
        this.form.submit = '질문수정';
        this.updateObj = this.questList[idx];
        this.sheet = true;
      } else if(this.form.type == 3) {
        this.newContent = this.noteList[idx].content;
        this.currentTime = this.noteList[idx].title;
        this.form.submit = '노트수정';
        this.updateObj = this.noteList[idx];
        this.sheet = true;
      }
    },
    clickDelete(idx) {
      this.$swal({
        title: '정말 삭제할까요?',
        text: "삭제를 원하지 않으면 취소버튼을 눌러주세요!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#2ac187',
        cancelButtonColor: '#d33',
        cancelButtonText: '취소',
        confirmButtonText: '네, 삭제할게요!'
      }).then((result) => {
        if (result.isConfirmed) {
          this.deleteContent(idx);
        }
      })
    },
    deleteContent(idx) {
      let obj = {};
      if(this.form.type == 2){
        obj = this.questList[idx];
      } else if(this.form.type == 3) {
        obj = this.noteList[idx];
      }

      this.axios.delete('/class/board', {
        params: {
          boardId: obj.boardId,
        },
      }).then( res => {
        if(res.status == 200) {
          this.$swal(
            '삭제 완료!',
            '작성한 QnA를 삭제하였습니다.',
            'success'
          );
          this.sheet = false;
          if(this.form.type == 2){
            this.questList.splice(idx, 1);
          } else if(this.form.type == 3) {
            this.noteList.splice(idx, 1);
          }
        }
      })
      
    },
    clickWriteBtn() {
      this.currentTime = document.querySelector(".art-video").currentTime;
      this.sheet = true;
    }
  },
  watch: {
    sheet: function() {
      if(this.sheet == true) {
        document.querySelector(".art-video").pause();
      }
      if(this.sheet == false) {
        this.newContent = '';
        this.currentTime = document.querySelector(".art-video").currentTime;
        if(this.form.type == 2) {
          this.form.submit = '질문등록';
        } else if(this.form.type == 3) {
          this.form.submit = '노트작성';
        }
      }
    },
  },
  created() {
    this.getNoteList();
    this.getQuestList();
  }
};
</script>
<style>
#learn-page-box {
  width: 100%;
  background-color: #1d202e !important;
}

.art-video-player {
  z-index: 0 !important;
}
</style>
<style scoped>
* {
  word-break: keep-all;
}
.currName {
  color: #f3f3f3;
  padding-left: 15px;
}

.v-expansion-panel-header {
  border: 1px solid #4d4c51;
  background-color: #343646;
}

.panel-list-item {
  background-color: #1d202e;
  color: #f3f3f3;
}

.panel-list-item-title {
  font-size: 1.1em;
  color: #cccccc;
  font-weight: 100;
}

.panel-header {
  background-color: #343646;
  padding: 25px;
}

.panel-content {
  background-color: #1d202e;
}

.panel-header-text {
  padding-left: 10px;
  color: #cccccc;
  font-weight: 200;
}

.panel-header-title {
  color: #cccccc;
  padding: 20px;
}

.bottom-nav {
  background-color: #2e303f;
}

.bottom-nav-text {
  color: white;
}

.bottom-nav-text-2 {
  color: white;
  padding-bottom: 20%;
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

.nickname {
  font-size: 1.2em;
  cursor: pointer;
  font-weight: bold;
}
</style>