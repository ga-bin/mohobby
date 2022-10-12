<template>
  <div id="container">
    <SnsSidebar></SnsSidebar>
    <h1>피드디테일</h1>
    <v-container fluid>
      <v-card class="mx-auto" min-width="600">
        <v-flex>
          <v-row dense>
            <v-col cols="3" md="3" sm="2" align-self="center">
              <!-- 프로필 -->
              <v-avatar class="ml-10 my-10 mr-4" color="grey darken-1" size="64">
                <!-- 이미지부분 -->
                <v-img aspect-ratio="30" :src="require(`@/assets/image/user/${items.profileImg}`)" />
              </v-avatar>
            </v-col>
            <v-col cols="8" align-self="center">
              <!-- 아이디 -->
              {{items.memberId}}<br>
              <!-- 년,월,일 -->
              {{this.$moment(items.writeDate).format('YYYY.MM.DD') }}
            </v-col>
            <!-- 게시글 설정 -->
            <v-col col="1" md="1" sm="1" id="mdi-dots-vertical" align-self="center">  
              <v-spacer></v-spacer> 
              <v-btn icon>
                <v-icon>mdi-dots-vertical</v-icon>
              </v-btn>
            </v-col> 
          </v-row>
        </v-flex>

        <!-- 썸네일 -->
        <v-row>
          <v-col cols="12" id="image_box">
              <v-carousel ref="myCarousel" hide-delimiters :touchless="ture">
                <v-carousel-item
                  :aspect-ratio="4 / 3"
                  :width="width"
                  :src="require(`@/assets/image/sns/${items.thumbnail}`)"
                ></v-carousel-item>
              </v-carousel>
          </v-col>
        </v-row>

        <!-- 좋아요, 댓글, 메세지 -->
        <v-row>
          <v-col cols="4">
            <v-btn v-if="items.likeStatus === 1" icon text @click="like()">
              <v-icon color="red lighten-2">mdi-heart</v-icon>
            </v-btn>
            <v-btn v-else icon text @click="like()">
              <v-icon>mdi-heart-outline</v-icon>
            </v-btn>{{ items.likes }}
            <v-icon>mdi-chat-outline</v-icon>{{ items.cmts }}
            <v-icon @click="send">mdi-send</v-icon>
          </v-col>
        </v-row>

        <!-- 내용 -->
        <v-row>
          <v-col cols="12">
            <p id="content_box">{{ items.content }}</p>
          </v-col>
        </v-row>
        
        <!-- 해시태그 -->
        <v-col cols="12" v-for="hashtag in hashtags" :key="hashtag">
          <div @click="search($event)"><span id="hashtag"># {{ hashtag }}</span></div>
        </v-col>

        <br>
        <v-col cols="12">
        <Comment></Comment>
      </v-col>
      </v-card>
    </v-container>
  </div>
</template>
<script>
import SnsSidebar from "@/components/sns/Common/SnsSidebar.vue";
import Comment from "@/components/sns/FeedDetail/Comment.vue";

export default {
  name: "snsFeedDetail",
  components: { SnsSidebar, Comment },
  data() {
    return{
      width: 800,
      roomId: 0,
      items: [], //게시글 정보 저장
      hashtags: [], //해시태그 배열 split 후 저장
      feeds : [], //해시태그 검색 정보 저장
      targetType: 2,
      memId : "",
      postId : "",
      likeReq: {targetId : 7,
                memberId:'user1',
              targetType:1},
    }
  },
  setup() { },
  created() {
    this.postId = this.$route.query.id;
    console.log("디테일페이지입니다");
    console.log("this.$store.state.id" + this.$store.state.id);
      this.memId = this.$store.state.id;
      if (this.$store.state.id != null || this.$store.state.id != "");
    this.showDetail();
  },
  mounted() {},
  unmounted() {},
  methods: {

    //게시글 상세 로드
    showDetail() {
      let postId = this.$route.query.id;
      this.axios('/sns/user/feed_detail/' + postId, {
        params: {
          memberId: this.memId,
        }
      }).then(res => {
        this.items = res.data;
        let str = this.items.hashtag;
        let hashtag = str.split(',');
        this.hashtags = hashtag;
        console.log(this.items);
      }).catch(err => {
        console.log(err);
      });
    },

    //해시태그 클릭시 검색 이벤트 발생
    search(e){
            let getHashtag = e.target.innerText;
            let hashtag = getHashtag.slice(1);
            this.axios('/sns/search/hashtag', {
                params : {
                    hashtag : hashtag
                }
            }).then(res => {
                this.feeds = res.data;
                this.goSearchPage(this.feeds);
                console.log("검색성공:"+this.feeds);

            }).catch(err =>{
                console.log(err);
            });
    },

    //검색페이지 이동
    goSearchPage(feeds){
      console.log("goSearchPage실행"+feeds);
      this.$router.push({ name: "snsmain", params: {  hashtagResult: feeds } })
    },

    //채팅방 이동
    send() {
      this.$router.push({ name: "chat", params: { roomId: this.roomId } });
    },

    //좋아요
//     like() {
//       this.postId = this.$route.query.id;
//       const vm = this;
//       console.log(this.items.likeStatus)
//       if (this.memId === null || this.memId === "") {
//         alert('로그인이 필요합니다!');
//         return;
//       } else {
//           this.items.likes++;
//           //DB Jjim insert
//           this.axios('/sns/like', {
//         method: "POST",
//         headers: {
//           "Content-Type": "application/json; charset=utf-8",
//         },
//         body: JSON.stringify({
//           "memberId": this.memId,
//           "targetId": vm.postId,
//           "targetType": this.targetType,
//         })
//       })
// ``````.then(function (response) {
//               console.log("좋아요성공" + response);
//             })
//             .catch(function (error) {
//               console.log("좋아요실패 " + error);
//             })
//             .finally(function() {              
//             this.axios
//               .put("/sns/like", {
//               params: {
//                 postId: vm.postId,
//               }
//             })
//             .then(function (response) {
//               console.log("좋아요업댓" + response);
//             })
//             .catch(function (error) {
//               console.log("업댓실패" + error);
//             });
//           })
//         }
//     },

    //좋아요
    like() {
      if (this.memId === null || this.memId === "") {
        alert('로그인이 필요합니다!');
        return;
      } else {
          //DB Jjim insert
          this.axios.post('/sns/like', {
                targetId : this.postId,
                memberId : this.memId
            }).then(res => {
              console.log(res);
              // window.location.assign('/used/detail?pNo='+this.$route.query.pNo);
            }).catch(err => {
              console.log(err)
            });
          }
        }
    },

    //사진 넘기기
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
  }

</script>

<style scoped>
#container {
  margin: 0 auto;
  width: 30%
}

#mdi-dots-vertical {
  float: right;
}

#image_box {
  width: 550px;
  height: 500px;
}

.box {
  display: inline-block;
}

#like_box {
  width: 550;
  margin: 0 auto;
}


#content_box {
  padding : 0 20px;
}

#hashtag{
  color:navy;
  cursor: pointer;
}
div.user.text-overline{
  display:inline-block;

}
</style>
