<template>
  <v-app-bar app color="white" elevate-on-scroll elevation="4">

    <v-toolbar-title @click="$router.push('/').catch(() => { })" style="cursor: pointer; font-weight: bolder;">Mohobby
    </v-toolbar-title>
    <v-spacer />
    <p style="font-weight: bolder;" v-if="this.$store.state.id">{{ this.$store.state.id }}님, 오늘 모하비?</p>
    <v-spacer />
    <v-btn text class="ml-2" to="/snsmain" style="font-weight: bolder;">sns</v-btn>
    <v-btn text class="ml-2" to="/class/list/all" style="font-weight: bolder;">강의</v-btn>
    <v-btn text class="ml-2" to="/moimmain" style="font-weight: bolder;">소모임</v-btn>
    <v-spacer />
    <v-col lg="4" cols="12">
      <v-form class="mt-5">
        <v-text-field v-model="searchText" @keydown.enter.prevent='search()' rounded outlined dense
          placeholder="Search Here" append-icon="mdi-magnify" />
      </v-form>
    </v-col>
    <v-spacer />
    <!-- <v-btn v-if="this.$store.state.id" icon/> -->
    <v-menu offset-y v-if="this.$store.state.id">
      <template style="margin-right:30px;" v-slot:activator="{ on, attrs }">
        <span id="bellspan" v-bind="attrs" v-on="on" style="cursor: pointer; margin-right:10px;">
          <v-badge style="cursor: pointer;" v-if="noticeCount != 0" offset-x="10" offset-y="10" color="red"
            :content="noticeCount">
            <v-icon style="cursor: pointer; ">mdi-bell</v-icon>
          </v-badge>
        </span>
      </template>
      <v-list three-line width="400" height="400">
        <v-toolbar color="#2ac187" dark>
          <!-- <v-app-bar-nav-icon></v-app-bar-nav-icon> -->
          <v-toolbar-title>{{ this.$moment().format("YYYY-MM-DD") }}</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-btn color="white" class="black--text" rounded @click.prevent.stop="deleteAllNotice()">
            전체삭제
          </v-btn>
        </v-toolbar>

        <template v-for="(item, index) in items">
          <div style="background-color: white">
            <v-divider v-if="item.divider" :key="index" :inset="item.inset"></v-divider>
            <v-list-item v-else :key="item.title" @click="pageMove(item)">
              <v-list-item-avatar>
                <v-img :src="item.avatar"></v-img>
              </v-list-item-avatar>
              <v-list-item-content>
                <v-list-item-title v-html="item.title"></v-list-item-title>
                <v-list-item-subtitle v-html="item.subtitle"></v-list-item-subtitle>
              </v-list-item-content>
              <v-icon @click.prevent.stop="deleteNotice(item)" style="hover">close</v-icon>
            </v-list-item>
          </div>
        </template>
      </v-list>
    </v-menu>
    <v-btn v-if="!this.$store.state.id" @click="$router.push('/login')" elevation="2"
      style="margin-right:10px; border: none; background-color: white">로그인</v-btn>
    <v-btn v-if="!this.$store.state.id" @click="$router.push('/register')" elevation="2"
      style="border: none; background-color: white">회원가입</v-btn>
    <!-- <v-btn v-if="this.$store.state.id" icon> -->
    <v-menu offset-y v-if="this.$store.state.id">
      <template v-slot:activator="{ on, attrs }">
        <span id="bellspan" v-bind="attrs" v-on="on" style="cursor: pointer; margin-right:30px; margin-left: 30px;">
          <v-badge style="cursor: pointer;" v-if="noticeMsgCount != 0" offset-x="10" offset-y="10" color="red"
            :content="noticeMsgCount">
            <v-icon>mail</v-icon>
          </v-badge>
        </span>
      </template>
      <v-list three-line width="400" height="400">
        <v-toolbar color="#2ac187" dark>
          <!-- <v-app-bar-nav-icon></v-app-bar-nav-icon> -->
          <v-toolbar-title>{{ this.$moment().format("YYYY-MM-DD") }}</v-toolbar-title>
          <v-spacer></v-spacer>

          <v-btn color="white" class="black--text" rounded @click.prevent.stop="deleteAllMsgNotice()">
            전체삭제
          </v-btn>
        </v-toolbar>
        <template v-for="(item, index) in messages">
          <div @click="pageMove(item)" style="background-color: white">
            <v-subheader v-if="item.header" :key="item.header">
              <v-text> v-text="item.header"</v-text>
            </v-subheader>
            <v-divider v-else-if="item.divider" :key="index" :inset="item.inset"></v-divider>
            <v-list-item v-else :key="item.title">
              <v-list-item-avatar>
                <v-img :src="item.avatar"></v-img>
              </v-list-item-avatar>
              <v-list-item-content>
                <v-list-item-title v-html="item.title"></v-list-item-title>
                <v-list-item-subtitle v-html="item.subtitle"></v-list-item-subtitle>
              </v-list-item-content>
              <v-badge style="cursor: pointer;" v-if="item.count != 0" offset-x="10" offset-y="10" color="red"
                :content="item.count" class="mr-5">
              </v-badge>
              <v-icon @click.prevent.stop="deleteMsgNotice(item)" style="hover">close</v-icon>
            </v-list-item>
          </div>
        </template>
      </v-list>
    </v-menu>
    <v-btn v-if="this.$store.state.id" icon>
      <v-icon @click="$router.push('/mypageprofile')">mdi-account</v-icon>
    </v-btn>
    <v-btn v-if="this.$store.state.id" @click="logout()" icon>
      <v-icon>mdi-arrow-right-box</v-icon>
    </v-btn>
  </v-app-bar>
</template>
<script>

export default {
  components: {},

  data() {
    return {
      noticeMsgCount: "", //메신저 알람수
      noticeCount: "", //일반 알람수
      avatar: "", //알람 프로필
      subtitle: "", // 알람 내용
      items: [{ header: this.$moment().format("YYYY-MM-DD") }], //일반 알람 목록
      messages: [{ header: this.$moment().format("YYYY-MM-DD") }], //메신저 알람 목록,
      searchText: "",
      isUser:""
    };
  },
  setup() { },
  created() {
   
    if (this.$store.state.id != '') {
      this.connect()
    }
  },
  // mounted() {
  //   let vm = this
  //   console.log("mounted")
  //   this.$store.watch(
  //     this.$store.getters.getId, n => {
  //       console.log("123")
  //       this.connect()
  //       vm.stompClient.unsubscribe(this.$store.state.isUser)
  //     }
  //   );
  // },
  computed: {
    getId() {
      return this.$store.getters.getId
    }
  },
  watch: {
    getId(val, oldVal) {
     
      this.connect()
      //vm.stompClient.unsubscribe(this.$store.state.isUser)
    }
  },

  afterEach() {
    this.$router.go(0);
  },
  methods: {
    connect() {
   
      this.noticeRes()
      this.getAllNotice()
    },
    //알림정보 가져오기
    getAllNotice() {
     
      this.items = []
      this.messages = []
      let vm = this;
      if(vm.$store.state.id!=''){
      this.axios
        .get("/getAllNotice/", {
          params: {
            memberId: this.$store.state.id
          },
        })
        .then((res) => {
        
          for (let i = 0; i < res.data.length; i++) {
            if (res.data[i].noticeType == 2) {
              let idx = vm.messages.findIndex(obj => obj.postId == res.data[i].postId)
              if (idx < 0) {
                vm.messages.unshift({ divider: true, inset: true });
                vm.messages.unshift({
                  avatar: require(`@/assets/image/user/${res.data[i].avatar}`),
                  title: res.data[i].title,
                  subtitle: res.data[i].subtitle,
                  postId: res.data[i].postId,
                  boardType: res.data[i].boardType,
                  moimId: res.data[i].moimId,
                  noticeType: res.data[i].noticeType,
                  noticeId: res.data[i].noticeId,
                  count: 1
                })
              }
              else {
                vm.messages[idx].count = vm.messages[idx].count + 1
              }
            }
            else {
              if (res.data[i].noticeType == 0) {
                vm.items.unshift({ divider: true, inset: true });
                vm.items.unshift({
                  avatar: require(`@/assets/image/user/${res.data[i].avatar}`),
                  title: res.data[i].title + " 님이",
                  subtitle: res.data[i].subtitle,
                  postId: res.data[i].postId,
                  boardType: res.data[i].boardType,
                  moimId: res.data[i].moimId,
                  noticeType: res.data[i].noticeType,
                  noticeId: res.data[i].noticeId,
                })
              } else if (res.data[i].noticeType == 1) {
                vm.items.unshift({ divider: true, inset: true });
                vm.items.unshift({
                  avatar: require(`@/assets/image/moim/${res.data[i].avatar}`),
                  title: res.data[i].title + " 님이",
                  subtitle: res.data[i].subtitle,
                  postId: res.data[i].postId,
                  boardType: res.data[i].boardType,
                  moimId: res.data[i].moimId,
                  noticeType: res.data[i].noticeType,
                  noticeId: res.data[i].noticeId,
                })
              }
            }
          }
          for (let i = 0; i < vm.messages.length-1; i++) {
            vm.noticeMsgCount = vm.noticeMsgCount + vm.messages[i].count
          }
          vm.noticeCount = ((vm.items.length) / 2)
          vm.stompClient.send("/app/SubscribeId", this.$store.state.id, (res) => {
          console.log(res);
        });
        })
        .catch((err) => {
          console.log("에러나나요/?");
        });
    }},
    //알림 처리
    noticeRes() {
      console.log("noticeRes TEst")
      let vm = this;
      if(vm.$store.state.id!=''){
      vm.stompClient.subscribe("/queue/" + this.$store.state.id + "/notice",
        function (res) {

      
          if(res.body==vm.$store.state.id){
            vm.isUser = res.headers.subscription;}
            else{
            console.log(typeof res.body)
            console.log("res.body :" +res.body)
            let resNotice = JSON.parse(res.body);
            console.log("resNotice : "+ resNotice)
            //sns 알림 처리
            if (resNotice.noticeType == 0) {
              //sns - 좋아요 알림 처리
              if (resNotice.contentType == 0) {
                if (resNotice.likeStatus == 0) {
                  vm.subtitle = "좋아요를 눌렀습니다.";
                } else if (resNotice.likeStatus == 1) {
                  vm.subtitle = "좋아요를 취소했습니다.";
                }
              }  //sns - 댓글 알림 처리
              else if (resNotice.contentType == 1) {
                vm.subtitle = "댓글을 남겼습니다.";
              } else if (resNotice.contentType == 2) {
                vm.subtitle = "님이 언급했어요!"
              }
              vm.items.unshift({
                avatar: require(`@/assets/image/user/${resNotice.profileImge}`),
                title: resNotice.nickname + " 님이",
                subtitle: vm.subtitle,
                postId: resNotice.postId,
                noticeType: resNotice.noticeType,
                noticeId: resNotice.noticeId,
              });
              vm.items.unshift({ divider: true, inset: true })
              ++vm.noticeCount
            }
            //소모임 알림 처리
            else if (resNotice.noticeType == 1) {
              //소모임 댓글 알림 처리
              vm.items.unshift({ divider: true, inset: true })
              if (resNotice.contentType == 0) {
                vm.subtitle = "댓글을 남기셨습니다."
              } else if (resNotice.contentType == 1) {
                vm.subtitle = "새로운 게시글이 등록되었습니다."
              }
              vm.items.unshift({
                avatar: require(`@/assets/image/moim/${resNotice.profileImge}`),
                title: resNotice.nickname + " 님이",
                subtitle: vm.subtitle,
                postId: resNotice.postId,
                boardType: resNotice.boardType,
                moimId: resNotice.moimId,
                noticeType: resNotice.noticeType,
                noticeId: resNotice.noticeId,
              });
              ++vm.noticeCount;
            }
            //메신저 알림 처리
            else if (resNotice.noticeType == 2) {
              console.log("메시지 오나요??")
              let idx = vm.messages.findIndex(obj => obj.postId == resNotice.postId)
              if (idx < 0) {
                vm.messages.unshift({ divider: true, inset: true });
                vm.subtitle = "새로운 메세지가 도착했습니다.";
                vm.messages.unshift({
                  avatar: require(`@/assets/image/user/${resNotice.profileImge}`),
                  title: resNotice.nickname + "님으로 부터",
                  subtitle: vm.subtitle,
                  postId: resNotice.postId,
                  boardType: resNotice.boardType,
                  moimId: resNotice.moimId,
                  noticeType: resNotice.noticeType,
                  noticeId: resNotice.noticeId,
                  count: 1
                })
              }
              else {
                vm.messages[idx].count = vm.messages[idx].count + 1
              }
              vm.noticeMsgCount=vm.noticeMsgCount+1
            }
        }});
    }},
    //알림 삭제
    deleteNotice(item) {
      if (item.noticeType != 2) {
        for (let i = 0; i < this.items.length; i++) {
          if (this.items[i].noticeId == item.noticeId) {
            this.items.splice(i, 2);
          }
        }
        this.axios
          .delete("/deleteNotice", {
            params: {
              noticeId: item.noticeId,
            },
          })
          .then((res) => {
            console.log(res);
          })
          .catch((err) => {
            console.log(err);
          });
        --this.noticeCount;
      }
    },
    //일반 알림 전체 삭제
    deleteAllNotice(item) {
      this.axios
        .delete("/deleteAllNotice", {
          params: {
            memberId: this.$store.state.id,
          },
        })
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
      this.noticeCount = 0
      this.items = []
    },
    //메신저 알림 삭제
    deleteMsgNotice(item) {
      for (let i = 0; i < this.messages.length; i++) {
        if (this.messages[i].noticeId == item.noticeId) {
          this.messages.splice(i, 2);
        }
      }
      console.log(item.postId)
      this.axios
        .delete("/deleteMsgNotice", {
          params: {
            postId: item.postId,
          },
        })
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
      this.noticeMsgCount = this.noticeMsgCount - item.count;
    },

    //메신저 알림 전체 삭제
    deleteAllMsgNotice(item) {
      console.log("여기오나요???")
      this.axios
        .delete("/deleteAllMsgNotice", {
          params: {
            noticeType: item.noticeType,
          },
        })
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
      this.noticeMsgCount = 0
      this.messages = []
    },

    //알림 클릭 이벤트
    pageMove(item) {
      console.log("pageMove Test : " + item)
      console.log("item.noticeType : " + item.noticeType)
      if (item.noticeType != 2) {
        this.deleteNotice(item)
        if (item.noticeType == 0) {
          this.$router.push("/snsFeedDetail?writer=" + item.targetId + "&postId=" + item.postId);
        } else if (item.noticeType == 1) {
          this.$router.push("/moimDetail/" + item.moimId + "/" + item.postId + "/moimPost?moimId=" + item.moimId + "&boardId=" + item.postId + "&boardType=" + item.boardType
          );
        }
      } else if (item.noticeType == 2) {
        console.log("여기오나요?")
        this.deleteMsgNotice(item)
        this.$router.push({ name: "chat", query: { getRoomId: item.postId } });
      }
    },
    search() {
      console.log(this.searchText);
      this.$router.push({ name: 'mainsearch', query: { searchText: this.searchText } });
      this.$router.go(0);
    },
    logout() {
      this.$store.commit("setIsLoginFalse");
      this.$store.commit("logout");
      this.$store.commit("setUserData", null);
      this.$router.push("/");
      this.stompClient.unsubscribe(this.isUser)
    },
  },
};
</script>
<style scoped>
#bellspan {
  width: 3px;
}

.v-input__slot {
  background: white;
}
</style>