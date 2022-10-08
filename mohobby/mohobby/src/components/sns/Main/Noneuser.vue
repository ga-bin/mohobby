<!-- none_user random postlist component in the list -->
<template>
  <div>
    <v-container fluid>
      <v-row dense>
        <v-col v-for="card in cards" :key="card.title" :cols="card.flex">
        <!-- <v-col :cols="card.flex"> -->
          <v-card @click="getFeedDetail()">
            <v-img 
              aspect-ratio="4/3"
             :src="require(`@/assets/image/sns/${card.thumbnail}`)"
              class="white--text align-end"
              gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
              height="180px"
            >
              <v-icon color="red">mdi-heart</v-icon>{{card.likes}} 
              <v-icon color="#ededed">mdi-chat-outline</v-icon>{{card.cmts}}
              <!-- <v-card-title v-text="card.title"></v-card-title> -->
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
        cards: [],
      };
    },
    created() {
      this.search();
    },
    methods : {
        search() {
          //AllList조회
          this.axios('/sns/main/allFeeds').then(res => {
              console.log(res);
              this.cards = res.data;
              
              console.log(this.cards);
              console.log(this.cards.data[0].cmts);
            }).catch(err =>{
              console.log(err);
            });
      },
      getFeedDetail() {
        this.$router.push({ name: "snsFeedDetail" });
      }
  }
};

</script>

<style>
  .v-container {
    margin : 0px;
  }
</style>
