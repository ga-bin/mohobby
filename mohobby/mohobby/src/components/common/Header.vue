<template>
  <v-app-bar app color="white" elevate-on-scroll elevation="4">
    <v-btn @click="test()" icon>
      <v-icon>mdi-arrow-left-box</v-icon>
    </v-btn>

    <v-toolbar-title
      @click="$router.push('/').catch(() => {})"
      style="cursor: pointer"
      >Mohobby</v-toolbar-title
    >
    <p>{{ this.$store.state.id }}</p>
    <v-spacer />
    <v-btn text class="ml-2" to="/snsmain">sns</v-btn>
    <v-btn text class="ml-2" to="/class/list/all">강의</v-btn>
    <v-btn text class="ml-2" to="/moimmain">소모임</v-btn>
    <v-btn text class="ml-2" to="/challengemain">챌린지</v-btn>
    <v-spacer />
    <v-col lg="4" cols="12">
      <v-form class="mt-5">
        <v-text-field
          rounded
          outlined
          dense
          placeholder="Search Here"
          append-icon="mdi-magnify"
        />
      </v-form>
    </v-col>
    <v-spacer />

    <v-menu offset-y v-if="this.$store.state.id">
      <template v-slot:activator="{ on, attrs }">
        <span id="bellspan" v-bind="attrs" v-on="on" style="cursor: pointer">
          <v-badge
            v-if="noticeCount != 0"
            offset-x="10"
            offset-y="10"
            color="red"
            :content="noticeCount"
          >
            <v-icon>mdi-bell</v-icon>
          </v-badge>
        </span>
      </template>
      <v-list three-line width="400" height="400">
        <template v-for="(item, index) in items">
          <div @click="pageMove(item)" style="background-color: white">
            <v-subheader
              v-if="item.header"
              :key="item.header"
              v-text="item.header"
            ></v-subheader>
            <v-divider
              v-else-if="item.divider"
              :key="index"
              :inset="item.inset"
            ></v-divider>
            <v-list-item v-else :key="item.title">
              <v-list-item-avatar>
                <v-img :src="item.avatar"></v-img>
              </v-list-item-avatar>
              <v-list-item-content>
                <v-list-item-title v-html="item.title"></v-list-item-title>
                <v-list-item-subtitle
                  v-html="item.subtitle"
                ></v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
          </div>
        </template>
      </v-list>
    </v-menu>
    <v-btn icon>
      <v-icon v-if="!this.$store.state.id" @click="$router.push('/login')"
        >mdi-arrow-left-box</v-icon
      >
    </v-btn>

    <v-icon v-if="!this.$store.state.id" @click="$router.push('/register')"
      >mdi-account-multiple-plus</v-icon
    >

    <v-btn v-if="this.$store.state.id" icon>
      <v-badge
        offset-x="10"
        offset-y="10"
        color="red"
        :content="messages1"
        :value="messages"
      >
        <v-icon>mdi-chat-processing-outline</v-icon>
      </v-badge>
    </v-btn>

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
      avatar:"",
      noticeCount:0,
      subtitle: "",
      items: [
        { header: this.$moment().format('YYYY-MM-DD') },
      ],
    };
  },
  setup() { },
  created() {
    this.avatar="comfuck.jpg"
    this.getAllNotice()
    this.startSckct()
  },
  mounted() { },
  unmounted() { },

  methods: {
    test() {
      console.log(this.items)
    },
    logout() {
      this.$store.commit("setIsLoginFalse");
      this.$store.commit("logout");
      this.$store.commit("setUserData", null);
      this.$router.push("/");
    },
    getAllNotice() {
      let vm = this
      console.log("걸리나요")
      this.axios.get('/getAllNotice/', {
        params: {
          memberId: this.$store.state.id
        }
      }).then(res => {

        vm.noticeCount = res.data.length

        for (let i = 0; i < res.data.length; i++) {
          vm.items.push(res.data[i])
          vm.items.push({ divider: true, inset: true })
        }
      }).catch(err => {
        console.log(err)
      })
    },
    startSckct() {
      if(this.$store.state.id == "") {
        this.noticeRev();
      }
    },
    //알림 처리
    noticeRev() {
      let vm = this;
      this.stompClient.connect(
        {},
        (frame) => {
          this.stompClient.subscribe("/queue/" + this.$store.state.id + "/notice", function (res) {
            let resNotice = JSON.parse(res.body)
            console.log(resNotice)
            if (resNotice.memberId != vm.$store.state.id) {
              //sns 알림 처리
              if (resNotice.noticeType == 0) {
                //sns - 좋아요 알림 처리
                if (resNotice.contentType == 0) {
                  if (resNotice.likeStatus == 0) {
                    vm.subtitle = "좋아요를 눌렀습니다."
                  }
                  else if (resNotice.likeStatus == 1) {
                    vm.subtitle = "좋아요를 취소했습니다."
                  }
                  vm.items.push({
                    avatar: require(`@/assets/image/user/${resNotice.profileImge}`),
                    title: resNotice.nickname,
                    subtitle: vm.subtitle,
                    postId: resNotice.postId,
                    boardType: resNotice.boardType,
                  });
                  vm.items.push({ divider: true, inset: true });
                }
                //sns - 댓글 알림 처리
                else if (resNotice.contentType == 1) {
                  vm.subtitle = "댓글을 남겼습니다."
                }
                vm.items.push({
                  avatar: require(`@/assets/image/user/${resNotice.profileImge}`),
                  title: resNotice.nickname,
                  subtitle: vm.subtitle,
                  postId: resNotice.postId,
                  noticeType: resNotice.noticeType,
                  noticeId: resNotice.noticeId
                })
                vm.items.push({ divider: true, inset: true })
                ++vm.noticeCount
              }
              //소모임 알림 처리
              else if (resNotice.noticeType == 1) {
                //소모임 댓글 알림 처리
                if (resNotice.contentType == 0) {
                  vm.subtitle = "댓글을 남기셨습니다."
                }
                else if (resNotice.contentType == 1) {
                  vm.subtitle = "새로운 게시글이 등록되었습니다."
                }

                vm.items.push({
                  avatar: require(`@/assets/image/moim/${resNotice.profileImge}`),
                  title: resNotice.nickname,
                  subtitle: vm.subtitle,
                  postId: resNotice.postId,
                  boardType: resNotice.boardType,
                  moimId: resNotice.moimId,
                  noticeType: resNotice.noticeType,
                  noticeId: resNotice.noticeId
                })
                vm.items.push({ divider: true, inset: true })
                ++vm.noticeCount
              }
            }
          }
          );
          console.log("소켓 연결 성공", frame);
        },
        (error) => {
          console.log("소켓 연결 실패", error);
        }
      );
    },

    pageMove(item) {

      console.log(this.items)
      for (let i = 0; i < this.items.length; i++) {
        if (this.items[i].noticeId == item.noticeId) {
          this.items.splice(i, 2);
          i--;
        }
      }
      console.log("11")
      console.log(this.items)
      --this.noticeCount

      this.axios.delete('/deleteNotice', {
        params: {
          noticeId: item.noticeId
        }
      }).then(res => {
        console.log(res)
      }).catch(err => {
        console.log(err)
      })
      if (item.noticeType == 0) {
        this.$router.push("/snsFeedDetail?id=" + item.postId);
      } else if (item.boardType == 1) {
        this.$router.push("/moimDetail/" + item.moimId + "/" + item.postId + "/moimPost?moimId=" + item.moimId + "&boardId=" + item.postId + "&boardType=" + item.boardType);
      }
    }
  },
};
</script>
<style scoped>
#bellspan {
  width: 3px;
}
</style>
