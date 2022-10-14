<template>
  <div>
    <!-- 권한에 따라 컴포넌트 다르게 보여주기 -->
    <MoimSidebar/>
    <!-- <MoimSidebar_admin></MoimSidebar_admin> -->

    <router-view/>
  </div>
</template>
<script>
import MoimSidebar from "@/components/moim/moimDetail/MoimSidebar.vue";
import MoimSidebar_admin from "@/components/moim/moimDetail/MoimSidebar_admin.vue";

export default {
  components: { MoimSidebar, MoimSidebar_admin },
  data() {
    return {
     
    }
  },
  setup() { },
  created() {
  },
  mounted() { },
  unmounted() { },
  methods: {
  },
  props: [],
}
</script>

<style scoped>
.container {
  width: 75%;
}
</style>