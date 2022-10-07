<template>
  <div>
    <MoimPhotoTop></MoimPhotoTop>
    <MoimPhoto></MoimPhoto>
  </div>
</template>
<script>
  import MoimSidebar from "@/components/moim/moimDetail/MoimSidebar.vue";
  import MoimPhoto from "@/components/moim/moimDetail/photo/MoimPhoto.vue";
import MoimPhotoTop from "../../../components/moim/moimDetail/photo/MoimPhotoTop.vue";
  export default {
  components: { MoimSidebar, MoimPhoto, MoimPhotoTop },
  data: () => ({
    }),
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {},
};
</script>
<style>

</style>