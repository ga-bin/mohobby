<template>
  <div id = "container">
    <div>
      <v-btn @click="goUserFeed()">유저피드</v-btn>
      <v-btn @click="goFeedInsert()">글쓰기</v-btn>
    </div>
    <SnsSearchbar></SnsSearchbar>
    <!-- 인기 피드리스트 -->
    <h3>추천 만능 재주꾼들 피드</h3>
    <HotLecturerList></HotLecturerList>
    <h3>재주 견습생들 피드</h3>
    <!-- 랜덤피드 무한스크롤링 -->
    <ul>
      <li v-if="noneUser===9">   
        <NoneUser />
      </li>
    </ul>
  </div>
</template>
<script>
  import SnsSearchbar from "../../components/sns/Common/Searchbar"
  import HotLecturerList from "../../components/sns/Main/HotLecturerList";
  import NoneUser from "../../components/sns/Main/Noneuser";

  export default {
    name: "snsMain",
    components: { SnsSearchbar, HotLecturerList, NoneUser, SnsSearchbar },
    data() {
      return{
        card_list : [],
        noneUser : 9 //memberId=="" && !memberId
      }
    },
    methods: {
      //list dummy data
      getCard() {
        const card = [];

        const card_titles = [
         "1"
        ,"2"
        ,"3"
        ,"4"
        ,"5"
        ,"6"
        ];
        const card_srcs = [
         require(`@/assets/image/sns/캠핑갬성1.png`)
        ,require(`@/assets/image/sns/캠핑갬성2.png`)
        ,require(`@/assets/image/sns/캠핑갬성3.png`)
        ,require(`@/assets/image/sns/캠핑갬성4.png`)
        ,require(`@/assets/image/sns/캠핑갬성5.png`)
        ,require(`@/assets/image/sns/캠핑갬성6.png`)
        ];
        const card_likecnts = [
          "153",
          "200",
          "198",
          "123",
          "98",
          "159"
        ];
        const card_cmtcnts = [
          "20",
          "12",
          "10",
          "19",
          "32",
          "60"
        ];

        for (let i = 0; i < 6; i++) {
          card.push({
            title: card_titles[
              Math.floor(Math.random() * card_titles.length)
            ],
            src: card_srcs[
              Math.floor(Math.random() * card_srcs.length)
            ],
            likecnt: card_likecnts[
              Math.floor(Math.random() * card_likecnts.length)
            ],
            cmtcnt: card_cmtcnts[
              Math.floor(Math.random() * card_cmtcnts.length)
            ],
            flex:"2"
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
      },
      goUserFeed() {
        this.$router.push({ name: "SnsUserFeed" });
      },
      goFeedInsert() {
        this.$router.push({ name: "SnsFeedInsert" });
      }
    },

    mounted () {
      this.card_list = this.getCard();
      window.addEventListener('scroll', this.handleScroll);
    }
};
</script>

<style scoped>
  * {
    list-style:none;
  }
  #container {
    width : 80%;
    list-style:none;
    margin: 0 auto;
  }
</style>
