 <!-- 댓글입력창 -->
<template>
    <div>
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
            @keydown.enter="enter()"
            />
          </v-col>
          <v-spacer></v-spacer>
          <div style="margin-right: 80px">
            <v-btn class="ma-2 white--text" color="#2ac187" rounded @click="regCmt()">등록</v-btn>
          </div>
        </v-card-actions>
      </div>
      <div>
        <div class="profile" v-for="cmt in comments" :key="cmt.commId" >
          <v-avatar class="ml-10 my-5 mr-4" color="grey darken-1" size="30">
            <v-img aspect-ratio="30" :src="require(`@/assets/image/user/${cmt.profileImg}`)" alt="profile_img" />
          </v-avatar>
          <input type="hidden" class="cmtId" value="cmt.commId">
          <div class="user text-overline">{{cmt.memberId}}
            <small class="date">{{ writeDate(cmt.writeDate) }}</small>
            <div class="btn">
              <v-btn x-small outlined color="success" class="mr-3">수정</v-btn>
              <v-btn x-small outlined color="error" @click="deleteCmt(cmt.commId)">삭제</v-btn>
            </div>
            <v-card-actions>
              <div class="content"> {{cmt.content}} </div>
            </v-card-actions>
        </div>
      </div>
    </div>
  </div>
  
</template>
<script>

export default {
  name:"Cmt",
  props: {
    postid: String,
  },
  data() {
    return{
      inputCmt:"",
      memberId : this.$store.state.id,
      comments: [],
    }
  },
  created() {
    console.log(this.$store.state.id);
    this.getCmtList();
  },
  methods:{
    //댓글등록
    regCmt() {
      console.log(this.inputCmt);
      if (this.memberId == "" || this.memberId == undefined) {
        this.$swal('로그인부터 부탁드립니다🙏')
        return;
      }
      if (this.inputCmt == "" || this.inputCmt == undefined){
        this.$swal('내용을 입력부탁드립니다🙏')
        return;
      }
        this.axios.post('/sns/cmt', {
              memberId : this.memberId,
              targetId : this.postid,
              content : this.inputCmt,
          }).then(res => {
            console.log("댓글등록 성공! "+res);
            this.inputCmt = ""; //댓글입력창 초기화
            this.getCmtList();
          }).catch(err => {
            console.log(err)
          });
    },
    //댓글 enter등록
    enter(){
        if (window.event.keyCode == 13) {
          this.regCmt();
        }
    },
    //댓글리스트upload
    getCmtList() {
      this.axios('/sns/cmt/' + this.postid, {
      }).then(res => {
        this.comments = res.data;
        console.log("댓글리스트 가져오기 성공!");
      }).catch(err => {
        console.log(err);
      });
    },
    //댓글 삭제버튼 추가하기
    // onAdd(){
    //   const input = documnet.querySelector('.input');
    //   const itemAdd = document.querySelector('.itemAdd');
    //   const items = document.querySelector('.items');

    //   const item = document.createElement('li');
    //   item.setAttribute('class', 'item');

    //   const itemText = document.createElement('span');
    //   itemText.setAttribute('class', 'itemText');
    //   itemText.innerHtml = text;

    //   const itemDel = document.createElement('button');
    //   itemDel.setAttribute('class','itemDel');
    //   itemDel.innerHTML = '삭제';

    //   item.appendChild(itemText);
    //   item.appendChild(itemDel);
    //   item.appendChild(item);

    //   itemAdd.addEventListener('click',() => {
    //     onAdd();
    //   })
    // },

    //date처리
    writeDate(writeDate){
      this.$moment(writeDate).fromNow();
    },

    //댓글 삭제
    deleteCmt: function (cmtId) {
        // let deleteBtn = e.target.document.querySelector('.cmtId').value;
        console.log(cmtId);
        this.axios({
          url: "/sns/cmt/" + cmtId,
          methods: "DELETE",
        }).then(res => {
          this.getCmtList();
          console.log("댓글 삭제 성공!"+res);
        }).catch(err => {
          console.log(err)
        })
      },
  },  
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

 .btn{
  position: absolute;
  right: 0;
 }
</style>