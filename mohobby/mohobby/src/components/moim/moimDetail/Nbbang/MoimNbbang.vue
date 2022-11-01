<template>
  <div class="container">
    <!-- N빵 생성하기 -->
    <div class="margin">
    <v-card-actions class="mb-16">
      <v-spacer />
      <v-btn @click="makeNbbang()"><v-icon>mdi-plus-circle-outline</v-icon>N빵 생성하기</v-btn>
    </v-card-actions>
  </div>
  
  <v-card
  class="mx-auto mb-8"
      max-width="800"
      outlined
      v-for="item in items"
      :key="item.dutchId"
      @click="Nbbangdetail(item.dutchId)"
    >
      <v-list-item three-line>
        <v-list-item-avatar tile size="80" color="grey"></v-list-item-avatar>
        <v-list-item-content>
          <div class="text-overline mt-6">
            {{ item.memberId }}
            <hr />
            {{ writeDate }}
          </div>
          <v-list-item-subtitle class="content mt-5">
          <v-icon class="mr-1 mb-1 ml-7" color="light-green">mdi-chart-pie</v-icon>
          N빵</v-list-item-subtitle
          >
          <v-card-actions class="ml-13">{{item.totalPrice}}원 / {{item.people}}명</v-card-actions>
        </v-list-item-content>
      </v-list-item>

      <v-card-actions>
      </v-card-actions>
    </v-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      items: [],
      moimId : this.$route.params.moimId, 
      //moim : 비공개 0 공개 1
      //moimRight : 비회원 0 회원 1
      moim: 1,
      moimRight: 1,
      writeDate: this.$moment().format('YY-MM-DD'),
      dutchId: 0,
    };
  },
  methods: {
    getNbbangList(){
      console.log(this.moimId)
      this.axios.get("/moimNbbangList", {
        params : {
          moimId : this.moimId
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
    },
    makeNbbang: function () {
      this.$router.push({ path: "makeNbbang" , params : { moimId : this.moimId } });
    },
    Nbbangdetail: function(dutchId){
      this.dutchId = dutchId,
      this.$router.push({ name: "nBBangDetail", query : { moimId : this.moimId, dutchId : this.dutchId }})
    }
  },
  created(){
    this.getNbbangList();
  },
};
</script>

<style scoped>
.container {
  width: 85%;
}
.margin {
  margin-right: 10.5%;
}

</style>