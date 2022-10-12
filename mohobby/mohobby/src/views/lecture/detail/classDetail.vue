<template>
    <v-carousel hide-delimiters>
        <v-carousel-item
            v-for="(item,i) in items"
            :key="i"
            :src="item.src"
        >
        </v-carousel-item>
    </v-carousel>
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

        }
    },
    created() {
        console.log(this.classId);
    },
    methods() {}
}
</script>

<style>

</style>