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

    <!-- 투표 리스트 시작 -->
    <v-card class="mx-auto mb-8" max-width="800" outlined v-for="item in items" @click="goDetail()">
      <v-list-item three-line>
        <v-list-item-avatar tile size="80" color="grey"></v-list-item-avatar>
        <v-list-item-content>
          <div class="text-overline mt-6">
            {{ item.voteId }}
            <hr />
            {{ item.startDate }}
          </div>
          <v-list-item-subtitle class="content mt-5">
            {{ item.content }}
          </v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>
      <div class="vote">
        <div class="voteContent">
          <v-card>
            <v-card-actions class="pl-4">{{item.topic}}</v-card-actions>
            <v-row>
              <v-col cols="12" sm="4" md="4">
                <v-radio-group v-model="radioGroup[idx]['check']">
                  <v-radio v-for="vote in items" :key="vote.topic" :label="vote.topic" :value="vote.content"
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
      radioGroup: 
      [{cnt: 1},
      {cnt: 3},
      {cnt: 2}
      ],
      commentNo: 22,
      items: [{voteLabel : [items.content]}],
      moimId : this.$route.params.moimId,
    };
  },
  methods: {
    getVoteList() {
      this.axios.get("/voteList", {
        params : {
          moimId: this.moimId
        }
      })
      .then((resp)=> {
        console.log(resp)
        console.log(this.items)
        this.items = resp.data;
      })
      .catch((err) => {
        console.log(this.items)
        console.log(err)
      })
    },

    voteMake: function () {
      this.$router.push({ path: "makeVote" });
    },
    goDetail: function () {
      this.$router.push({ path: "voteDetail" });
    },
  },
  created() {
    this.getVoteList()
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