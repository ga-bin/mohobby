<template>
  <!-- 프로필 -->
  <div>
  <div class="profile" v-for="(item,idx) in items" :key="item.commId" >
    <div>{{item.commId}}</div>
    <v-avatar class="ml-10 my-5 mr-4" color="grey darken-1" size="30">
      <!-- <v-img aspect-ratio="30" :src="item.src" /> -->
    </v-avatar>
    <div class="user text-overline">{{item.commentWriter}}
      <small class="date">{{item.commentDate | yyyyMMdd}}</small>
      <div class="btn">
        <v-btn x-small outlined color="success" class="mr-3" @click="updateComment($event)">수정</v-btn>
        <v-dialog v-model="dialog" max-width="500">
          <template v-slot:activator="{ on, attrs }">
            <v-btn x-small outlined color="error" @click="dialog=true">삭제</v-btn>
          </template>
          <v-card>
            <v-card-text class="pa-5">
              댓글을 삭제하시겠습니까?
            </v-card-text>
            
            <v-divider></v-divider>
            
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn @click="dialog = false">
                취소
              </v-btn>
                  <v-btn color="error" @click="[dialog = false, deleteComment(idx)]">
                    삭제
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
      </div>
      <v-card-actions class="mt-10">
        <div id="comment" class="content"> {{item.content}} </div>
        <div id="text-field">
          <v-text-field v-model="contents" @keydown.enter="updateComplete(idx)">
          </v-text-field>
          <v-spacer />
          <div id="commBtn">
          <v-btn @click="updateComplete(idx,$event)">수정 완료</v-btn>
          </div>
        </div>
      </v-card-actions>
    </div>

  </div>
  <!-- 댓글 -->
  <div>
    <v-card-actions>
      <v-col cols="10">
      <v-text-field
      class="ml-11"
      placeholder="댓글을 남겨보세요!"
      filled
      rounded
      dense
      hide-details
      v-model="content"
      @keyup.enter="insertComment()"
      ></v-text-field>
    </v-col>
    <v-spacer></v-spacer>
    <div style="margin-right: 80px">
    <v-btn
    rounded
    color="orange"
    text
    @click="insertComment()"
    >
    <v-icon>mdi-send</v-icon>
    </v-btn>
    </div>
  </v-card-actions>
  </div>
</div>
</template>
<script>
export default {
  data() {
    return {
      dialog: false,
      boardId : this.$route.query.boardId,
      moimId : this.$route.query.moimId,
      boardType : this.$route.query.boardType,
      items : [],
      memberId : 'user1',
      targetId : '',
      content : '',
      contents: '',
    }
  },
  methods: {
    getBoard() {
      this.axios.get("/detailComment", {
        params : {
          moimId : this.moimId,
          boardType : this.boardType,
          boardId : this.boardId
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
    insertComment() {
        let vm = this;
        this.axios.post("/insertMoimBoardComment", {
            memberId : this.memberId,
            targetId : this.boardId,
            content : this.content
        }).then((resp) => {
          console.log(resp.data);
          this.$swal("댓글등록 완료");
          this.content = '';
          vm.getBoard()
        })
        .catch((error) => {
          console.log(error)
        })
      },
      updateComment(e) {
        // console.log(e.target.parentNode.parentNode.parentNode.childNodes[3].childNodes[1].childNodes[2])
        // e.target.parentNode.parentNode.parentNode.childNodes[3].childNodes[0].style.display = "none";
        // e.target.parentNode.parentNode.parentNode.childNodes[3].childNodes[1].style.display = "block";
        // e.target.parentNode.parentNode.parentNode.childNodes[3].childNodes[1].childNodes[2].style.display = "block";
        document.getElementById('comment').style.display = "none";
        document.getElementById('text-field').style.display = "block";
        document.getElementById('commBtn').style.display = "block";
      },
      updateComplete(idx, e){  
        let vm = this
        // console.log(e.target.parentNode.parentNode.parentNode)
        // e.target.parentNode.parentNode.parentNode.style.display = "none";
        // e.target.parentNode.parentNode.parentNode.childNodes[2].style.display = "none";
        
        document.getElementById('text-field').style.display = "none";
        document.getElementById('commBtn').style.display = "none";

        this.axios.put("/updateComment",{
          content : this.contents,
          commId : this.items[idx].commId,
      })
        .then((resp)=> {
          console.log("댓글 수정 결과" + resp);
          this.$swal("댓글 수정 완료");
          vm.getBoard()
          document.getElementById('comment').style.display = "block";
        })
        .catch((err) => {
          console.log(this.items)
          console.log(err)
        })
     },
     deleteComment(idx){
      let vm = this;
      this.axios.delete("/boardDeleteComm",{
        params:{
          commId : this.items[idx].commId,
        }
      }).then((resp) => {
        console.log("댓글 삭제 결과" + resp);
        this.$swal("댓글 삭제 완료") 
        vm.getBoard()
      }).catch((err)=> {
        console.log(err)
      })
     }
  },
  created() {
    this.getBoard()
  },
  filters: {
    // filter로 쓸 filter ID 지정
    yyyyMMdd: function (value) {
      // 들어오는 value 값이 공백이면 그냥 공백으로 돌려줌
      if (value == "") return "";
      // 현재 Date 혹은 DateTime 데이터를 javaScript date 타입화
      var js_date = new Date(value);
      // 연도, 월, 일 추출
      var year = js_date.getFullYear();
      var month = js_date.getMonth() + 1;
      var day = js_date.getDate();
      // 월, 일의 경우 한자리 수 값이 있기 때문에 공백에 0 처리
      if (month < 10) {
        month = "0" + month;
      }
      if (day < 10) {
        day = "0" + day;
      }
      // 최종 포맷 (ex - '2021/10/08')
      return year + "/" + month + "/" + day;
    },
  }
}
</script>
<style scoped>
 .profile{
  position: relative;
  display : flex;
  margin-left: 30px;
  width: 90%;
 }
 .profile::after {
  content : "";
  position : absolute;
  left:30px;
  height: 1px;
  width : 100%;
  border-bottom : 1px solid rgba(184, 189, 190, 0.8);
 }
 .user {
  margin-top : 20px;
 }

 .date {
  position: absolute;
  right: 0;
 }

 #text-field{
  display: none;
 }

 #commBtn{
  display: none;
 }

 .btn{
  position: absolute;
  right: 0;
 }
</style>
