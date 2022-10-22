<template>
  <div id="wrap_box">
      <SnsSidebar />
      <div class="container">
        <div class="profile">
          <!-- 프로필이미지 -->
          <div class="profile-image">
            <v-avatar class="ml-10 my-10 mr-4" color="grey darken-1" size="150">
                  <v-img aspect-ratio="30" :src="require(`@/assets/image/user/${infoes.profileImg}`)" alt="profile_img" />
            </v-avatar>
          </div>
          <!-- 프로필이미지 끝 -->
          <!-- 내 게시물 정보(닉네임, 개시물, 팔로워, 팔로잉 개수) -->
          <div class="profile-user-settings">
            <h1 class="profile-user-name">{{ infoes.memberId }}</h1>
            <!-- 권한 마크 ---- 일반: 0/ 강사 : 1/ 소모임관리자 : 2/ 둘 다: 3-->
            <div title="강의를 개설한 만능 재주꾼에게만 주어지는 마크입니다" v-if = "infoes.role == 1 || infoes.role == 3" class="btn profile-settings-btn" aria-label="profile settings"><v-icon color="blue">mdi-shield-check</v-icon><i class="fas fa-cog" aria-hidden="true"></i></div>
            <div title="모임을 운영하는 투철한 모험가에게만 주어지는 마크입니다" v-if = "infoes.role == 2 || infoes.role == 3" class="btn profile-settings-btn" aria-label="profile settings"><v-icon color="green">mdi-shield-star</v-icon><i class="fas fa-cog" aria-hidden="true"></i></div>
            <!-- 메뉴팝업 -->
            <v-menu v-if = "sessionId && sessionId != infoes.memberId">
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
          <div class="profile-stats">
            <ul>
              <li><span class="profile-real-name">{{ infoes.nickname }}</span></li>
            </ul>
            <ul>
              <li><span class="profile-stat-count">{{ infoes.postCnt }}</span> posts</li>
              <li>
                <FollowModal :text="followingtext" :dataList="following"></FollowModal>
              </li>
              <li>
                <FollowModal :text="followertext" :dataList="follower"></FollowModal>
              </li>
            </ul>
            <!-- 내 게시물 정보 끝 -->
            <ul>
                <!-- 소개 -->
                <li><p class="profile-bio">{{ infoes.intro }} 📷✈️🏕️</p></li>
                <li></li>
                <!-- 소개 끝 -->
            </ul>
          </div>
          <!-- 버튼 컴포넌트: 유저본인이냐에 따라 버튼 바뀜 -->
          <ProfileBtn :userId="userId" />
          <!-- 버튼 컴포넌트 끝 -->
        </div>
      </div>
      <!-- 피드 컴포넌트 -->
      <div>
        <Feeds :userId="userId"/>
      </div>
      <!-- 피드 끝 -->
    </div>
  </template>
  <script>
  import Feeds from "@/components/sns/FeedDetail/Feeds.vue";
  import SnsSidebar from "@/components/sns/Common/SnsSidebar.vue";
  import ProfileBtn from "@/components/sns/FeedDetail/ProfileBtn.vue";
  import FollowModal from "@/components/user/FollowModal.vue";
  export default {
    name: "UserProfile",
    components: { SnsSidebar, Feeds, ProfileBtn, FollowModal },
    data() {
        return{     
            infoes:[],
            sessionId:this.$store.state.id,
            sessionInfo:this.$store.state.user,
            userId:"",
            // userId: this.$store.state.id, //임시(로그인유저프로필)
            follower : [],
            following : [],
            followertext : "following",
            followingtext : "follower",
            lists: [  //메뉴 리스트
              { title: "신고하기" },
            ],
        }
    },
    setup() {},
    created() {
        console.log("유저 프로필로 이동!");
        this.userId = this.$route.query.userId; //넘겨받은 유저아이디 바인딩
        console.log(this.$route.query.userId);
        this.loadUserProfile(this.userId);
        //   if(this$store.state.id != ""){
        //     this.sessionId == this.$store.state.id; //세션에 저장된 memId 변수대입
        //     console.log(this.$store.state.id +","+ this.sessionId); 
        //   }
        this.getFollowing(this.userId);
        this.getFollower(this.userId);
      },
      methods: {
        //프로필 업로드
        loadUserProfile(userId) {
            this.axios('/sns/user/profile/' + userId)
            .then(res => {
              this.infoes = res.data;
            }).catch(err => {
              console.log(err);
            });  
          },
        //채팅방 이동
        send() {
        this.$router.push({ name: "chat", params: { roomId: this.roomId } });
        },

        
        /*
        * Kim ga bin
        * MyPage followingList 확인하기
        */
        // 팔로잉 목록 불러오기
        getFollowing(userId) {
        const vm = this;
        this.axios({
                url: "http://localhost:8088/java/mypagefollowing/" + userId,
                method: "get",
                })
                .then(function (response) {
                    console.log(response.data);
                    for (let i = 0 ; i < response.data.length; i++) {
                    vm.following.push(response.data[i]);
                    vm.following.push({ divider: true, inset: true });
                    }
                    console.log("vm.following" + vm.following);
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        // 팔로워 목록 불러오기
        getFollower(userId) {
        const vm = this;
        this.axios({
                url: "http://localhost:8088/java/mypagefollower/" + userId,
                method: "get",
                })
                .then(function (response) {
                    console.log(response.data);
                    for (let i = 0 ; i < response.data.length; i++) {
                    vm.follower.push(response.data[i]);
                    vm.follower.push({ divider: true, inset: true });
                    }
                    console.log("vm.follower" + vm.follower);
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    },
    mounted() {},
    unmounted() { },
  };
  </script>
  <style scoped lang="css" src="@/assets/css/sns/SnsUserFeed.css" />