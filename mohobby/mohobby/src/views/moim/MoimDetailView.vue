<template>
  <div>
  <!-- 버튼 -->
  <div class="container">
  <v-card-actions>
    <v-spacer></v-spacer>
    <v-btn color="mr-3" @click="click()">Sidebar</v-btn>
    <v-card-actions v-if="(moim===0 && moimRight===1) || (moim===1 && moimRight===1)">
      <v-btn class="mr-3"><v-icon>mdi-account-plus</v-icon></v-btn>
      <v-btn class="mr-3" @click="write()"><v-icon>mdi-lead-pencil</v-icon></v-btn>
    </v-card-actions>
    <v-card-actions v-if="(moim===0 && moimRight===0) || (moim===1 && moimRight===0)">
      <v-btn disabled class="mr-3"><v-icon>mdi-account-plus</v-icon></v-btn>
      <v-btn disabled class="mr-3"><v-icon>mdi-lead-pencil</v-icon></v-btn>
    </v-card-actions>
  </v-card-actions>
  </div>

  <!-- 컴포넌트 -->
  <MoimSidebar v-if="fclick === true"></MoimSidebar>

  <!-- 권한에 따라 컴포넌트 다르게 보여주기 -->
  <span>boardId : {{$route.query.boardId}}</span>
  <router-view></router-view>
  </div>
</template>
<script>
  import MoimSidebar from "@/components/moim/moimDetail/MoimSidebar.vue";

  export default {
  components: { MoimSidebar },
  data: () => ({
    fclick: true,
    // 비공개 : 0, 공개 : 1
    moim: 1,
    moimRight : 1,
    }),
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods : {
     click : function() {
      this.fclick = !this.fclick
    },
    write : function() {
      this.$router.push({ name : 'moimboardwrite'})
    }
  },
};
</script>

<style scoped>

.container {
  width : 65%;
}

</style>