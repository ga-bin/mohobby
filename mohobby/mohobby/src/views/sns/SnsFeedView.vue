<template>
    <div>
        <SnsSidebar />
        <h1>sns 피드 화면</h1>
    </div>
</template>
<script>
import SnsSidebar from '../../components/sns/SnsCommn/SnsSidebar.vue'

export default {
  name: "feed",
  components: { 
    SnsSidebar
  },

  data() {
    return {
      sampleData: "",
    };
  },

  setup() {},
  created() {

  },
  mounted() {

  },
  unmounted() {

  },
  methods: {

  },
};
</script>
  
<style></style>