<template>
    <div id="classInfoInput">
        <h1>상세내용입니다</h1>

    </div>
</template>

<script>


export default {
    name: 'classInfo',
    data() {
        return {
            path: '',
        }
    },
    props: {
        classId: {
            type: String,
            default: 1,
        },
    },
    created() {
        this.readInfo();
    },
    methods: {
        readInfo() {
            let content = "";

            this.axios.get('/readClassInfo', {
                params: {
                    classId: this.classId
                }
            }).then(res => {
                document.querySelector("#classInfoInput").innerHTML = res.data;
            }).catch(err => {
                console.log(err);
            }).finally(() => {

            })



        },
        readPath() {

        },
    },
}
</script>

<style scoped>

</style>