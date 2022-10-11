<template>
  <div id="container">
    <SnsSidebar></SnsSidebar>
    <h1>피드디테일</h1>
    <v-container>
      <v-card class="mx-auto" max-width="1000">
        <v-row>
          <v-col col="12">

            <!-- 프로필 -->
            <div>
              <div class="flex">
                <v-avatar class="ml-10 my-10 mr-4" color="grey darken-1" size="64">
                  <v-img aspect-ratio="30" :src="require(`@/assets/image/user/${items.profileImg}`)" />
                </v-avatar>
                <div class="user text-overline">{{items.memberId}}<br>{{
                this.$moment(items.writeDate).format('YYYY.MM.DD') }}</div>
              </div>
            </div>
            <div id="mdi-dots-vertical">
              <v-btn icon>
                <v-icon>mdi-dots-vertical</v-icon>
              </v-btn>
            </div>
          </v-col>
        </v-row>
        <v-row>
          <v-col col="12">
            <div id="image_box">
              <!-- <div class="d-flex flex-column justify-space-between align-center">
            <v-slider
              v-model="width"
              class="align-self-stretch"
              min="300"
              max="600"
              step="1"
            ></v-slider> -->
              <!-- <v-img v-for="(item, i) in imagelist" :key="i" :src="require(`../../../back/uploads/${item}`)"
       contain height="150px" width="200px" style="border: 2px solid black; margin-left:100px;"/> -->
              <v-carousel ref="myCarousel" hide-delimiters :touchless="ture">
                <v-carousel-item :aspect-ratio="4 / 3" :width="width"
                  :src="require(`@/assets/image/sns/${items.thumbnail}`)"></v-carousel-item>
              </v-carousel>
              <!-- </div> -->
            </div>
          </v-col>
        </v-row>
        <v-col col="12">
          <div>{{ items.content }}</div>
        </v-col>
        <v-col col="12" v-for="hashtag in hashtags" :key="hashtag">
          <div @click="search($event)">#{{ hashtag }}</div>
        </v-col>
        <div style="display=flex;">
          <div id="like_box">
            <v-btn icon id="full_heart" text @click="likeBtn">
              <v-icon color="red lighten-2">mdi-heart</v-icon>
            </v-btn>
            <v-btn icon id="empty_heart" text @click="likeBtn">
              <v-icon>mdi-heart-outline</v-icon>
            </v-btn>
            {{ items.likes }}
          </div>
          <div>
            <v-icon>mdi-chat-outline</v-icon>
            {{ items.cmts }}
          </div>
          <div>
            <div v-if="this.$store.state.id !== items.memberId">
              <v-icon @click="send">mdi-send</v-icon>
          </div>
          </div>
        </div>
      </v-card>
      <v-col col="12">
        <Comment></Comment>
      </v-col>
    </v-container>
  </div>
</template>
<script>
import SnsSidebar from "../../components/sns/Common/SnsSidebar.vue";
import Comment from "../../components/sns/FeedDetail/Comment.vue";
export default {
  name: "snsFeedDetail",
  components: { SnsSidebar, Comment },
  data: () => ({
    profileImg: "대충프로필",
    regDate: "2022.01.01",
    likeStatus: 0, //좋아요 없음
    width: 800,
    roomId: 0,
    items: [],
    hashtags: [],
    feeds : [],
    show : true,
  }),
  setup() { },
  created() {
    console.log(this.$route.query.id);
    console.log(this.$store.state.id);
    this.showDetail();
  },
  mounted() {
    // For touch devices
    // this.$refs.myCarousel.$el.addEventListener("touchmove", (e) => {
    //   this.drag = false;
    //   this.touch = true;
    //   this.logic(e);
    // });
    // window.addEventListener("touchend", (e) => {
    //   this.move = [];
    // });

    // // For non-touch devices
    // this.$refs.myCarousel.$el.addEventListener("mousedown", (e) => {
    //   this.drag = true;
    //   this.touch = false;
    //   this.logic(e);
    // });
    // this.$refs.myCarousel.$el.addEventListener("mousemove", (e) => {
    //   this.drag ? this.logic(e) : null;
    // });
    // window.addEventListener("mouseup", (e) => {
    //   this.drag = false;
    //   this.touch = false;
    //   this.move = [];
    // });
  },
  unmounted() { },
  methods: {
    //Detail조회
    showDetail() {
      let postId = this.$route.query.id;
      this.axios('/sns/user/feed_detail', {
        params: {
          postId: postId
        }
      }).then(res => {
        this.items = res.data;
        let str = this.items.hashtag;
        let hashtag = str.split(',');
        console.log(hashtag);
        this.hashtags = hashtag;
        console.log(this.items);
        console.log(this.items.cmts);
      }).catch(err => {
        console.log(err);
      });
    },

    search(e){
            //유저 아이디 or 닉네임 조회
            let getHashtag = e.target.innerText;
            let hashtag = getHashtag.slice(1);
            console.log(hashtag);
            this.axios('/sns/search/hashtag', {
                params : {
                    hashtag : hashtag
                }
            }).then(res => {
                console.log(res);
                this.feeds = res.data;
                this.goSearchPage(this.feeds);
                console.log("검색성공:"+this.feeds);

            }).catch(err =>{
                console.log(err);
            });
       
    },
    goSearchPage(feeds){
      console.log("goSearchPage실행"+feeds);
      

      this.$router.push({ name: "snsmain", params: {  sfeeds: feeds } })

    },

    send() {
      this.$router.push({ name: "chat", params: { roomId: this.roomId } })
      let vm = this;
      this.axios.post('http://localhost:8088/java/CreateRoom/', {
        myId: vm.$store.state.id,
        tarId: vm.items.memberId
      })
        .then(function (res) {
          console.log(res);
        })
        .catch(function (error) {
          console.log(error);
        })
      //this.$router.push({name:"chat",params:{roomId:this.roomId}})
    },
    likeBtn() {
      let memberId = this.$store.state.id;
      let postId = this.$route.query.id;
      //좋아요
      // let target = event.target.getElementById;
      if (!memberId) {
        alert('로그인이 필요합니다!');
      } else {
        if (this.likeStatus == 0) {
          this.showFullHeart();
          console.log(this.showFullHeart);
          this.likeStatus = 1; // DB로 업데이트

          this.axios
            .post("/sns/like", {
              memberId: memberId,
              targetId: postId,
            })
            .then(function (response) {
              console.log("좋아요: " + response);
            })
            .catch(function (error) {
              console.log("좋아요실패: " + error);
            });
          this.axios
            .put("/sns/like", {
              params: {
                targetId: postId,
                postId: postId,
              }
            })
            .then(function (response) {
              console.log("좋아요수 업댓성공: " + response);
            })
            .catch(function (error) {
              console.log("좋아요수 업댓실패: " + error);
            });
        } else { //좋아요 취소
          this.showEmptyHeart();
          console.log(this.showEmptyHeart);
          this.likeStatus = 0;

          this.axios
            .delete("/sns/like", {
              params: {
                memberId: memberId,
                targetId: postId,
              }
            })
            .then(function (response) {
              console.log("좋아요삭제: " + response);
            })
            .catch(function (error) {
              console.log("좋아요삭제 실패: " + error);
            });
          this.axios
            .put("/sns/like", {
              params: {
                targetId: postId,
                postId: postId,
              }
            })
            .then(function (response) {
              console.log("좋아요수 업댓성공: " + response);
            })
            .catch(function (error) {
              console.log("좋아요수 업댓실패: " + error);
            });
        }
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
};
</script>

<style scoped>
#container {
  margin: 0 auto;
  width: 550px;
}

#mdi-dots-vertical {
  float: right;
}

#image_box {
  width: 550px;
  height: 500px;
}

.box {
  display: inline-block
}

#like_box {
  width: 550;
  margin: 0 auto;
}

#full_heart {
  display: none;
}
</style>
