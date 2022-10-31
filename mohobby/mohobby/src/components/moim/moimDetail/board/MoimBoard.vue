<template>
  <div>
    <div style="background-color: #F5F5F5; ">
      <div class="container">
        <v-container>
          <v-text-field label="글 내용 검색" v-model="boardSearch" @keyup.enter="Search" single-line solo></v-text-field>
        </v-container>
        <v-card class="mx-auto mb-8" max-width="800" outlined v-for="(item, idx) in items" :key="idx"
          @click="goPost(idx)">
          <v-list-item three-line>
            <v-list-item-avatar tile size="80" >
                <v-img :src="profile[idx].avatar"></v-img>
              </v-list-item-avatar>
            <v-list-item-content>
              <div class="text-overline mt-6">
                {{ item.memberId }}
                <hr />
                {{ item.writeDate | moment('YYYY-MM-DD HH:mm') }}
              </div>
              <v-list-item-subtitle class="content mt-5">
                {{ item.title }}
              </v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
          <v-card-actions>
            <v-spacer></v-spacer>
            <div class="text-overline mb-1 mr-2">댓글 {{ item.cnt }}</div>
          </v-card-actions>
        </v-card>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      boardSearch: "",
      items: [],
      moimId: this.$route.params.moimId,
      boardType: 1,
      profile: []
    };
  },
  methods: {
    Search() {
      console.log('검색테스트');
      console.log(this.boardSearch);
      this.axios.get("/boardSearch", {
        params: {
          moimId: this.moimId,
          title: this.boardSearch,
          boardType: this.boardType
        }
      })
        .then((resp) => {
          console.log(resp);
          this.items = resp.data
        })
        .catch((err) => {
          console.log(err);
        })
    },
    goPost(idx) {
      this.$router.push({
        name: "moimPost",
        query: {
          moimId: this.moimId,
          boardId: this.items[idx].boardId,
          title : this.items[idx].title,
          boardType: 1,
        },
      });
    },
    getBoard() {
      let vm = this;
      this.axios
        .get("/moimBoardList", {
          params: {
            moimId: this.moimId,
            boardType: this.boardType,
          },
        })
        .then((resp) => {
          console.log(resp);
          console.log("getboard실행됐음")
          console.log("getboard실행됐음")
          console.log("getboard실행됐음")
          console.log("getboard실행됐음")
          console.log("getboard실행됐음")
          console.log("getboard실행됐음")

          console.log(this.items);
          this.items = resp.data;
        })
        .catch((err) => {
          console.log(this.items);
          console.log(err);
        }).finally((response)=>{
          console.log("finally")
          console.log("finally")
          console.log("finally")
          console.log("finally")
          console.log("finally")
          console.log("finally")
          console.log("finally")
          for(let i=0; i<vm.items.length; i++){
            vm.axios.get("/getImg",{
              params:{
                memberId : vm.items[i].memberId
              }
            }).then((response)=>{
              console.log('response: '+response)
              console.log('response: '+response)
              console.log('response: '+response)
              console.log('response: '+response)
              console.log('response: '+response)

              console.log('response.data: '+response.data)
              console.log('response.data: '+response.data)
              console.log('response.data: '+response.data)
              console.log('response.data: '+response.data)
              console.log('response.data: '+response.data)
              console.log('response.data: '+response.data)

              this.profile.push({avatar: require(`@/assets/image/user/${response.data}`)})
            }).catch((err)=>{
              console.log(err)
            })
          }
        })
    },
  },
  created() {
    this.getBoard();
    this.getPost();
  },
};
</script>

<style scoped>
.container {
  width: 91%;
}
</style>