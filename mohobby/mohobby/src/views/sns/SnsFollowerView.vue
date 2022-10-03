<template>
    <div>
      <SnsSidebar></SnsSidebar>
      <h1>sns 팔로워 관리 화면</h1>
      <div id="app">
      <v-app id="inspire">
        <v-hover v-slot="{ hover }">
        <v-card
          class="mx-auto"
          color="grey lighten-4"
          max-width="600"
        >
        <v-img
          :aspect-ratio="4/3"
          src="https://img.insight.co.kr/static/2020/03/06/700/j0rpqkg2y659dw4acito.jpg"
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
          <div class="font-weight-light text-h6 mb-2">
            Our Vintage kitchen utensils delight any chef.<br>
            Made of bamboo by hand
          </div>
        </v-card-text>
      </v-card>
    </v-hover>
  </v-app>
</div>
    </div>
  </template>
<script>
  import SnsSidebar from "../../components/sns/SnsSidebar.vue";
  export default {
    name: "",
    components: { SnsSidebar },
    data() {
      return {
        likecnt : "159",
        cmtcnt : "10"
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
  