<!-- 댓글입력창 -->
<template>
  <div>
    <div>
      <!-- 본 댓글입력창 : 세션값 있으면 입력 가능. 없으면 입결 불가능-->
      <v-card-actions>
        <v-col cols="10" v-if="memberId">
          <v-text-field class="m1-11" placeholder="댓글을 입력해주세요!" counter filled rounded solo dense hide-details
            v-model="inputCmt" name="content" @keydown.enter="regCmt()" />
        </v-col>
        <v-col cols="10" v-else>
          <v-text-field class="m1-11" placeholder="댓글은 로그인부터 부탁드립니다🙏" disabled counter filled rounded solo dense
            hide-details />
        </v-col>
        <v-spacer></v-spacer>
        <!-- 댓글 등록버튼 -->
        <div style="margin-right: 80px">
          <v-btn @click="regCmt()" class="ma-2 white--text" color="#2255b1" dense rounded>등록</v-btn>
        </div>
      </v-card-actions>
    </div>
    <!-- 본 댓글 입력창 끝 -->
    <!-- 댓글리스트 -->
    <div>
      <div class="profile" v-for="(cmt, idx) in comments" :key="cmt.commId">
        <div v-show="cmt.parentCommId != ''" style="width: 35px"></div>
        <v-avatar class="ml-10 my-5 mr-4" color="grey darken-1" size="30">
          <v-img aspect-ratio="30" alt="profile_img" style="cursor: pointer"
            :src="require(`@/assets/image/user/${cmt.profileImg}`)"
            @click="$router.push({ path: '/snsUserFeed?userId=' + cmt.memberId }).catch(() => { $router.go(0); })" />
        </v-avatar>
        <input type="hidden" class="cmtId" value="cmt.commId" />
        <div class="text-overline">
          <span class="user" @click="$router.push({ path: '/snsUserFeed?userId=' + cmt.memberId }).catch(() => { $router.go(0); })">
            {{ cmt.memberId }}
          </span>
          <small class="date">
            <span style="font-size: 1em">
              {{ writeDate(cmt.writeDate) }}
            </span>
          </small>
          <!-- 버튼: 저장, 답장, 수정, 삭제 
            댓글 쓴 사람과 로그인세션 아이디가 같을 떄  수정, 삭제, 답장 버튼
           -->
          <div class="btn">
            <v-btn v-if="btnStatus[idx].replyBtn == true" x-small color="#e9e9ec" class="mr-3"
              @click="showRegReCmt(cmt.commId, cmt.memberId, idx)">답장
            </v-btn>
            <v-btn v-if="cmt.memberId == memberId && btnStatus[idx].saveBtn == true" x-small outlined color="#2255b1"
              class="mr-3" @click="editCmt(cmt.commId, idx)">저장</v-btn>
            <v-btn v-if="cmt.memberId == memberId && btnStatus[idx].editBtn == true" x-small outlined color="#2255b1"
              class="mr-3" @click="showEditForm(cmt.commId, idx)">수정</v-btn>
            <v-btn v-if="cmt.memberId == memberId && btnStatus[idx].deleteBtn == true" x-small outlined color="#F36A3E"
              @click="deleteCmt(cmt.commId, cmt.targetId, idx)">
              삭제</v-btn>
            <v-btn v-if="btnStatus[idx].cancelBtn == true"
              @click="cancleEdit(idx)" x-small outlined color="success" class="mr-3">취소</v-btn>
          </div>
          <!-- 댓글 버튼 끝 -->
          <!-- 대댓폼 (유저소환) -->
          <v-card-actions>
            <div class="content">
              <div v-if="cmt.parentCommId != ''">
                <span class="member_id"
                  @click="$router.push({ path: '/snsUserFeed?userId=' + cmt.parentMemberId, }).catch(() => { $router.go(0); })">
                  <strong>@{{ cmt.parentMemberId }}</strong></span>
                <input class="cmt" :id ="temp+idx" type="text" readonly :value=cmt.content >
                <input class="reCmt" :id =idx type="text" :value=cmt.content v-on:keyup.enter="editCmt(cmt.commId, idx)" >
              </div>
              <!-- 상위댓글이 없으면 -->
              <div v-else>
                <input class="cmt" :id ="temp+idx" type="text" readonly :value=cmt.content>
                <input class="reCmt" :id =idx type="text" :value=cmt.content v-on:keyup.enter="editCmt(cmt.commId, idx)">
              </div>
              <!-- 유저소환 끝 -->
            </div>
          </v-card-actions>
          <!-- showRegReCmt(cmt.commId) 대댓글 입력창 -->
          <div v-if="btnStatus[idx].replyForm == true">
            <v-card-actions>
              <v-col cols="10">
                <v-text-field class="m1-11" dense filled rounded solo hide-details :prefix="cmtMemberId"
                  v-model="inputReCmt" name="content" @keydown.enter="regReCmt(cmt.commId, cmt.memberId)" />
              </v-col>
              <v-spacer></v-spacer>
              <!-- 대댓글 등록버튼 -->
              <div style="margin-right: 80px">
                <v-btn small class="ma-2 white--text" color="#2255b1" rounded @click="regReCmt(cmt.commId, cmt.memberId)">등록
                </v-btn>
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
  name: "Cmt",
  props: {
    postid: String,
    targetId: String,
  },
  data() {
    return {
      temp:"temp",
      cmtCount: "",
      inputCmt: "", //댓글v-model
      inputReCmt: "", //대댓글v-model
      editedContent: "", //수정댓글v-model
      originContent: "", //원댓글
      memberId: this.$store.state.id,
      comments: [], //cmt리스트
      editCmtId: "",
      reCmt: "", //대댓등록창 show여부
      cmtMemberId: "", //소환된 회원
      originContentFrm: false, //원댓폼
      editForm: false, //댓글수정창
      btnStatus: []
    };
  },
  created() {
    this.getCmtList() //댓글리스트
    this.editBtn = true //수정
    this.deleteBtn = true //삭제
    this.replyBtn = true
  },
  methods: {
    //세션유무 검증
    confirmMember(memberId) {
      if (memberId) {
        console.log("true");
        return true;
      }
      else {
        console.log("false");
        return false;
      }
    },
    //로그인 검증 모달
    loginConfirm() {
      this.$swal({
        title: "로그인하셔야 가능하세요🙏",
        text: "🙏로그인화면으로 이동부탁드립니다🙏",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#2255b1",
        cancelButtonColor: "#F36A3E",
        cancelButtonText: "취소",
        confirmButtonText: "이동",
      }).then((result) => {
        if (result.isConfirmed) {
          this.$router.push({ path: "login" });
        }
      });
    },
    //date filter
    writeDate(writeDate) {
      return this.$moment(writeDate).fromNow();
    },
    //댓글리스트upload
    getCmtList() {
      let vm = this
      this.axios("/sns/cmt/" + this.postid)
        .then((res) => {
          this.comments = res.data;
          this.cmtCount = res.data.length
          vm.btnStatus = []
          for (let i = 0; i < res.data.length; i++) {
            vm.btnStatus.push({
              replyForm:false, //대댓글입력창
              replyBtn: true, //답장버튼
              saveBtn: false, //저장버튼
              editBtn: true, //수정버튼
              deleteBtn: true, //삭제버튼
              cancelBtn: false, //취소버튼
            })
          }
          this.$emit('cmtCount', this.cmtCount)
        })
        .catch((err) => {
          console.log(err);
        });
    },
    //댓글등록 - 입력창
    regCmt() {
      if (this.confirmMember(this.memberId) == false) { //유효성검사 - 회원 id, 내용값
        this.loginConfirm();
      } else if (this.inputCmt == "") {
        this.$swal("내용 입력하셔야 등록 가능하세요🙏")
      } else {
        this.axios
          .post("/sns/cmt", {
            memberId: this.memberId,
            targetId: this.postid,
            content: this.inputCmt,
          })
          .then((res) => {
            console.log("댓글등록 성공! " + res);
            this.inputCmt = ""; //댓글입력창 초기화
            this.getCmtList();
            const noticeContent = {
              myId: this.$store.state.id,
              targetId: this.targetId,
              contentType: 1, //0:좋아요 1:댓글
              postId: this.postid,
              noticeType: 0, //0:sns ,1:소모임, 2:강의
            };
            this.stompClient.send(
              "/app/Notice",
              JSON.stringify(noticeContent),
              (res) => {
                console.log(res);
              }
            );
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    //댓글 삭제
    deleteCmt(commId, targetId) {
      this.axios
        .delete("/sns/cmt/" + commId + "/" + targetId)
        .then((res) => {
          console.log("댓글 삭제 성공! " + res);
          this.inputCmt = ""; //댓글입력창 초기화
          this.getCmtList();
        })
        .catch((err) => {
          alert(err);
        });
    },
    //댓글 수정폼 호출
    showEditForm(commId, idx) {
    document.getElementById(this.temp+idx).style.display='none'
    document.getElementById(idx).style.display='block'
      this.btnStatus[idx].cancelBtn=true //취소
      this.btnStatus[idx].replyBtn = false //답장
      this.btnStatus[idx].editBtn = false //수정
      this.btnStatus[idx].deleteBtn =false //학제
      this.btnStatus[idx].saveBtn = true //저장
      this.btnStatus[idx].editForm =true 
    },
    //댓글 수정
    editCmt(commId, idx) {
      console.log(  document.getElementById(idx).value)
      document.getElementById(this.temp+idx).value =  document.getElementById(idx).value //변경된 내용으로 교체
      console.log( document.getElementById(this.temp+idx).value)
      console.log(  document.getElementById(idx).value)
      this.axios
        .put("/sns/cmt/" + commId, {
          content: document.getElementById(idx).value
        })
        .then((res) => {
          console.log("댓글수정 성공! " + res);
          document.getElementById(this.temp+idx).style.display='block'
    document.getElementById(idx).style.display='none'
    this.btnStatus[idx].cancelBtn=false //취소
      this.btnStatus[idx].replyBtn = true //답장
      this.btnStatus[idx].editBtn = true //수정
      this.btnStatus[idx].deleteBtn =true //삭제
      this.btnStatus[idx].saveBtn = false //저장
      this.getCmtList()
        })
        .catch((err) => {
          alert("댓글수정 실패: " + err);
        });
    },
    //답장버튼 -> 대댓글 입력창 열기(닫기)
    //답장 click -> 인자 comm_id-> 데이터변수 reCmt에 바인딩 -> v-if에서 비교 조건으로 사용
    //답장 버튼 -> 수정 삭제 버튼 false
    showRegReCmt(cmtId, cmtmemId, idx) {
      if (this.confirmMember(this.memberId) == false) { //유효성검사 - 회원 id, 내용값
        this.loginConfirm();
      } else {
        console.log(this.btnStatus)
        this.btnStatus[idx].replyForm = true
        this.btnStatus[idx].replyBtn = false
        this.btnStatus[idx].editBtn = false
        this.btnStatus[idx].deleteBtn = false
        this.btnStatus[idx].cancelBtn =true
        this.inputReCmt = ""; //대댓 입력창 초기화
        this.cmtMemberId = "@" + cmtmemId;
      }
    },
    cancleEdit(idx) {
      document.getElementById(this.temp+idx).style.display='block'
    document.getElementById(idx).style.display='none'

      this.btnStatus[idx].replyForm = false
      this.btnStatus[idx].replyBtn=true
      this.btnStatus[idx].editBtn=true
      this.btnStatus[idx].deleteBtn=true
      this.btnStatus[idx].saveBtn=false
      this.btnStatus[idx].cancelBtn=false
    },
    //대댓글등록
    regReCmt(commId, parentMemberId, idx) {
      if (this.confirmMember(this.memberId) == false) { //유효성검사 - 회원 id, 내용값
        this.loginConfirm();
      }
      else if (this.inputReCmt == "") {
        this.$swal("내용 입력하셔야 등록 가능하세요🙏")
      }
      else {
        let vm = this
        this.axios
          .post("/sns/recmt", {
            postId: this.postid,
            memberId: this.memberId,
            parentCommId: commId,
            targetId: this.postid,
            content: this.inputReCmt,
            parentMemberId: parentMemberId,
          })
          .then((res) => {
            console.log("대댓글등록 성공! " + res)
            this.inputReCmt = ""
            this.reCmt = !this.reCmt
            this.getCmtList()
            const noticeContent = {
              myId: this.$store.state.id,
              targetId: parentMemberId,
              contentType: 2, //0:좋아요 1:댓글
              postId: vm.postid,
              noticeType: 0, //0:sns ,1:소모임, 2:강의
            };
            console.log(noticeContent)
            this.stompClient.send(
              "/app/Notice",
              JSON.stringify(noticeContent),
              (res) => {
                console.log(res);
              }
            );
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
  },
};
</script>
<style scoped lang="css" src="@/assets/css/sns/CmtReg.css" />