<template>
    <div>
        <AdminSidebar></AdminSidebar>
    <v-row> 
        <h3>신고된 유저</h3>
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
    </div>
</template>
<script>
import AdminSidebar from "../../components/admin/AdminSidebar.vue";
export default {
    components: { AdminSidebar },
     data () {
      return {
        headers: [
          {
            text: '신고번호',
            align: 'start',
            sortable: false,
            value: 'flag_id',
          },
          { text: '신고자', value: 'flag_from' },
          { text: '신고대상자', value: 'flag_to' },
          { text: '신고코드', value: 'flag_code' },
          { text: '신고이유', value: 'flag_reason' },
          { text: '상세보기', value: 'action' },
          { text: '관리자 승인여부', value: 'admin_confirm' },
          { text: '신고결과', value: 'flag_result' },
        ],
        flags: [
          {
            flag_id: 'Frozen Yogurt',
            flag_from: 159,
            flag_to: 6.0,
            flag_code: 24,
            flag_reason: 4.0,
            admin_confirm: '1%',
            flag_result: '1%',
          },
          {
            name: 'Ice cream sandwich',
            calories: 237,
            fat: 9.0,
            carbs: 37,
            protein: 4.3,
            iron: '1%',
          },
          {
            name: 'Eclair',
            calories: 262,
            fat: 16.0,
            carbs: 23,
            protein: 6.0,
            iron: '7%',
          },
          {
            name: 'Cupcake',
            calories: 305,
            fat: 3.7,
            carbs: 67,
            protein: 4.3,
            iron: '8%',
          },
          {
            name: 'Gingerbread',
            calories: 356,
            fat: 16.0,
            carbs: 49,
            protein: 3.9,
            iron: '16%',
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
     }
}
</script>