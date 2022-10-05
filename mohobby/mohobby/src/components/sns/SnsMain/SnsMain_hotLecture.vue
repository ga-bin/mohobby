<template>
    <div>
      <SnsSidebar></SnsSidebar>
      <h1>sns 팔로워 관리 화면</h1>
            <div id="app" 
                  v-for="(item,idx) in items"
                  :key="idx">
              <v-app id="inspire">
                <v-hover v-slot="{ hover }">
                <v-card
                  class="mx-auto"
                  color="grey lighten-4"
                  max-width="200"
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
                    <v-icon color="white">mdi-heart</v-icon>{{likecnt}}
                    <v-icon color="white">mdi-chat</v-icon>{{cmtcnt}}
                    </div>
                  </v-expand-transition>
                </v-img>
                <v-card-text
                  class="pt-6"
                  style="position: relative;"
                >
                  <v-btn
                    absolute
                    color="red"
                    class="white--text"
                    fab
                    large
                    right
                    top
                  >
                    <v-icon>mdi-heart</v-icon>
                  </v-btn>
                  <div class="font-weight-light text-h7 mb-1">
                    Our Vintage kitchen utensils delight any chef..
                  </div>
                </v-card-text>
              </v-card>
            </v-hover>
          </v-app>
      </div>
    </div>
  </template>
<script>
  import SnsSidebar from "../SnsCommn/SnsSidebar.vue";
  export default {
    name: "HotLecturer",
    components: { SnsSidebar },
    data() {
      return {
        items : ,
        likecnt : "159",
        cmtcnt : "10",
        imgsrc: "https://img.insight.co.kr/static/2020/03/06/700/j0rpqkg2y659dw4acito.jpg"
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
  