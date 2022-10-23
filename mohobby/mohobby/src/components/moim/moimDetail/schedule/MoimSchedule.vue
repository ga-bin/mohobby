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
            @click="prev()"
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
            @click="next()"
          >
            <v-icon small>
              mdi-chevron-right
            </v-icon>
          </v-btn>
          
          <v-toolbar-title v-if="calendar">
            {{today}}
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
          <v-calendar   v-model="value" ref="calendar" :start="start" @click:date="open" :type="type" ></v-calendar>
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
      month:'',
      year:'',
      value: '',
      dialog: false,
      today:'',
      //받아오는 값 저장할 변수 지정
      calendar: {
        startDate: '',
        startTime: '',
        info: '',
        endDate: '',
        endTime: '',
        title: '',
        dateOpen: false,
        start: '2022-10-07',
        type: 'month',
        memberId: this.$route.params.moimId,
      }
    }},

  created(){
    this.todate()
  },
  components: { MakeSchedule },
  methods: {
    todate() {
      var day = new Date();
      this.year = day.getFullYear();
      this.month = ("0" + (day.getMonth() + 1)).slice(-2);
      this.today=this.year+"-"+this.month;
    },
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
    },
    prev () {
      this.month--
      if(this.month<1){
        this.month=12;
        this.year--;
      }
      this.today=this.year+"-"+this.month;
      this.$refs.calendar.prev()
    },
    next () {
      this.month++
      if(this.month>12){
        this.month=1;
        this.year++;
      }
      this.today=this.year+"-"+this.month;
      this.$refs.calendar.next()
    },
  },
  mounted: {

  },
  create(){
this.calendar.title = new Date()
  }
}
</script>
<style>
.smallCal{
  width: 200px;
}
</style>