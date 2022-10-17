 <!-- 댓글입력창 -->
<template>
    <div>
      <div>
        <v-card-actions>
          <v-col cols="10">
            <v-text-field
            class="m1-11"
            placeholder="댓글을 입력해주세요!"
            counter
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
        <!-- 댓글리스트 -->
        <div class="profile" v-for="cmt in comments" :key="cmt.commId" >
          <div v-if='cmt.parentCommId != ""' style="width:35px;"></div>
          <v-avatar class="ml-10 my-5 mr-4" color="grey darken-1" size="30">
            <v-img aspect-ratio="30" :src="require(`@/assets/image/user/${cmt.profileImg}`)" alt="profile_img" 
            @click="$router.push({ path: '/snsUserFeed?memId='+cmt.memberId}).catch(()=>{$router.go(0)})" style="cursor:pointer" />
          </v-avatar>
          <input type="hidden" class="cmtId" value="cmt.commId">
          <div class="user text-overline">{{cmt.memberId}}
            <small class="date"><span style="font-size: 1em;">{{ writeDate(cmt.writeDate) }}</span></small>
            <div class="btn">
              <v-btn x-small outlined color="success" class="mr-3" v-if="cmt.memberId == memberId" @click="editCmt(cmt.commId)">저장</v-btn>
              <v-btn x-small outlined color="dark-grey" class="mr-3" v-if="cmt.commId != editForm" @click="showRegReCmt(cmt.commId, cmt.memberId)">답장</v-btn>
              <v-btn x-small outlined color="success" class="mr-3" v-if="cmt.memberId == memberId" @click="showEditForm(cmt.commId)">수정</v-btn>
              <v-btn x-small outlined color="error" v-if="cmt.memberId == memberId" @click="deleteCmt(cmt.commId, cmt.targetId)">삭제</v-btn>
            </div>       
            <v-card-actions>
              <div class="content"> 
                <div v-if='cmt.parentCommId != ""'>
                  <span class="member_id" @click="$router.push({ path: '/snsUserFeed?memId='+cmt.parentMemberId}).catch(()=>{$router.go(0)})"><strong>@{{cmt.parentMemberId}}</strong></span>{{cmt.content}}
                </div>
                <div v-if='cmt.parentCommId == "" && cmt.commId != editForm'>
                  {{cmt.content}}
                </div>
                <!-- 댓글 수정창 -->
                <div v-if="cmt.commId == editForm && cmt.commId != reCmt">
                  <v-textarea
                    name="editContent"
                    auto-grow
                    outlined
                    :value="originContent"
                    @keydown.enter="editEnter(cmt.commId)"
                    v-model="editedContent"
                  ></v-textarea>
                </div>
              </div>
            </v-card-actions>
            <!-- showRegReCmt(cmt.commId) 대댓글 입력창 -->
            <div v-if="cmt.commId == reCmt"> 
              <v-card-actions>
                <v-col cols="10">
                  <v-text-field
                  class="m1-11"
                  filled
                  rounded
                  solo
                  dense
                  hide-details
                  :prefix="cmtMemberId"
                  v-model="inputReCmt"
                  name="content"
                  @keydown.enter="reEnter(cmt.commId, cmt.memberId)"
                  />
                </v-col>
                <v-spacer></v-spacer>
                <div style="margin-right: 80px">
                  <v-btn class="ma-2 white--text" color="#2ac187" rounded @click="regReCmt(cmt.commId, cmt.memberId)">등록</v-btn>
                </div>
              </v-card-actions>
          </div>
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
      inputCmt:"",//댓글v-model
      inputReCmt:"",//대댓글v-model
      editedContent: "",//수정댓글v-model
      originContent: "",//원댓글
      memberId : this.$store.state.id,
      comments: [],//cmt리스트
      reCmt: "", //대댓등록창 show여부
      editForm:"",//댓글수정창 show여부
      cmtMemberId: "", //소환된 회원
    }
  },
  created() {
    console.log(this.$store.state.id);
    this.getCmtList();

  },
  methods:{
    //date처리
    writeDate(writeDate){
      return this.$moment(writeDate).fromNow();
    },    
    //댓글리스트upload
    getCmtList() {
      this.axios('/sns/cmt/' + this.postid, {
      }).then(res => {
        console.log(res.data);
        this.comments = res.data;
        console.log("댓글리스트 가져오기 성공!");
      }).catch(err => {
        console.log(err);
      });
    },
    //댓글등록
    regCmt() {
      console.log(this.inputCmt);
      if (this.memberId == "" || this.memberId == undefined) {
        this.$swal('로그인부터 부탁드립니다🙏')
        return;
      }
      if (this.inputCmt == "" || this.inputCmt == undefined){
        this.$swal('내용 입력부터 부탁드립니다🙏')
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
    //댓글 수정 enter
    editEnter(commId){
      if (window.event.keyCode == 13) {
        this.editCmt(commId);
      }
    },
    //대댓 enter등록
    reEnter(commId, parentMemberId){
      if (window.event.keyCode == 13) {
          this.regReCmt(commId, parentMemberId);
        }
    },
    //댓글 삭제
    deleteCmt(commId, targetId) {
      console.log("dd-----------------:"+commId, targetId);
      this.axios.delete('/sns/cmt/' + commId + '/' + targetId)
        .then(res => {
          this.getCmtList();
          console.log("댓글 삭제 성공! "+res);
          this.inputCmt = ""; //댓글입력창 초기화
          this.getCmtList();
        }).catch(err => {
          console.log(err)
        });
      },
      //댓글 수정폼 호출
      showEditForm(commId, content){
        if(commId == this.editForm){ //수정창닫기
          this.editForm = -1;
        } else{ //댓글창열기
          this.editForm = commId;
          this.originContent = content
        }
      },
      //댓글 수정
      editCmt(commId){
        if (this.memberId == "" || this.memberId == undefined) {
          this.$swal('로그인부터 부탁드립니다🙏')
          return;
        }
        if (this.editedContent == "" || this.editedContent == undefined){
          this.$swal('내용 입력부터 부탁드립니다🙏')
          return;
        }
        this.axios.put('/sns/cmt/' + commId, {
              content : this.editedContent,
          }).then(res => {
            this.editForm = "";
            console.log("댓글수정 성공! "+res);
            this.getCmtList();
          }).catch(err => {
            console.log(err)
          });
      },
      //대댓글 입력창 열기(닫기)
      //답장 click -> 인자 comm_id-> 데이터변수 reCmt에 바인딩 -> v-if에서 비교 조건으로 사용
      showRegReCmt(cmtId, cmtmemId){
        this.inputReCmt = "";
        if(cmtId == this.reCmt){ //댓글창닫기
          this.reCmt = -1;
        } else{ //댓글창열기
          this.reCmt = cmtId;
          if(cmtId == this.reCmt)
          this.cmtMemberId = "@"+cmtmemId;
        }
      },
      //대댓글등록
      regReCmt(commId, parentMemberId){
        if (this.memberId == "" || this.memberId == undefined) { //회원검증
        this.$swal('로그인부터 부탁드립니다🙏')
        return;
        }
        if (this.inputReCmt == "" || this.inputReCmt == undefined){ //내용검증
          this.$swal('내용 입력부터 부탁드립니다🙏')
          return;
        }
          this.axios.post('/sns/recmt', {
                memberId : this.memberId,
                parentCommId : commId,
                targetId : this.postid,
                content : this.inputReCmt,
                parentMemberId : parentMemberId,
            }).then(res => {
              console.log("대댓글등록 성공! "+res);
              this.inputReCmt = "";
              this.reCmt = !this.reCmt;
              this.getCmtList();
            }).catch(err => {
              console.log(err)
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
 }

 .cmt_box{
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
 v-avatar{
  cursor:pointer;
 }
 .member_id{
  color:#2ac187;
  font-weight: 300;
  cursor:pointer;
 }
 .user{
  cursor:pointer;
 }
</style>