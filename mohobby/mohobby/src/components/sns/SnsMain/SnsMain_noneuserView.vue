<!-- none_user random postlist component in the list -->
<template>
  <div>
    <v-container fluid>
      <v-row dense>
        <v-col v-for="card in card" :key="card.title" :cols="card.flex">
        <!-- <v-col :cols="card.flex"> -->
          <v-card @click="getFeedDetail()">
            <v-img 
             :aspect-ratio="1/1"
             :src="card.src"
              class="white--text align-end"
              gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
              height="180px"
            >
              <v-icon>mdi-heart</v-icon>{{card.likecnt}} 
              <v-icon>mdi-chat-outline</v-icon>{{card.cmtcnt}}
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
  name: "noneUser",
  props : ["card"],
  methods : {
    
  }
};

</script>

<style>
  .v-container {
    margin : 0px;
  }
</style>
