<template>
  <div class="container">
    <h3>인기있는 소모임을 확인하세요!</h3>
    <br>
    <div class="box" @click="box(idx)" v-for="(item,idx) in items" :key="item.moimId">
      <v-card class="mx-3" max-width="550" outlined>
        <v-list-item three-line>
          <div style="display:none">
            {{item.moimId}}
          </div>
          <v-list-item-content>
            <div class="text-overline mb-4">
              {{item.moimName}}
            </div>
            <v-list-item-subtitle>{{item.moimInfo}}</v-list-item-subtitle>
          </v-list-item-content>
          <v-list-item-avatar tile size="80">
            <img :src="item.moimImg" />
          </v-list-item-avatar>
        </v-list-item>
        <v-card-actions>
          <span class="people">
            {{item.regCnt}}명 참여중
          </span>
          <v-spacer></v-spacer>
          <v-chip outlined rounded text>
            {{item.moimRegion}}
          </v-chip>
          <v-chip outlined rounded text>
            {{item.moimCatg}}
          </v-chip>
        </v-card-actions>
      </v-card>
    </div>
    <div class="moreMoim">
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      items: [],
    };
  },
  created(){
    this.getList()
  },
  methods: {
    box(idx) {
      this.$router.push({ path : 'moimDetail' , query : { moimId : this.items[idx].moimId }})
    },
    getList() {
      this.axios
        .get("/moimPopularSelect")
        .then((resp) => {
          console.log(resp);
          this.items = resp.data;
        })
        .catch(function (error) {
          console.log(error);
        });
    },
  }
}
</script>

<style scoped>
.container {
  margin: 0 auto;
  width: 1000px;
}

.box {
  float: left;
  width: 50%;
  margin-bottom: 15px;
  cursor: pointer;

}

.people {
  border-left: 2px solid green;
  padding-left: 10px;
}

.moreMoim {
  float: right;
  width: 1000px;
}
</style>