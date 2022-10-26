<template>
  <div>
    <div class="container">
      <v-container>
        <v-text-field label="글 내용 검색" single-line solo></v-text-field>
      </v-container>
    </div>
    <v-card
      class="mx-auto mb-8"
      max-width="800"
      outlined
      v-for="(item, idx) in items"
      :key="idx"
      @click="goPost(idx)"
    >
      <v-list-item three-line>
        <v-list-item-avatar tile size="80" color="grey"></v-list-item-avatar>
        <v-list-item-content>
          <div class="text-overline mt-6">
            {{ item.memberId }}
            <hr />
            {{ item.writeDate | moment('YYYY-MM-DD HH:mm') }}
          </div>
          <v-list-item-subtitle class="content mt-5">
            {{ item.title }}
          </v-list-item-subtitle >
        </v-list-item-content>
      </v-list-item>
      <v-card-actions>
        <v-spacer></v-spacer>
        <div class="text-overline mb-1 mr-2">댓글 {{ item.cnt }}</div>
      </v-card-actions>
    </v-card>
  </div>
</template>
<script>
export default {
  data() {
    return {
      items: [],
      //moim : 비공개 0 공개 1
      //moimRight : 비회원 0 회원 1
      moimId: this.$route.params.moimId,
      moimRight: 1,
      Id: this.$route.params.moimId,
      boardType: 1,
      boardList : []
    };
  },
  methods: {
    makeShowList() {
      this.items.push({ header: "게시판" });
      for(let i = 0; i < this.items.length; i++) {
        this.items.push({
          avatar: require(`@/assets/image/user/${this.items[i].profileImg}`),
        },)
      }
    },
    goPost(idx) {
      this.$router.push({
        name: "moimPost",
        query: {
          moimId: this.Id,
          boardId: this.items[idx].boardId,
          boardType: 1,
        },
      });
    },
    getBoard() {
      this.axios
        .get("/moimBoardList", {
          params: {
            moimId: this.Id,
            boardType: this.boardType,
          },
        })
        .then((resp) => {
          console.log(resp);
          console.log(this.items);
          this.items = resp.data;
        })
        .catch((err) => {
          console.log(this.items);
          console.log(err);
        });
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