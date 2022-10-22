<template>
    <main>
      <v-col lg="4" cols="12" style="margin-left: 100px;">
      <v-form class="mt-5">
        <v-text-field
          rounded
          outlined
          dense
          placeholder="Search Here"
          append-icon="mdi-magnify"
          
        />
        <h3>검색된 sns</h3>
      </v-form>
    </v-col>  
    </main>
</template>
<script>
export default {
    name: '',
    components: {},
    data() {
        return {
            example: '',
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
.v-input {
    margin-left: 30px;
}
</style>