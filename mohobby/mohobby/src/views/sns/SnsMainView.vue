<template>
  <div>
    <SnsSidebar />
      <main id="snsMain">
        <div class="cards">
          
          <h1>추천 만능 재주꾼들 피드</h1>

          <h1>재주 견습생들 피드</h1>
            <SnsPost v-for="card in cards"
            :key="card.title"
            :card="card"
            />
        </div>
      </main>
  </div>
</template>
<script>
//import SnsSidebar from "../../components/sns/SnsSidebar.vue"; 
import SnsPost from "./SnsPost.vue";
export default {
  name: "snsMain",
  components: {
    SnsPost
  },
  data() {
    return {
      cards: [
        {
          title: "1",
          src: require("https://img.insight.co.kr/static/2020/03/06/700/j0rpqkg2y659dw4acito.jpg"),
          cmtcnt: ""
        },
        {
          title: "2",
          src: require("https://img.insight.co.kr/static/2020/03/06/700/w73pq7140798cym777d8.jpg"),
          cmtcnt: ""
        },
        {
          title: "3",
          src: require("https://img.insight.co.kr/static/2020/03/06/700/d852a5r04tvpa860xz92.jpg"),
          cmtcnt: ""
        },
        {
          title: "4",
          src: require("https://img.insight.co.kr/static/2020/03/06/700/o5384w751o0583qs2659.jpg"),
          cmtcnt: ""
        },
        {
          title: "5",
          src: require("https://img.insight.co.kr/static/2020/03/06/700/4o5ookw8k16p4k5jgzur.jpg"),
          cmtcnt: ""
        },
      ]
    };
  },
  methods: {
    
    getCard() {
      const card_titles = [
        "1"
       ,"2"
       ,"3"
       ,"4"
       ,"5"
       ,
      ];

      const card_srcs = [
      "https://img.insight.co.kr/static/2020/03/06/700/j0rpqkg2y659dw4acito.jpg"
      ,"https://img.insight.co.kr/static/2020/03/06/700/w73pq7140798cym777d8.jpg"
      ,"https://img.insight.co.kr/static/2020/03/06/700/d852a5r04tvpa860xz92.jpg"
      ,"https://img.insight.co.kr/static/2020/03/06/700/o5384w751o0583qs2659.jpg"
      ,"https://img.insight.co.kr/static/2020/03/06/700/4o5ookw8k16p4k5jgzur.jpg"
      ,
      ];
    
      const card = [];

      for (let i = 0; i < 5; i++) {
        card.push({
          title: card_titles[
            Math.floor(Math.random() * card_titles.length)
          ],
          src: card_srcs[
            Math.floor(Math.random() * card_src.length)
          ],
        });
      }

      return card;
    },
    
   handleScroll() {
    if (
      window.scrollY + window.innerHeight >=
      document.body.scrollHeight - 50
      ) {
        const new_card = this.getCard();
      this.card_list = [...this.card_list, ...new_card];
    }
   }
  },

  mounted () {
    this.card_list = this.getCard();
    window.addEventListener('scroll', this.handleScroll);
  }
};
</script>

<style>
  * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
  }
  main {
    width: 100vw;
    min-height: 100vh;
    overflow: hidden;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .card{
    display: flex;
    max-width: 1280;
    padding: 25px;
    margin: 0 auto;
  }
</style>

