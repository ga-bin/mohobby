<template>
    <div class="container">
        <v-row justify="end">
            <v-dialog v-model="dialog" max-width="500">
                <template v-slot:activator="{ on, attrs }">
                    <v-btn color="green" dark text x-large v-bind="attrs" v-on="on" @click="selectMember()">
                        멤버 선택 >
                    </v-btn>
                </template>
                <v-card class="pa-9">
                    <v-card-title class="justify-center">
                        <span class="font-weight-bold">멤버 선택</span>
                    </v-card-title>

                    <!-- 멤버 검색 -->
                    <div>
                        <v-text-field outlined label="아이디를 검색하세요!" @keydown.enter="serchMember()" append-icon="mdi-magnify"></v-text-field>
                    </div>
                    <v-row>
                        <v-col cols="12" sm="6" md="2">
                            <v-avatar class="mb-4" color="grey darken-1" size="64">
                                <v-img aspect-ratio="30" :src=src></v-img>
                            </v-avatar>
                        </v-col>
                        <v-col cols="12" sm="6" md="4" class="mt-5">
                            <div>전체선택</div>
                        </v-col>
                        <v-col cols="12" sm="6" md="6" class="mt-5">
                            <v-spacer></v-spacer>
                            <div class="select"><v-icon size="36" color="grey">mdi-check-circle</v-icon></div>
                        </v-col>
                    </v-row>
                    <hr class="mt-5 mb-5">
                    <v-row>
                    <v-col cols="12" sm="6" md="2">
                            <v-avatar class="mb-4" color="grey darken-1" size="64">
                                <v-img aspect-ratio="30" :src=src></v-img>
                            </v-avatar>
                        </v-col>

                        <!-- 멤버 전체 조회 -->
                        <v-col cols="12" sm="6" md="4" class="mt-5" id="check">
                            <div v-if="members !== null" v-for="member in members">{{member.memberId}}</div>
                            <div v-if="search !== null" v-for="members in search">{{members.memberId}}</div>
                        </v-col>
                        <v-col cols="12" sm="6" md="6" class="mt-5">
                            <v-spacer></v-spacer>
                            <div class="select"><v-icon size="36" color="green">mdi-check-circle</v-icon></div>
                        </v-col>
                    </v-row>

                    <v-card-actions class="justify-center mt-6">
                        <v-btn color="green darken-1" outlined rounded text @click="[dialog = false, insertMember()]">
                            등록
                        </v-btn>
                        <v-btn color="green darken-1" outlined rounded text @click="dialog = false">
                            취소
                        </v-btn>
                    </v-card-actions>
                </v-card>
            </v-dialog>
        </v-row>
    </div>
</template>
<script>
export default {
    data() {
        return {
            members : [],
            search : [],
            moimId : this.$route.params.moimId, 
            dialog: false,
            src: "https://dimg.donga.com/ugc/CDB/WEEKLY/Article/5a/d0/5c/e0/5ad05ce00110d2738de6.jpg"
        }
    },
    methods:{
        selectMember() {
            this.axios.get("/moimMemberList", {
                params : {
                    moimId : this.moimId
                }
            })
            .then((resp)=> {
                console.log(resp)
                this.items = resp.data;
            })
            .catch((err) => {
                console.log(err)
            })
            },
        serchMember(){
            this.axios.get("/oneMemberSearch", {
                params : {
                    moimId : this.moimId
                }
            }).then((resp)=> {
                console.log(this.search)
                console.log(resp)
                this.search = resp.data;
                if(resp.data[0] == null) {
                    document.querySelector("#check").style.display  = "block"
                    document.querySelector("#check").innerText = "일치하는 검색결과가 없습니다.";
                    this.items = resp.data;
                } else {
                    document.querySelector("#check").style.display  = "none"
                    document.querySelector("#check").innerText = "";
                }

            }).catch((err)=>{
                console.log(err)
            })
        },
        insertMember(){

        }
    },
}
</script>
<style scoped>
.select {
    float : right
}
</style>