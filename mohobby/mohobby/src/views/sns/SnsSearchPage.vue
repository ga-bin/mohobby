<template>
  <div>
    <v-container fluid>
      <h3>해시태그 검색결과 페이지</h3>
      <div id = "noSearchResult" v-if='feeds == ""'>
          <h1>검색 결과가 없습니다 !</h1>
      </div>
      <v-row dense v-else>
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
  name:'SearchPage',
  props: {
    feeds : [],
  },
  data() {
    return{
      
    }
  },
  created(){
    console.log("검색컴포넌트 입장!");
  },
  methods: {
    getFeedDetail(postId) {
        this.$router.push({ name: 'snsFeedDetail', query: {id : postId} });
      },
  },
}
</script>
