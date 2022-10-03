<template>
  <div>
  <MoimMain_Top></MoimMain_Top>
  <MoimMainrecruit></MoimMainrecruit>
  <MoimMainrecruit2></MoimMainrecruit2>
</div>
</template>
<script>
import MoimSidebar from "../../components/moim/MoimSidebar.vue";
import MoimMainrecruit from "../../components/moim/Main/MoimMain_recruit.vue";
import MoimMainrecruit2 from "../../components/moim/Main/MoimMain_recruit2.vue";
import MoimMain_Top from "../../components/moim/Main/MoimMain_Top.vue";
export default {
  name: "",
  components: {
    MoimSidebar,
    MoimMainrecruit,
    MoimMainrecruit2,
    MoimMain_Top,
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
