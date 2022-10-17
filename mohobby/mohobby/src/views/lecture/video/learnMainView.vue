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
    <v-bottom-navigation :value="value" color="primary" horizontal class="bottom-nav">
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
          <v-btn @click.stop="questForm">
            <span class="bottom-nav-text">질문하기</span>

            <v-icon color="white">mdi-chat-question</v-icon>
          </v-btn>

          <v-btn @click.stop="noteForm">
            <span class="bottom-nav-text">학습노트</span>

            <v-icon color="white">mdi-notebook-edit</v-icon>
          </v-btn>

        </div>
      </v-row>
      <div class="d-flex justify-end align-center">
        <v-btn>

          <span class="bottom-nav-text" style="margin-right: 20px; font-size: 1.1em;">다음학습</span>
          <v-icon color="white">mdi-chevron-right</v-icon>
        </v-btn>
      </div>


    </v-bottom-navigation>
    <v-bottom-sheet v-model="sheet">
      <v-sheet class="text-center" height="300px">

        <div class="d-flex justify-end align-center">

          <v-btn class="mt-6" text color="success" @click="console.log()">
            {{ form.submit }}
          </v-btn>
          <v-btn class="mt-6" text color="error" @click="sheet = false">
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
    <v-card>
      <v-system-bar></v-system-bar>
      <v-toolbar flat>
        <v-toolbar-title>My Document</v-toolbar-title>
        <v-spacer></v-spacer>
        <div>
          <v-switch v-model="sticky" label="Sticky Banner" hide-details></v-switch>
        </div>
      </v-toolbar>
      <v-banner single-line :sticky="sticky">
        We can't save your edits while you are in offline mode.

        <template v-slot:actions>
          <v-btn text color="deep-purple accent-4">
            Get Online
          </v-btn>
        </template>
      </v-banner>
      <v-card-text class="grey lighten-4">
        <v-sheet max-width="800" height="auto" class="mx-auto">
        </v-sheet>
      </v-card-text>
    </v-card>




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
        controls: [
          {
            position: 'right',
            html: 'Control',
            tooltip: 'Control Tooltip',
            click: function () {
              console.log('You clicked on the custom control');
              console.log(document.querySelector(".art-video").currentTime);
            },
          },
        ],
        highlight: [
          {
            time: 15,
            text: 'One more chance',
          },
          {
            time: 30,
            text: '谁でもいいはずなのに',
          },
          {
            time: 45,
            text: '夏の想い出がまわる',
          },
          {
            time: 60,
            text: 'こんなとこにあるはずもないのに',
          },
          {
            time: 75,
            text: '终わり',
          },
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
        type: 3, //2:질문, 3:노트
        submit: '노트작성',

      },
    };
  },
  components: {
    Artplayer,
  },
  methods: {
    getInstance(art) {
      console.log(art);
    },
    changePanelHeader() {
      console.log(event.currentTarget.style);
    },
    questForm() {
      document.querySelector(".art-video").pause();
      this.form.type = 2;
      this.form.submit = '질문등록';
      this.sheet = true;
    },
    noteForm() {
      document.querySelector(".art-video").pause();
      this.form.type = 3;
      this.form.submit = '노트작성';
      this.sheet = true;
    },
  },
  watch: {

  },
  created() {
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
</style>