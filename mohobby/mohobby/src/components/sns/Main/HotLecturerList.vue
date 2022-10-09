<template>
  <div class="mx-auto">
    <v-sheet max-width="1300">
      <v-slide-group class="mx-auto">
      <v-slide-item
        class="ma-2"
        style="width:200px"
        >
          <div>
            <!-- 카드컴포넌트 -->
            <HotLecturer />
          </div>
        </v-slide-item>
      </v-slide-group>
    </v-sheet>
  </div>
</template>
<script>

  import HotLecturer from "./HotLecturer";

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
