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
      v-for=" (item,idx) in items"
      :key="item.writeDate"
      @click="goPost(idx)"
    >
      <v-list-item three-line>
        <v-list-item-avatar tile size="80" color="grey"></v-list-item-avatar>
        <v-list-item-content>
          <div class="text-overline mt-6">
            {{ item.memberId }}
            <hr>
            {{item.writeDate | yyyyMMdd}}

          </div>
          <v-list-item-subtitle class="content mt-5">
            {{ item.content }}</v-list-item-subtitle
          >
        </v-list-item-content>
      </v-list-item>

      <v-card-actions>
        <v-spacer></v-spacer>
        <div class="text-overline mb-1 mr-2">댓글 {{item.cnt}}</div>
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
      moim: 1,
      moimRight: 1,
      Id : this.$route.params.moimId,
      boardType : 1
    };
  },
  methods: {
    goPost(idx) {
      this.$router.push({ name: 'moimPost', query : { moimId : this.Id, boardId: this.items[idx].boardId, boardType: 1}});
    },
    getBoard() {
      this.axios.get("/moimBoardList", {
        params : {
          moimId : this.Id,
          boardType : this.boardType
        }
      })
      .then((resp)=> {
        console.log(resp)
        console.log(this.items)
        this.items = resp.data;
      })
      .catch((err) => {
        console.log(this.items)
        console.log(err)
      })
      }
    },
  created() {
    this.getBoard()
  },
  filters: {
    // filter로 쓸 filter ID 지정
    yyyyMMdd: function (value) {
      // 들어오는 value 값이 공백이면 그냥 공백으로 돌려줌
      if (value == "") return "";
      // 현재 Date 혹은 DateTime 데이터를 javaScript date 타입화
      var js_date = new Date(value);
      // 연도, 월, 일 추출
      var year = js_date.getFullYear();
      var month = js_date.getMonth() + 1;
      var day = js_date.getDate();
      // 월, 일의 경우 한자리 수 값이 있기 때문에 공백에 0 처리
      if (month < 10) {
        month = "0" + month;
      }
      if (day < 10) {
        day = "0" + day;
      }
      // 최종 포맷 (ex - '2021/10/08')
      return year + "/" + month + "/" + day;
    },
  }
};
</script>

<style scoped>
.container {
  width: 91%;
}
</style>