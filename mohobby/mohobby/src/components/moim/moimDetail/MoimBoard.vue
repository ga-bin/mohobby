<template>
  <div>
    <div class="container">
    <v-container>
      <v-text-field
            label="글 내용 검색"
            single-line
            solo
          ></v-text-field>
    </v-container>
  </div>
    
    <v-card
    class="mx-auto mb-8"
    max-width="800"
    outlined
    v-for="item in items"
    :key="item.date"
    @click="goPost()"    
    >
    
    <v-list-item three-line>
      <v-list-item-avatar
        tile
        size="80"
        color="grey"
      ></v-list-item-avatar>
      <v-list-item-content>
        <div class="text-overline mt-6">
          {{item.id}}
          <hr>
          {{item.date}}
        </div>
        <v-list-item-subtitle class="content mt-5">
        {{item.contents}}</v-list-item-subtitle>
      </v-list-item-content>
    </v-list-item>

      <v-card-actions>
          <v-spacer></v-spacer>
          <div class="text-overline mb-1 mr-2">댓글 22</div>
      </v-card-actions>
  </v-card>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        items:[{
          date: "2022-10-04 11:12",
          id: "관리자",
          contents: "[공지] 여러분 예담 공지를 확인하세요!"
        },
        {
          date: "2022-10-05 10:42",
          id: "관리자",
          contents: "[공지] 여러분 예담 공지를 확인하세요!"
        },
        {
          date: "2022-10-05 10:50",
          id: "관리자",
          contents: "[공지] 여러분 예담 공지를 확인하세요!"
        }]
      }
    },
    methods : {
      goPost : function(){
        this.$router.push({path: 'moimPost'});
      }
    },
    props :{

    }
  }
</script>
<style scoped>
  .container {
    width : 80%;
  }
</style>