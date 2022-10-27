<template>
  <div>
  <v-dialog
    v-model="region"
    max-width="600px"
  >
  <template v-slot:activator="{ on, attrs }">
    <!-- 지역 입력 -->
  <v-col
  cols="5"
    sm="5"
    class="pt-6 pb-3">
      <v-card-actions></v-card-actions>
      <v-select
        v-if="regionList"
        :items="regionList"
        :item-text="'keywordName'"
        :item-value="'keywordName'"
        v-model="moimRegion"
        label="지역"
        dense
      >
      </v-select>
    </v-col>
 </template>
  </v-dialog>
</div>
</template>

<script>
export default {
  data() {
      return {
      region : false,
      moimRegion: "",
      regionList: [],
      }
  },
  created() {
    const vm = this;
     this.axios({
    
    url: "http://localhost:8088/java/regionAll",
    method: "get",
  })
    .then(function (response) {
      console.log(response);
      if (response.data != "") {
        console.log(response.data);
        vm.regionList = response.data;
      }
    })
    .catch(function (error) {
      console.log(error);
    });
  },
}
</script>
<style>
</style>