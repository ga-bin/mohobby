<template>
  <div>
  <div id="moim">
    <!-- <MoimSidebar></MoimSidebar> -->
    <h1>Mo#obby 소모임</h1>
    <a href="#">+ 소모임 모집하기</a>
    <div>
      <h3>내가 참여중인 소모임</h3>
      <img src="#">
      <img src="#">
    </div>
    <div>
      <h3>내가 운영중인 소모임</h3>
      <img src="#">
      <img src="#">
    </div>
    <!-- 소모임 검색창 -->
    <div>
      <input type="text" style="border: 1" placeholder="소모임 명을 검색하세요!"/>
      <button type="button">검색</button>
    </div>

    <!-- 소모임 키워드 -->
    <div>
      <p>전체</p>
      <p>취미</p>
      <p>스포츠/레저</p>
      <p>어학/외국어</p>
      <p>문화/예술</p>
      <p>여행/캠핑</p>
      <p>음악</p>
      <p>건강/다이어트</p>
      <p>친목/모임</p>
      <p>맛집/요리</p>
      <p>인문/과학</p>
      <p>팬클럽</p>
      <p>게임</p>
      <p>만화/애니메이션</p>
      <p>반려동물/동물</p>
      <p>교육/공부</p>
      <p>IT/컴퓨터</p>
      <p>경제/재테크</p>
      <p>종교/봉사</p>
      <p>자연/귀농</p>
    </div>
  </div>
  <MoimMainrecruit></MoimMainrecruit>
  <MoimMainrecruit2></MoimMainrecruit2>
</div>
</template>
<script>
import MoimSidebar from "../../components/moim/MoimSidebar.vue";
import MoimMainrecruit from "../../components/moim/MoimMain_recruit.vue";
import MoimMainrecruit2 from "../../components/moim/MoimMain_recruit2.vue";
export default {
  name: "",
  components: {
    MoimSidebar,
    MoimMainrecruit,
    MoimMainrecruit2,
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
  methods: {},
};
</script>

<style></style>
