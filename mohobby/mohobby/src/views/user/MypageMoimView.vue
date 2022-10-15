<template>
  <div>
    <UserSidebar></UserSidebar>
    <h3>내가 운영중인 소모임</h3>
      <Category @selected-catg="selectedCatg"></Category>
      <div class="box" @click="box(idx)" v-for="(takeMoim,idx) in takeMoimList" :key="idx">
  <v-card
    class="mx-3"
    max-width="550"
    outlined
  >
    <v-list-item three-line>
      <v-list-item-content>
        <div class="text-overline mb-4">
          {{item.moimName}}
        </div>
        <v-list-item-subtitle>{{item.moimInfo}}</v-list-item-subtitle>
      </v-list-item-content>
      <v-list-item-avatar
        tile
        size="80">
        <img :src="item.moimImg" />
      </v-list-item-avatar>
    </v-list-item>
    <v-card-actions>
      <span class="people">
          {{item.regCnt}}명 참여중
    </span>
    <v-spacer></v-spacer>
      <v-chip 
        outlined
        rounded
        text
      >
      {{item.moimRegion}}
      </v-chip>
      <v-chip
        outlined
        rounded
        text
      >
      {{item.moimCatg}}
      </v-chip>
    </v-card-actions>
  </v-card>  
</div>
    <br>
    <h3>내가 참여중인 소모임</h3>
    <Category @selected-catg="selectedCatg"></Category>
      <div class="box" @click="box(idx)" v-for="(item,idx) in items" :key="item.title">
  <v-card
    class="mx-3"
    max-width="550"
    outlined
  >
    <v-list-item three-line>
      <v-list-item-content>
        <div class="text-overline mb-4">
          {{item.moimName}}
        </div>
        <v-list-item-subtitle>{{item.moimInfo}}</v-list-item-subtitle>
      </v-list-item-content>
      <v-list-item-avatar
        tile
        size="80">
        <img :src="item.moimImg" />
      </v-list-item-avatar>
    </v-list-item>
    <v-card-actions>
      <span class="people">
          {{item.regCnt}}명 참여중
    </span>
    <v-spacer></v-spacer>
      <v-chip 
        outlined
        rounded
        text
      >
      {{item.moimRegion}}
      </v-chip>
      <v-chip
        outlined
        rounded
        text
      >
      {{item.moimCatg}}
      </v-chip>
    </v-card-actions>
  </v-card>  
</div>
  </div>
</template>
<script>
import Category from '@/components/user/Category.vue';
import UserSidebar from "../../components/user/UserSidebar.vue";

export default {
  name: "",
  components: { UserSidebar, Category },
  data() {
    return {
        items: [],
        memberId : this.$store.state.id,
        moimCatg : "",
        takeMoimList : [],
        manageMoimList : [],
        catg : [],
        selectedCatg : "",
    };
  },
  setup() {},
  created() {
  },
  mounted() {
    this.getTakeMoim();
    this.getManageMoim();
  },
  unmounted() {},
  methods: {
    selectedCatg(data) {
      this.selectedCatg = data;
    },
    // 참여 중 소모임 목록 가져오기
  getTakeMoim() {
      const vm = this;
      this.axios({
        url: "http://localhost:8088/java/mypagetakemoim/" + this.memberId + "/" + this.selectCatg,
        method: "get",
      })
        .then(function (response) {
          console.log("참여 중 소모임 " + response.data);
          vm.takeMoimList = response.data;
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    // 운영 중 소모임 목록 가져오기
    getManageMoim() {
      const vm = this;
      this.axios({
        url: "http://localhost:8088/java/mypagemanagemoim/" + this.memberId + "/" + this.selectCatg,
        method: "get",
      })
        .then(function (response) {
          console.log("운영 중 소모임 " + response.data);
          vm.manageMoimList = response.data;
        })
        .catch(function (error) {
          console.log(error);
        });
    },
  },
};
</script>

<style scoped>
  .container {
      margin: 0 auto;
      width : 1000px;
  }
  .box {
      float: left; 
      width: 50%;
      margin-bottom: 15px;
      cursor : pointer;    
  }
</style>