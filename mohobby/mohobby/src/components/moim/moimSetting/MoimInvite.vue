<template>
    <div class="container">
        <v-card-text>
            <v-container>
                <v-row>
                    <v-col cols="12" sm="6" md="4">
                        <v-card class="mx-auto" max-width="500" color="green accent-3">
                            <v-card-text class="pa-16">
                                <v-img src="@/assets/image/moim/mohobby.png"></v-img>
                            </v-card-text>
                        </v-card>
                        <div class="text">팔로잉 목록</div>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                        <v-card class="mx-auto" max-width="500" color="green accent-3">
                            <v-card-text class="pa-16">
                                <v-img src="@/assets/image/moim/pngegg.png"></v-img>
                            </v-card-text>
                        </v-card>
                        <div class="text">팔로잉 목록</div>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                        <v-card class="mx-auto" max-width="500" color="green accent-3">
                            <v-card-text class="pa-16">
                                <v-img src="@/assets/image/moim/mohobby.png"></v-img>
                            </v-card-text>
                        </v-card>
                        <div class="text">팔로잉 목록</div>
                    </v-col>
                </v-row>
            </v-container>
        </v-card-text>
    </div>
</template>
<script>
export default {
    data() {
        return {
        };
    },
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
</style>