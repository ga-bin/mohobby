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
                @click="CommentInsert()"
                @keyup="enterkey($event)"
                >등록</v-btn>
           </v-col>
        </v-row>
    </div>
</template>
<script>

export default {

    data() {
      return{
        inputCmt:"",
      }
    },
    method:{
    //댓글등록
    CommentInsert() {
      if (this.memId === null || this.memId === "") {
        alert('로그인이 필요합니다!');
        return;
      } else {
          //DB Jjim insert
          this.axios.post('/sns/cmt', {
                memberId : this.memId,
                targetId : this.postId,
                content : this.inputCmt,
            }).then(res => {
              console.log("댓글등록 성공!");
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