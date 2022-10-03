<template>
    <div>
      <SnsSidebar></SnsSidebar>
      <h1>sns 챌린지 관리 화면</h1>
      <div id="app">
        <v-app id="inspire">
        <div>
          {{profileImg}} {{regDate}}
          <div id="mdi-dots-vertical">
            <v-btn icon>
              <v-icon>mdi-dots-vertical</v-icon>
            </v-btn>
          </div>
        </div>
        <div id ="image_box">
          <div class="d-flex flex-column justify-space-between align-center">
            <v-slider
              v-model="width"
              class="align-self-stretch"
              min="300"
              max="600"
              step="1"
            ></v-slider>
        
            <v-img
              :aspect-ratio="4/3"
              :width="width"
              src="https://img.insight.co.kr/static/2020/03/06/700/j0rpqkg2y659dw4acito.jpg"
            ></v-img>
          </div>
        </div>
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
        </v-app>
      </div>

    </div>
  </template>
  <script>
    import SnsSidebar from "../../components/sns/SnsSidebar.vue";
    export default {
      name: "snsFeedDetail",
      components: { SnsSidebar },
      data: () => ({
      profileImg : "대충프로필",
      regDate : "2022.01.01",
      cmtcnt : 15,
      likecnt : 0,
      width: 800,
      }),
      setup() {
        
      },
      created() {
    
      },
      mounted() {
    
      },
      unmounted() {
    
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
  
  <style>#app{
    margin: 0 auto;
    width:550px;
  }
    #mdi-dots-vertical{
      float:right;
    }
    #image_box {
      margin: 0 auto;
      width: 550px;
      height: 500px;
    }
    #full_heart{
      display : none;
    }
  </style>
  