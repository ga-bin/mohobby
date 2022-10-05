<template>
  <div class="container">
  <h3>소모임 멤버를 모집합니다!</h3>
  <br>
  <div class="box" v-for="item in items" :key="item.title">
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
      <v-btn 
        outlined
        rounded
        text
      >
      {{item.region}}
      </v-btn>
      <v-btn
        outlined
        rounded
        text
      >
      {{item.hobby}}
      </v-btn>
    </v-card-actions>
  </v-card>  
</div>
<div class="moreMoim">
<v-card-actions>
<v-spacer></v-spacer>
<v-btn
text
>
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
      title : '월요산방',
      content : '월요산방 content',
      region : '자양동',
      people : '513',
      photo : "https://dimg.donga.com/ugc/CDB/WEEKLY/Article/5a/d0/5c/e0/5ad05ce00110d2738de6.jpg",
      hobby : '운동/건강'
  },
  {
      title : '화요산방',
      content : '화요산방 content',
      region : '자양동',
      people : '513',
      photo : "https://dimg.donga.com/ugc/CDB/WEEKLY/Article/5a/d0/5c/e0/5ad05ce00110d2738de6.jpg",
      hobby : '운동/건강'
  },
  {
      title : '수요산방',
      content : '수요산방 content',
      region : '자양동',
      people : '513',
      photo : "https://dimg.donga.com/ugc/CDB/WEEKLY/Article/5a/d0/5c/e0/5ad05ce00110d2738de6.jpg",
      hobby : '운동/건강'
  },
  {
      title : '목요산방',
      content : '목요산방 content',
      region : '자양동',
      people : '513',
      photo : "https://dimg.donga.com/ugc/CDB/WEEKLY/Article/5a/d0/5c/e0/5ad05ce00110d2738de6.jpg",
      hobby : '운동/건강'
  },
  {
      title : '금요산방',
      content : '금요산방 content',
      region : '자양동',
      people : '513',
      photo : "https://dimg.donga.com/ugc/CDB/WEEKLY/Article/5a/d0/5c/e0/5ad05ce00110d2738de6.jpg",
      hobby : '운동/건강'
  },
  {
      title : '토요산방',
      content : '토요산방 content',
      region : '자양동',
      people : '513',
      photo : "https://dimg.donga.com/ugc/CDB/WEEKLY/Article/5a/d0/5c/e0/5ad05ce00110d2738de6.jpg",
      hobby : '운동/건강'
  },
  ]
  };
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