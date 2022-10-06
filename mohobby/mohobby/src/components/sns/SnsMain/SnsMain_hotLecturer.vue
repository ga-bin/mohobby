<template>
    <div id="app">
      <div>
          <v-hover v-slot="{ hover }">
            <v-card
              class="mx-auto"
              color="grey lighten-4"
              max-width="250"
            >
            <v-img
              :aspect-ratio="4/3"
              :src="imgsrc"
            >
              <v-expand-transition>
                <div
                  v-if="hover"
                  class="d-flex transition-fast-in-fast-out black darken-2 v-card--reveal text-small white--text"
                  style="height: 100%;"
                >
                <v-icon color="white">mdi-heart</v-icon>{{ likecnt }}
                <v-icon color="white">mdi-chat</v-icon>{{ cmtcnt }}
                </div>
              </v-expand-transition>
            </v-img>
            <v-card-text
              class="pt-5"
              style="position: relative;"
            >
              <v-btn
                absolute
                color="red"
                class="white--text"
                fab
                small
                right
                top
              >
                <v-icon small>mdi-heart</v-icon>
              </v-btn>
              <!-- 좋아요 후 색상 -->
              <!-- <v-btn
                absolute
                color="white"
                class="#2ac187--text"
                fab
                small
                right
                top
              >
                <v-icon small>mdi-heart</v-icon>
              </v-btn> -->
              <div class="font-weight-light text-h7 mb-1">
                <!-- 20자 이내 -->
                We just can say ENTER..
              </div>
            </v-card-text>
          </v-card>
        </v-hover>
      </div>
    </div>
</template>
<script>
  export default {
    name: "HotLecturer",
    data() {
      return {
        items: [],
        likecnt : "159",
        cmtcnt : "10",
        imgsrc: require(`@/assets/image/sns/기도.png`)
      };
    },
    setup() {
      
    },
    created() {
  
    },
    mounted() {
  
    },
    unmounted() {
  
    },
    methods: {
      getItems_list() {
            // const item = [];
            const item_srcs = [
                { src: require(`@/assets/image/sns/기도.png`) },
                { src: "https://img.insight.co.kr/static/2020/03/06/700/w73pq7140798cym777d8.jpg" },
                { src: "https://img.insight.co.kr/static/2020/03/06/700/d852a5r04tvpa860xz92.jpg" },
                { src: "https://img.insight.co.kr/static/2020/03/06/700/o5384w751o0583qs2659.jpg" },
                { src: "https://img.insight.co.kr/static/2020/03/06/700/4o5ookw8k16p4k5jgzur.jpg" }
            ];
            for (let i = 0; i <= 20; i++) {
                this.items.push(item_srcs[Math.floor(Math.random() * item_srcs.length)]);
            }
        },
    },
  };
  </script>
  <style>
    .v-card--reveal {
    align-items: center;
    bottom: 0;
    justify-content: center;
    opacity: .5;
    position: absolute;
    width: 100%;
    }
  </style>
  