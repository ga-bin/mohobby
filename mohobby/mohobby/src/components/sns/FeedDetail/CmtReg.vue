<!-- 댓글입력창 -->
<template>
  <div>
    <div>


      <!-- 본 댓글입력창 : 세션값 있으면 입력 가능. 없으면 입결 불가능-->
      <v-card-actions>
        <v-col cols="10" v-if="memberId">
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
            @keydown.enter="regCmt()"
          />
        </v-col>
        <v-col cols="10" v-else>
          <v-text-field
            class="m1-11"
            placeholder="댓글은 로그인부터 부탁드립니다🙏"
            disabled
            counter
            filled
            rounded
            solo
            dense
            hide-details
          />
        </v-col>
        <v-spacer></v-spacer>


        <!-- 댓글 등록버튼 -->
        <div style="margin-right: 80px">
          <v-btn @click="regCmt()" class="ma-2 white--text" color="#2ac187" dense rounded>등록</v-btn>
        </div>
      </v-card-actions>
    </div>
    <!-- 본 댓글 입력창 끝 -->

  
    <!-- 댓글리스트 -->
    <div>
      <div class="profile" v-for="cmt in comments" :key="cmt.commId">
        <div v-if="cmt.parentCommId != ''" style="width: 35px"></div>
        <v-avatar class="ml-10 my-5 mr-4" color="grey darken-1" size="30">
          <v-img aspect-ratio="30" alt="profile_img" style="cursor: pointer"
            :src="require(`@/assets/image/user/${cmt.profileImg}`)"
          @click="$router.push({ path: '/snsUserFeed?userId=' + cmt.memberId }).catch(() => {$router.go(0);})" />
        </v-avatar>
        <input type="hidden" class="cmtId" value="cmt.commId" />
        <div class="user text-overline">
          {{ cmt.memberId }}
          <small class="date">
            <span style="font-size: 1em">
              {{writeDate(cmt.writeDate)}}
            </span>
          </small>


          <!-- 버튼: 저장, 답장, 수정, 삭제 
            댓글 쓴 사람과 로그인세션 아이디가 같을 떄 -> 수정, 삭제, 답장 버튼
           -->
          <div class="btn">
            <v-btn
              x-small
              outlined
              color="dark-grey"
              class="mr-3"
              @click="showRegReCmt(cmt.commId, cmt.memberId)"
              >답장</v-btn>

            <v-btn
            v-if="cmt.memberId == memberId && saveBtn == true"
              x-small
              outlined
              color="success"
              class="mr-3"
              @click="editCmt(cmt.commId)"
              >저장</v-btn>

            <v-btn
            v-if="cmt.memberId == memberId && editBtn == true"
              x-small
              outlined
              color="success"
              class="mr-3"
              @click="showEditForm(cmt.commId)"
              >수정</v-btn>

              <v-btn
              v-if="cmt.memberId == memberId && deleteBtn == true"
              x-small
              outlined
              color="error"
              @click="deleteCmt(cmt.commId, cmt.targetId)">
              삭제</v-btn>
          </div>
          <!-- 댓글 버튼 끝 -->


          <!-- 대댓 유저소환 -->
          <v-card-actions>
            <div class="content">
              <div v-if="cmt.parentCommId != ''">
                <span class="member_id" 
                      @click="$router.push({path: '/snsUserFeed?userId=' + cmt.parentMemberId,}).catch(() => {$router.go(0);})">
                <strong>@{{ cmt.parentMemberId }}</strong></span>
                {{ cmt.content }}
              </div>
              <div v-if="cmt.parentCommId == '' && cmt.commId != editForm && !formValue">
                {{ cmt.content }}
              </div>
              <!-- 유저소환 끝 -->


              <!-- 댓글 수정창 -->
              <div v-if="cmt.commId == editForm && cmt.commId != reCmt && formValue">
                <v-textarea
                  name="editContent"
                  auto-grow
                  outlined
                  :value="originContent"
                  @keydown.enter="editCmt(cmt.commId)"
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
                  @keydown.enter="regReCmt(cmt.commId, cmt.memberId)"
                />
              </v-col>
              <v-spacer></v-spacer>


              <!-- 대댓글 등록버튼 -->
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
  name: "Cmt",
  props: {
    postid: String,
    targetId: String,
 
  },

  data() {
    return {
      cmtCount:"",
      inputCmt: "", //댓글v-model
      inputReCmt: "", //대댓글v-model
      editedContent: "", //수정댓글v-model
      originContent: "", //원댓글
      memberId: this.$store.state.id,
      comments: [], //cmt리스트
      reCmt: "", //대댓등록창 show여부
      editForm: "", //댓글수정창
      cmtMemberId: "", //소환된 회원
      formValue: false, //form 노출여부
      saveBtn: false, //저장버튼
      editBtn: false, //수정버튼
      deleteBtn: false, //삭제버튼
    };
  },

  created() {
    this.getCmtList(); //댓글리스트
    this.editBtn = true;
    this.deleteBtn = true;
  },

  methods: {

    //세션유무 검증
    confirmMember(memberId){  
      if(memberId){
        console.log("true");
        return true;
      }
      else{
        console.log("false");
        return false;
      }
      
    },

    //로그인 검증 모달
    loginConfirm(){

      this.$swal({
        title: "로그인하셔야 가능하세요🙏",
        text: "🙏로그인화면으로 이동부탁드립니다🙏",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#2ac187",
        cancelButtonColor: "#d33",
        cancelButtonText: "취소",
        confirmButtonText: "이동",
      }).then((result) => {
        if (result.isConfirmed) {
          this.$router.push({ path: "login" });
        }
      });

    },


    //date처리
    writeDate(writeDate) {

      return this.$moment(writeDate).fromNow();

    },

    
    //댓글리스트upload
    getCmtList() {

      this.axios("/sns/cmt/" + this.postid)
        .then((res) => {
          console.log(res.data);
          this.comments = res.data;
          this.cmtCount=res.data.length
          this.$emit('cmtCount',  this.cmtCount)
        })
        .catch((err) => {
          console.log(err);
        });

    },


    //댓글등록 - 입력창
    regCmt() {

      if(this.confirmMember(this.memberId) == false){ //유효성검사 - 회원 id, 내용값
        this.loginConfirm();

      } else if (this.inputCmt == ""){ 
        this.$swal("내용 입력하셔야 등록 가능하세요🙏")
        
      } else{
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
    showEditForm(commId, content) {
      
      this.formValue = !this.formValue;
      
      if (commId == this.editForm) {
        //수정창닫기
        this.editForm = -1;
      } else {
        //댓글창열기
        this.editForm = commId;
        this.originContent = content;
      }

    },
    
    //댓글 수정
    editCmt(commId) {
      this.checkLogin();//로그인검증
      this.axios
        .put("/sns/cmt/" + commId, {
          content: this.editedContent,
        })
        .then((res) => {
          this.editForm = "";
          console.log("댓글수정 성공! " + res);
          this.getCmtList();
          this.formValue = !this.formValue;
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //답장버튼 -> 대댓글 입력창 열기(닫기)
    //답장 click -> 인자 comm_id-> 데이터변수 reCmt에 바인딩 -> v-if에서 비교 조건으로 사용
    showRegReCmt(cmtId, cmtmemId) {
      if(this.confirmMember(this.memberId) == false){ //유효성검사 - 회원 id, 내용값
        this.loginConfirm();
      } else {
        this.inputReCmt = ""; //대댓 입력창 초기화
        if (cmtId == this.reCmt) {
          //댓글창닫기
          this.reCmt = -1; //reCmt 에 임의로 -1을 줘서 같을 수 없도록
        } else {
          //댓글창열기
          this.reCmt = cmtId;
          if (cmtId == this.reCmt) this.cmtMemberId = "@" + cmtmemId;
        }
      }
    },
    //대댓글등록
    regReCmt(commId, parentMemberId) {
      if(this.confirmMember(this.memberId) == false){ //유효성검사 - 회원 id, 내용값
        this.loginConfirm();
      } else if (this.inputCmt == ""){ 
        this.$swal("내용 입력하셔야 등록 가능하세요🙏")
      } else{
      let vm =this
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
            postId: vm.postid,
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
};
</script>
<style scoped lang="css" src="@/assets/css/sns/CmtReg.css" />
