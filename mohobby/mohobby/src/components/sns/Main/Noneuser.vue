<!-- none_user random postlist component in the list -->
<template>
  <div>
    <v-container fluid>
      <v-row dense>
        <v-col v-for="feed in feeds" :key="feed.title" :cols="2">
          <v-card @click="getFeedDetail(feed.postId)">
            <v-img 
             :aspect-ratio="4/3"
             :src="require(`@/assets/image/sns/${feed.thumbnail}`)"
              class="white--text align-end"
              gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
              height="150px"
              width="180px"
            >
              <v-icon color="red">mdi-heart</v-icon>{{feed.likes}} 
              <v-icon color="#ededed">mdi-chat-outline</v-icon>{{feed.cmts}}
            </v-img>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>
<script>
export default {
  name: "NoneUser",
  // props : ["card"],
  data() {
      return {
        feeds: [],
      };
    },
    created() {
      this.search();
    }, 
    mounted () {
      // this.feeds;
      window.addEventListener('scroll', this.handleScroll);
    },
    methods : {
      search() {
        //AllList조회
        this.axios('/sns/main/allFeeds').then(res => {
            console.log(res);
            this.feeds = res.data;
            console.log("noneUser 로드 성공")
          }).catch(err =>{
            console.log(err);
          });
      },
      //디테일피드 테스트버튼
      getFeedDetail(postId) {
        this.$router.push({ name: 'snsFeedDetail',
                            query: {id : postId} 
                          });
      },
      //infinite scroll
      handleScroll() {
      if (
        window.scrollY + window.innerHeight >=
        document.body.scrollHeight - 50
        ) {
          const new_feeds = this.feeds;
        this.feeds = [...this.feeds, ...new_feeds];
        }
      },  
  }
};

</script>

<style>
  .v-container {
    margin : 0px;
  }
</style>
