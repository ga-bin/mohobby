<template>
  <main>
    <AdminSidebar></AdminSidebar>
    <h3>Q&A게시판 질문 내역</h3>
      <v-col
        class="d-flex"
        cols="6"
        sm="3"
      >
        <v-select
          v-model="selectedClassId"
          :items="classList"
          label="클래스 이름"
          item-text="className"
          item-value="classId"
          outlined
          @change="getQnAList()"
        ></v-select>
      </v-col>
      <template>
        <!-- <v-data-table
          :headers="headers"
          :items="QnAList"
          :items-per-page="5"
          class="elevation-1"
          @click:row="answerQnA()"
        > -->
        <v-data-table
        :headers="headers"
        :items="QnAList"
        sort-by="calories"
        class="elevation-1"
      >
        <template v-slot:item.actions="{ item }">
          <v-icon small class="mr-2" @click="answerQnA(item)">
            mdi-pencil
          </v-icon>
          <!-- <v-icon @click="deleteFlagUser(item)"> mdi-delete </v-icon> -->
        </template>
        small

        <template v-slot:no-data>
          <v-btn color="primary"> Reset </v-btn>
        </template>
        <template v-slot:item.showDetail="{ item }">
          <v-icon @click="goToUserProfile(item)"> mdi-arrow-right-bold-box </v-icon>
        </template>
        small

        <template v-slot:no-data>
          <v-btn color="primary"> Reset </v-btn>
        </template>
      </v-data-table>
        <!-- <template v-slot:item="{ item }">
        <tr @click="answerQnA(item)" v-b-modal.modal-lg>
          <td></td>
        </tr>
      </template> -->
        </v-data-table>
      </template>
    <h3>지급 내역</h3>
    <h3>강의 목록</h3>
    <v-row>
      <v-col lg="12">
        <v-data-table
          :headers="classHeaders"
          :items="classList"
          :items-per-page="5"
          class="elevation-1"
        >
          <template v-slot:item.action>
            <v-btn color="success">View</v-btn>
          </template>
        </v-data-table>
      </v-col>
    </v-row>
  </main>
</template>
<script>
import AdminSidebar from "../../components/admin/AdminSidebar.vue";
export default {
  name: "",
  components: { AdminSidebar },
  data() {
    return {
              headers: [
          {
            text: '강의번호',
            align: 'classId',
            sortable: false,
            value: 'classId',
          },
          { text: '게시글 번호', value: 'boardId' },
          { text: '강의명', value: 'className' },
          { text: '질문작성자', value: 'memberId' },
          { text: '질문내용', value: 'title' },
          { text: '답변내용', value: 'content' },
          { text: '답변여부', value: 'replyCheck' },
          { text: "수정하기", value: "actions", sortable: false },
          { text: "상세보기", value: "showDetail", sortable: false },
        ],
      tags: ["전체", "심사대기", "심사중", "승인", "미승인"],
      classHeaders: [
        {
          text: "강의번호",
          align: "start",
          sortable: false,
          value: "classId",
        },
        { text: "카테고리", value: "categoryName" },
        { text: "신청자", value: "memberId" },
        { text: "강의명", value: "className" },
        { text: "상세보기", value: "action" },
        { text: "관리자승인여부", value: "auditStatus" },
      ],
      classList: [],
      beforeComfirmList: [],
      comfirmingList: [],
      approveList: [],
      notApproveList: [],
      QnAList : [],
      selectedClassId : "",
      selectedQnABoard : {},
    };
  },
  beforeCreate() {},
  created() {
    this.getAllClass();
    this.getQnAAllList();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeUnmount() {},
  unmounted() {},
  methods: {
    // 클래스 전체 가지고오기
    getAllClass() {
      const vm = this;
      this.axios({
        url: "/adminClassList",
        method: "get",
      })
        .then(function (response) {
          console.log(response.data);
          vm.classList = response.data;

          for (let i = 0; i < response.data.length; i++) {
            console.log(
              "response.data[i].auditStatus" + response.data[i].auditStatus
            );
            if (response.data[i].auditStatus === 0) {
              vm.classList[i].auditStatus = "심사대기";
              vm.beforeComfirmList.push(vm.classList[i]);
            } else if (response.data[i].auditStatus === 1) {
              vm.classList[i].auditStatus = "심사중";
              vm.comfirmingList.push(vm.classList[i]);
            } else if (response.data[i].auditStatus === 2) {
              vm.classList[i].auditStatus = "승인";
              vm.approveList.push(vm.classList[i]);
            } else if (response.data[i].auditStatus === 3) {
              vm.classList[i].auditStatus = "미승인";
              vm.notApproveList.push(vm.classList[i]);
            }
          }

          console.log(vm.classList);
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    // qna리스트 전체가지고 오기
    getQnAAllList() {
      const vm = this;
      this.axios({
        url: "/QnAAllList/",
        method: "get",
      })
        .then(function (response) {
          console.log(response.data);
          vm.QnAList = response.data;

          for (let i = 0; i < response.data.length; i++) {
            console.log(
              "response.data[i].replyCheck" + response.data[i].replyCheck
            );
            if (response.data[i].replyCheck === 0) {
              vm.QnAList[i].replyCheck = "미처리";
            } else if (response.data[i].replyCheck === 1) {
              vm.QnAList[i].replyCheck = "처리";
            }
          }

          console.log(vm.classList);
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    // 선택한 클래스의 qna리스트 가지고 오기
    getQnAList() {
      const vm = this;
      this.axios({
        url: "/QnAList/" + this.selectedClassId,
        method: "get",
      })
        .then(function (response) {
          console.log(response.data);
          vm.QnAList = response.data;

          for (let i = 0; i < response.data.length; i++) {
            console.log(
              "response.data[i].replyCheck" + response.data[i].replyCheck
            );
            if (response.data[i].replyCheck === 0) {
              vm.QnAList[i].replyCheck = "미처리";
            } else if (response.data[i].replyCheck === 1) {
              vm.QnAList[i].replyCheck = "처리";
            }
          }

          console.log(vm.classList);
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    // QnA 답변하기
    answerQnA(item) {
      this.selectedQnABoard = Object.assign({}, item);

      const vm = this;
      this.$swal.fire({
      title: '답변을 입력하세요',
      html: `<input type="text" id="content" class="swal2-input" placeholder="답변">`,
      confirmButtonText: '답변하기',
      cancelButtonText: '취소하기',
      focusConfirm: false,
      preConfirm: () => {
        const content = this.$swal.getPopup().querySelector('#content').value;
        if (!content) {
          this.$swal.showValidationMessage(`답변이 등록되지 않았습니다.`)
        }
        return { content: content }
      }
    }).then((result) => {
      const content = result.value.content;
      vm.axios({
        url: "/QnAUpdate",
        method: "put",
        data: {
           content: result.value.content,
           boardId: item.boardId,
        },
      })
        .then(function (response) {
            console.log(response);
            vm.$swal.fire('답변이 등록되었습니다.');
            vm.getAllClass();
        })
        .catch(function (error) {
          console.log(error);
        });
    })
}
  },
};
</script>
