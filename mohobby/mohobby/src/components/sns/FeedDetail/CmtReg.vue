 <!-- 댓글입력창 -->
<template>
    <div>
      <v-card-actions>
        <v-col cols="10">
          <v-text-field
          class="m1-11"
          placeholder="댓글을 입력해주세요!"
          filled
          rounded
          solo
          dense
          hide-details
          v-model="inputCmt"
          name="content"
          />
      </v-col>
      <v-spacer></v-spacer>
      <div style="margin-right: 80px">
        <v-btn class="ma-2 white--text" color="#2ac187" rounded 
                @click="regCmt()"
                @keyup="enter()"
                >등록</v-btn>
      </div>
    </v-card-actions>
  </div>
</template>
<script>

export default {
  name:"RegCmt",
  props: {
    postid: String,
  },
  data() {
    return{
      inputCmt:"",
      memberId : this.$store.state.id,
    }
  },
  created() {
    console.log(this.$store.state.id);
  },
  methods:{
    //댓글등록
    regCmt() {
      console.log(this.inputCmt);
      if (this.memberId == "" || this.memberId == undefined) {
        this.$swal('로그인 후 이용하세요!')
        return;
      }
      if (this.inputCmt == "" || this.inputCmt == undefined){
        this.$swal('내용을 입력하세요!')
        return;
      }
        this.axios.post('/sns/cmt', {
              memberId : this.memberId,
              targetId : this.postid,
              content : this.inputCmt,
          }).then(res => {
            console.log("댓글등록 성공! "+res);
          }).catch(err => {
            console.log(err)
          });
    },
    enter(){
      // document.getElementsByName('content').addEventListener('keyup', (e)=>{      });
        if (window.event.keyCode == 13) {
          this.regCmt();
        }
    }
  },  
}

</script>