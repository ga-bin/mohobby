<!-- 댓글입력창 -->
<template>
    <div>
        <v-row>
            <v-col cols="10">
                <v-text-field
                label=""
                placeholder="댓글을 입력해주세요"
                solo
                rounded
                v-model="inputCmt"
                name="content"
                ></v-text-field>
            </v-col>
            <v-col cols="2">
                <v-btn class="ma-2 white--text" color="blue-grey" rounded 
                @click="regCmt()"
                @keyup="enterkey($event)"
                >등록</v-btn>
           </v-col>
        </v-row>
    </div>
</template>
<script>

export default {
    name:"RegCmt",
    props: {
    postId: Number,
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
      
      if (this.memberId === null || this.memberId === "") {
        alert('로그인이 필요합니다!');
        return;
      } else {
          this.axios.post('/sns/cmt', {
                memberId : this.memberId,
                targetId : this.postId,
                content : this.inputCmt,
            }).then(res => {
              console.log("댓글등록 성공! "+res);
            }).catch(err => {
              console.log(err)
            });
          }
      },
      enterkey: function (e) {
        if (window.event.keyCode == 13) {
          this.search(e);
        }
      }
    }
}

</script>