<template>
  <v-dialog max-width="600px" v-model="dialog">
    <v-card>
      <v-card-title>
        <h3>일정 추가</h3>
      </v-card-title>
      <v-card-text>
        <v-form class="px-3" ref="form">
          <v-text-field label="일정" v-model="calendar.title" prepend-icon="mdi-folder-marker">
          </v-text-field>
          <v-textarea label="상세설명" v-model="calendar.info" prepend-icon="mdi-pencil">
          </v-textarea>
          <v-row>
            <v-col cols="6" class="pb-0">
              <v-menu>
                <template v-slot:activator="{on}">

                  <v-text-field slot="activator" label="시작일" readonly prepend-icon="mdi-calendar-month" v-on="on"
                    :value="calendar.startDate" class=""></v-text-field>
                </template>

              </v-menu>
            </v-col>
            <v-col cols="6" class="pb-0">
              <v-menu :close-on-content-click="false" v-model="startTimer" offset-y>
                <template v-slot:activator="{ on }">
                  <v-text-field label="시작 시간" readonly :value="calendar.startTime" prepend-icon="mdi-timer" v-on="on">
                  </v-text-field>
                </template>
                <v-time-picker v-if="startTimer" v-model="calendar.startTime">
                  <v-btn class="mx-auto" @click="selectTime">선택
                  </v-btn>
                </v-time-picker>
              </v-menu>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="6" class="pt-0">
              <v-menu>
                <template v-slot:activator="{on}">

                  <v-text-field slot="activator" label="종료일" readonly prepend-icon="mdi-calendar-month" v-on="on"
                    :value="calendar.endDate" class=""></v-text-field>
                </template>
                <v-date-picker v-model="calendar.endDate" :allowed-dates="allowedDates"></v-date-picker>
              </v-menu>
            </v-col>
            <v-col cols="6" class="pt-0">
              <v-menu :close-on-content-click="false" v-model="endTimer" offset-y>
                <template v-slot:activator="{ on }">
                  <v-text-field label="종료 시간" readonly :value="calendar.endTime" prepend-icon="mdi-timer" v-on="on">
                  </v-text-field>
                </template>
                <v-time-picker v-if="endTimer" v-model="calendar.endTime">
                  <v-btn class="mx-auto" @click="selectTime">선택
                  </v-btn>
                </v-time-picker>
              </v-menu>
            </v-col>
          </v-row>

          <div class="text-center">
            <v-btn text class="primary white--text mx-2 mt-3" @click="submit()">
              추가
            </v-btn>
            <v-btn text class="primary white--text mx-2 mt-3" @click="dialog=false">
              닫기
            </v-btn>
          </div>
        </v-form>
      </v-card-text>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  //부모한테서 값을 받아오기
  props: ['dialog', 'calendar', 'items'],
  data() {
    return {
      startTimer: false,
      endTimer: false,
      dialoghere: ''
    }
  },
  methods: {
    submit() {
      let vm = this
      let myDate = new Date()
      let startTime = this.calendar.startTime
      let endTime = this.calendar.endTime
      this.axios
        .post("/scheduleInsert", {
          startDate: this.calendar.startDate,
          startTime: this.calendar.startTime,
          info: this.calendar.info,
          endDate: this.calendar.endDate,
          endTime: this.calendar.endTime,
          title: this.calendar.title,
          memberId: this.$store.state.id,
          moimId: this.$route.params.moimId
        }).then((resp) => {
          console.log('======================================')
          console.log('startDate:' + this.calendar.startDate)
          console.log('startTime:' + this.calendar.startTime)
          console.log('info:' + this.calendar.info)
          console.log('endDate:' + this.calendar.endDate)
          console.log('endTime:' + this.calendar.endTime)
          console.log('title:' + this.calendar.title)
          console.log('memberId ' + this.$store.state.id)
          console.log('moimId' + this.$route.params.moimId)
          console.log('======================================')
          vm.items.unshift({
            startDate: this.calendar.startDate,
            startTime: this.calendar.startTime,
            info: this.calendar.info,
            endDate: this.calendar.endDate,
            endTime: this.calendar.endTime,
            title: this.calendar.title,
            memberId: this.$store.state.id,
            moimId: this.$route.params.moimId
          })
          console.log(this.dialog)
        
          this.$emit('update:items', vm.items);
          this.$emit('dialogClose', false);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    close() {
      //this.$store.commit('CLOSE_CALENDAR_DIALOG');
    },
    selectTime() {
      this.endTimer = false;
      this.startTimer = false;
    },
    allowedDates(val) {
      let endDate = val.split('-').reduce((a, b) => a + b);
      let startDate = this.calendar.startDate.split('-').reduce((a, b) => a + b);
      return endDate >= startDate;
    }
  },

}
</script>