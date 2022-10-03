<template>
  <div>
    <!-- <MoimSidebar></MoimSidebar> -->
    <MoimMain_Top></MoimMain_Top>
    <MoimMainrecruit></MoimMainrecruit>
    <MoimMainrecruit2></MoimMainrecruit2>
  </div>
</template>
<script>
import MoimSidebar from "../../components/moim/MoimSidebar.vue";
import MoimMain_Top from "../../components/moim/Main/MoimMain_Top.vue";
import MoimMainrecruit from "../../components/moim/Main/MoimMain_recruit.vue";
import MoimMainrecruit2 from "../../components/moim/Main/MoimMain_recruit2.vue";

export default {
  name: "",
  components: { MoimSidebar, MoimMain_Top, MoimMainrecruit, MoimMainrecruit2 },
  data: () => ({
    }),
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {},
};
</script>

<style></style>
