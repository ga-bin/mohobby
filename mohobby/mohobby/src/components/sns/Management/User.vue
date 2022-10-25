<template>
    <div>
      <v-card class="mx-auto" width="800" min-height="520">
        <v-toolbar color="#2ac187" dark> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' {{ keyword }} '에 대한 검색결과입니다 </v-toolbar>
          <v-list three-line>
            <div v-for="(user, i) in users" :key="i">
              <template>
                <v-list-item>
                  <v-list-item-avatar>
                    <v-img @click="goUserProfile(user.memberId)" :src="require(`@/assets/image/user/${user.profileImg}`)" />
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
          <!-- <div class="wrap_box" v-if="noResult == true" >
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
          </div> -->
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

          users: [], //유저검색결과 props 담을 변수

          // noResult: false,
          // user: true,
          // userResultForm: false,

      };
    },
    created() {
      console.log("User page");
      console.log(this.userResult);
      this.users = this.userResult;
      console.log(this.users);
      // this.userSearch();
      // this.keyword = this.searchResult;
        // this.searchMem(this.keyword);


    },
    watch: {
      userResult(){
        this.users=this.userResult
      }
      // searchResult() {
      //   this.searchMem(this.searchResult);
      //   }

        // userSearch(){
          
        // }

      },

        
    methods: {
      //유저 프로필로 이동
      goUserProfile(userId) {
        this.$router.push({ path: "/snsUserFeed", query: { userId: userId } });
      },
    }
  }
  </script>
  

