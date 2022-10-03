<template>
  <div class="container">
    <!-- <MoimSidebar></MoimSidebar> -->
    <v-card-actions>
  <v-spacer></v-spacer>
  <v-btn text>
  <v-chip
    color="success"
    outlined
    @click="select"
  >
  <v-icon left>
    mdi-plus
  </v-icon>
  새로운 소모임 모집하기
</v-chip>
</v-btn>
</v-card-actions>

    <h1>Mo#obby 소모임</h1> 
    
    <!-- 소모임 유저별 모임 리스트 -->
    <MyMoim_none v-if="moimRight==='0'"></MyMoim_none>
    <MyMoim_user v-if="moimRight==='1'"></MyMoim_user>
    <MyMoim_leader v-if="moimRight==='2'"></MyMoim_leader>

    <!-- 소모임 검색창 -->
    <div>
      <v-text-field
            outlined
            label="Search"
            append-icon="mdi-magnify"
          ></v-text-field>
    </div>
    <!-- 소모임 키워드 -->
    <div>
      <v-row justify="space-around">
    <v-col
      cols="12"
      sm="30"
      md="30"
    >
      <v-sheet
        class="py-4 px-1"
      >
        <v-chip-group
          active-class="primary--text"
        >
          <v-chip
            v-for="catg in catg"
            :key="catg"
          >
            {{ catg }}
          </v-chip>
        </v-chip-group>
      </v-sheet>
    </v-col>
  </v-row>
    </div>
  </div>
</template>
<script>
  import MyMoim_none from "./mymoim/MyMoim_none.vue";
  import MyMoim_user from "./mymoim/MyMoim_user.vue";
  import MyMoim_leader from "./mymoim/MyMoim_leader.vue";
  export default {
    components :{MyMoim_none, MyMoim_user, MyMoim_leader},
    data() {
      return {
        catg: [
          '전체',
          '취미',
          '스포츠/레저',
          '어학/외국어',
          '문화/예술',
          '여행/캠핑',
          '음악',
          '건강/다이어트',
          '친목/모임',
          '맛집/요리',
          '인문/과학',
          '팬클럽',
          '게임',
          '만화/애니메이션',
          '반려동물/동물',
          '교육/공부',
          'IT/컴퓨터',
          '경제/재테크',
          '종교/봉사',
          '자연/귀농'
        ],
        moimRight : '2'
      }
    },
    methods : {
  select : function() {
    this.$router.push({ path: 'moimRegisterIn' })
    }
  }
 }

</script>
<style scoped>
  .container {
    width : 1000px;
  }

  .h1 {
    display: inline-block;
  }
</style>