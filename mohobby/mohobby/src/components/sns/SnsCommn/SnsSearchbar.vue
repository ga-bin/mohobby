<template>
    <div>
    <v-row justify="space-around">
    <v-col
        cols="12"
        sm="30"
        md="30"
    >
        <v-sheet
        class="py-4 px-1"
        >
        <v-chip-group
            active-class="primary--text"
        >
        <v-chip
        v-for="catg in catg"
        :key="catg"
        >
        {{ catg }}
        </v-chip>
        </v-chip-group>
        </v-sheet>
    </v-col>
    </v-row>
    </div>
</template>
<script>
    export default {
    name: "search",
    data() {
      return {}
    }
}
</script>