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
        <img :src="item.src" />
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
        items : [{
          src : "https://cdn.class101.net/images/84d01f2e-ec28-4e30-8ed1-fc6e55954e42/960xauto.webp"   
          },
          {
          src : "https://pbs.twimg.com/media/D9y_RPpU4AI6xzC.png"
          },
          {
          src : "https://i.pinimg.com/originals/69/9d/14/699d14b23afc5c0bf3b714767b291684.jpg"
          },
          {
          src : "https://image.idus.com/image/files/d6fabae15e924a6bbcd7595a62715a30_720.jpg"
          },
          {
          src : "https://images.mypetlife.co.kr/content/uploads/2019/12/09151959/%EC%8B%AC%EC%8B%AC%ED%95%9C_%EA%B3%A0%EC%96%91%EC%9D%B42-390x220.png"
          },
          {
          src : "http://picturebook-illust.com/upload_board/new_Gallery/ThumbNail/g/thumb_20202612533.jpg"
          },
          {
          src : "https://image.idus.com/image/files/d6fabae15e924a6bbcd7595a62715a30_720.jpg"
          },
          {
          src : "https://image.idus.com/image/files/d6fabae15e924a6bbcd7595a62715a30_720.jpg"
          },
          {
          src : "https://image.idus.com/image/files/d6fabae15e924a6bbcd7595a62715a30_720.jpg"
          },
          {
          src : "https://image.idus.com/image/files/d6fabae15e924a6bbcd7595a62715a30_720.jpg"
          },
          {
          src : "https://image.idus.com/image/files/d6fabae15e924a6bbcd7595a62715a30_720.jpg"
          },
      ]
      }
    },
    methods : {
      joimMoim() {
        this.axios.get("/")
      }
    },
    created : {

    }
  }

</script>
<style scoped>
  .box {
    margin-right: 25px;
  }
</style>