<template>
    <!-- 인기강사피드 (로그인X)-->
    <HotLecturerList />
    <!-- 취미기반강사피드 (로그인O) -->
</template>
<script>

  import HotLecturerList from "./HotLecturer";

export default {
    name: "HotLecturerSpace",
    components: { HotLecturerList },
    data() {
        return {
            
        };
    },
    created() {
  
     
    },
    methods: {
     
    },
}
</script>
