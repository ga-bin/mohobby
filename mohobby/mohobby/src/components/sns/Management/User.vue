<template>
    <div>
      <v-card class="mx-auto" width="800" min-height="520">
        <v-toolbar color="#2ac187" dark> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{ keyword }}</v-toolbar>
          <v-list three-line>
            <div v-for="(user, i) in users" :key="i">
              <template>
                <v-list-item>
                  <v-list-item-avatar>
                    <v-img @click="goUserProfile(users[i])" :src="require(`@/assets/image/user/${user.profileImg}`)" />
                  </v-list-item-avatar>
                  <v-list-item-content>
                    <v-list-item-title v-html="user.followerId" />
                    <v-list-item-title v-html="user.followingId" />
                    <v-list-item-title v-html="user.memberId" />
                    <v-list-item-subtitle v-html="user.nickname" />
                  </v-list-item-content>
                  <v-btn v-show="user.followingId" 
                         @click="unfollow(memberId,users[i].followingId)"
                         rounded color="#2ac187" class="white--text">손절</v-btn>
                  <v-btn v-show="user.followingId" 
                        @click="unfollow(memberId,users[i].followingId)"
                        rounded color="white" class="#2ac187--text">팔로우</v-btn>
                </v-list-item>
              </template>
            </div>
          </v-list>
        </v-card>
      </div>
</template>
<script>
  import CmtReg from "@/components/sns/FeedDetail/CmtReg.vue";
  export default {
    name: "UserResult",
    components: { CmtReg },
    props:{
      userResult: [], //유저검색 결과
      keyword : String, //키워드
    },
    data() {
      return {

          users: [], //props 담을 변수
          memberId : this.$store.state.id,
          userId: "",

      };
    },
    created() {
      console.log("User page");
      console.log(this.userResult);

      //유저검색
      this.users = this.userResult;
      console.log(this.user);

    },
    watch: {
      userResult(){
        this.users=this.userResult
      }
    },

        
    methods: {
      //유저 프로필로 이동
      goUserProfile(e) {
        if (e.followerId){
          this.userId = e.followerId;
        } else if (e.memberId) {
          this.userId = e.memberId;
        } else if (e.followingId){
          this.userId = e.followingId;
        }
        this.$router.push({ path: "/snsUserFeed", query: { userId: this.userId } });
      },

      //언팔로우
      unfollow(memberId, userId) {
        this.axios
          .delete("/sns/follow/" + memberId + "/" + userId)
          .then((res) => {
            console.log("언팔로우성공 전" + this.followStatus);
            console.log("언팔로우 성공! " + res);
            const btn = document.getElementsByClassName('.white--text');
            btn.innerText = '친구걸기';

          })
          .catch((err) => {
            console.log(err);
          });
        },
      }
  }
  </script>
  

