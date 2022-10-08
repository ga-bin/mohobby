<template>
  <div class="container">
    <h3>인기있는 소모임을 확인하세요!</h3>
    <br>
    <div class="box" @click="box(idx)" v-for="(item,idx) in items" :key="item.boardId">
      <v-card class="mx-3" max-width="550" outlined>
        <v-list-item three-line>
          <div style="display:none">
            {{item.boardId}}
          </div>
          <v-list-item-content>
            <div class="text-overline mb-4">
              {{item.title}}
            </div>
            <v-list-item-subtitle>{{item.content}}</v-list-item-subtitle>
          </v-list-item-content>
          <v-list-item-avatar tile size="80">
            <img :src="item.photo" />
          </v-list-item-avatar>
        </v-list-item>
        <v-card-actions>
          <span class="people">
            {{item.people}}명 참여중
          </span>
          <v-spacer></v-spacer>
          <v-chip outlined rounded text>
            {{item.region}}
          </v-chip>
          <v-chip outlined rounded text>
            {{item.hobby}}
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
      boardId: 0,
      items: [{
        boardId: 1,
        title: '월요산방',
        content: '월요산방 content',
        region: '자양동',
        people: '513',
        photo: "https://img.animalplanet.co.kr/news/2019/08/10/700/v4q0b0ff4hcpew1g6t39.jpg",
        hobby: '운동/건강'
      },
      {
        boardId: 2,
        title: '화요산방',
        content: '화요산방 content',
        region: '자양동',
        people: '513',
        photo: "https://img.animalplanet.co.kr/news/2019/08/10/700/v4q0b0ff4hcpew1g6t39.jpg",
        hobby: '운동/건강'
      },
      {
        boardId: 3,
        title: '수요산방',
        content: '수요산방 content',
        region: '자양동',
        people: '513',
        photo: "https://img.animalplanet.co.kr/news/2019/08/10/700/v4q0b0ff4hcpew1g6t39.jpg",
        hobby: '운동/건강'
      },
      {
        boardId: 4,
        title: '목요산방',
        content: '목요산방 content',
        region: '자양동',
        people: '513',
        photo: "https://img.animalplanet.co.kr/news/2019/08/10/700/v4q0b0ff4hcpew1g6t39.jpg",
        hobby: '운동/건강'
      },
      {
        boardId: 5,
        title: '금요산방',
        content: '금요산방 content',
        region: '자양동',
        people: '513',
        photo: "https://img.animalplanet.co.kr/news/2019/08/10/700/v4q0b0ff4hcpew1g6t39.jpg",
        hobby: '운동/건강'
      },
      {
        boardId: 6,
        title: '토요산방',
        content: '토요산방 content',
        region: '자양동',
        people: '513',
        photo: "https://img.animalplanet.co.kr/news/2019/08/10/700/v4q0b0ff4hcpew1g6t39.jpg",
        hobby: '운동/건강'
      },
      ]
    };
  },
  methods: {
    box(idx) {
      this.$router.push({ path : 'moimDetail' , query : { boardId : this.items[idx].boardId }})
    }
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