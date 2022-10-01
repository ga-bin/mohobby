<template>
  <div class="team">
     <v-container fluid grid-list-xl>
      <v-layout wrap justify-space-around>
         <v-flex v-for="person in team" :key="person.role">
  <v-card
    class="mx-auto"
    max-width="320px"
  >
    <v-img
      src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
      height="200px"
    ></v-img>

    <v-card-title>
      Top western road trips
    </v-card-title>

    <v-card-subtitle>
      1,000 miles of wonder
    </v-card-subtitle>

    <v-card-actions>
      <v-btn
        color="orange lighten-2"
        text
      >
        Explore
      </v-btn>

      <v-spacer></v-spacer>

      <v-btn
        icon
        @click="show = !show"
      >
        <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
      </v-btn>
    </v-card-actions>

    <v-expand-transition>
      <div v-show="show">
        <v-divider></v-divider>

        <v-card-text>
          I'm a thing. But, like most politicians, he promised more than he could deliver. You won't have time for sleeping, soldier, not with all the bed making you'll be doing. Then we'll go with that data file! Hey, you add a one and two zeros to that or we walk! You're going to do his laundry? I've got to find a way to escape.
        </v-card-text>
      </div>
    </v-expand-transition>
  </v-card>
         </v-flex>
    </v-layout>
    </v-container>
  </div>
</template>
<script>
export default {
  data() {
    return {
      team : [
      { name : 'The net ninja', role : 'web developer'},
      { name : 'Rye', role : 'Graphic designer'},
      { name : 'Chun Li', role : 'web developer'},
      { name : 'Gouken', role : 'social media maverick'},
      { name : 'Yoshi', role : 'sales guru'},
      { name : 'Yoshi', role : 'sales guru'},
      { name : 'Yoshi', role : 'sales guru'},
      { name : 'Yoshi', role : 'sales guru'},
    ],
    show : false,
  }
  },
};
</script>
<style scoped>
.row {
  width: 100%,
  
}

.v-card {
  width: 100%;
}

.v-main {
  width: 100%;
}

.team {
  width: 100%;
}

.v-main__wrap {
  width : 100%;
}
</style>
