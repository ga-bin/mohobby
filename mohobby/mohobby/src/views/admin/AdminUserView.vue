<template>
    <div>
        <AdminSidebar></AdminSidebar>
       <v-card
    class="mx-auto text-center"
    color="green"
    dark
    max-width="600"
  >
    <v-card-text>
      <v-sheet color="rgba(0, 0, 0, .12)">
        <v-sparkline
          :value="value"
          color="rgba(255, 255, 255, .7)"
          height="100"
          padding="24"
          stroke-linecap="round"
          smooth
        >
          <template v-slot:label="item">
            ${{ item.value }}
          </template>
        </v-sparkline>
      </v-sheet>
    </v-card-text>

    <v-card-text>
      <div class="text-h4 font-weight-thin">
        회원수 증가률
      </div>
    </v-card-text>

    <v-divider></v-divider>

    <v-card-actions class="justify-center">
      <v-btn
        block
        text
      >
        Go to Report
      </v-btn>
    </v-card-actions>
  </v-card>
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
            flag_id: '1',
            flag_from: 'user1',
            flag_to: 'user2',
            flag_code: 10,
            flag_reason: '상대방을 비방하는 글을 올렸습니다.',
            admin_confirm: '확인중',
            flag_result: '',
          },
            {
            flag_id: '1',
            flag_from: 'user1',
            flag_to: 'user2',
            flag_code: 10,
            flag_reason: '상대방을 비방하는 글을 올렸습니다.',
            admin_confirm: '확인중',
            flag_result: '',
          },
             {
            flag_id: '1',
            flag_from: 'user1',
            flag_to: 'user2',
            flag_code: 10,
            flag_reason: '상대방을 비방하는 글을 올렸습니다.',
            admin_confirm: '확인중',
            flag_result: '',
          },
             {
            flag_id: '1',
            flag_from: 'user1',
            flag_to: 'user2',
            flag_code: 10,
            flag_reason: '상대방을 비방하는 글을 올렸습니다.',
            admin_confirm: '확인중',
            flag_result: '',
          },
            {
            flag_id: '1',
            flag_from: 'user1',
            flag_to: 'user2',
            flag_code: 10,
            flag_reason: '상대방을 비방하는 글을 올렸습니다.',
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
        value: [
        423,
        446,
        675,
        510,
        590,
        610,
        760,
      ],
    }
     }
}
</script>
<style>
  .v-sheet--offset {
    position: relative;
  }
</style>