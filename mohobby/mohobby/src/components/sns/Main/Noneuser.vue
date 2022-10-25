<!-- none_user random postlist component in the list -->
<template>
  <div>
    <v-container fluid>
      <v-row>
        <v-col v-for="(feed,i) in feeds" :key="i" class="d-flex child-flex" cols="3">
          <div @click="goFeedDetail(feed.postId, feed.memberId)"> 
            <v-img :src="require(`@/assets/image/sns/${feed.postId}/${feed.thumbnail}`)"
                  class="white--text align-end rounded-sm" aspect-ratio="1.2"
               gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)">
              <div class="ml-6 mb-5">
                <v-icon color="#ededed">mdi-heart</v-icon><span>{{feed.likes}}</span> 
                <v-icon class="ml-2" color="#ededed">mdi-chat-outline</v-icon><span>{{feed.cmts}}</span>
              </div>
              <template v-slot:placeholder>
                <v-row class="fill-height ma-0" align="center" justify="center">
                  <v-progress-circular indeterminate color="grey lighten-5" />
                </v-row>
              </template>
            </v-img>
          </div>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>
<script>
export default {
  name: "NoneUser",
  props: {
    feeds : [],
    keyword : String,
  },
  data() {
      return {
      };
    },
    created() {
      // this.search(); 
      // this.feeds = this.feeds;
      console.log(this.feeds);

    }, 
    
    // mounted () {
    //   window.addEventListener('scroll', this.handleScroll);
    // },

    methods : {

      //디테일피드 테스트버튼
      goFeedDetail(postId, memberId) {
        console.log(memberId);
        this.$router.push({ path: "/snsFeedDetail", query: {postId : postId, writer : memberId} });
      },


      //무한스크롤
      // handleScroll() {
      // if (
      //   window.scrollY + window.innerHeight >=
      //   document.body.scrollHeight - 50
      //   ) {
      //     const new_feeds = this.feeds;
      //   this.feeds = [...this.feeds, ...new_feeds];
      //   }
      // },  
  }
};

</script>