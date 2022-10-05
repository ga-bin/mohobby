<template>
  <div>
  <!-- 버튼 -->
  <div class="container">
  <v-card-actions>
    <v-spacer></v-spacer>
    <v-btn color="mr-3" @click="click()">Sidebar</v-btn>
    <v-btn class="mr-3"><v-icon>mdi-account-plus</v-icon></v-btn>
    <v-btn class="mr-3"><v-icon>mdi-lead-pencil</v-icon></v-btn>
  </v-card-actions>
</div>
  <!-- 컴포넌트 -->
  <MoimSidebar v-if="fclick === true"></MoimSidebar>
  <MoimBoard></MoimBoard>
  </div>
</template>
<script>
  import MoimSidebar from "../../components/moim/moimDetail/MoimSidebar.vue";
  import MoimBoard from "../../components/moim/moimDetail/MoimBoard.vue";

  export default {
  components: { MoimSidebar, MoimBoard },
  data: () => ({
    fclick: true,
    }),
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods : {
     click : function() {
      this.fclick = !this.fclick
    }
  },
};
</script>

<style scoped>
.container {
  width : 65%;
}

</style>