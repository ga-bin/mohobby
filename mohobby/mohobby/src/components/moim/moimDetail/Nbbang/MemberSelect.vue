<template>
  <div class="container">
    <v-row justify="end">
      <v-dialog v-model="dialog" max-width="500">
        <template v-slot:activator="{ on, attrs }">
          <v-btn
            color="green"
            dark
            text
            x-large
            v-bind="attrs"
            v-on="on"
            @click="selectMember()"
          >
            멤버 선택 >
          </v-btn>
        </template>
        <v-card class="pa-9">
          <v-card-title class="justify-center">
            <span class="font-weight-bold">멤버 선택</span>
          </v-card-title>

          <!-- 멤버 검색 -->
          <div>
            <v-text-field
              outlined
              label="아이디를 검색하세요!"
              v-model="memberId"
              id="searchBar"
              @keydown.enter="serchMember()"
              append-icon="mdi-magnify"
            ></v-text-field>
          </div>
          <v-row>
            <v-col cols="12" sm="6" md="2">
              <v-avatar class="mb-4" color="grey darken-1" size="64">
                <v-img aspect-ratio="30" :src="src"></v-img>
              </v-avatar>
            </v-col>
            <v-col cols="12" sm="6" md="4" class="mt-5">
              <div>전체선택</div>
            </v-col>
            <v-col cols="12" sm="6" md="6" class="mt-5">
              <v-spacer></v-spacer>
              <div class="select">
                <v-checkbox
                  v-model="members"
                  color="primary"
                  value="members"
                  hide-details
                ></v-checkbox>
              </div>
            </v-col>
          </v-row>
          <hr class="mt-5 mb-5" />
          <!-- 멤버 전체 조회 -->
          <h4 v-if="members.length == 0">일치하는 결과가 없습니다.</h4>
          <v-row v-for="(member, idx) in members">
            <v-col cols="12" sm="6" md="2">
              <v-avatar class="mb-4" color="grey darken-1" size="64">
                <v-img aspect-ratio="30" :src="src"></v-img>
              </v-avatar>
            </v-col>
            <v-col cols="12" sm="6" md="4" class="mt-5" id="check">
              <div>{{ member.memberId }}</div>
            </v-col>

            <v-col cols="12" sm="6" md="6" class="mt-5">
              <v-spacer></v-spacer>
              <div class="select">
                <v-checkbox
                  v-model="selectmembers"
                  color="light-green"
                  :value="member.memberId"
                  hide-details
                  @change="check($event)"
                ></v-checkbox>
              </div>
            </v-col>
          </v-row>

          <v-card-actions class="justify-center mt-6">
            <v-btn
              color="green darken-1"
              outlined
              rounded
              text
              @click="[(dialog = false), insertMember()]"
            >
              등록
            </v-btn>
            <v-btn
              color="green darken-1"
              outlined
              rounded
              text
              @click="dialog = false"
            >
              취소
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
  </div>
</template>
<script>
export default {
  data() {
    return {
      members: [],
      checked: [],
      clacPrice: "",
      moimId: this.$route.params.moimId,
      memberId: "",
      dialog: false,
      src: "https://dimg.donga.com/ugc/CDB/WEEKLY/Article/5a/d0/5c/e0/5ad05ce00110d2738de6.jpg",
    };
  },
  methods: {
    check(e) {
      console.log(e);
    },
    selectMember() {
      this.axios
        .get("/moimMemberList", {
          params: {
            moimId: this.moimId,
          },
        })
        .then((resp) => {
          console.log(resp);
          this.members = resp.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    serchMember() {
      console.log("멤버아이디 : " + this.memberId);
      this.axios
        .get("/oneMemberSearch", {
          params: {
            memberId: this.memberId,
            moimId: this.moimId,
          },
        })
        .then((resp) => {
          console.log(this.memsbers);
          console.log(resp);
          this.members = resp.data;
          document.querySelector("#searchBar").value = "";
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // // 멤버 배열에 저장후 db에 저장
    // insertMember(){
    //     for(let i in checked){
    //         this.checked += checked.split(',')
    //     }
    //     console.log(this.checked);
    //     this.axios.post("/",{
    //         data : {
    //             checked : this.checked
    //         }
    //     })
    // },
    insertMember() {
      this.axios.post("/", {
        data: {},
      });
    },
  },
  created() {
    this.selectMember();
  },
};
</script>
<style scoped>
.select {
  float: right;
}

h4 {
  text-align: center;
}
</style>
