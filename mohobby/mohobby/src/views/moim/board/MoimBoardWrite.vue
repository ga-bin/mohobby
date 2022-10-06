<template>
  <div class="container">
    <MoimSidebar></MoimSidebar>
    <MoimBoardTitle></MoimBoardTitle>
    <MoimBoardEditor></MoimBoardEditor>
    <div class="center">
      <v-btn>취소</v-btn>
      <v-btn color="success" class="ml-5">작성완료</v-btn>
    </div>
  </div>
</template>

<script>
  import MoimSidebar from "@/components/moim/moimDetail/MoimSidebar.vue"
  import MoimBoardTitle from "@/components/moim/moimDetail/board/write/MoimBoardTitle.vue"
  import MoimBoardEditor from "@/components/moim/moimDetail/board/write/MoimBoardEditor.vue";
  export default {
    components : { MoimSidebar, MoimBoardTitle, MoimBoardEditor }
  }

</script>

<style scoped>
    .center {
      margin-top: 30px;
      display: flex;
      justify-content : center;
    }
</style>