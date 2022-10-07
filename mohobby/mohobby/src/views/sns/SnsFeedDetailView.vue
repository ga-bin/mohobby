<template>
  <div>
    <SnsSidebar></SnsSidebar>
    <h1>sns 챌린지 관리 화면</h1>
    <div id="app">
      <v-app id="inspire">
        <div>
          {{ profileImg }} {{ regDate }}
          <div id="mdi-dots-vertical">
            <v-btn icon>
              <v-icon>mdi-dots-vertical</v-icon>
            </v-btn>
          </div>
        </div>
        <v-app id="vapp">
          <v-carousel ref="myCarousel" hide-delimiters :touchless="ture">
            <v-carousel-item
              v-for="(item, i) in items"
              :key="i"
              :src="item.src"
            ></v-carousel-item>
          </v-carousel>
        </v-app>

        <div id="image_box">
          <div class="d-flex flex-column justify-space-between align-center">
            <v-slider
              v-model="width"
              class="align-self-stretch"
              min="300"
              max="600"
              step="1"
            ></v-slider>

            <v-img
              :aspect-ratio="4 / 3"
              :width="width"
              src="https://img.insight.co.kr/static/2020/03/06/700/j0rpqkg2y659dw4acito.jpg"
            ></v-img>
          </div>
        </div>
        <div>
          <v-btn icon id="full_heart" text @click="likeBtn">
            <v-icon color="red lighten-2">mdi-heart</v-icon>
          </v-btn>
          <v-btn icon id="empty_heart" text @click="likeBtn">
            <v-icon>mdi-heart-outline</v-icon>
          </v-btn>

          {{ likecnt }}
          <v-icon>mdi-chat-outline</v-icon>
          {{ cmtcnt }}
          <v-icon @click="send">mdi-send</v-icon>
        </div>
      </v-app>
    </div>
  </div>
</template>
<script>
import axios from "axios";
import SnsSidebar from "../../components/sns/SnsCommn/SnsSidebar.vue";
export default {
  name: "snsFeedDetail",
  components: { SnsSidebar },
  data: () => ({
    profileImg: "대충프로필",
    regDate: "2022.01.01",
    cmtcnt: 15,
    likeStatus: 0, //좋아요 없음
    likecnt: 0,
    width: 800,
    memberId: "user1",
    targetId: 1,
    roomId: 0,
  }),
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
    send(){
     this.$router.push({name:"chat",params:{roomId:this.roomId}})

    },
    likeBtn() {
      // let target = event.target.getElementById;
      if (this.likeStatus === 0) {
        this.showFullHeart();
        console.log(this.showFullHeart);
        this.likeStatus = 1; // DB로 업데이트
        this.likecnt++;
        //좋아요 인서트
        axios
          .post("http://localhost:8088/java/feedDetail", {
            status: 1,
            memberId: this.memberId,
            targetId: this.targetId,
          })
          .then(function (response) {
            console.log(response);
          })
          .catch(function (error) {
            console.log(error);
          });
      } else {
        this.showEmptyHeart();
        console.log(this.showEmptyHeart);
        this.likeStatus = 0;
        this.likecnt--;

        //좋아요 업데이트
        axios
          .post("http://localhost:8088/java/updateLike", {
            status: 0,
            memberId: this.memberId,
            targetId: this.targetId,
          })
          .then(function (response) {
            console.log(response);
          })
          .catch(function (error) {
            console.log(error);
          });
      }
    },
    showEmptyHeart() {
      document.getElementById("empty_heart").style.display = "inline-block";
      document.getElementById("full_heart").style.display = "none";
    },
    showFullHeart() {
      document.getElementById("empty_heart").style.display = "none";
      document.getElementById("full_heart").style.display = "inline-block";
    },

    //carousel
    logic(e) {
      let currentMove = this.touch ? e.touches[0].clientX : e.clientX;
      if (this.move.length == 0) {
        this.move.push(currentMove);
      }
      if (this.move[this.move.length - 1] - currentMove < -100) {
        this.$refs.myCarousel.$el
          .querySelector(".v-window__prev")
          .querySelector(".v-btn")
          .click();
        this.drag = false;
        this.touch = false;
      }
      if (this.move[this.move.length - 1] - currentMove > 100) {
        this.$refs.myCarousel.$el
          .querySelector(".v-window__next")
          .querySelector(".v-btn")
          .click();
        this.drag = false;
        this.touch = false;
      }
    },
  },
  mounted() {
    // For touch devices
    this.$refs.myCarousel.$el.addEventListener("touchmove", (e) => {
      this.drag = false;
      this.touch = true;
      this.logic(e);
    });
    window.addEventListener("touchend", (e) => {
      this.move = [];
    });

    // For non-touch devices
    this.$refs.myCarousel.$el.addEventListener("mousedown", (e) => {
      this.drag = true;
      this.touch = false;
      this.logic(e);
    });
    this.$refs.myCarousel.$el.addEventListener("mousemove", (e) => {
      this.drag ? this.logic(e) : null;
    });
    window.addEventListener("mouseup", (e) => {
      this.drag = false;
      this.touch = false;
      this.move = [];
    });
  },
};
</script>

<style>
#app {
  margin: 0 auto;
  width: 550px;
}

#mdi-dots-vertical {
  float: right;
}

#image_box {
  margin: 0 auto;
  width: 550px;
  height: 500px;
}

#full_heart {
  display: none;
}
</style>
