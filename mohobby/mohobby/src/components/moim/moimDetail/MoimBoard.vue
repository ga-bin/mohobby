<template>
  <div>
    <v-container>
      <v-text-field
            label="글 내용 검색"
            single-line
            solo
          ></v-text-field>
    </v-container>

    <v-card
    class="mx-auto"
    max-width="800"
    outlined
    >
    <v-list-item three-line>
      <v-list-item-avatar
        tile
        size="80"
        color="grey"
      ></v-list-item-avatar>
      <v-list-item-content>
        <div class="text-overline mt-6">
          {{id}}
          <hr>
          {{date}}
        </div>
        <v-list-item-subtitle class="content mt-5">
        {{contents}}</v-list-item-subtitle>
      </v-list-item-content>
    </v-list-item>

    <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn class="mb-1 mr-2">댓글</v-btn>
    </v-card-actions>
  </v-card>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        date: "2022-10-04 11:12",
        id: "관리자",
        contents: "[공지] 여러분 예담 공지를 확인하세요!"
        }
      }
    }
</script>
<style scoped>
</style>