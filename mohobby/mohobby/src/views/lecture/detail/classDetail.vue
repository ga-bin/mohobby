<template>
    <div id="header-carousel">
        <v-carousel hide-delimiters style="height: 100%">
            <v-carousel-item
                v-for="i in classInfo.imgAmount"
                :key="i"
                :src="require(`@/assets/image/class/info/${classId}/${i}.jpg`)"
            >
                <v-btn
                    :color="classInfo.jjim == 1 ? 'pink' : 'gray'"
                    icon
                    x-large
                    absolute
                    right
                    @click.stop = "clickHeart"
                >
                    <v-icon>{{ classInfo.jjim == 1 ? 'mdi-heart' : 'mdi-heart-outline' }}</v-icon>
                </v-btn>
                <v-jumbotron dark>
                    <v-container style="position: absolute; bottom: 2px; left: 50%; transform:translateX(-50%); ">
                        <h1 style="font-size: 3.2em;">{{ classInfo.className }}</h1>
                    </v-container>
                </v-jumbotron>            
            </v-carousel-item>
        </v-carousel>
    </div>
</template>

<script>
export default {
    name: 'classDetail',
    props: {
        classId: {
            type: Number,
            default: 1,
        },
    },
    data() {
        return {
            classInfo: {},

        }
    },
    created() {
        this.initInfo();
    },
    methods: {
        initInfo() {
            this.axios.get('/class/detail/'+this.classId, {
                params: {
                    memberId: this.$store.state.id
                }
            }).then( res => {
                this.classInfo = res.data;
                console.log(this.classInfo);
            });
        },
        clickHeart: function() {
            if(!this.$store.state.id) {
                this.$swal('로그인 후 이용하세요!', '', 'info');
                return;
            }


            if(this.classInfo.jjim == 0) {
                this.axios('/class/jjim', {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json; charset=utf-8",
                    },
                    data: JSON.stringify({
                        targetId: this.classId,
                        targetType: 1,
                        memberId: this.$store.state.id
                    })
                }).catch(error => {
                    console.log(error);
                });

                this.classInfo.jjim = 1;

            } else if(this.classInfo.jjim == 1) {
                this.axios('/class/jjim', {
                    method: "DELETE",
                    headers: {
                        "Content-Type": "application/json; charset=utf-8",
                    },
                    data: JSON.stringify({
                        targetId: this.classId,
                        targetType: 1,
                        memberId: this.$store.state.id
                    })
                }).catch(error => {
                    console.log(error);
                });

                this.classInfo.jjim = 0;

            }
            
        },
    }
}
</script>

<style>

</style>