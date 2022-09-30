<template>
  <div>
    <div class="carousels">
      <v-carousel cycle>
        <v-carousel-item
          v-for="(item, i) in items"
          :key="i"
          :src="item.src"
          reverse-transition="fade-transition"
          transition="fade-transition"
        ></v-carousel-item>
      </v-carousel>
    </div>
    <v-spacer />
    <h4>SNS인기글</h4>
    <v-container fluid>
      <v-row dense>
        <v-col v-for="card in cards" :key="card.title" :cols="card.flex">
          <v-card>
            <v-img
              :src="card.src"
              class="white--text align-end"
              gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
              height="200px"
            >
              <v-card-title v-text="card.title"></v-card-title>
            </v-img>

            <v-card-actions>
              <v-spacer></v-spacer>

              <v-btn icon>
                <v-icon>mdi-heart</v-icon>
              </v-btn>

              <v-btn icon>
                <v-icon>mdi-bookmark</v-icon>
              </v-btn>

              <v-btn icon>
                <v-icon>mdi-share-variant</v-icon>
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
    <h4>새로 생성된 모임</h4>
    <moimlist></moimlist>
    <moimlist></moimlist>
    <moimlist></moimlist>
    <moimlist></moimlist>
  </div>
</template>

<script>
import moimlist from "../../components/common/moimlist.vue";
export default {
  components: { moimlist },
  data() {
    return {
      items: [
        {
          src: "https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg",
        },
        {
          src: "https://cdn.vuetifyjs.com/images/carousel/sky.jpg",
        },
        {
          src: "https://cdn.vuetifyjs.com/images/carousel/bird.jpg",
        },
        {
          src: "https://cdn.vuetifyjs.com/images/carousel/planet.jpg",
        },
      ],
      cards: [
        {
          title: "Pre-fab homes",
          src: "https://cdn.vuetifyjs.com/images/cards/house.jpg",
          flex: 2,
        },
        {
          title: "Favorite road trips",
          src: "https://cdn.vuetifyjs.com/images/cards/road.jpg",
          flex: 2,
        },
        {
          title: "Best airlines",
          src: "https://cdn.vuetifyjs.com/images/cards/plane.jpg",
          flex: 2,
        },
        {
          title: "Pre-fab homes1",
          src: "https://cdn.vuetifyjs.com/images/cards/house.jpg",
          flex: 2,
        },
        {
          title: "Favorite road trips2",
          src: "https://cdn.vuetifyjs.com/images/cards/road.jpg",
          flex: 2,
        },
        {
          title: "Best airlines3",
          src: "https://cdn.vuetifyjs.com/images/cards/plane.jpg",
          flex: 2,
        },
      ],
    };
  },
};
</script>

<style>
.carousels {
  position: relative;
}

.carousels .v-window {
  height: 400px !important;
}
</style>
