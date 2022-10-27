<template>
  <div class="container">
    <v-row>
    <v-col cols="12" sm="6" md="6">
    <div>
    <h3>내가 운영중인 소모임</h3>
      <v-sheet max-width="500">
    <v-slide-group class="pa-2">
      <v-slide-item
        v-for="item in items"
        :key="item.src"
        >
        <div class="box">
        <v-list-item-avatar
        tile
        size="155">
        <img :src="item.src" />
        </v-list-item-avatar>
      </div>
      </v-slide-item>
    </v-slide-group>
  </v-sheet>
  </div>
  </v-col>
  <!--두번째 슬라이드 -->
    <v-col cols="12" sm="6" md="6">
  <div>
    <h3>내가 참여중인 소모임</h3>
      <v-sheet max-width="500">
    <v-slide-group class="pa-2">
      <v-slide-item
        v-for="item in chamyeo"
        :key="item.src"
        >
        <div class="box">
        <v-list-item-avatar
        tile
        size="155">
        <img :src="item.src" />
        </v-list-item-avatar>
      </div>
      </v-slide-item>
    </v-slide-group>
  </v-sheet>
  </div>
</v-col>
</v-row>
</div>
</template>
<script>
  export default {
    data() {
      return {
        memberId : 'user1',
        items : [],
        chamyeo : []
      }
    },
    methods : {
      operateMoim() {
        this.axios.get("/operateMoim", {
          params : {
            memberId : this.memberId
          }
        })
        .then ((resp) => {
          console.log(resp)
          this.items = resp.data;
        })
        .catch ((err) => {
          console.log(err)
        })
      },
      joimMoim() {
        this.axios.get("/joinMoim", {
          params : {
            memberId : this.memberId
          }
        })
        .then ((resp) => {
          console.log(resp)
          this.chamyeo = resp.data;
        })
        .catch ((err) => {
          console.log(err)
        })
      }
    },
    created() {
      this.joimMoim(),
      this.operateMoim()
    }

  }
</script>
<style scoped>
  .container {
    display :flex;
  }
</style>