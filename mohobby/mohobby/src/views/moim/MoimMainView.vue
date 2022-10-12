<template>
  <div>
    <MoimMain_Top @search="childValue"></MoimMain_Top>
    <MoimMainrecruit :search="search"></MoimMainrecruit>
    <MoimMainrecruit2></MoimMainrecruit2>
  </div>
</template>
<script>
import MoimMain_Top from "../../components/moim/Main/MoimMain_Top.vue";
import MoimMainrecruit from "../../components/moim/Main/MoimMain_recruit.vue";
import MoimMainrecruit2 from "../../components/moim/Main/MoimMain_recruit2.vue";

export default {
  components: { MoimMain_Top, MoimMainrecruit, MoimMainrecruit2 },
  data() {
    return {
      search : '',
    }
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
    childValue(search) {
      console.log(search)
      console.log(this.search)
      this.search = search
    },
  },
};
</script>

<style scoped></style>
