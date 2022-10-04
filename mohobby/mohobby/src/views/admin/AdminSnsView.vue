<template>
    <main>
        <AdminSidebar></AdminSidebar>
        <h3>TOP10해시태그</h3>
         <v-col class="py-2" v-for="(hashtag, index) in hashtags" :key="index">
            <v-btn>{{ hashtag }}</v-btn>
        </v-col>
        
    </main>
</template>s
<script>
import AdminSidebar from "../../components/admin/AdminSidebar.vue";
export default {
    name: '',
    components: { AdminSidebar },
    data() {
        return {
            hashtags: ['#공예', '#운동', '#자전거', '#대구', '#서울', '#부산', '#정모', '#동호회', '#뜨개질', '#기타']
    }
},
    beforeCreate() {},
    created() {},
    beforeMount() {},
    mounted() {},
    beforeUpdate() {},
    updated() {},
    beforeUnmount() {},
    unmounted() {},
    methods: {}
}
</script>
<style scoped>
.py-2 {
    display: inline-block;
    width: 100px;
    margin: 7px;
}
</style>