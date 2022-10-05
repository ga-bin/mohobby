<template>
  <div>
  <!-- 버튼 -->
  <v-card-actions>
    <v-spacer></v-spacer>
    <v-btn class="mr-3"><v-icon>mdi-account-plus</v-icon></v-btn>
    <v-btn class="mr-3"><v-icon>mdi-wechat</v-icon></v-btn>
    <v-btn class="mr-3"><v-icon>mdi-lead-pencil</v-icon></v-btn>
  </v-card-actions>
  
  <!-- 컴포넌트 -->
  <MoimSidebar></MoimSidebar>
  <MoimBoard></MoimBoard>
  </div>
</template>
<script>
  import MoimSidebar from "../../components/moim/moimDetail/MoimSidebar.vue";
  import MoimBoard from "../../components/moim/moimDetail/MoimBoard.vue";

  export default {
  name: "",
  components: { MoimSidebar, MoimBoard },
  data: () => ({
    }),
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {},
};
</script>

<style></style>