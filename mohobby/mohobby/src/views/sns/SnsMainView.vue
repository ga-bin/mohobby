<template>
  <div id = "container">
    <SnsSearchbar></SnsSearchbar>
    <!-- 인기 만능재주꾼 피드리스트 -->
    <HotLecturerList></HotLecturerList>

    <!-- 카드형 무한스크롤링 -->
    <ul>
      <li v-if="noneUser===9">   
        <NoneUser v-for="(card, i) in card_list" 
                  :key="i" 
                  :card="card_list" 
        />
      </li>
    </ul>
    <!-- <listCmp 
          :card="card_list"
    /> -->
  </div>
</template>
<script>
  import SnsSearchbar from "../../components/sns/SnsCommn/SnsSearchbar"
  import HotLecturerList from "../../components/sns/SnsMain/SnsMain_hotLecturerList";
  import NoneUser from "../../components/sns/SnsMain/SnsMain_noneuser";

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
