<template>
    <div>
      <SnsSidebar></SnsSidebar>
      <h1>sns 관리 화면</h1>
      <SnsSearchbar />
      <Lecture />
      <Moim />
      <Challenge />
      <Follower />
      <Following />
    </div>
  </template>
<script>
    import SnsSidebar from "@/components/sns/Common/SnsSidebar";
    import SnsSearchbar from "@/components/sns/Common/Searchbar"
    import Follower from "@/components/sns/Management/Follower";
    import Following from "@/components/sns/Management/Following";
    import Lecture from "@/components/sns/Management/Lecture";
    import Moim from "@/components/sns/Management/Moim";
    import Challenge from "@/components/sns/Management/Challenge";
  export default {
    name: "",
    components: { SnsSidebar, SnsSearchbar, Follower, Following, Lecture, Moim, Challenge, Lecture },
    data() {
      return {
        sampleData: "",
      };
    },
    setup() {
      
    },
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
  