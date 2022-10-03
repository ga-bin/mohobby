<template>
    <div class="container">
      <!-- <MoimSidebar></MoimSidebar> -->
    <v-card-actions>
    <v-spacer></v-spacer>
      <v-btn text>
      <v-chip
        class="ma-2"
        color="success"
        outlined>
        <v-icon left>
          mdi-plus
        </v-icon>
        새로운 소모임 모집하기
      </v-chip>
      </v-btn>
    </v-card-actions>
  
      <h1>Mo#obby 소모임</h1> 
    
      <div>
        <h3>내가 참여중인 소모임</h3>
        <img src="#">
        <img src="#">
      </div>
      <div>
        <h3>내가 운영중인 소모임</h3>
        <img src="#">
        <img src="#">
      </div>
      <!-- 소모임 검색창 -->
      <div>
        <input type="text" style="border: 1" placeholder="소모임 명을 검색하세요!"/>
        <button type="button">검색</button>
      </div>
  
      <!-- 소모임 키워드 -->
      <div>
        <v-row justify="space-around">
      <v-col
        cols="12"
        sm="12"
        md="12"
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
export default {
  data() {
  return {
    catg : [
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
    ]
  }
}
}
  </script>
  <style>
    .mozip{
      float: right;
    }
    .container {
        width : 1000px;
    }
  </style>