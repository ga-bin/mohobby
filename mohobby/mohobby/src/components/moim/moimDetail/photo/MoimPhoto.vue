<template>
  <div>
    <div class="container">
      <h1>사진첩</h1>
      <hr>
      <br>
  
      <v-row>
        <v-col v-for="n in items" :key="n" class="d-flex child-flex" cols="4">
          <div id="main-product-img">
            <img :src="n.src" />
            <!-- <img :src="`https://picsum.photos/500/300?image=${n * 5 + 10}`"/> -->
            
          </div>
          <template v-slot:placeholder>
            <v-row class="fill-height ma-0" align="center" justify="center">
              <v-progress-circular indeterminate color="grey lighten-5"></v-progress-circular>
            </v-row>
          </template>
        </v-col>
      </v-row>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      items: [{
        src: require('@/assets/image/moim/board/7-0.jpg')
      },
      {
        src: require('@/assets/image/moim/board/8-0.jpg')
      },
      {
        src: require('@/assets/image/moim/board/9-0.jpg')
      },
      {
        src: require('@/assets/image/moim/board/10-0.jpg')
      },
      ]
    }
  }

}
</script>

<style scoped>
.container {
  width: 70%;
}

#main-product-img img {
  width: 270px;
  height: 300px;
  max-width: 100%;
  transition: all 0.2s linear;
  border-radius: 0.8em;
}

#main-product-img {
  width: 270px;
  height: 300px;
  border-radius: 0.8em;
  margin: 0px auto;
  overflow: hidden;
}

#main-product-img:hover img {
  transform: scale(1.05);
}
</style>