<template>
  <div style="margin: 50px 40px;">
        <v-row style="border: 1px solid #ededed; border-radius: 10px;" >
            <v-col cols="3" class="d-flex justify-center align-center">
                <v-sheet>
                    <div>
                        <v-row class="d-flex justify-center mt-5">
                            <v-img
                                max-height="400"
                                max-width="400"
                                src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
                            ></v-img>
                        </v-row>
                        <v-row class="d-flex justify-center">
                            <div class="mt-3">강의이름</div>
                        </v-row>
                        <v-row class="d-flex justify-center">
                            <div>장소</div>
                        </v-row>
                        <v-row class="d-flex justify-center">
                            <div>날짜</div>
                        </v-row>
                        <v-row class="d-flex justify-center my-5">
                            <v-btn small outlined>
                                출석
                            </v-btn>
                            <v-btn small outlined class="mx-3">
                                외출
                            </v-btn>
                            <v-btn small outlined>
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
                                <span>09:02</span>
                            </v-col>
                            <v-divider vertical></v-divider>
                            <v-col>
                                <span class="px-10">외출</span>
                                <span>09:02</span>
                            </v-col>
                            <v-divider vertical></v-divider>
                            <v-col>
                                <span class="px-10">복귀</span>
                                <span>09:02</span>
                            </v-col>
                            <v-divider vertical></v-divider>
                            <v-col>
                                <span class="px-10">조퇴</span>
                                <span>09:02</span>
                            </v-col>
                        </v-row>
                        <v-row class="d-flex justify-center pt-10">
                            <v-col class="pl-10">
                                <v-avatar
                                    color="#507864"
                                    size="56"
                                >
                                    <h4 style="color: white">86</h4>
                                </v-avatar>
                                출석
                            </v-col>
                            <v-col>
                                <v-avatar
                                    color="#507864"
                                    size="56"
                                >
                                    <h4 style="color: white">86</h4>
                                </v-avatar>
                                출석
                            </v-col>
                            <v-col>
                                <v-avatar
                                    color="#507864"
                                    size="56"
                                >
                                    <h4 style="color: white">86</h4>
                                </v-avatar>
                                출석
                            </v-col>
                            <v-col>
                                <v-avatar
                                    color="#507864"
                                    size="56"
                                >
                                    <h4 style="color: white">86</h4>
                                </v-avatar>
                                출석
                            </v-col>
                            <v-col>
                                <v-avatar
                                    color="#507864"
                                    size="56"
                                >
                                    <h4 style="color: white">86</h4>
                                </v-avatar>
                                출석
                            </v-col>
                        </v-row>
                        <v-row class="d-flex justify-center align-center pt-10">
                            <v-col cols="3" style="font-size: 0.9em;">
                                <span
                                    style="background-color: white; border: 1px solid grey; border-radius: 20px; width: 105px; font-size: 0.9em;"
                                    class="pa-1 pl-2"
                                >
                                    나의 출석율
                                </span>
                                <span class="pl-5">

                                    72.3% (86/119일)
                                </span>
                                
                            </v-col>
                            <v-col cols="9">
                                <v-progress-linear height="20" value="72.3" color="#2ac187" dark style="font-size: 0.8em;">
                                    72.3%
                                </v-progress-linear>
                            </v-col>
                        </v-row>
                        <v-row class="d-flex justify-center align-center">
                            <v-col cols="3" style="font-size: 0.9em;">
                                <span
                                    style="background-color: white; border: 1px solid grey; border-radius: 20px; width: 105px; font-size: 0.9em;"
                                    class="pa-1 pl-2"
                                >
                                    과정 진행율
                                </span>
                                <span class="pl-5">

                                    72.3% (86/119일)
                                </span>
                                
                            </v-col>
                            <v-col cols="9">
                                <v-progress-linear height="20" value="72.3" color="#2ac187" dark style="font-size: 0.8em;">
                                    72.3%
                                </v-progress-linear>
                            </v-col>
                        </v-row>
                    </div>
                </v-sheet>
            </v-col>
        </v-row>
    </div>
</template>

<script>
import moment from 'moment';

export default {
    data() {
        return {
           
        };
    },
    methods: {

    },
}
</script>

<style>

</style>