<template>
    <main>
        <AdminSidebar></AdminSidebar>
        <div style="margin-left: 60px; width: 1000px">
        <br>
        <h3>신고된 소모임</h3>
        <br>
        <v-row> 
        <v-col lg="12">
            <v-data-table
                :headers="headers"
                :items="moimFlagList"
                :items-per-page="5"
                class="elevation-1">
            <template v-slot:item.action>
                <v-btn color="success" @click="showFlaggedMoim($event)">상세보기</v-btn>
            </template>
            </v-data-table>
        </v-col>
    </v-row>
    </div>
    </main>
</template>
<script>
import AdminSidebar from "../../components/admin/AdminSidebar.vue";
export default {
    name: '',
    components: { AdminSidebar },
    data() {
        return {
             headers: [
          {
            text: '신고번호',
            align: 'start',
            sortable: false,
            value: 'flag_id',
          },
          { text: '신고자', value: 'flagFrom' },
          { text: '신고소모임', value: 'flagTo' },
          { text: '신고코드', value: 'flagCode' },
          { text: '신고이유', value: 'flagReason' },
          { text: '상세보기', value: 'action' },
          { text: '관리자 승인여부', value: 'adminConfirm' },
          { text: '신고결과', value: 'flagResult' },
        ],
        moimFlagList : [],
    }
},
    beforeCreate() {},
    created() {
      this.getFlagedMoim();
    },
    beforeMount() {},
    mounted() {},
    beforeUpdate() {},
    updated() {},
    beforeUnmount() {},
    unmounted() {},
    methods: {
      getFlagedMoim() {
        const vm = this;
          this.axios({
            url: "http://localhost:8088/java/adminflagmoim",
            method: "get",
          })
            .then(function (response) {
              console.log(response);
              for (let i = 0; i < response.data.length; i++) {
                if(response.data[i].adminConfirm == 0) {
                  response.data[i].adminConfirm = "미승인";
                } else if(response.data[i].adminConfirm == 1){
                  response.data[i].adminConfirm = "승인";
                }
                if(response.data[i].flagResult == 0) {
                  response.data[i].flagResult = "통과";
                } else if(response.data[i].flagResult == 1) {
                  response.data[i].flagResult = "패널티";
                }
              }
              vm.moimFlagList = response.data;
            })
            .catch(function (error) {
              console.log(error);
            });
      },
      showFlaggedMoim(event) {
        // console.log(this.items[idx].flagTo);
        // console.log(this.items[idx].flagTo);
        // console.log(this.items[idx].flagTo);
        // console.log(this.items[idx].flagTo);
      //   this.$router.push({
      //   name: "moimBoard",
      //   params: { moimId: this.items[idx].flagTo, boardType: 1 },
      // });
      }
    }
}
</script>