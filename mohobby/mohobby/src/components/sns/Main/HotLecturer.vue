<!-- 인기강사TOP20피드 -->
<template>
    <div id="container" class="mx auto">
      <v-container fluid>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-row text-center>
            <v-col v-for="item in items" :key="item.postId" cols="2">
              <div @click="getFeedDetail(item.postId)" style="width:200px;">
                <v-hover v-slot="{ hover }">
                  <v-card
                    class="mx-auto"
                    color="grey lighten-4"
                    max-width="250"
                  >
                  <v-img
                    style="display:flex;"
                    :aspect-ratio="4/3"
                    :src="require(`@/assets/image/sns/${item.thumbnail}`)"
                  >
                    <v-expand-transition>
                      <div
                        v-if="hover"
                        class="d-flex transition-fast-in-fast-out black darken-2 v-card--reveal text-small white--text"
                        style="height: 100%;"
                      >
                      <div>
                        <v-icon color="white">mdi-heart</v-icon> {{ item.likes }}
                        <v-icon color="white">mdi-chat</v-icon> {{ item.cmts }}
                      </div>
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
                    @click="search()"
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
                    {{ item.content }}..
                  </div>
                </v-card-text>
              </v-card>
            </v-hover>
          </div>
      </v-col>
    </v-row>
    </v-card-actions>
    </v-container>
    </div>
</template>
<script>

  export default {
    name: "HotLecturer",
    props:{
      items : []
    },
    data() {
      return {
        // items: [],
      };
    },
    setup() {
      
    },
    created() {
      this.getHotLecturerList();
    },
    mounted() {
      
    },
    unmounted() {
  
    },
    methods: {
      getFeedDetail(postId) {
        this.$router.push({ name: 'snsFeedDetail', query: {id : postId} });
      },
      getHotLecturerList() {
        //hotLectureList조회
        this.axios('/sns/main/top20LecturerFeeds').then(res => {
            console.log(res);
            this.items = res.data;
            console.log("getHotLecturerList받아오기 성공")
          }).catch(err =>{
            console.log(err);
          });
      },
      // feedDetail(postId){
      //   this.$router.push( '/snsFeedDetail?postId='+ postId)
      feedDetail(){
      this.$router.push( '/snsFeedDetail');
      }
    }
  };
  </script>
  <style scoped>
  #container{
      box-sizing: border-box;
      margin:0 auto;
      width: 80%;
    }
  .v-card--reveal {
    align-items: center;
    bottom: 0;
    justify-content: center;
    opacity: .5;
    position: absolute;
    width: 100%;
  }
  </style>
  