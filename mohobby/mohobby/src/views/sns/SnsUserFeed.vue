<template>
    <div>
      <SnsSidebar></SnsSidebar>
      <Profile></Profile>
      <h1>유저피드</h1>
    </div>
  </template>
<script>
  import SnsSidebar from "../../components/sns/Common/SnsSidebar.vue";
  import Profile from "../../components/sns/Feed/Profile.vue";
  export default {
    name: "SnsUserFeed",
    components: { SnsSidebar, Profile },
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