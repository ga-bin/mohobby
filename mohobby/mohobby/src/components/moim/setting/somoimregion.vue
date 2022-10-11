<template>
    <div>
    <v-dialog
      v-model="region"
      max-width="600px"
    >
    <template v-slot:activator="{ on, attrs }">
      <v-btn
          color="green"
          dark
          rounded
          v-bind="attrs"
          v-on="on"
        >
          지역 선택
        </v-btn>
      </template>
      <v-sheet
        class="pa-4"
        elevation="10"
        rounded="xl"
      >
      <v-card-title class="justify-center">
          <span class="font-weight-bold">지역선택</span>
      </v-card-title>
      <v-card-actions>
        
      <v-text-field
            hide-details
            outlined
            label="Search"
            prepend-icon="mdi-magnify" 
          >
        </v-text-field>
        <div class="searchB">
        <v-btn
      color="success"
      dark
      large
      >
      검색
      </v-btn>
      </div>
    </v-card-actions>
    <v-card-actions >
    &nbsp;&nbsp;
    <v-icon color="green">mdi-crosshairs-gps</v-icon>
    &nbsp;&nbsp;
    <div class="font-weight-bold">내 위치</div>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <div>대구시 달서구</div>
   </v-card-actions>
   <br>
   <br>
   <div class="font-weight-bold">검색 결과</div>
   <v-card-actions class="justify-center">
    <v-btn
    @click="region=false"
    >
      닫기
    </v-btn>
   </v-card-actions>
      </v-sheet>
      </v-dialog>
    </div>
</template>

<script>
export default {
    data() {
        return {
        region : false
        }
    }
}
</script>
<style>
</style>