<template>
  <div id="container">
    <SnsSidebar></SnsSidebar>
    <h1>피드디테일</h1>
    <v-container fluid>
      <v-card class="mx-auto" min-width="600">
        <!-- 프로필 -->
        <div>
          <div class="flex">
            <v-avatar class="ml-10 my-10 mr-4" color="grey darken-1" size="64">
              <v-img
                aspect-ratio="30"
                :src="require(`@/assets/image/user/${items.profileImg}`)"
                @click="goMyFeed(items.memberId)"
              />
            </v-avatar>
            <div class="user text-overline">
              {{ items.memberId }}<br />{{
                this.$moment(items.writeDate).format("YYYY.MM.DD")
              }}
            </div>
          </div>
        </div>

        <!-- 썸네일 -->
        <v-row>
          <v-col cols="12" id="image_box">
            <v-carousel ref="myCarousel" hide-delimiters :touchless="true">
              <v-carousel-item
                v-for="(img, i) in imgs"
                :key="i"
                :aspect-ratio="4 / 3"
                :width="width"
                :src="require(`@/assets/image/sns/${img.postId}/${img.fileName}`)" />
            </v-carousel>
          </v-col>
        </v-row>
        <!-- 썸네일 끝 -->

        <!-- 좋아요, 댓글, 메세지 -->
        <v-row>
          <v-col cols="4">
            <div class="d-flex justify-start">
              <v-btn v-if="items.likeStatus === 1" icon text @click="like()">
                <v-icon color="red lighten-2">mdi-heart</v-icon>
              </v-btn>
              <v-btn v-else icon text @click="like()">
                <v-icon>mdi-heart-outline</v-icon></v-btn>{{ items.likes }}
                <v-icon>mdi-chat-outline</v-icon>{{ items.cmts }}
              <v-icon @click="send">mdi-send</v-icon>
            </div>
          </v-col>
          <v-col cols="8">

          <!-- 북마크아이콘 -->
          <div class="d-flex justify-end">
            <v-btn  v-if="mark" @click="dialog2 = true" icon class="ma-2" dark >
              <v-icon color="grey">mdi-bookmark-outline</v-icon>
            </v-btn>
            <v-btn icon v-else @click="bookmarkDel(items.postId)">
              <v-icon color="grey">mdi-bookmark</v-icon>
            </v-btn>
            <!-- 북마크아이콘 끝 -->

              <!-- 
                
                dot 메뉴
              
              -->
              <v-menu>
                <template v-slot:activator="{ on, attrs }">
                  <v-btn dark icon v-bind="attrs" v-on="on">
                    <v-icon color="grey">mdi-dots-vertical</v-icon>
                  </v-btn>
                </template>
                <v-list>
                  <v-list-item v-for="(list, i) in lists" :key="i">
                    <v-list-item-title style="cursor: pointer" @click="listBtn(i)">
                      {{ list.title }}
                    </v-list-item-title>
                  </v-list-item>
                </v-list>
              </v-menu>
            </div>
            <!-- 메뉴 끝 -->
          </v-col>
        </v-row>
        <!-- 좋아요, 댓글, 메세지 끝 -->

            <!-- 

                북마크dialog
                
             -->
             <v-dialog v-model="dialog2" max-width="500px">
            <v-card>
              <v-card-title class="justify-space-around">
                저장할 컬렉션 선택
              </v-card-title>
              <v-card-text >
                <!-- 컬렉션 선택 select_box -->
                <v-select @click="getCollectionList(items.memberId)" 
                          :items="select"
                          item-text="catgName"
                          item-value="catgId"
                          label="저장할 컬렉션을 선택하세요" v-model="selectedCollection" />
              </v-card-text>
              <v-row class="ma-4 justify-space-around">
                <v-btn color="white" dense rounded dark @click="dialog3 = !dialog3">
                  <v-icon color="#2ac187">mdi-plus</v-icon>
                </v-btn>
              </v-row>
              <v-card-actions>
                <!-- 북마크 컬렉션 지정 저장, 취소버튼 -->
                <v-row class="ma-4 justify-space-around">
                  <v-btn text @click="bookmark(selectedCollection,items.postId)">
                    저장
                  </v-btn>
                  <v-btn text @click="dialog2 = false">
                    취소
                  </v-btn>
                  <!-- 북마크 컬렉션 지정 저장,취소버튼 끝 -->
                </v-row>
              </v-card-actions>
            </v-card>
          </v-dialog>
          <!-- 컬렉션추가 dialog in dialog -->
          <v-dialog v-model="dialog3" max-width="500px">
            <v-card>
              <v-card-title class="justify-space-around">
                <span>새 컬렉션 생성</span>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-row>
                      <v-col cols="12">
                          <v-text-field  v-model="catgName" label="*컬렉션이름을 입력해주세요!" required />
                          <!-- @change="inputE()" -->
                        </v-col>
                  </v-row>
                </v-container>
              </v-card-text>
              <v-card-actions class="mx-auto">
                <v-row class="ma-4 justify-space-around">
                  <v-btn color="blue darken-1" text @click="createCollection(memId)">
                    저장
                  </v-btn>
                  <v-btn color="primary" text @click="dialog3 = false">
                    취소
                  </v-btn>
                </v-row>
              </v-card-actions>
            </v-card>
          </v-dialog>
          <!-- 북마크 끝 -->

        <!-- 

          내용

         -->
        <div class="contents">
          <v-card-text class="text--primary">
            <p id="content_box">{{ items.content }}</p>
          </v-card-text>
        </div>
        <!-- 내용 끝 -->


        <!-- 

          해시태그

         -->
        <v-chip-group id="hashtagGroup">
          <v-chip v-for="hashtag in hashtags" :key="hashtag"
            :color="`${colors[nonce - 1]} lighten-3`"
            @click="search($event)"
            dark label small>
            #{{ hashtag }}
          </v-chip>
        </v-chip-group>
        <br />

        <!-- 

          댓글

         -->
        <v-col cols="12">
          <CmtReg :postid="postId" :targetId="items.memberId" />
        </v-col>
      </v-card>
    </v-container>
  </div>
</template>
<script>
import SnsSidebar from "@/components/sns/Common/SnsSidebar.vue";
import CmtReg from "@/components/sns/FeedDetail/CmtReg.vue";

export default {
  name: "FeedDetail",
  components: { SnsSidebar, CmtReg },
  data() {
    return {
      //carousel
      move: [],
      drag: false,
      touch: false,
      imgs: [], //이미지 저장
      width: 800,
      roomId: 0,
      //게시글관련
      items: [], //게시글 정보 저장
      hashtags: [], //해시태그 배열 split 후 저장
      feeds: [], //해시태그 검색 정보 저장
      targetType: 2,
      memId: this.$store.state.id,
      postId: Number,
      show: true,
      targetId:"",
      colors: ["teal", "orange", "green", "purple", "indigo", "cyan"], //tag color
      nonce: 1,
      lists: [
        //메뉴 리스트
        { title: "수정" },
        { title: "삭제" },
        { title: "게시글 공유" },
      ],
      //북마크
      catgName: "", //카테고리이름
      thumbnail:"", //썸네일
      mark:true, //북마크 아이콘
      dialog: false, //
      dialog2: false,
      dialog3: false,
      select: [], //유저의 기존 컬렉션,
      selectedCollection:"", //북마크를 저장할 컬렉션
      catgName: "",//새로 생성할 컬렉션 이름
      notifications: false,
      sound: true,
      widgets: false,
    };
  },
  setup() {},
  created() {
    this.postId = this.$route.query.postId//라우터로 넘어온 postId
    this.showDetail();
    this.detailImg();
    this.getCollectionList(this.memId);
  },
  mounted() {},
  unmounted() {},
  methods: {
    detailImg() {
      this.axios("/sns/user/feed_detail_img/" + this.postId, {
        params: {
          memberId: this.memId,
        },
      })
        .then((res) => {
          this.imgs = res.data;
          console.log("이미지 로딩 성공!");
        })
        .catch((err) => {
          alert(err);
        });
    },
    //게시글 상세 로드
    showDetail() {
      this.axios("/sns/user/feed_detail/" + this.postId, {
        params: {
          memberId: this.memId,
        },
      })
        .then((res) => {
          this.items = res.data;
          if (this.items.hashtag != null) {
            let str = this.items.hashtag; //%%,%%,%% 형태
            let hashtag = str.split(","); //해시태그 자르기
            this.hashtags = hashtag; //자른 해시태그들 hashtags에 담기
          }
          console.log("상세페이지 접근 성공!");
        })
        .catch((err) => {
          alert(err);
        });
    },
    //DOT LIST
    listBtn(i) {
      if (i == 0) {
        //게시글 수정
        console.log("수정하기");
        this.editPost();
      }
      if (i == 1) {
        //게시글 삭제
        console.log("삭제하기");
        this.feedSwal(this.items.postId);
      }
      if (i == 2) {
        //게시글 공유
        console.log("공유하기");
        this.sharePost();
      }
    },
    //게시글 수정*******************************

  
    //게시글 삭제
    deletePost(postId) {
         this.axios
        .delete("/sns/myfeed/" + postId)
        .then((res) => {
          console.log("댓글 삭제 성공! " + res);
          this.goMyFeed(this.items.memberId);
        })
        .catch((err) => {
          alert(err);
        });
    },
    //게시글 공유
    sharePost() {},
    //게시글 삭제
    feedSwal(postId) {
      this.$swal({
        title: "정말 삭제할까요?",
        text: "삭제된 게시글은 복구가 불가합니다🙏",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#2ac187",
        cancelButtonColor: "#d33",
        cancelButtonText: "취소",
        confirmButtonText: "네, 삭제할게요!",
      }).then((result) => {
        if (result.isConfirmed) {
          this.deletePost(postId);
          this.$swal("삭제 완료!", "게시글이 삭제되었습니다.", "success");
        }
      });
    },
    //해시태그 클릭 검색
    search(e) {
      let getHashtag = e.target.innerText; //선택한 해시태그
      let hashtag = getHashtag.slice(1); //# 잘라내기
      console.log(hashtag);
      this.axios("/sns/search/hashtag", {
        params: {
          hashtag: hashtag,
        },
      })
        .then((res) => {
          this.feeds = res.data; //해시태그 검색결과 담기
          console.log("AXIOS SUCCESS");
          this.goSearch(this.feeds, this.show); // 메인 ->컴색컴포넌트
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //검색페이지 이동
    goSearch(feeds, show) {
      console.log("main->searchPage실행" + feeds);
      this.$router.push({
        name: "snsmain",
        params: { hashtagResult: feeds, showing: show },
      });
    },

    //채팅방 이동
    send() {
      this.$router.push({ name: "chat", params: { roomId: this.roomId } });
    },

    //좋아요
    like() {
      //좋아요 알림
      const noticeContent = {
        myId: this.$store.state.id,
        targetId: this.items.memberId,
        contentType: 0,
        postId: this.postId,
        likeStatus: this.items.likeStatus,
        boardType: 0,
      };
      this.stompClient.send(
        "/app/NoticeSns",
        JSON.stringify(noticeContent),
        (res) => {
          console.log(res);
        }
      );
      //멤버검증
      if (this.memId === null || this.memId === "") {
        this.$swal('로그인부터 부탁드립니다🙏');
        return;
      } else {
        //DB Jjim insert
        this.axios
          .post("/sns/like", {
            targetId: this.postId,
            memberId: this.memId,
          })
          .then((res) => {
            if (this.items.likeStatus == 0) {
              //좋아요 상태가 0이면 개수++,상태를 1로
              ++this.items.likes;
              this.items.likeStatus = 1;
            } else if (this.items.likes > 0) {
              //좋상이 1이고 좋개가 0이 아니면 개수--,상태를 0으로
              --this.items.likes;
              this.items.likeStatus = 0;
            }
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    //북마크
    bookmark(selectedCollection,postId){
      if (this.memId === null || this.memId === "") {
        this.$swal('로그인부터 부탁드립니다🙏');
        return;
      } else {
        //카테고리 아이디 선택한값 받아오기
        this.axios
          .post("/sns/collection/bookmark", {
            catgId: selectedCollection,
            postId: postId,
          })
          .then((res) => {
            this.mark = !this.mark
            this.dialog2 = !this.dialog2
            console.log("북마크 성공!")
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    //북마크 삭제
    bookmarkDel(postId){
      this.axios
        .delete("/sns/collection/bookmark/"+ postId)
        .then((res) => {
          this.mark = !this.mark
          console.log("북마크 삭제 성공! " + res);
        })
        .catch((err) => {
          alert(err);
        });
    },
    //컬렉션 리스트 호출
    getCollectionList(memberId){
      this.axios('/sns/collection/' + memberId, {
          params: {
              memberId: memberId,
          }
      }).then(res => {
          this.select = res.data;
          console.log("컬렉션리스트 호출 성공!")
      }).catch(err => {
          alert(err);
      });
    },
    //컬렉션생성
    createCollection(memberId){
        if (this.catgName == "" || this.catgName == undefined){
            this.$swal('컬렉션 이름을 입력해주세요🙏')
            return;
        }
        if(this.catgName.length >11){
          this.$swal('이름은 10글자 이내로 입력해주세요🙏')
            return;
        }
        this.dialog = !this.dialog;
        // let thumbnailImg = this.items.postId+"/"+this.items.thumbnail;
        this.axios.post('/sns/collection', {
            memberId : memberId,
            catgName : this.catgName,
            thumbnail : '기도.jpg'
          }).then(res => {
            console.log(thumbnailImg);
            console.log("컬렉션생성 성공!"+res);
            this.dialog3 = !this.dialog3;
            this.getCollectionList(memberId);
        }).catch(err => {
            alert(err);
        });
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
    //내 피드로 이동
    goMyFeed(member) {
      this.$router.push({ name: "snsUserFeed", query: { memId: member } });
    },
  },
};
/*
    //게시글 수정
    editPost() {
      console("게시글 수정 실행!");
      // if (this.editedContent == "" || this.editedContent == undefined){
      //   this.$swal('내용 입력부터 부탁드립니다🙏')
      //   return;
      // }
      // this.axios.put('/sns/myfeed/' + this.postId, {
      //       content : this.editedContent,
      //   }).then(res => {
      //     console.log("게시글수정 성공! "+res);
      //   }).catch(err => {
      //     console.log(err)
      //   });
    },
*/
</script>

<style scoped>
#container {
  margin: 0 auto;
  width: 30%;
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
  padding: 0 20px;
}

#hashtag {
  color: navy;
  cursor: pointer;
}

div.user.text-overline {
  display: inline-block;
}

#hashtagGroup {
  margin-left: 10px;
}

.container {
  width: 85%;
  margin-top: 30px;
}

.user {
  display: inline-block;
  margin-left: 5px;
  margin-top: 40px;
}

.flex {
  display: flex;
  height: 150px;
}
</style>
