<template>
  <div class="container">
    <v-card class="pa-5">
      <div class="head">
      <div>투표 하기</div>
      </div>
      <hr>
      <v-text-field class="mt-3" label="투표 제목" hide-details />
      <v-text-field class="mt-3" label="항목 1" hide-details />
      <v-text-field class="mt-3" label="항목 2" hide-details />
      <v-text-field class="mt-3" label="항목 3" hide-details />

      <v-btn class="mt-4" small>투표 항목 추가하기</v-btn>

      <v-checkbox
        class="mb-4"
       
        label="익명 투표하기"
        hide-details
      ></v-checkbox>

      <v-expansion-panels>
        <v-expansion-panel>
          <v-expansion-panel-header v-slot="{ open }">
            <v-row no-gutters>
              <v-col cols="4">
                투표 종료일
              </v-col>
              <v-col cols="8" class="text--secondary">
                <v-fade-transition leave-absolute>
                  <span v-if="open"></span>
                  <v-row v-else no-gutters style="width: 100%">
                    <v-col cols="6">
                      {{ trip.end || '날짜를 정해주세요' }}
                    </v-col>
                  </v-row>
                </v-fade-transition>
              </v-col>
            </v-row>
          </v-expansion-panel-header>
          <v-expansion-panel-content>
            <v-row justify="space-around" no-gutters>

              <v-col cols="3">
                <v-menu ref="endMenu" :close-on-content-click="false" :return-value.sync="trip.end" offset-y
                  min-width="290px">
                  <template v-slot:activator="{ on, attrs }">
                    <v-text-field v-model="trip.end" label="End date" prepend-icon="mdi-calendar" readonly v-bind="attrs"
                      v-on="on"></v-text-field>
                  </template>
                  <v-date-picker v-model="date" no-title scrollable>
                    <v-spacer></v-spacer>
                    <v-btn text color="primary" @click="$refs.endMenu.isActive = false">
                      Cancel
                    </v-btn>
                    <v-btn text color="primary" @click="$refs.endMenu.save(date)">
                      OK
                    </v-btn>
                  </v-date-picker>
                </v-menu>
              </v-col>
            </v-row>
          </v-expansion-panel-content>
        </v-expansion-panel>
       </v-expansion-panels>

      <div class="attach">
      <v-btn class="mt-5" @click="goDetail()">첨부하기</v-btn>
      </div>
    </v-card>
  </div>
</template>
<script>
  export default {
    data(){
      return{
      date: '',
      calendarFlag: false,
      picker: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10),
      trip: {
        name: '',
        location: null,
        start: null,
        end: null,
      },
    }
    },
    methods: {
      goDetail: function () {
      this.$router.push({ path: "voteDetail" });
      },
      showCalendar(date) {
        this.calendarFlag = true;
      },
      onClickOutside() {
      this.calendarFlag = false
      },
      pickDate(){
        this.date = this.picker;
      }
    }
  }
</script>
<style scoped>
.container{
  max-width: 60%;
}
.head{
  text-align: center;
  margin-bottom: 2%;
}
.item{
  margin-top: 3%;
}
.attach{
  text-align: center;
  margin-top: 2%;
}
</style>