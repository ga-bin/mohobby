<template>
  <div class="container">
    <v-card-actions class="mb-16">
      <v-spacer></v-spacer>
      <div class="margin">
        <v-btn @click="voteMake()">
          <v-icon>mdi-plus-circle-outline</v-icon>투표 생성하기
        </v-btn>
      </div>
    </v-card-actions>
    <v-card class="mx-auto mb-8" max-width="800" outlined v-for="(item,idx) in items" :key="item.date" @click="goDetail()">
      <v-list-item three-line>
        <v-list-item-avatar tile size="80" color="grey"></v-list-item-avatar>
        <v-list-item-content>
          <div class="text-overline mt-6">
            {{ item.id }}
            <hr />
            {{ item.date }}
          </div>
          <v-list-item-subtitle class="content mt-5">
            {{ item.contents }}
          </v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>
      <div class="vote">
        <div class="voteContent">
          <v-card>
            <v-card-actions class="pl-4">{{item.voteName}}</v-card-actions>
            <v-row>
              <v-col cols="12" sm="4" md="4">
                <v-radio-group v-model="radioGroup[idx]['check']">
                  <v-radio v-for="vote in item.voteLabel" :key="vote.type" :label="vote.type" :value="vote.checked"
                    hide-details />
                </v-radio-group>
              </v-col>
            </v-row>
          </v-card>
        </div>
        <div>
        </div>
      </div>
      <v-card-actions class="mr-5">
        <v-spacer></v-spacer>
        <div class="text-overline mb-1 mr-2">댓글 {{commentNo}}</div>
      </v-card-actions>
    </v-card>
  </div>
</template>
<script>
export default {
  created() {
    console.log(this.radioGroup)
  },
  data() {
    return {
      radioGroup: [{
        check: 1
      },
      {
        check: 3
      },
      {
        check: 2
      }
      ],
      commentNo: 22,
      items: [
        {
          date: "2022-10-04 11:12",
          id: "관리자",
          voteName: "가입자 더 받기",
          voteLabel: [{
            type: "ㄴㄴ",
            checked: 1
          },
          {
            type: "50명",
            checked: 2
          },
          {
            type: "100명",
            checked: 3
          }]
        },
        {
          date: "2022-10-05 10:42",
          id: "식빵이",
          voteName: "야식 추천",
          voteNum: 2,
          voteLabel: [{
            type: "빵",
            checked: 1
          },
          {
            type: "닭발",
            checked: 2
          },
          {
            type: "치킨",
            checked: 3
          }]
        },
        {
          date: "2022-10-05 10:50",
          id: "샹달프 미니잼",
          voteName: "잼 대회",
          voteNum: 3,
          voteLabel: [{ type: "오뚜끼 딸기" },
          { type: "샹달프 포도" }]
        },
      ],
    };
  },
  methods: {
    voteMake: function () {
      this.$router.push({ path: "makeVote" });
    },
    goDetail: function () {
      this.$router.push({ path: "voteDetail" });
    },
  }
}
</script>

<style scoped>
.container {
  width : 85%;
}

.voteContent {
  max-width: 90%;
  margin-bottom: 3%;
}

.vote {
  margin-left: 14%;
}

.margin {
  margin-right: 10.5%;
}
</style>