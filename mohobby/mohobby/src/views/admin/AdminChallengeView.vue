<template>
    <main>
        <AdminSidebar></AdminSidebar>
        <h3>승인 대기 중인 챌린지</h3>
         <v-col class="py-2" v-for="(category, index) in categorys" :key="index">
            <v-btn>{{ category }}</v-btn>
        </v-col>
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
            categorys: ['공예', '운동', '음악', '미술', '기타' ],
            headers: [
          {
            text: '챌린지번호',
            align: 'start',
            sortable: false,
            value: 'challId',
          },
          { text: '챌린지명', value: 'challName' },
          { text: '유저ID', value: 'memberId' },
          { text: '인증날짜', value: 'certDate' },
          { text: '상세보기', value: 'action' },
          { text: '인증승인여부', value: 'certYn' },
        ],
        flags: [
          {
            challId : 1,
            challName: '한달에 독서 3권하기 가능? 불가능?',
            memberId: 'user1',
            certDate: '2022-10-03',
            certYn: '승인됨'
          },
          {
            challId : 1,
            challName: '한달에 독서 3권하기 가능? 불가능?',
            memberId: 'user1',
            certDate: '2022-10-03',
            certYn: '승인됨'
          },
          {
            challId : 1,
            challName: '한달에 독서 3권하기 가능? 불가능?',
            memberId: 'user1',
            certDate: '2022-10-03',
            certYn: '승인됨'
          },
          {
            challId : 1,
            challName: '한달에 독서 3권하기 가능? 불가능?',
            memberId: 'user1',
            certDate: '2022-10-03',
            certYn: '승인됨'
          },
          {
            challId : 1,
            challName: '한달에 독서 3권하기 가능? 불가능?',
            memberId: 'user1',
            certDate: '2022-10-03',
            certYn: '승인됨'
          },
          {
            challId : 1,
            challName: '한달에 독서 3권하기 가능? 불가능?',
            memberId: 'user1',
            certDate: '2022-10-03',
            certYn: '승인됨'
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
<style scoped>
.py-2 {
    display: inline-block;
    width: 80px;
}
</style>