<template>
    <div class="container">
        <v-card-text>
            <v-container>
                <v-row>
                    <v-col cols="12" sm="6" md="4">
                        <v-dialog v-model="dialog" scrollable max-width="500px">
                            <template v-slot:activator="{ on, attrs }">
                                <v-card v-bind="attrs" v-on="on" class="mx-auto pa-10" width="344" height="150"
                                    color="green accent-3">
                                    <div class="thumbnail-wrappper ml-5">
                                        <v-img class="mt-6" src="@/assets/image/moim/mohobby.png"></v-img>
                                    </div>
                                </v-card>
                            </template>
                            <v-card>
                                <v-card-title class="justify-center">
                                    <span>팔로워</span>
                                </v-card-title>
                                <v-divider></v-divider>
                                <v-row class="pa-5">
                                    <v-col cols="12" sm="6" md="1">
                                        <v-avatar class="mb-4" color="grey darken-1" size="50">
                                            <v-img aspect-ratio="30" src="@/assets/image/sns/image1.png"></v-img>
                                        </v-avatar>
                                    </v-col>
                                    <v-col cols="12" sm="6" md="4" class="mt-3 ml-5">
                                        <div>user1</div>
                                    </v-col>
                                    <v-col cols="12" sm="6" md="6" class="mt-1">
                                        <v-card-actions class="select">
                                            <v-spacer></v-spacer>
                                            <v-icon size="36" color="green">mdi-check-circle</v-icon>
                                        </v-card-actions>
                                    </v-col>
                                </v-row>
                                <v-divider></v-divider>
                                <v-card-actions class="justify-center ma-5">
                                    <v-btn color="error" @click="dialog = false">
                                        취소
                                    </v-btn>
                                    <v-btn color="success" @click="dialog = false">
                                        등록
                                    </v-btn>
                                </v-card-actions>
                            </v-card>
                        </v-dialog>
                        <div class="text">팔로워 목록</div>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                        <div @click="follow()">
                            <v-card class="mx-auto pa-10" width="344" height="150" color="green accent-3">
                                <div class="thumbnail-wrappper2 mx-auto">
                                    <v-img src="@/assets/image/moim/pngwing.com.png"></v-img>
                                </div>
                            </v-card>
                        </div>
                        <div class="text">팔로잉 목록</div>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                        <v-card class="mx-auto pa-5" width="344" height="150" color="green accent-3">
                            <div class="thumbnail-wrappper3 mx-auto mb-10">
                                <v-img src="@/assets/image/moim/qrcode.png"></v-img>
                            </div>
                        </v-card>
                        <div class="text">팔로잉 목록</div>
                    </v-col>
                </v-row>
                <hr class="mt-16 mb-16">
                <v-row>
                    <v-col cols="12" sm="6" md="1">
                        <v-avatar class="mb-4" color="grey darken-1" size="64">
                            <v-img aspect-ratio="30" src="@/assets/image/sns/image1.png"></v-img>
                        </v-avatar>
                    </v-col>
                    <v-col cols="12" sm="6" md="4" class="mt-5 ml-5">
                        <div>user1</div>
                    </v-col>
                    <v-col cols="12" sm="6" md="6" class="mt-2">
                        <v-card-actions class="select">
                            <v-spacer></v-spacer>
                            <v-icon size="36" color="green">mdi-check-circle</v-icon>
                        </v-card-actions>
                    </v-col>
                </v-row>
                <v-row class="justify-center">
                    <div class="margin">
                        <v-col cols="12" sm="6" md="12">
                            <v-btn class="ma-2" color="success">
                                초대하기
                            </v-btn>
                        </v-col>
                    </div>
                </v-row>
            </v-container>
        </v-card-text>
    </div>
</template>
<script>
export default {
    data() {
        return {
            dialog : false
        };
    },
    methods() {

    }
}

</script>
<style scoped>
.container {
    width: 85%;
}

.text {
    text-align: center;
    margin-top: 30px;
    font-size: 20px;
    font-weight: bold;
}

.thumbnail-wrappper {
    width: 80%;
}

.thumbnail-wrappper2 {
    width: 40%;
}

.thumbnail-wrappper3 {
    width: 50%;
}

.margin {
    margin-top: 100px;
}

v-img {
    max-width: 100%;
    height: auto;
}
</style>