<template>
  <div class="container">
  <h3>소모임 멤버를 모집합니다!</h3>
  <br>
  <div class="box" @click="box(idx)" v-for="(item,idx) in items" :key="item.title">
  <v-card
    class="mx-3"
    max-width="550"
    outlined
  >
    <v-list-item three-line>
      <v-list-item-content>
        <div class="text-overline mb-4">
          {{item.title}}
        </div>
        <v-list-item-subtitle>{{item.content}}</v-list-item-subtitle>
      </v-list-item-content>
      <v-list-item-avatar
        tile
        size="80">
        <img :src="item.photo" />
      </v-list-item-avatar>
    </v-list-item>
    <v-card-actions>
      <span class="people">
          {{item.people}}명 참여중
    </span>
    <v-spacer></v-spacer>
      <v-chip 
        outlined
        rounded
        text
      >
      {{item.region}}
      </v-chip>
      <v-chip
        outlined
        rounded
        text
      >
      {{item.hobby}}
      </v-chip>
    </v-card-actions>
  </v-card>  
</div>
<div class="moreMoim">
  <!--비회원 소모임 모집 -->
  <v-card-actions  v-if="moimRight==='0'" >
  <v-spacer></v-spacer>
  <div class="text-center">
    <v-dialog
      v-model="noneuser"
      width="500"
    >
  <template v-slot:activator="{ on, attrs }">
  <v-btn text>
  <v-chip
    color="success"
    outlined
    v-bind="attrs"
    v-on="on"
  >
  <v-icon left>
    mdi-plus
  </v-icon>
  새로운 소모임 모집하기
</v-chip>
</v-btn>
</template>
<v-card>
  <br><br>
        <v-card-text class="font-weight-bold">
          소모임을 만들기 위해서는 먼저 로그인을 해주세요!.
        </v-card-text>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            depressed
            @click="noneuser=false"
          >
            닫기
          </v-btn>
          <v-btn
            color="success"
            depressed
            @click="join"
          >
            로그인하러 가기
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</v-card-actions>
<!-- 회원 소모임 버튼 -->
<v-card-actions v-else>
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
</div>
</div>
</template>

<script>

export default {
  data() {
  return {
      items: [{
      boardId : 1,
      title : '월요산방',
      content : '월요산방 content',
      region : '자양동',
      people : '513',
      photo : "https://dimg.donga.com/ugc/CDB/WEEKLY/Article/5a/d0/5c/e0/5ad05ce00110d2738de6.jpg",
      hobby : '운동/건강'
  },
  {   
      boardId : 2,
      title : '화요산방',
      content : '화요산방 content',
      region : '자양동',
      people : '513',
      photo : "https://dimg.donga.com/ugc/CDB/WEEKLY/Article/5a/d0/5c/e0/5ad05ce00110d2738de6.jpg",
      hobby : '운동/건강'
  },
  {   
      boardId : 3,
      title : '수요산방',
      content : '수요산방 content',
      region : '자양동',
      people : '513',
      photo : "https://dimg.donga.com/ugc/CDB/WEEKLY/Article/5a/d0/5c/e0/5ad05ce00110d2738de6.jpg",
      hobby : '운동/건강'
  },
  {   
      boardId : 4,
      title : '목요산방',
      content : '목요산방 content',
      region : '자양동',
      people : '513',
      photo : "https://dimg.donga.com/ugc/CDB/WEEKLY/Article/5a/d0/5c/e0/5ad05ce00110d2738de6.jpg",
      hobby : '운동/건강'
  },
  {   
      boardId : 5,
      title : '금요산방',
      content : '금요산방 content',
      region : '자양동',
      people : '513',
      photo : "https://dimg.donga.com/ugc/CDB/WEEKLY/Article/5a/d0/5c/e0/5ad05ce00110d2738de6.jpg",
      hobby : '운동/건강'
  },
  {   
    boardId : 6,
      title : '토요산방',
      content : '토요산방 content',
      region : '자양동',
      people : '513',
      photo : "https://dimg.donga.com/ugc/CDB/WEEKLY/Article/5a/d0/5c/e0/5ad05ce00110d2738de6.jpg",
      hobby : '운동/건강'
  },
  ],
  moimRight : '0',
  noneuser : false,
  };
},

methods : {
  select : function() {
    if (this.moimRight !== '0') {
      this.$router.push({ path: 'moimRegisterIn' })
      }
    },
    join() {
     this.$router.push({ path: 'login' })
    },
    box(idx) {
      this.$router.push({ path : 'moimDetail' , query : { boardId : this.items[idx].boardId }})
    }
}

}
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
  .people {
      border-left : 2px solid green;
      padding-left: 10px;
  }
  .moreMoim {
    float : right;
    width : 1000px;
  }

</style>