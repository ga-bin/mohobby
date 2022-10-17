<template>
  <div>
    <h3>강의 테스트 페이지</h3>
    <nav>
      <router-link to="/classmain/test/OpenBankingTest" class="btn btn-info btn-sm mr-2">오픈뱅킹테스트</router-link> | 
      <router-link to="/classmain/test/QuillEditorTest" class="btn btn-info btn-sm mr-2">에디터테스트</router-link> | 
      <router-link to="/classmain/test/iamportTest" class="btn btn-info btn-sm mr-2">iamport테스트</router-link> | 
      <router-link to="/classmain/test/AccountRealNameTest" class="btn btn-info btn-sm mr-2">계좌 실명 조회 테스트</router-link> | 
      <router-link to="/classmain/test/AttdQRTest" class="btn btn-info btn-sm mr-2">QR코드 생성 테스트</router-link> | 
      <router-link to="/classmain/test/KakaoMapTest" class="btn btn-info btn-sm mr-2">카카오 맵 테스트</router-link> | 
      <router-link to="/classmain/test/VideoTest" class="btn btn-info btn-sm mr-2">비디오 테스트</router-link> | 
    </nav>
    <router-view />
  </div>
</template>
<script>

// highlight.js style
import "highlight.js/styles/tomorrow.css";

// import theme style

export default {
  components: {
},
  data() {
    
  },
  setup() {},
  created() {},
  mounted: function () {
    
  },
  unmounted() {},
  methods: {
   
  },
  computed: {
   
  }
};
</script>

<style scoped>
  form {
    display: inline-block;
  }
  button {
    border: 1px solid gray;
  }
  input[type=submit] {
    border: 1px solid gray;
  }
</style>
