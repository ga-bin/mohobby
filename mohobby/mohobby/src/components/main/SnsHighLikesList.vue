<template>
  <v-container fluid>
    <v-sheet max-width="2000">
      <v-slide-group class="pa-2">
        <v-slide-item
          v-for="snsfeed in snsHighLikesList.data"
          :key="snsfeed.index"
        >
          <div class="displayflex">
            <v-card width="250px" @click="getFeedDetail(snsfeed.postId)">
              <v-img
                :src="require(`@/assets/image/sns/${snsfeed.thumbnail}`)"
                class="white--text align-end"
                gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                height="200px"
              >
                <v-card-title v-text="snsfeed.memberId"></v-card-title>
              </v-img>

              <v-card-actions>
                <v-spacer></v-spacer>

                <v-btn icon>
                  <v-icon color="red">mdi-heart</v-icon>
                </v-btn>

                <span>{{ snsfeed.likes }}</span>
              </v-card-actions>
            </v-card>
          </div>
        </v-slide-item>
      </v-slide-group>
    </v-sheet>
  </v-container>
</template>
<script>
export default {
  components: {},
  props: {
    snsHighLikesList: {
      type: Array,
      required: true,
    },
  },
  data() {
    return {
      sampleData: "",
    };
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
    getFeedDetail(postId) {
      console.log(postId);
      this.$router.push({ name: "snsFeedDetail", query: { id: postId } });
    },
  },
};
</script>
<style scoped>
.displayflex {
  display: inline-block;
  width: 270px;
  height: 300px;
  max-width: 100%;
}
</style>
