<template>
  <div>
  <ClassSidebar></ClassSidebar>
  <h3>강의 메인 페이지</h3>
  <router-link to="/OpenBankingTest" class="btn btn-info btn-sm mr-2">오픈뱅킹테스트</router-link> <br />
  <router-link to="/QuillEditorTest" class="btn btn-info btn-sm mr-2">에디터테스트</router-link> <br />
  <router-link to="/iamportTest" class="btn btn-info btn-sm mr-2">iamport테스트</router-link> <br />
  <router-link to="/AccountRealNameTest" class="btn btn-info btn-sm mr-2">계좌 실명 조회 테스트</router-link>
  </div>
</template>
<script>
  import ClassSidebar from "@/components/lecture/ClassSidebar.vue";

// highlight.js style
import "highlight.js/styles/tomorrow.css";

// import theme style

export default {
  components: {
    ClassSidebar
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
