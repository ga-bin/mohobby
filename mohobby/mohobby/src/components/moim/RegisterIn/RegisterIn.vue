<template>
  <v-card
    class="mx-auto"
    max-width="800"
    height="700"
    outlined
  >
    <v-list-item three-line>
      <v-list-item-avatar
        tile
        size="200"
        color="grey"
      ></v-list-item-avatar>
      <v-list-item-content>
        <v-list-item-title class="text-h5 mb-1">
          <v-spacer></v-spacer>
          <v-text-field
                  label="모임명"
                  required
                ></v-text-field>
        </v-list-item-title>
        <v-list-item-subtitle class="text-h5 mb-1">
          <v-text-field
                  width="300"
                  label="소개글"
                  required
                >최소 10글자 이상 입력하세요!</v-text-field>
        </v-list-item-subtitle>
        <v-col
                cols="12"
                sm="6"
              >
                <v-select
                  :items="['30명', '50명', '100명']"
                  label="최대 인원"
                  required
                ></v-select>
              </v-col>
        <small>양식을 다 채워서 가입해주세요!</small>
      </v-list-item-content>
      
    </v-list-item>
    <v-vinder></v-vinder>

    <v-col
      cols="12"
      sm="5">
    <v-dialog
      v-model="check"
      persistent
      max-width="600px"
      >
      
      <template v-slot:activator="{ on, attrs }">
          <div>
            <v-title>소모임 주제</v-title>
          </div>
        <v-btn
          color="primary"
          dark
          v-bind="attrs"
          v-on="on"
        >
          주제 선택
        </v-btn>
      </template>
      <v-sheet
        elevation="10"
        rounded="xl"
      >
        <v-sheet
          class="pa-3 primary text-right"
          dark
          rounded="t-xl"
        >
          <v-btn icon>
            <v-icon>mdi-content-save-cog-outline</v-icon>
          </v-btn>

          <v-btn
            class="ml-2"
            icon
            @click="check = false"
          >
            <v-icon>mdi-close</v-icon>
          </v-btn>
        </v-sheet>

        <div class="pa-4">
          <v-chip-group
            active-class="primary--text"
            column
          >
            <v-chip
              v-for="tag in tags"
              :key="tag"
            >
              {{ tag }}
            </v-chip>
          </v-chip-group>
        </div>
      </v-sheet>
      </v-dialog>
      </v-col>

    <v-col
      cols="12"
      sm="5">
    <v-dialog
      v-model="dialog"
      persistent
      max-width="600px"
    >
    <template v-slot:activator="{ on, attrs }">
      <div>
        <v-title>소모임 활동 지역</v-title>
      </div>
      <v-btn
      color="primary"
      dark
      v-bind="attrs"
      v-on="on"
      >
          지역 선택
        </v-btn>
      </template>
      <v-card>

      </v-card>
    </v-dialog>
    </v-col>


    <v-card-actions class="lastbutton">
      <v-btn
        outlined
        rounded
        text
      >
        취소하기
      </v-btn>
      <v-btn
        outlined
        rounded
        text
      >
        생성하기
      </v-btn>
    </v-card-actions>
  </v-card>
</template>
<script>
export default {
  data() {
    return {
      items : [{
        src : "https://cdn.class101.net/images/84d01f2e-ec28-4e30-8ed1-fc6e55954e42/960xauto.webp"
      }],
      tags: [
        'Work',
        'Home Improvement',
        'Vacation',
        'Food',
        'Drawers',
        'Shopping',
        'Art',
        'Tech',
        'Creative Writing',
      ],
      check : false
    }
  }
}
</script>
<style scoped>
.lastbutton {
  float: right;
}
</style>