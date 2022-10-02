<template>
  <div class="container">
    <div>
    <h3>내가 참여중인 소모임</h3>
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
  <!--두번째 슬라이드 -->
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
          src : "https://d2u3dcdbebyaiu.cloudfront.net/uploads/atch_img/811/4ad01fcc01e90c9b519c28dcaf03c5fb_res.jpeg"
          },
          {
          src : "https://images.mypetlife.co.kr/content/uploads/2019/12/09151959/%EC%8B%AC%EC%8B%AC%ED%95%9C_%EA%B3%A0%EC%96%91%EC%9D%B42-390x220.png"
          },
          {
          src : "http://picturebook-illust.com/upload_board/new_Gallery/ThumbNail/g/thumb_20202612533.jpg"
          },
          {
          src : "https://www.urbanbrush.net/web/wp-content/uploads/edd/2021/08/urbanbrush-20210830110315248584.jpg"
          },
          {
          src : "https://blog.kakaocdn.net/dn/dO534v/btrfpCSMoEj/Pk5p325io9NZhoECLijkW1/img.jpg"
          },
          {
          src : "https://www.urbanbrush.net/web/wp-content/uploads/edd/2020/03/urbanbrush-20200309105258442777.jpg"
          },
          {
          src : "https://mblogthumb-phinf.pstatic.net/MjAxOTA5MTBfMTAg/MDAxNTY4MTAxMjkwNTA1.1E93joFoC7KPpqB2ASVV6rjlBJV--3R1VspNJHQzW04g._0wErR-8InBgDJwoh2aP7cnz_14eq5Bcol1kCXcwcukg.JPEG.tomo_cat/IMG_5301.jpg?type=w800"
          },
          {
          src : "https://image.idus.com/image/files/d6fabae15e924a6bbcd7595a62715a30_720.jpg"
          },
      ]
      }
    }

  }
</script>
<style scoped>
  .container {
    display :flex;
  }
</style>