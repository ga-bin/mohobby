<template>
  <div class="container">
    <v-card-text>
      <v-container>
        <v-row>
          <v-col cols="12" sm="6" md="4">
            <v-text-field
              v-model="title"
              outlined
              placeholder="N빵의 이름을 알려주세요!"
            ></v-text-field>
          </v-col>
          <v-col cols="12" sm="6" md="8">
            <v-text-field
              outlined
              placeholder="금액을 입력해주세요"
              v-model="price"
              oninput="javascript: this.value = this.value.replace(/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣|a-z]/g, '' );"
              prefix="₩"
            >
            </v-text-field>
          </v-col>

          <v-col cols="12" sm="6" md="4">
            <v-avatar class="mb-4" color="grey darken-1" size="64">
              <v-img aspect-ratio="30" :src="src"></v-img>
            </v-avatar>
            <div>{{ writer }}</div>
          </v-col>
          <v-spacer></v-spacer>
          <v-col cols="12" sm="6" md="4">
            <MemberSelect
              :empty.sync="empty"
              :price.sync="price"
              :totalPrice.sync="totalPrice"
            ></MemberSelect>
          </v-col>
        </v-row>
        <hr class="mt-5 mb-5" />
        <v-row v-for="list in empty">
          <v-col cols="12" sm="6" md="1">
            <v-avatar class="mb-4" color="grey darken-1" size="64">
              <v-img aspect-ratio="30" :src="src"></v-img>
            </v-avatar>
          </v-col>
          <v-col cols="12" sm="6" md="10" class="ml-5">
            <span class="mb-3">{{ list }}</span>
            <v-text-field
              outlined
              disabled
              v-if="totalPrice != 0"
              :value="totalPrice | comma | won"
              placeholder="금액 들어올 칸"
            >
            </v-text-field>
            <v-text-field
              outlined
              disabled
              v-if="totalPrice == 0"
              v-model="totalPrice"
              :value="totalPrice | comma | won"
              placeholder="금액 들어올 칸"
            >
            </v-text-field>
            totalPrice : {{ totalPrice }}
          </v-col>
        </v-row>
        <v-row justify="center" class="mt-12">
          <v-btn color="success" @click="allInsert()"> 짜잔 </v-btn>
        </v-row>
      </v-container>
    </v-card-text>
  </div>
</template>
<script>
import MemberSelect from "./MemberSelect.vue";
export default {
  data() {
    return {
      writer: this.$store.state.id, //작성자
      title: "",
      empty: [], //멤버 담는 배열
      price: "", //총 금액
      totalPrice: "", //나눠서 뿌리는 값
      moimId: this.$route.params.moimId,
      calcPrice: "", //하위컴포넌트 나눈값 (n빵 금액)
      calcCheck: "",
      src: "https://dimg.donga.com/ugc/CDB/WEEKLY/Article/5a/d0/5c/e0/5ad05ce00110d2738de6.jpg",
    };
  },
  components: { MemberSelect },
  methods: {
    allInsert() {
      console.log(this.empty.length)
      this.axios
        .post("/makeNbbang", {
          title: this.title,
          totalPrice: this.price,
          calcPrice: this.totalPrice,
          memberId: this.writer,
          moimId: this.moimId,
          people: this.empty.length
        })
        .then((resp) => {
          if(resp.status == 200) {
            console.log('calcPrice: '+this.totalPrice)
            console.log('moimId: '+this.moimId)
            console.log(resp.data);
            this.axios.post("/insertPtp", {
              memberId: this.empty,
              moimId: this.moimId,
              calcPrice: this.calcPrice
            });
            console.log("then==============")
            console.log('writer:'+this.writer)
            console.log('title:'+this.title)
            console.log('totalPrice:'+this.price)
            console.log('calcPrice:'+this.totalPrice)
            console.log('moimId:' +this.moimId)
            this.$swal("N빵 생성이 완료되었습니다.");
            this.$router.push({ name : "moimNbbang" });
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  watch: {
    price(val) {
      this.totalPrice = this.price / this.empty.length;
    }
  },
};
</script>
<style scoped>
.container {
  width: 85%;
}
</style>
