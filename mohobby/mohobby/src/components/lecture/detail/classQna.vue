<template>
  <v-container fluid>
    <!-- qna 내역 존재 -->
    <div v-if="qnaList.length != 0">
      <!-- 정렬 방식 -->
      <div>
        <v-card class="d-flex justify-end" flat tile>
          <v-card tile flat>
            <v-select :items="listStd" item-text="title" item-value="value"
              :menu-props="{ bottom: true, offsetY: true }" attach style="width: 160px;" v-model="defaultSelect" />
          </v-card>
        </v-card>
      </div>
      <!-- qna 목록 -->
      <div style="padding-top: 20px" v-for="(rv,i) in qnaList" :key="i">
        <v-card class="mx-auto" outlined>
          <v-card-text>
            <v-row>
              <!-- 프로필 -->
              <v-avatar style="margin: 10px 0px 30px 5px;" color="grey darken-1" size="50">
                <!-- 이미지부분 -->
                <v-img aspect-ratio="30" :src="require(`@/assets/image/user/${rv.profileImg}`)" />
              </v-avatar>
              <v-col>
                <div style="padding-bottom: 3px;">
                  <span class="qnaTitle">{{ rv.title }}</span>
                  <span style="font-size: 1em;">{{ replaceDate(rv.writeDate) }}</span>
                </div>
                <span class="qnaNickname"
                  @click="$router.push({ path: '/snsUserFeed?memId='+rv.memberId}).catch(()=>{$router.go(0)})">{{
                  rv.nickname }}</span>
                <div style="font-size: 1.2em; padding-top: 20px">{{ rv.content }}</div>
              </v-col>
              <v-rating empty-icon="mdi-star-outline" full-icon="mdi-star" half-icon="mdi-star-half-full"
                half-increments length="5" :value="rv.rate" readonly color="#ffcb02" background-color="#ffcb02"
                size="20"></v-rating>
            </v-row>
          </v-card-text>
        </v-card>
      </div>
    </div>
    <!-- qna 내역 없음 -->
    <div v-if="qnaList.length == 0">
      <v-card flat justify="center" align="center" style="padding-top: 50px">
        <h1>🙇</h1>
        <h1>등록된 QnA가 없습니다</h1>
      </v-card>
    </div>
  </v-container>
</template>

<script>
export default {
  name: "classQna",
  props: {
    classId: {
      type: String,
      default: 1
    }
  },
  data() {
    return {
      classInfo: {},
      qnaList: [],
      listStd: [
        { title: "최신순", value: "0" },
        { title: "별점순", value: "1" }
      ],
      defaultSelect: {
        value: "0"
      }
    };
  },
  created() {
    this.initInfo();
    this.getQnaList();
  },
  methods: {
    initInfo() {
      let p = this.$parent;
      if (typeof p == "undefined") {
        p.initInfo();
      }
      this.classInfo = p.$data.classInfo;
    },
    getQnaList() {
      this.axios("/class/board", {
        params: {
          classId: this.classId,
          boardType: 1
        }
      })
        .then(res => {
          if (res.data.length > 0) {
            this.qnaList = res.data;
          }
        })
        .catch(err => console.log(err));
    },
    replaceDate(date) {
      return this.$moment(date).fromNow();
    },
    selectSort() {
      let listSort = this.qnaList;
      if (this.defaultSelect == 0) {
        listSort.sort((a, b) => b.boardId - a.boardId);
      } else if (this.defaultSelect == 1) {
        listSort.sort((a, b) => b.rate - a.rate);
      }
      this.qnaList = listSort;
    }
  },
  watch: {
    defaultSelect: function () {
      this.selectSort();
    }
  }
};
</script>

<style scoped>
* {
  color: #2b2b2b;
}

.qnaTitle {
  font-weight: bold;
  font-size: 1.2em;
  padding-right: 8px;
}

.qnaNickname {
  font-size: 1em;
  cursor: pointer;
}
</style>