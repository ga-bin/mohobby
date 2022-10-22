<template>
  <div id="container">
    <div class="carousels">
      <v-carousel cycle>
        <v-carousel-item
          v-for="(item, i) in items"
          :key="i"
          :src="item.src"
          reverse-transition="fade-transition"
          transition="fade-transition"
        ></v-carousel-item>
      </v-carousel>
    </div>
    <v-spacer />
    <h3>SNS인기feed</h3>
    <snsHighLikesList :snsHighLikesList="snsHighLikesList"></snsHighLikesList>
    <h3>인기 소모임</h3>
    <moimPopularList :moimPopularList="moimPopularList"></moimPopularList>
    <h3>모집 중인 강의</h3>
    <collectClassList :collectClassList="collectClassList"></collectClassList>
    <h3>챌린저스 후기</h3>
    <challReview></challReview>
  </div>
</template>

<script>
import collectClassList from "../../components/main/CollectClassList.vue";
import moimPopularList from "../../components/main/MoimPopularList.vue";
import challReview from "../../components/main/ChallReview.vue";
import snsHighLikesList from "../../components/main/SnsHighLikesList.vue";

export default {
  components: {
    moimPopularList,
    challReview,
    snsHighLikesList,
    collectClassList,
    Image,
  },

  data() {
    return {
      items: [
        {
          src: "https://image.idus.com/image/files/945cc30fe6474be4aa0595b0fa10c663.jpg",
        },
        {
          src: "https://image.idus.com/image/files/4e22bce11c114e84a139e9625bced2dd.jpg",
        },
        {
          src: "https://image.idus.com/image/files/980f79a053774b1fb38c3200f73c4152.jpg",
        },
        {
          src: "https://image.idus.com/image/files/313d51459f094114afdb70d40e777cb8.jpg",
        },
      ],
      snsHighLikesList: [],
      moimPopularList: [],
      collectClassList: [],
    };
  },
  setup() {},
  created() {},
  mounted() {
    this.getSnsList();
    this.getMoimList();
    this.getClassList();
  },
  unmounted() {},
  methods: {
    getSnsList() {
      const vm = this;
      this.axios({
        url: "/mainsnslist",
        method: "get",
      })
        .then(function (response) {
          vm.snsHighLikesList = response;
        })
        .catch(function (error) {
          console.log(error);
        });
    },

    getMoimList() {
      const vm = this;
      this.axios({
        url: "/mainmoimlist",
        method: "get",
      })
        .then(function (response) {
          vm.moimPopularList = response;
          console.log(vm.moimPopularList);
        })
        .catch(function (error) {
          console.log(error);
        });
    },

    getClassList() {
      const vm = this;
      this.axios({
        url: "/mainclasslist",
        method: "get",
      })
        .then(function (response) {
          vm.collectClassList = response;
          console.log(vm.collectClassList);
        })
        .catch(function (error) {
          console.log(error);
        });
    },
  },
};
</script>

<style>
.container {
  width: 100%;
}
.carousels {
  position: relative;
  width: 100%;
}

.carousels .v-window {
  height: 400px !important;
  width: 100%;
}
</style>
