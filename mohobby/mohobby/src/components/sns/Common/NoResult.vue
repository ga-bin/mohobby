<template>
    <div id="wrap">
        <h3>'{{temp}}'에 대한 검색 결과가 없습니다 !</h3>
        <h1>다른 검색어를 입력하시거나 철자와 띄어쓰기를 확인해 보세요.</h1>
        <h1>이것을 찾아보시겠어요?(유사검색 추천어 5개)</h1>
    </div>
</template>
<script>
export default {
    name: "NoResult",
    props: {
        temp : String,
    },
    data() {
        return {
        }
    },
    watch: {

    },
    created() {

    },
    methods: {

    }
}

</script>
<style scoped>
    #wrap {
        display: grid;
        place-items: center;
        min-height: 100vh;
    }
</style>