<template>
  <div>
    <div>
      <h3>내가 참여중인 소모임</h3>
      <v-sheet
      max-width="1000"
  >
    <v-slide-group
      class="pa-2"
    >
      <v-slide-item
        v-for="item in items"
        :key="item"
        >
        <div class="box">
        <v-list-item-avatar
        tile
        size="150">
        <img :src="item.moimImg" />
        </v-list-item-avatar>
      </div>
      </v-slide-item>
    </v-slide-group>
  </v-sheet>
    </div>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        memberId : 'user1',
        items : []
      }
    },
    methods : {
      joimMoim() {
        this.axios.get("/joinMoim", {
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
      }
    },
    created() {
      this.joimMoim()
    }
  }

</script>
<style scoped>
  .box {
    margin-right: 25px;
  }
</style>