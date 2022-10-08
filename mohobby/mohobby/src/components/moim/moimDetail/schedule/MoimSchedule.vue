<template>
  <div>
    <v-row>
      <v-col cols="12" md="6" class="mx-auto">
        <v-sheet height="64">
          <v-toolbar
          flat
        >
        <!-- prev month -->
        <v-btn
            fab
            text
            small
            color="grey darken-2"
            @click="prev"
          >
            <v-icon small>
              mdi-chevron-left
            </v-icon>
          </v-btn>

          <!-- next month -->
          <v-btn
            fab
            text
            small
            color="grey darken-2"
            @click="next"
          >
            <v-icon small>
              mdi-chevron-right
            </v-icon>
          </v-btn>

          <!-- 달력 -->
          <v-toolbar-title v-if="$refs.calendar">
            {{ $refs.calendar.title }}
          </v-toolbar-title>
          <v-spacer></v-spacer>
          <v-menu
            bottom
            right
          >
          </v-menu>
        </v-toolbar>
      </v-sheet>

        <!-- 달력 부분 -->
        <v-sheet height="500" max-width="800">
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
      dialog: false,
      //받아오는 값 저장할 변수 지정
      calendar: {
        startDate: '',
        startTime: '',
        hasTime: '',
        year: '',
        month: ''
      },
      dateOpen: false,
      start: '2022-10-07',
      type: 'month',
      typeOptions: [
        { text: 'Day', value: 'day' },
        { text: 'Week', value: 'week' },
        { text: 'Month', value: 'month' },
      ],
      colors: ['blue', 'indigo', 'deep-purple', 'cyan', 'green', 'orange', 'grey darken-1'],
      names: ['Meeting', 'Holiday', 'PTO', 'Travel', 'Event', 'Birthday', 'Conference', 'Party'],
    }
  },
  components: { MakeSchedule },
  methods: {
    open(date) {
      console.log(date)
      //받아온 데이터 변수에 저장
      this.calendar.month = date.month;
      this.calendar.year = date.year;
      this.calendar.startDate = date.date;
      this.calendar.startTime = date.time;
      this.calendar.hasTime = date.hasTime;
      console.log(this.calendar);
      // this.$store.commit('OPEN_CALENDAR_DIALOG', date)
      //다이어로그 실행
      this.dialog = true;
    },
    dialogClose(name) {
      console.log(name);
      this.dialog = false;
    }
  },
  mounted: {
    prev () {
      this.$refs.calendar.month.prev()
    },
    next () {
      this.$refs.calendar.month.next()
    },
  }
}
</script>
<style>
.smallCal{
  width: 200px;
}
</style>