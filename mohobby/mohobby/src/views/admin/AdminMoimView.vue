<template>
    <main>
        <AdminSidebar></AdminSidebar>
        <h3>신고된 소모임</h3>
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
            text: '신고번호',
            align: 'start',
            sortable: false,
            value: 'flag_id',
          },
          { text: '신고자', value: 'flag_from' },
          { text: '신고소모임', value: 'flag_to' },
          { text: '신고코드', value: 'flag_code' },
          { text: '신고이유', value: 'flag_reason' },
          { text: '상세보기', value: 'action' },
          { text: '관리자 승인여부', value: 'admin_confirm' },
          { text: '신고결과', value: 'flag_result' },
        ],
        flags: [
          {
            flag_id: '1',
            flag_from: 'user1',
            flag_to: 'user2',
            flag_code: 10,
            flag_reason: '부적절한 게시물의 업로드가 잦습니다.',
            admin_confirm: '확인중',
            flag_result: '',
          },
            {
            flag_id: '1',
            flag_from: 'user1',
            flag_to: 'user2',
            flag_code: 10,
            flag_reason: '부적절한 게시물의 업로드가 잦습니다.',
            admin_confirm: '확인중',
            flag_result: '',
          },
             {
            flag_id: '1',
            flag_from: 'user1',
            flag_to: 'user2',
            flag_code: 10,
            flag_reason: '부적절한 게시물의 업로드가 잦습니다.',
            admin_confirm: '확인중',
            flag_result: '',
          },
             {
            flag_id: '1',
            flag_from: 'user1',
            flag_to: 'user2',
            flag_code: 10,
            flag_reason: '부적절한 게시물의 업로드가 잦습니다.',
            admin_confirm: '확인중',
            flag_result: '',
          },
            {
            flag_id: '1',
            flag_from: 'user1',
            flag_to: 'user2',
            flag_code: 10,
            flag_reason: '부적절한 게시물의 업로드가 잦습니다.',
            admin_confirm: '확인중',
            flag_result: '',
          },
          {
            name: 'Jelly bean',
            calories: 375,
            fat: 0.0,
            carbs: 94,
            protein: 0.0,
            iron: '0%',
          },
          {
            name: 'Lollipop',
            calories: 392,
            fat: 0.2,
            carbs: 98,
            protein: 0,
            iron: '2%',
          },
          {
            name: 'Honeycomb',
            calories: 408,
            fat: 3.2,
            carbs: 87,
            protein: 6.5,
            iron: '45%',
          },
          {
            name: 'Donut',
            calories: 452,
            fat: 25.0,
            carbs: 51,
            protein: 4.9,
            iron: '22%',
          },
          {
            name: 'KitKat',
            calories: 518,
            fat: 26.0,
            carbs: 65,
            protein: 7,
            iron: '6%',
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