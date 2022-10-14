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
                v-for="(img,i) in imgs" :key="i"
                  :aspect-ratio="4 / 3"
                  :width="width"
                  :src="require(`@/assets/image/sns/${img.postId}/${img.fileName}`)"
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
        <v-chip-group id="hashtagGroup">
            <v-chip v-for="hashtag in hashtags" :key="hashtag"
              :color="`${colors[nonce - 1]} lighten-3`"
              @click="search($event)"
              dark
              label
              small>
              #{{ hashtag }}
            </v-chip>
        </v-chip-group>
        <br>
        <v-col cols="12">
          <CmtReg :postId = "postId"></CmtReg>
          <CmtList></CmtList>
      </v-col>
      </v-card>
    </v-container>
  </div>
</template>
<script>
import SnsSidebar from "@/components/sns/Common/SnsSidebar.vue";
import CmtReg from "@/components/sns/FeedDetail/CmtReg.vue";
import CmtList from "@/components/sns/FeedDetail/CmtList.vue";

export default {
  name: "snsFeedDetail",
  components: { SnsSidebar, CmtReg, CmtList },
  data() {
    return{
      width: 800,
      roomId: 0,
      items: [], //게시글 정보 저장
      imgs: [], //이미지 저장
      hashtags: [], //해시태그 배열 split 후 저장
      feeds : [], //해시태그 검색 정보 저장
      targetType: 2,
      memId : "",
      postId : "",
      likeReq: {targetId : 7,
                memberId:'user1',
              targetType:1},
      show:true,
      colors: ['teal', 'orange', 'green', 'purple', 'indigo', 'cyan'], //tag color
      nonce: 1,
    }
  },
  setup() { },
  created() {
    this.postId = this.$route.query.id;
    this.memId = this.$store.state.id;
    this.showDetail();
    this.detailImg();
  },
  mounted() {},
  unmounted() {},
  methods: {
    detailImg() {
      this.axios('/sns/user/feed_detail_img/' + this.postId, {
        params: {
          memberId: this.memId,
        }
      }).then(res => {
        this.imgs = res.data;
        console.log("이미지 로딩 성공!");
      }).catch(err => {
        console.log(err);
      });
    },
    //게시글 상세 로드
    showDetail() {
      this.axios('/sns/user/feed_detail/' + this.postId, {
        params: {
          memberId: this.memId,
        }
      }).then(res => {
        this.items = res.data;
        if(this.items.hashtag != null){
            let str = this.items.hashtag; //%%,%%,%% 형태
            let hashtag = str.split(','); //해시태그 자르기
            this.hashtags = hashtag; //자른 해시태그들 hashtags에 담기
          }
        console.log("상세페이지 접근 성공!");
      }).catch(err => {
        console.log(err);
      });
    },

    //해시태그 클릭 검색
    search(e){
            let getHashtag = e.target.innerText; //선택한 해시태그
            let hashtag = getHashtag.slice(1); //# 잘라내기
            console.log(hashtag);
            this.axios('/sns/search/hashtag', {
                params : {
                    hashtag : hashtag
                }
            }).then(res => {
                this.feeds = res.data; //해시태그 검색결과 담기
                console.log("AXIOS SUCCESS")
                this.goSearch(this.feeds, this.show);// 메인 ->컴색컴포넌트

            }).catch(err =>{
                console.log(err);
            });
    },

    //검색페이지 이동
    goSearch(feeds, show){
      console.log("main->searchPage실행"+feeds);
      this.$router.push({ name: "snsmain", params: {  hashtagResult: feeds, showing: show } })
    },

    //채팅방 이동
    send() {
      this.$router.push({ name: "chat", params: { roomId: this.roomId } });
    },

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
              if(this.items.likeStatus == 0){
                this.items.likes++;
                this.items.likeStatus=1;
              }else{
                this.items.likes--;
                this.items.likeStatus=0;
              }
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
#hashtagGroup {
  margin-left: 10px;
}
</style>
