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
      :key="item.date"
      @click="Nbbangdetail()"
    >
      <v-list-item three-line>
        <v-list-item-avatar tile size="80" color="grey"></v-list-item-avatar>
        <v-list-item-content>
          <div class="text-overline mt-6">
            {{ item.id }}
            <hr />
            {{ item.date }}
          </div>
          <v-list-item-subtitle class="content mt-5">
          <v-icon class="mr-1 mb-1 ml-7" color="light-green">mdi-chart-pie</v-icon>
          {{ item.contents }}</v-list-item-subtitle
          >
          <v-card-actions class="ml-13">{{item.money}}원 / {{item.person}}명</v-card-actions>
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
      items: [
        {
          date: "2022-10-04 11:12",
          id: "IVE",
          contents: "N빵",
          money : '9,000',
          person : 3
        },
        {
          date: "2022-10-05 10:42",
          id: "aespa",
          contents: "N빵",
          money : 10000,
          person : 4
        },
        {
          date: "2022-10-05 10:50",
          id: "NewJeans",
          contents: "N빵",
          money : 200000,
          person : 4
        },
      ],
      //moim : 비공개 0 공개 1
      //moimRight : 비회원 0 회원 1
      moim: 1,
      moimRight: 1,
    };
  },
  methods: {
    makeNbbang: function () {
      this.$router.push({ path: "makeNbbang" });
    },
    Nbbangdetail: function(){
      this.$router.push({ path: "nBBangDetail" })
    }
  },
  props: {},
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