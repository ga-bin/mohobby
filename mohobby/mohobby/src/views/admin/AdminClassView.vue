<template>
    <main>
        <AdminSidebar></AdminSidebar>
        <h3>승인 대기 중인 강의</h3>
        <v-row> 
        <v-col lg="12">
            <v-data-table
                :headers="headers"
                :items="flags"
                :items-per-page="5"
                class="elevation-1">
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
    name: '',
    components: { AdminSidebar },
    data() {
        return {
             headers: [
          {
            text: '강의번호',
            align: 'start',
            sortable: false,
            value: 'flag_id',
          },
          { text: '카테고리', value: 'catagory' },
          { text: '신청자', value: 'memberId' },
          { text: '강의명', value: 'className' },
          { text: '상세보기', value: 'action' },
          { text: '관리자승인여부', value: 'auditStatus' },
        ],
        flags: [
            {
            catagory: '공예',
            memberId: 'user1',
            className: "왕초보도 10일만에 만드는 레진공예 작품",
            auditStatus: '승인완료',
          },
               {
            catagory: '공예',
            memberId: 'user1',
            className: "왕초보도 10일만에 만드는 레진공예 작품",
            auditStatus: '승인완료',
          },
               {
            catagory: '공예',
            memberId: 'user1',
            className: "왕초보도 10일만에 만드는 레진공예 작품",
            auditStatus: '승인완료',
          },
            {
            catagory: '공예',
            memberId: 'user1',
            className: "왕초보도 10일만에 만드는 레진공예 작품",
            auditStatus: '승인완료',
          },
            {
            catagory: '공예',
            memberId: 'user1',
            className: "왕초보도 10일만에 만드는 레진공예 작품",
            auditStatus: '승인완료',
          },
             {
            catagory: '공예',
            memberId: 'user1',
            className: "왕초보도 10일만에 만드는 레진공예 작품",
            auditStatus: '승인완료',
          },
            {
            catagory: '공예',
            memberId: 'user1',
            className: "왕초보도 10일만에 만드는 레진공예 작품",
            auditStatus: '승인완료',
          },
             {
            catagory: '공예',
            memberId: 'user1',
            className: "왕초보도 10일만에 만드는 레진공예 작품",
            auditStatus: '승인완료',
          },
        ],
    }
},
    beforeCreate() {},
    created() {},
    beforeMount() {},
    mounted() {},
    beforeUpdate() {},
    updated() {},
    beforeUnmount() {},
    unmounted() {},
    methods: {}
}
</script>
