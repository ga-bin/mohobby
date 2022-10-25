<template>
  <main>
    <AdminSidebar></AdminSidebar>
    <h3>Q&A게시판 질문 내역</h3>
    <h3>지급 내역</h3>
    <h3>강의 목록</h3>
    <v-row>
      <v-col lg="12">
        <v-data-table
          :headers="headers"
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
      tags: ["전체", "심사대기", "심사중", "승인", "미승인"],
      headers: [
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
      classList: [
        {
          catagory: "공예",
          memberId: "user1",
          className: "왕초보도 10일만에 만드는 레진공예 작품",
          auditStatus: "승인완료",
        },
        {
          catagory: "공예",
          memberId: "user1",
          className: "왕초보도 10일만에 만드는 레진공예 작품",
          auditStatus: "승인완료",
        },
        {
          catagory: "공예",
          memberId: "user1",
          className: "왕초보도 10일만에 만드는 레진공예 작품",
          auditStatus: "승인완료",
        },
        {
          catagory: "공예",
          memberId: "user1",
          className: "왕초보도 10일만에 만드는 레진공예 작품",
          auditStatus: "승인완료",
        },
        {
          catagory: "공예",
          memberId: "user1",
          className: "왕초보도 10일만에 만드는 레진공예 작품",
          auditStatus: "승인완료",
        },
        {
          catagory: "공예",
          memberId: "user1",
          className: "왕초보도 10일만에 만드는 레진공예 작품",
          auditStatus: "승인완료",
        },
        {
          catagory: "공예",
          memberId: "user1",
          className: "왕초보도 10일만에 만드는 레진공예 작품",
          auditStatus: "승인완료",
        },
        {
          catagory: "공예",
          memberId: "user1",
          className: "왕초보도 10일만에 만드는 레진공예 작품",
          auditStatus: "승인완료",
        },
      ],
      classList: [],
      beforeComfirmList: [],
      comfirmingList: [],
      approveList: [],
      notApproveList: [],
    };
  },
  beforeCreate() {},
  created() {
    this.getAllClass();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeUnmount() {},
  unmounted() {},
  methods: {
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
  },
};
</script>
