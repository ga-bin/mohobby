<template>
  <div class="container">
    <BoardNoAuthority v-if="moim===0 && moimRight===0"></BoardNoAuthority>
    <MoimBoard v-else></MoimBoard>
  </div>
</template>

<script>
    import MoimBoard from "@/components/moim/moimDetail/board/MoimBoard.vue";
    import BoardNoAuthority from "@/components/moim/moimDetail/board/BoardNoAuthority.vue"

    export default {
  components: {MoimBoard, BoardNoAuthority },
  data: () => ({

    fclick: true,
    // 비공개 : 0, 공개 : 1
    moim: 1,
    moimRight : 1
    }),
  }
</script>

<style scoped>
  .container {
  width : 80%;
}
</style>