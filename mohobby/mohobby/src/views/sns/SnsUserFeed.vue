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
                <FollowModal :text="followingtext" :dataList="following" :followCnt="followCnt" />
              </li>
              <li>
                <FollowModal :text="followertext" :dataList="follower" :follow="follow" />
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

          <div class="profile-bio">
            <ul v-if="sessionId && sessionId == infoes.memberId">
              <button class="btn profile-edit-btn" @click="goMypage(sessionId)">
                Edit Profile
              </button>
            </ul>
            <ul v-else>
              <button
                v-if="followStatus === 0"
                @click="follow(sessionId, infoes.memberId)"
                class="btn profile-edit-btn2"
              >
                Follow
              </button>
              <button
                v-else
                style="background-color: #2ac187; color: white"
                @click="unfollow(sessionId, infoes.memberId)"
                class="btn profile-edit-btn2"
              >
                Unfollow
              </button>
              <button class="btn profile-edit-btn2" @click="send(sessionId)">
                Message
              </button>
            </ul>
          </div>


          <!-- <ProfileBtn :userId="userId" /> -->
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
  // import ProfileBtn from "@/components/sns/FeedDetail/ProfileBtn.vue";
  import FollowModal from "@/components/user/FollowModal.vue";
  export default {
    name: "UserProfile",
    components: { SnsSidebar, Feeds, FollowModal },
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
            followStatus: Number,
            followerCnt:"",
            followingCnt:"",
            follow:[
            {"followerCnt": ""},
            {"followingCnt": ""},
            ]

        }
    },

    created() {
        console.log("유저 프로필로 이동!");
        this.userId = this.$route.query.userId; //넘겨받은 유저아이디 바인딩
        console.log(this.$route.query.userId);
        this.loadUserProfile(this.userId);
        this.followCheck(this.sessionId, this.userId);
        this.getFollowing(this.userId);
        this.getFollower(this.userId);
      },

      methods: {
        //프로필 업로드
        loadUserProfile(userId) {
            this.axios('/sns/user/profile/' + userId)
            .then(res => {
              this.infoes = res.data;
              console.log(this.infoes);
              this.follow.followerCnt = this.infoes.followerCnt;
              this.follow.followingCnt = this.infoes.followingCnt;
            }).catch(err => {
              console.log(err);
            });  
          },


        //세션유무 검증
        confirmMember(memberId) {
        if (memberId) {
          console.log("true");
          return true;
        } else {
          console.log("false");
          return false;
        }
      },


      //로그인 검증 모달
      loginConfirm() {
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


      //팔로우 상태 조회
      followCheck(memberId, userId) {
        this.axios("/sns/follow/check", {
          params: {
            myId: memberId,
            targetId: userId,
          },
        })
          .then((res) => {
            this.followStatus = res.data;
            console.log("팔로우상태: " + this.followStatus);
          })
          .catch((err) => {
            alert(err);
          });
      },


      //팔로우
      follow(memberId, userId) {
        if (this.confirmMember(memberId) == false) {
          this.loginConfirm();
        } else {
          this.axios
            .post("/sns/follow", {
              followerId: memberId, //로그인세션 아이디
              followingId: userId, //피드주인 아이디
            })
            .then((res) => {
              console.log("팔로우성공 전" + this.followStatus);
              this.followStatus = 1;
              this.loadUserProfile(userId);
              // this.followCheck(memberId, userId);
              console.log("팔로우성공 후" + this.followStatus);
              console.log("팔로우 성공! " + res);
            })
            .catch((err) => {
              console.log(err);
            });
        }
      },


      //언팔로우
      unfollow(memberId, userId) {
        if (this.confirmMember(memberId) == false) {
          this.loginConfirm();
        } else {
        this.axios
          .delete("/sns/follow/" + memberId + "/" + userId)
          .then((res) => {
            console.log("언팔로우성공 전" + this.followStatus);
            this.followStatus = 0;
            this.loadUserProfile(userId);
            // this.followCheck(memberId, userId);
            console.log("언팔로우성공 후" + this.followStatus);
            console.log("언팔로우 성공! " + res);
          })
          .catch((err) => {
            console.log(err);
          });
        }
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
        },


        //마이페이지로 이동
        goMypage(memberId) {
          this.$router.push({
            path: "/mypageprofile",
            query: { memberId: memberId },
          });
        },


        //채팅방 이동
        send(memberId) {
          let vm = this;
          if (this.confirmMember(memberId) == false) {
            this.loginConfirm();
          } else {
            this.axios
              .get("/getSnsChatRoomNo", {
                params: { myId: memberId, targetId: this.userId },
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
            }
          },

          
        // //채팅방 이동
        // send() {
        // this.$router.push({ name: "chat", params: { roomId: this.roomId } });
        // },
          
          
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
    }
 };
  </script>
  <style scoped lang="css" src="@/assets/css/sns/SnsUserFeed.css" />