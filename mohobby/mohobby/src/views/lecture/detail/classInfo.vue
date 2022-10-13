<template>
    <div id="classInfoInput">
        <h1>상세내용입니다</h1>

    </div>
</template>

<script>
export default {
    name: 'classInfo',
    data() {
        return{
            path: '',
        }
    },
    props: {
        classId: {
            type: Number,
            default: 1,
        },
    },
    created() {
        this.readInfo();
    },
    methods: {
        readInfo() {
            let path = "";

            this.axios.get('/readClassInfo', {
                params: {
                    classId: this.classId
                }
            }).then( res => {
                path = res.data;
            }).catch( err => {
                console.log(err);
            }).finally( () => {
                console.log(path);
                document.getElementById("classInfoInput").innerHTML=`<object type="text/html" data="../../../../assets/html/classInfo/${this.classId}.html"></object>`;
            })


        }, 
        readPath() {
            
        },
    },
}
</script>

<style>

</style>