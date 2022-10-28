<template>
  <div style="margin: 50px 40px;">
        <v-row v-if="info.classId != null" style="border: 1px solid #ededed; border-radius: 10px;" >
            <v-col cols="3" class="d-flex justify-center align-center">
                <v-sheet>
                    <div>
                        <v-row class="d-flex justify-center">
                            <v-img
                                max-height="270"
                                max-width="270"
                                :src="require(`@/assets/image/class/thumb/${info.classId}/0.jpg`)"
                            ></v-img>
                        </v-row>
                        <v-row class="d-flex justify-center">
                            <div class="mt-3">{{ info.className | substr(15) }}</div>
                        </v-row>
                        <v-row class="d-flex justify-center mt-3">
                            <div>{{ info.address.substr(0,3) }}</div>
                        </v-row>
                        <v-row class="d-flex justify-center mt-7">
                            <div>{{ $moment(info.startDate).format('YYYY-MM-DD') + ' ~ ' + $moment(info.endDate).format('YYYY-MM-DD') }} </div>
                        </v-row>
                        <v-row class="d-flex justify-center mt-10">
                            <v-btn small outlined color="#2ac187" 
                                @click="attdBtn"
                                v-if="info.outDate == null || (info.comebackDate != null && info.outDate != null)"
                            >
                                출석
                            </v-btn>
                            <v-btn small outlined color="#2ac187" 
                                @click="exitBtn"
                                v-if="info.outDate != null && info.leaveDate == null && info.exitDate == null"
                            >
                                퇴실
                            </v-btn>
                            <v-btn small outlined color="#2ac187" 
                                @click="comebackBtn"
                                v-if="info.outDate != null && info.comebackDate == null"
                            >
                                복귀
                            </v-btn>
                            <v-btn small outlined class="mx-4" @click="outBtn">
                                외출
                            </v-btn>
                            <v-btn small outlined @click="leaveBtn">
                                조퇴
                            </v-btn>
                        </v-row>
                    </div>

                </v-sheet>
            </v-col>
            <v-col cols="9" class="d-flex">
                <v-sheet
                    color="#f3f3f3"
                    width="100%"
                    height="100%"
                    style="border-radius: 5px;"
                >
                    <div class="px-7 py-5">
                        <v-row class="">
                            <v-col>
                                나의 출결 및 진도 현황 ({{$moment().format('YYYY.MM.DD')}} 기준)
                            </v-col>
                        </v-row>
                        <v-row class="d-flex justify-center pt-10">
                            <v-col>
                                <span class="px-10">출석</span>
                                <span>{{ info.attdDate == null ? '00:00' : $moment(info.attdDate).format('HH:mm') }}</span>
                            </v-col>
                            <v-divider vertical></v-divider>
                            <v-col>
                                <span class="px-10">외출</span>
                                <span>{{ info.outDate == null ? '00:00' : $moment(info.outDate).format('HH:mm') }}</span>
                            </v-col>
                            <v-divider vertical></v-divider>
                            <v-col>
                                <span class="px-10">복귀</span>
                                <span>{{ info.comebackDate == null ? '00:00' : $moment(info.comebackDate).format('HH:mm') }}</span>
                            </v-col>
                            <v-divider vertical></v-divider>
                            <v-col>
                                <span class="px-10">조퇴</span>
                                <span>{{ info.leaveDate == null ? '00:00' : $moment(info.leaveDate).format('HH:mm') }}</span>
                            </v-col>
                        </v-row>
                        <v-row class="d-flex justify-center pt-10">
                            <v-col class="pl-10">
                                <v-avatar
                                    color="#507864"
                                    size="56"
                                >
                                    <h4 style="color: white">{{ info.sumAttd }}</h4>
                                </v-avatar>
                                출석
                            </v-col>
                            <v-col>
                                <v-avatar
                                    color="#507864"
                                    size="56"
                                >
                                    <h4 style="color: white">{{ info.sumLate }}</h4>
                                </v-avatar>
                                지각
                            </v-col>
                            <v-col>
                                <v-avatar
                                    color="#507864"
                                    size="56"
                                >
                                    <h4 style="color: white">{{ info.sumLeave }}</h4>
                                </v-avatar>
                                조퇴
                            </v-col>
                            <v-col>
                                <v-avatar
                                    color="#507864"
                                    size="56"
                                >
                                    <h4 style="color: white">{{ info.sumOut }}</h4>
                                </v-avatar>
                                외출
                            </v-col>
                            <v-col>
                                <v-avatar
                                    color="#507864"
                                    size="56"
                                >
                                    <h4 style="color: white">{{ info.sumAbsent }}</h4>
                                </v-avatar>
                                결석
                            </v-col>
                        </v-row>
                        <v-row class="d-flex justify-center align-center pt-10">
                            <v-col cols="4" style="font-size: 0.9em;">
                                <span
                                    style="background-color: white; border: 1px solid grey; border-radius: 20px; width: 105px; font-size: 0.9em;"
                                    class="pa-1 pl-2"
                                >
                                    나의 출석율
                                </span>
                                <span class="pl-5">

                                    {{ Math.round(info.sumAttd/info.total*100, 1) }}% ({{ info.sumAttd }}/{{ info.total }}일)
                                </span>
                                
                            </v-col>
                            <v-col cols="8">
                                <v-progress-linear 
                                    height="20" 
                                    :value="Math.round(info.sumAttd/info.total*100, 1)" 
                                    color="#2ac187" 
                                    dark 
                                    style="font-size: 0.8em;"
                                >
                                    {{ Math.round(info.sumAttd/info.total*100, 1) }}%
                                </v-progress-linear>
                            </v-col>
                        </v-row>
                        <v-row class="d-flex justify-center align-center">
                            <v-col cols="4" style="font-size: 0.9em;">
                                <span
                                    style="background-color: white; border: 1px solid grey; border-radius: 20px; width: 105px; font-size: 0.9em;"
                                    class="pa-1 pl-2"
                                >
                                    과정 진행율
                                </span>
                                <span class="pl-5">

                                    {{ info.progress }}% ({{ info.gone }}/{{ info.total }}일)
                                </span>
                                
                            </v-col>
                            <v-col cols="8">
                                <v-progress-linear 
                                    height="20" 
                                    :value="info.progress" 
                                    color="#2ac187" 
                                    dark 
                                    style="font-size: 0.8em;"
                                >
                                    {{ info.progress }}%
                                </v-progress-linear>
                            </v-col>
                        </v-row>
                    </div>
                </v-sheet>
            </v-col>
        </v-row>
        <v-card
            v-if="info.classId == null"
            flat justify="center" 
            align="center" 
            style="padding-top: 50px"
        >
            <h1>🙇</h1>
            <h1>수강중인 강의가 없습니다</h1>
        </v-card>
        <v-dialog
            v-model="dialog"
            width="400"
        >

            <v-card>
                <img :src="qrimg" style="width: 400px; height: 400px;" />
            </v-card>
        </v-dialog>
    </div>
</template>

<script>

export default {
    data() {
        return {
           info: {},
           dialog: false,
           qrimg: '',
        };
    },
    created() {
        this.getInfo();
    },
    methods: {
        getInfo() {
            this.axios('/class/attd', {
                params: {
                    memberId: this.$store.state.id,
                }
            }).then(res => {
                if(res.data != null) {
                    this.info = res.data;
                }
            }).catch(err => console.log(err));
        },
        floatingQR(url) {
            this.axios.get('/AttdQR', {
                params: {
                    link: url,
                }
            }).then( result => {
                this.qrimg = result.data;
            }).then( () => {
                this.dialog=true;
            });
        },
        attdBtn() {
            if(!this.info.classDate) {
                this.$swal('출석일이 아닙니다!', '', 'info');
                return;
            }

            let now = this.$moment().format('HH:mm');
            let start = this.info.startTime - 1 + ':30';
            if(now<=start) {
                this.$swal(start + '부터 출석이 가능합니다!', '', 'info');
                return;
            }

            if(this.info.leaveDate || this.info.exitDate) {
                this.$swal('조퇴 및 퇴실 후 재출석은 불가합니다!', '', 'info');
                return;
            }

            let url = this.$url + 'class/attd/login?id=' + this.$store.state.id + '&type=0';
            this.floatingQR(url);
        },
        exitBtn() {
            let now = this.$moment().format('HH:mm');
            let end = this.info.endTime + ':00';

            if(now<=end) {
                this.$swal(end + '부터 퇴실이 가능합니다!', '', 'info');
                return;
            }

            if(this.info.outDate && !this.info.comebackDate) {
                this.$swal('복귀 후 퇴실이 가능합니다!', '', 'info');
                return;
            }
        },
        comebackBtn() {

        },
        outBtn() {
            if(this.info.exitDate) {
                this.$swal('이미 퇴실하였습니다!', '', 'info');
                return;
            }

            if(!this.info.attdBtn) {
                this.$swal('입실 후 외출이 가능합니다!', '', 'info');
                return;
            }
        },
        leaveBtn() {
            if(this.info.exitDate) {
                this.$swal('이미 퇴실하였습니다!', '', 'info');
                return;
            }

            if(!this.info.attdBtn) {
                this.$swal('입실 후 조퇴가 가능합니다!', '', 'info');
                return;
            }
        },
    },
    watch: {
        dialog() {
            if(dialog) {
                //소켓구독
            }
            if(!dialog) {
                //소켓구독취소
            }
        },
    }
}
</script>

<style>

</style>