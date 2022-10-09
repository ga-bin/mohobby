<template>
  <div class="container">
    <v-card class="pa-5">
      <div class="head">
      <v-head>투표 하기</v-head>
      </div>
      <hr>
      <v-text-field class="mt-3" label="투표 제목" hide-details />
      <v-text-field class="mt-3" label="항목 1" hide-details />
      <v-text-field class="mt-3" label="항목 2" hide-details />
      <v-text-field class="mt-3" label="항목 3" hide-details />

      <v-btn class="mt-4" small>투표 항목 추가하기</v-btn>

      <v-checkbox
        class="mb-4"
        v-model="hover"
        label="익명 투표하기"
        hide-details
      ></v-checkbox>
      <hr>
      <div class="attach">
      <v-btn @click="goDetail()">첨부하기</v-btn>
      </div>
    </v-card>
  </div>
</template>
<script>
  export default {
    methods: {
      goDetail: function () {
      this.$router.push({ path: "voteDetail" });
    },
    }
  }
</script>
<style scoped>
.container{
  max-width: 60%;
}
.head{
  text-align: center;
  margin-bottom: 2%;
}
.item{
  margin-top: 3%;
}
.attach{
  text-align: center;
  margin-top: 2%;
}
</style>