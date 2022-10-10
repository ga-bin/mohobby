<template>
  <div class="mx-auto">
    <v-sheet max-width="1300">
      <v-slide-group class="mx-auto">
      <v-slide-item
        class="ma-2"
        style="width:200px"
        >
          <div style="display:flex">
            <!-- 인기강사피드 (로그인X)-->
            <HotLecturerList />
            <!-- 취미기반강사피드 (로그인O) -->
          </div>
        </v-slide-item>
      </v-slide-group>
    </v-sheet>
  </div>
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
