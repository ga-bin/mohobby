<template>
  <div>
    <!-- 버튼 -->
  <BoardButton v-if="$route.name !== 'moimSchedule'&& $route.name !== 'moimVote' && $route.name !== 'moimNbbang'" />
  <BoardButton v-else style="visibility:hidden"/>
  <!-- 컴포넌트 -->
  <MoimSidebar :boardId="Id" />

  <!-- 권한에 따라 컴포넌트 다르게 보여주기 -->
  <router-view :boardId="Id" />
  </div>
</template>
<script>
  import MoimSidebar from "@/components/moim/moimDetail/MoimSidebar.vue";
  import BoardButton from "../../components/moim/moimDetail/board/BoardButton.vue";

  export default {
  components: { MoimSidebar, BoardButton },
  data() {
  return {
    Id : this.boardId
  }
  },
  setup() {},
  created() {
  },
  mounted() {},
  unmounted() {},
  methods : {
  },
  props: ['boardId'],
}
</script>

<style scoped>

.container {
  width : 75%;
}

</style>