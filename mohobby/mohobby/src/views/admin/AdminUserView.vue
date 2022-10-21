<template>
  <main>
    <AdminSidebar></AdminSidebar>
    <div style="margin-left: 60px; width: 1000px">
      <br />
      <h3>신고된 유저</h3>
      <br />
      <v-data-table
        :headers="headers"
        :items="moimUserList"
        sort-by="calories"
        class="elevation-1"
      >
        <template v-slot:item.actions="{ item }">
          <v-icon small class="mr-2" @click="editFlagUser(item)">
            mdi-pencil
          </v-icon>
          <v-icon @click="deleteFlagUser(item)"> mdi-delete </v-icon>
        </template>
        small

        <template v-slot:no-data>
          <v-btn color="primary" @click="initialize"> Reset </v-btn>
        </template>
        <template v-slot:item.showDetail="{ item }">
          <v-icon @click="goToMoim(item)"> mdi-arrow-right-bold-box </v-icon>
        </template>
        small

        <template v-slot:no-data>
          <v-btn color="primary" @click="initialize"> Reset </v-btn>
        </template>
      </v-data-table>
    </div>
  </main>
</template>
<script>
import AdminSidebar from "../../components/admin/AdminSidebar.vue";
export default {
  name: "",
  components: { AdminSidebar },
  data() {
    return {
      dialog: false,
      dialogDelete: false,
      headers: [
        {
          text: "신고번호",
          align: "start",
          sortable: false,
          value: "flagId",
        },
        { text: "신고자", value: "flagFrom" },
        { text: "신고소모임", value: "flagTo" },
        { text: "신고코드", value: "flagCode" },
        { text: "신고이유 (g)", value: "flagReason" },
        { text: "관리자 승인여부", value: "adminConfirm" },
        { text: "신고결과", value: "flagResult" },
        { text: "수정하기", value: "actions", sortable: false },
        { text: "상세보기", value: "showDetail", sortable: false },
      ],
      desserts: [],
      editedIndex: -1,
      editedItem: {},
      defaultItem: {
        name: "",
        calories: 0,
        fat: 0,
        carbs: 0,
        protein: 0,
      },
      moimUserList: [],
      moimOneInfo: [],
      moimOpen : 0,
    };
  },
  beforeCreate() {},
  created() {
    this.getFlagedUser();
  },
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeUnmount() {},
  unmounted() {},
  computed: {
  },
  watch: {},

  methods: {
    getFlagedUser() {
      const vm = this;
      this.axios({
        url: "http://localhost:8088/java/admimflaguser",
        method: "get",
      })
        .then(function (response) {
          console.log(response);
          for (let i = 0; i < response.data.length; i++) {
            if (response.data[i].adminConfirm == 0) {
              response.data[i].adminConfirm = "미승인";
            } else if (response.data[i].adminConfirm == 1) {
              response.data[i].adminConfirm = "승인";
            }
            if (response.data[i].flagResult == 0) {
              response.data[i].flagResult = "통과";
            } else if (response.data[i].flagResult == 1) {
              response.data[i].flagResult = "패널티";
            }
          }
          vm.moimUserList = response.data;
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    editFlagUser(item) {
      this.editedItem = Object.assign({}, item);

      const vm = this;

      (async () => {
        const { value: fruit } = await this.$swal.fire({
          title: "유저 신고 결과를 결정해 주세요",
          input: "select",
          inputOptions: {
            0: "통과",
            1: "패널티",
          },
          inputPlaceholder: "유저 신고 결과",
          showCancelButton: true,
          inputValidator: (value) => {
            return new Promise((resolve) => {
              if (value) {
                if(value == 1) {
                  this.moimOpen = 1;
                } 
                this.axios({
                  url: "http://localhost:8088/java/updateFlag",
                  method: "put",
                  data: {
                    flagResult: value,
                    adminConfirm: 1,
                    flagId: item.flagId,
                  },
                })
                  .then(function (response) {
                    console.log(response);
                    vm.$swal.fire("유저 신고결과 수정이 완료되었습니다");
                    vm.updateMoimOpen(value);
                    vm.getFlagedUser();
                  })
                  .catch(function (error) {
                    console.log(error);
                    console.log("유저 신고결과 수정 실패");
                  });
              } else {
              }
            });
          },
        });
      })();
    },
    updateMoimOpen() {
      this.axios({
        url: "http://localhost:8088/java/updateuserblock",
        method: "put",
        data: {
          moimOpen: 2,
          moimId: 1,
        },
      })
        .then(function (response) {
          console.log(response);
          console.log("유저 오픈여부 수정 성공");
        })
        .catch(function (error) {
          console.log(error);
          console.log("유저 오픈여부 수정 실패");
        });
    },
    deleteFlagUser(item) {
      const vm = this;
      this.editedIndex = this.moimFlagList.indexOf(item);
      this.editedItem = Object.assign({}, item);
      console.log(item.flagId);
      console.log(item.flagId);
      console.log(item.flagId);
      console.log(item.flagId);
      console.log(item.flagId);

      this.axios({
        url: "http://localhost:8088/java/flagging/" + item.flagId,
        method: "delete",
      })
        .then(function (response) {
          console.log(response);
          vm.$swal.fire("유저 신고결과 식제가 완료되었습니다");
          vm.getFlagedUser();
        })
        .catch(function (error) {
          console.log(error);
          console.log("유저 신고결과 식제 실패");
        });
    },

    async goToMoim(item) {
      this.editedItem = Object.assign({}, item);
      await this.getOneMoim(this.editedItem.flagTo);
      if (this.moimOneInfo.moimOpen == 2) {
        this.$swal.fire("관리자에 의해 접근 금지된 모임입니다.");
      } else {
        this.$router.push({
          name: "moimBoard",
          params: { moimId: this.editedItem.flagTo, boardType: 1 },
        });
      }
    },

    async getOneUser(moimId) {
      const vm = this;
      await this.axios
        .get("/userOneInfo/" + moimId, {})
        .then((response) => {
          vm.moimOneInfo = response.data;
        })
        .catch(function (error) {
          console.log(error);
        });
    },
  },
};
</script>