<template>
  <div class="pa-5">
    <v-row>
      <v-col cols="12" md="6" class="mb-4">
        <v-row>
          <v-col cols="6">
            <v-menu ref="dateOpen" v-model="dateOpen" :close-on-content-click="false" :return-value.sync="start"
              offset-y>
              <template v-slot:activator="{ on }">
                <v-text-field v-model="start" label="Start Date" prepend-icon="mdi-calendar" dense readonly outlined
                  hide-details v-on="on"></v-text-field>
              </template>

              <v-date-picker v-model="start" no-title>
                <v-spacer />
                <v-btn text color="primary" @click="dateOpen = false">Cancel</v-btn>
                <v-btn text color="primary" @click="$refs.dateOpen.save(start)">OK</v-btn>
              </v-date-picker>
            </v-menu>
          </v-col>
          <v-col cols="6">
            <v-select v-model="type" :items="typeOptions" label="Type" class="my-auto"  hide-details outlined dense>
            </v-select>
          </v-col>
        </v-row>
        <v-sheet height="500">
          <v-calendar ref="calendar" :start="start" @click:date="open" :type="type" ></v-calendar>
        </v-sheet>
      </v-col>
    </v-row>
    <!-- 다이어로그 띄우기 -->
    <div class="example">
      <MakeSchedule :dialog="dialog" :calendar="calendar" @dialogClose="dialogClose(name)"></MakeSchedule>
    </div>
  </div>
</template>
  
<script>
import MakeSchedule from "./MakeSchedule.vue"
export default {
  data() {
    return {
      dialog : false,
      //받아오는 값 저장할 변수 지정
      calendar:{
        startDate: '',
        startTime: '',
        hasTime: ''
      },
      dateOpen: false,
      start: '2022-10-07',
      type: 'month',
      typeOptions: [
        { text: 'Day', value: 'day' },
        { text: 'Week', value: 'week' },
        { text: 'Month', value: 'month' },
      ],
    }
  },
  components: { MakeSchedule },
  methods: {
    open(date) {
      console.log(date)
      //받아온 데이터 변수에 저장
      this.calendar.startDate = date.date;
      this.calendar.startTime = date.time;
      this.calendar.hasTime = date.hasTime;
      console.log(this.calendar);
      // this.$store.commit('OPEN_CALENDAR_DIALOG', date)
      //다이어로그 실행
      this.dialog = true;
    },
    dialogClose(name){
      console.log(name);
      this.dialog = false;
    }
  }
}
</script>