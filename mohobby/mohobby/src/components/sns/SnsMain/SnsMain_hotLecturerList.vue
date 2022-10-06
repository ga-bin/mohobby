<template>
  <div class="mx-auto">
    <h3>추천 만능 재주꾼들 피드</h3>
    <v-sheet max-width="1300">
      <v-slide-group class="mx-auto">
      <v-slide-item
        class="ma-2"
        style="width:200px"
        >
        <!-- 카드컴포넌트 -->
            <HotLecturer></HotLecturer>
        </v-slide-item>
      </v-slide-group>
    </v-sheet>
  </div>
</template>
<script>

  import HotLecturer from "./SnsMain_hotLecturer";

export default {
    name: "HotLecturerList",
    components: {HotLecturer},
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
