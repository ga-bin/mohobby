<template>
  <div>
    <div v-if="user == true">
      <v-card class="mx-auto" width="800" min-height="520">
        <v-toolbar color="#2ac187" dark> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' {{ searchResult }} '에 대한 검색결과입니다 </v-toolbar>
          <v-list three-line>
            <div v-for="(user, i) in userInfo" :key="i">
              <template v-if="userResult == true">
                <v-list-item>
                  <v-list-item-avatar>
                    <v-img @click="goFeed(user.memberId)" :src="require(`@/assets/image/user/${user.profileImg}`)" />
                  </v-list-item-avatar>
                  <v-list-item-content>
                    <v-list-item-title v-html="user.memberId" />
                    {{user.nickname}}
                    <v-list-item-subtitle v-html="user.nickname" />
                  </v-list-item-content>
                </v-list-item>
              </template>
            </div>
          </v-list>
          <div class="wrap_box" v-if="noResult == true" >
            <v-img class="mx-auto mt-10 mb-10" width="100" src="@/assets/image/sns/default/warn.jpg" alt="no_result"></v-img>
            <div class="text_box">
              <div class="text_box_title"><span class="point">'{{ searchResult }}'</span> 에 대한 검색결과가 없습니다. </div>
              <ul>
                <li>
                  단어의 철자가 정확한지 확인해 주세요🙏
                </li>
                <li>
                  한글을 영어로 혹은 영어르르 한글로 입력했는지 확인해주세요🙏
                </li>
                <li>
                  검색어의 단어 수를 줄이거나, 보다 일반적인 검색어로 다시 검색해주세요🙏
                </li>
                <li>
                  해시태그 검색일 경우 앞에 '#'가 빠졌는지 확인해주세요🙏
                </li>
              </ul>
            </div>
          </div>
        </v-card>
      </div>
      <div v-if="hashtagResult == true">
        <v-container fluid>
          <v-row>
            <v-col v-for="(hashtag,i) in hashtags" :key="i" class="d-flex child-flex" cols="3">
              <div @click="goFeedDetail(hashtag.postId, hashtag.memberId)"> 
                <v-img :src="require(`@/assets/image/sns/${hashtag.postId}/${hashtag.thumbnail}`)"
                      class="white--text align-end rounded-sm" aspect-ratio="1.2"
                  gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)">
                  <div class="ml-6 mb-5">
                    <v-icon color="#ededed">mdi-heart</v-icon><span>{{hashtag.likes}}</span> 
                    <v-icon class="ml-2" color="#ededed">mdi-chat-outline</v-icon><span>{{hashtag.cmts}}</span>
                  </div>
                  <template v-slot:placeholder>
                    <v-row class="fill-height ma-0" align="center" justify="center">
                      <v-progress-circular indeterminate color="grey lighten-5" />
                    </v-row>
                </template>
              </v-img>
            </div>
          </v-col>
        </v-row>
      </v-container>
    </div>
  </div>
</template>
<script>
  import CmtReg from "@/components/sns/FeedDetail/CmtReg.vue";
  export default {
    name: "Follower",
    components: { CmtReg },
    props:{
      searchResult: String,
    },
    data() {
      return {
          userInfo: [],
          hashtags : [],
          keyword: "",
          noResult: false,
          user: true,
          userResult: false,
          hashtagResult: false,

          //carousel
          move: [],
          drag: false,
          touch: false,
          imgs: [], //이미지 저장
          width: 800,
          

          //게시글관련
          items: [], //게시글 정보 저장
          hashtags: [], //해시태그 배열 split 후 저장
          feeds: [], //해시태그 검색 정보 저장
          targetType: 2,
          memberId: this.$store.state.id, //세션아이디
          writer: this.$route.query.writer, //글쓴이 아이디
          postId: this.$route.query.postId,
          show: true,
          targetId: "",
          colors: ["teal", "orange", "green", "purple", "indigo", "cyan"], //tag color
          nonce: 1,
          lists: [
            //메뉴 리스트
            { title: "수정" },
            { title: "삭제" },
            { title: "비밀글로" },
          ],
          roomId: 0, //채팅

          //북마크
          dialog2: false, //컬렉션 선택 dialog
          dialog3: false, //컬렉션 추가 dialog
          catgName: "", //카테고리이름
          thumbnail: "", //썸네일
          mark: 0, //북마크 아이콘
          select: [], //유저의 기존 컬렉션,
          selectedCollection: "", //북마크를 저장할 컬렉션
          catgName: "", //새로 생성할 컬렉션 이름
          notifications: false,
          sound: true,
          widgets: false,
          userOneInfo : [],


          hashtagArray:[],
      };
    },
    created() {

      this.keyword = this.searchResult;
        this.searchMem(this.keyword);


    },
    watch: {

      searchResult() {
        this.searchMem(this.searchResult);
        }
      },

        
    methods: {
    //유저 피드로 이동
    goFeed(userId) {
      this.$router.push({ path: "/snsUserFeed", query: { userId: userId } });
    },


    //검색
    searchMem(keyword) {
      console.log("받아온 검색어!!!!!!!!!!!!!!!!!"+ keyword);
      if(keyword.includes("#")){ //'#'가 포함된 검색어가 들어오면 해시태그 검색을 실행
        this.search(keyword);
        console.log("키워드 확인")
      }else{ //나머지는 유저검색
        this.axios('/sns/search/user', {
              params : {
                memberId : keyword
              }
          }).then(res => {
              this.userInfo = res.data;
              if(keyword == "" || this.userInfo.length <1){
                this.noResult = true;
                this.userResult = false;
                console.log("검색어: " + this.searchResult);
                console.log("검색결과 없음!")
              } else {
                this.noResult = false;
                this.userResult = true;
                console.log("검색어: " + this.searchResult);
                console.log("검색결과 출력 성공!");
              }
          }).catch(err =>{
              console.log(err);
          });
      }
      },


    //해시태그 클릭 검색
    search(keyword) {
      let hashtag = keyword.slice(1); // # 잘라내기
      console.log("넘어온해시태그: "+hashtag);
      this.axios("/sns/search/hashtag", {
        params: {
          hashtag: hashtag,
        },
      })
        .then((res) => {
          this.hashtags = res.data; // 해시태hashtags그 검색결과 담기
          this.user = false;
          this.hashtagResult = true;
          console.log(this.hashtags);
          console.log("AXIOS SUCCESS");
          // this.hashtags.forEach((hashtag, i) => {
            // console.log(+ hashtag.memberId); //hashtag검색결과 정상출력
          //   this.hashtagArray.push({"postId": hashtag.postId, "hashtag":hashtag.memberId});
            
          //   this.showDetail(hashtag.postId, hashtag.memberId);
          //   this.detailImg(hashtag.postId);
          // });
          // console.log(this.hashtagArray);
          // for(let i=0; i<this.hashtags.length; i++){
          //   console.log(this.showDetail(this.hashtags[i].postId, this.hashtags[i].memberId));
          // }
          //노출여부
          
        })
        .catch((err) => {
          console.log(err);
        });
    },


    /*
        
        게시글

    */
    //게시글 상세 로드
    showDetail(postId, writer) {
      this.axios("/sns/user/feed_detail/" + postId, {
        params: {
          memberId: writer,
        },
      })
        .then((res) => {
          if(this.confirmMember(this.memberId) == true ){
            console.log("로그인세션을 확인합니다");
            this.getBookmarkStatus(postId);
            this.getCollectionList(this.memberId);
          }
          console.log();
          this.items = res.data;
          console.log(this.items.likeStatus);
          if (this.items.hashtag != null) {
            let str = this.items.hashtag; //%%,%%,%% 형태
            let hashtag = str.split(","); //해시태그 자르기
            this.hashtags = hashtag; //자른 해시태그들 hashtags에 담기
          }
          console.log("상세페이지 접근 성공!");
        })
        .catch((err) => {
          alert("게시글호출 실패" + err);
        });
    },


    //게시글 이미지 로드
    detailImg(postId) {
      this.axios("/sns/user/feed_detail_img/" + postId)
        .then((res) => {
          this.imgs = res.data;
          console.log("이미지 로딩 성공!");
        })
        .catch((err) => {
          console.log(err);
        });
    },



      //카카오톡 공유하기
  sendLink() {
    Kakao.Link.sendDefault({
      objectType: 'feed',
      content: {
        title: this.items.memberId + ' 님 모하비 피드',
        description: this.items.content,
        imageUrl: 'https://ifh.cc/g/H0FFVT.jpg',   
        link: {
          webUrl: 'http://localhost:8081/snsFeedDetail?writer=' + this.writer +'&postId=' + this.postId,
        },
      },
      // social: {
      //   likeCount: this.items.likes,  //좋아요 수
      //   commentCount: this.items.cmts,  //댓글 수
      // },
      buttons: [
        {
          title: '모하비에서 확인하기',  //첫 번째 버튼 
          link: {
            mobileWebUrl: 'http://localhost:8081/snsFeedDetail?writer=' + this.writer +'&postId=' + this.postId,  //버튼 클릭 시 이동 링크
            webUrl: 'http://localhost:8081/snsFeedDetail?writer=' + this.writer +'&postId=' + this.postId,
          },
        },
      ],
    })
  },
  //   // Kakao.init('0e317fda8cca7ac1d7e440fc807131bd'); // 사용하려는 앱의 JavaScript 키 입력



  //세션유무 검증
  confirmMember(memberId){  
    if(memberId){
      console.log("true");
      return true;
    }
    else{
      console.log("false");
      return false;
    }
  },
  //로그인 검증 모달
  loginConfirm(){
    this.$swal({
      title: "로그인하셔야 가능하세요🙏",
      text: "🙏로그인화면으로 이동부탁드립니다🙏",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#2ac187",
      cancelButtonColor: "#d33",
      cancelButtonText: "취소",
      confirmButtonText: "이동",
    }).then((result) => {
      if (result.isConfirmed) {
        this.$router.push({ path: "login" });
      }
    });
  },



    //게시글 수정*******************************



    //게시글 삭제 검증
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
    },


    // //검색페이지 이동
    // goSearch(feeds, show) {
    //   console.log("main->searchPage실행" + feeds);
    //   this.$router.push({ name: "snsmain", params: { hashtagResult: feeds, showing: show },
    //   });
    // },


    //채팅방 이동
    send() {
      let vm = this;
      this.axios
        .get("/getSnsChatRoomNo", {
          params: { myId: this.$store.state.id, targetId: this.items.memberId },
        })
        .then(function (res) {
          console.log(res.data.vroomNo);

          vm.$router.push({
            name: "chat",
            params: { getRoomId: res.data.vroomNo },
          });
        })
        .catch(function (err) {
          console.log(err);
        });
    },

    //좋아요
    like(memberId, postId) {
      //좋아요 알림
      const noticeContent = {
        myId: this.$store.state.id,
        targetId: this.items.memberId,
        contentType: 0,
        postId: postId,
        likeStatus: this.items.likeStatus,
        noticeType: 0,
      };
      this.stompClient.send(
     
        "/app/Notice",
        JSON.stringify(noticeContent),
        (res) => {
          console.log("!!!!!!!!!!!!!!!!!!!!!!!!!!!"),
          console.log(res);
        }
      );
      //좋아요 유저검증
      if(this.confirmMember(memberId) == false){
        this.loginConfirm();
      }else{
        //상태값에따라 좋아요 or 취소처리
        this.axios
          .post("/sns/like", {
            targetId: postId,
            memberId: memberId,
          })
          .then((res) => {
            if (this.items.likeStatus == 0) {
              //좋아요 상태가 0이면 개수++,상태를 1로
              ++this.items.likes;
              this.items.likeStatus = 1;
              console.log("좋아요 완료");
            } else if (this.items.likes > 0) {
              //좋상이 1이고 좋개가 0이 아니면 개수--,상태를 0으로
              --this.items.likes;
              this.items.likeStatus = 0;
              console.log("좋아요 취소");
            }
          })
          .catch((err) => {
            console.log(err);
          });
       }
    },


    /*

      북마크
    
      */


    //북마크 전 로그인검증
    markLogin(memberId,e){
      if(this.confirmMember(memberId) == true && e==1){
        this.dialog2 = true;
      }else if(this.confirmMember(memberId) == true && e==2){
        Kakao.Share.sendDefault();
      }else{
        this.loginConfirm();
      }     
    },



    //북마크상태조회
    getBookmarkStatus(postId) {
      this.axios("sns/collection/bookmark/isBookmark/" + postId, {
        params: {
          memberId: this.memberId,
        },
      })
        .then((res) => {
          console.log("북마크상태 조회 성공!");
          console.log("북마크상태: "+res.data);
          this.mark = res.data; //변수에 0(북마크X), 1(북마크O)이 담김
          console.log("북마크 상태"+this.mark);
          
        })
        .catch((err) => {
          alert("게시글호출 실패" + err);
        });
    },


    //북마크
    bookmark(selectedCollection, postId) {
        this.axios
          .post("/sns/collection/bookmark", {
            catgId: selectedCollection,
            postId: postId,
            thumbnail: this.items.thumbnail,
          })
          .then((res) => {
            this.getBookmarkStatus(postId);
            this.dialog2 = false;
            console.log("북마크 성공!");
          })
          .catch((err) => {
            console.log(err);
          });
    },


    //컬렉션 리스트 호출
    getCollectionList(memberId) {
      this.axios("/sns/collection/" + memberId)
        .then((res) => {
          this.select = res.data;
          console.log(this.select);
          console.log("컬렉션리스트 호출 성공!");
        })
        .catch((err) => {
          alert("컬렉션호출 실패" + err);
        });
    },


    //북마크 삭제
    bookmarkDel(postId, memberId) {
      this.axios
        .delete("/sns/collection/bookmark/" + postId + '/' + memberId)
        .then((res) => {
          this.getBookmarkStatus(postId);
          console.log("북마크 삭제 성공! " + res);
        })
        .catch((err) => {
          alert(err);
        });
    },


    //컬렉션생성
    createCollection(memberId) {
      if (this.catgName == "" || this.catgName == undefined) {
        this.$swal("컬렉션 이름을 입력해주세요🙏");
        return;
      }
      if (this.catgName.length > 11) {
        this.$swal("이름은 10글자 이내로 입력해주세요🙏");
        return;
      }
      this.dialog = !this.dialog;
      this.axios
        .post("/sns/collection", {
          memberId: memberId,
          catgName: this.catgName,
          thumbnail: "",
        })
        .then((res) => {
          // console.log(thumbnailImg);
          console.log(res);
          this.catgName = "";
          this.dialog3 = !this.dialog3;
          this.getCollectionList(memberId);

        })
        .catch((err) => {
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


    //유저 피드로 이동
    async goMyFeed(userId) {
      await this.getOneUser(userId);
      if (this.userOneInfo.role == 4) {
        this.$swal.fire("관리자에 의해 접근 유저 프로필입니다.");
      } else {
         this.$router.push({ path: "/snsUserFeed", query: { userId: userId } });
      }
    },
     async getOneUser(userId) {
      const vm = this;
      await this.axios({
            url: "/member/" + userId,
            method: "get",
          })
          .then(function (response) {
            vm.userOneInfo = response.data;
          })
          .catch(function (error) {
            console.log(error);
          });
    },

    }
  }
  </script>
  
  <style>
    .wrap_box {
      margin: 80px auto;
    }
    .text_box{
      margin-left: 145px;
      margin-bottom: 200px;
      width: 510px;
      vertical-align: middle;
      color: #a3a3a3ee;
      font-weight: 500;
    }
    .text_box_title{
      margin-left: 20px;
      margin-bottom: 3px;
      color: #858484ee;
    }
    .point{
      color: rgba(255, 94, 0, 0.932);
      font-weight: bold;
    }
    li {
      padding: 1px;
      font-size: 15px;
    }
  </style>
