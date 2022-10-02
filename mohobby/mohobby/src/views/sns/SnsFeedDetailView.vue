<template>
  <div>
    <SnsSidebar></SnsSidebar>
    <div id = "main">
      <h1>sns 피드 단건</h1>
      <div>
        {{profileImg}} {{regDate}}
      </div>
      <img src = "https://img.insight.co.kr/static/2020/03/06/700/j0rpqkg2y659dw4acito.jpg"/>
      <div>
        <v-btn icon
              text
              @click="likeBtn">
          <v-icon id="full_heart" color="red lighten-2">mdi-heart</v-icon>
          <v-icon id="empty_heart">mdi-heart-outline</v-icon>
        </v-btn>
        {{likecnt}}
        <v-icon>mdi-chat-outline</v-icon>
        {{cmtcnt}}
      </div>
    </div>
  </div>
  </template>
<script>
  import SnsSidebar from "../../components/sns/SnsSidebar.vue";
  export default {
    name: "",
    components: { SnsSidebar },
    data() {
      return {
        profileImg : "대충프로필",
        regDate : "2022.01.01",
        cmtcnt : 15,
        likecnt : 0
      };
    },

    methods: {
      likeBtn() {
        // let target = event.target.getElementById;
        if (this.likecnt > 0) {
          this.showEmptyHeart();
          console.log(this.showEmptyHeart);
          this.likecnt =- 1;
          
        }
        this.likecnt++;
        if (!this.showEmptyHeart()) {
        this.hiddenEmptyHeart();
        console.log(this.hiddenEmptyHeart);
        } //show/hidden 함께 실행됨.
        
        
        
      },
      showEmptyHeart() {
        document.getElementById("empty_heart").style.display = "inline-block";
        document.getElementById("full_heart").style.display = "none";
      },
      hiddenEmptyHeart() {
        document.getElementById("empty_heart").style.display = "none";
        document.getElementById("full_heart").style.display = "inline-block";
      }
    },
  };
  </script>
  
  <style>
    * {
      margin : 2% auto;
      box-sizing : border-box;
    }
    #full_heart{
      display : none;
    }
  </style>
  