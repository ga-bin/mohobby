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
    <v-card class="mx-auto mb-8" max-width="800" outlined v-for="(item,idx) in items" :key="item.date">
      <v-list-item three-line @click="">
        <v-list-item-avatar tile size="80" color="grey"></v-list-item-avatar>
        <v-list-item-content>
          <div class="text-overline mt-6">
            {{ item.memberId }}
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
          <v-card v-if="result == false">
            <v-card-actions class="pl-6 pt-5">{{item.topic}}</v-card-actions>
            <v-row>
              <v-col class="pl-10" cols="12" sm="4" md="4">
                <v-radio-group v-model="itemSelectList[idx]['itemSelect']">
                  <v-radio v-for="vote in itemsList" v-if="item.voteId === vote.voteId" :key="vote.itemId" :label="vote.content" :value="vote.itemId"
                    hide-details />
                </v-radio-group>
              </v-col>
            </v-row>
          </v-card>
          <v-card-text v-else class="text--primary" v-for="item in content" :key="item.radio">
            <p>{{item.radio}}</p>
            <v-progress-linear :value=item.no>
            </v-progress-linear>
          </v-card-text>
        </div>
      </div>
      <div v-if="itemSelectList[idx]['memberId'] === null">
      <v-card-actions class="mr-5" @click="">
        <v-spacer></v-spacer>
        <v-btn>투표하기 데이터 없음</v-btn>
      </v-card-actions>
      </div>
      <div v-else>
        <v-card-actions class="mr-5" @click="updateSelect()">
        <v-spacer></v-spacer>
        <v-btn>투표하기 데이터 있음</v-btn>
      </v-card-actions>
      </div>
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
      moimId : this.$route.params.moimId,
      result : false,
      items: [],
      itemsList : [],
      itemSelectList : [],
      content: [
                    {
                    radio: "티라미수 케잌",
                    no : 5
                    },
                    {
                    radio: "크림치즈 라즈베리 쿠키",
                    no : 1,
                  }
                    ,
                    {
                      radio: "잠봉뵈르 시오빵",
                      no : 3
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
    getvoteList() {
      this.axios.get("/voteList", {
        params : {
          moimId : this.moimId,
        }
      })
      .then ((resp) => {
        console.log(resp.data);
        this.items = resp.data
      })
      .catch((err) =>  {
        console.log(err)
      })
    },
    getvoteItemList() {
      this.axios.get("/voteItemList", {
        params : {
          moimId : this.moimId,
        }
      })
      .then ((resp) => {
        console.log(resp.data);
        this.itemsList = resp.data
      })
      .catch((err) =>  {
        console.log(err)
      })
    },
    voteItemSelect() {
      this.axios.get("/voteItemSelect", {
        params : {
          memberId : this.$store.state.id,
          moimId : this.moimId
        }
      })
      .then ((resp) => {
        console.log(resp.data)
        this.itemSelectList = resp.data
      })
      .catch((err) => {
        console.log(err)
      })
    },
    updateSelect() {
      console.log("데이터있음 데이터")
      this.result = !this.result
    }
  },
  created() {
    this.getvoteList()
    this.getvoteItemList()
    this.voteItemSelect()
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