<template>
  <div>
    <SnsSidebar />
    <h1>추천 만능 재주꾼들 피드</h1>

    <h1>재주 견습생들 피드</h1>
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
    <Observe @triggerIntersected="loadMore"/> <!--옵저버가 감지됐을때 loadMore함수 실행.-->
  </div>
</template>
<script>
//import SnsSidebar from "../../components/sns/SnsSidebar.vue"; 

export default {
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
  setup() {
    //로드해올 데이터를 정의(postList)
    const loadMore = async $state => {
      console.log("loading post...");
    }
    /*
    const page = ref(1); //page를 1씩 올려서 다음 페이지를 가지고 온다.
    const loadMore = async () => {
    const fetchData = await getAllMoviesAPI(page.value + 1);
    if (fetchData) {
      await store.dispatch("movie/setMoreMovies", fetchData); //store에 fetchData저장.
    } //dispatch('함수명', '전달인자') 순서에 상관없이 먼저 종료된 함수의 피드백을 받아 후속처리하는 비동기 처리방식
    page.value += 1;
    };

    return {
      loadMore
    };
    */
  },
  created() {
  
  },
  mounted() {

  },
  unmounted() {

  },
  methods: {

  },
};
</script>

<style></style>
