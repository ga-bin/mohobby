<template>
    <div class="container">
        <v-card-text>
            <v-container>
                <v-row>
                    <v-col cols="12" sm="6" md="4">
                        <v-text-field outlined disabled value="KRW(₩)" v-model="price"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="8">
                        <v-text-field outlined placeholder="금액을 입력해주세요" v-model="price" prefix="₩">
                        </v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                        <v-avatar class="mb-4" color="grey darken-1" size="64">
                            <v-img aspect-ratio="30" :src=src></v-img>
                        </v-avatar>
                    </v-col>
                    <v-spacer></v-spacer>
                    <v-col cols="12" sm="6" md="4">
                            <MemberSelect></MemberSelect>
                    </v-col>
                </v-row>
                <hr class="mt-5 mb-5">
                <v-row>
                    <v-col cols="12" sm="6" md="1">
                        <v-avatar class="mb-4" color="grey darken-1" size="64">
                            <v-img aspect-ratio="30" :src=src></v-img>
                        </v-avatar>
                    </v-col>
                    <v-col cols="12" sm="6" md="10" class="ml-5">
                        <span class="mb-3">유저아이디</span>
                        <v-text-field outlined placeholder="금액 들어올 칸" prefix="₩">
                        </v-text-field>
                    </v-col>
                </v-row>
                <v-row justify="center" class="mt-12">
                    <v-btn
                    color="success"
                    >
                        짜잔
                    </v-btn>
                </v-row>
            </v-container>
        </v-card-text>
    </div>
</template>
<script>
import MemberSelect from "./MemberSelect.vue";
export default {
    data() {
        return {
            price: '',
            src: "https://dimg.donga.com/ugc/CDB/WEEKLY/Article/5a/d0/5c/e0/5ad05ce00110d2738de6.jpg"
        };
    },
    components: { MemberSelect }
}

</script>
<style scoped>
.container {
    width: 85%;
}
</style>