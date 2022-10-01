<template>
  <div>
    <v-container fluid>
      <v-row dense>
        <v-col v-for="card in cards" :key="card.title" :cols="card.flex">
          <v-card>
            <v-img
              :src="card.src"
              class="white--text align-end"
              gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
              height="200px"
            >
              <v-card-title v-text="card.title"></v-card-title>
            </v-img>
            <!-- <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn icon>
                <v-icon>mdi-heart</v-icon>
              </v-btn>
              <v-btn icon>
                <v-icon>mdi-bookmark</v-icon>
              </v-btn>
              <v-btn icon>
                <v-icon>mdi-share-variant</v-icon>
              </v-btn>
            </v-card-actions> -->
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>
<script>
export default {
  props : ["card"],
  name: "",
  components: { },
  data() {
    return {
      sampleData: "",
      cards: [
        {
          title: "",
          src: "https://img.insight.co.kr/static/2020/03/06/700/j0rpqkg2y659dw4acito.jpg",
          flex: 2,
        },
        {
          title: "",
          src: "https://img.insight.co.kr/static/2020/03/06/700/w73pq7140798cym777d8.jpg",
          flex: 2,
        },
        {
          title: "",
          src: "https://img.insight.co.kr/static/2020/03/06/700/d852a5r04tvpa860xz92.jpg",
          flex: 2,
        },
        {
          title: "",
          src: "https://img.insight.co.kr/static/2020/03/06/700/o5384w751o0583qs2659.jpg",
          flex: 2,
        },
        {
          title: "",
          src: "https://img.insight.co.kr/static/2020/03/06/700/4o5ookw8k16p4k5jgzur.jpg",
          flex: 2,
        },
      ],
    };
  },

  methods: {

  },
};
</script>

<style></style>
