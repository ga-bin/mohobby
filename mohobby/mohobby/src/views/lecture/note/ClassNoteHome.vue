<template>
  <v-container>
    <v-row class="text-center">
      <v-col>
        <h1>Home Page</h1>
      </v-col>
    </v-row>
    <v-row>
      <v-col class="text-center">
        <ul>
          <li>
            <v-btn
              :to="{
                name: 'MyNoteFolder',
                params: {
                  currId: 1,
                }
              }"
            >1</v-btn>
          </li>
          <li>
            <v-btn
              :to="{
                name: 'MyNoteFolder',
                params: {
                  currId: 2,
                },
              }"
            >2</v-btn>
          </li>
          <li>
            <v-btn
              :to="{
                name: 'MyNoteFolder',
                params: {
                  currId: 3,
                },
              }"
            >3</v-btn>
          </li>
          <li>
            <v-btn
              :to="{
                name: 'MyNoteFolder',
                params: {
                  currId: 4,
                },
              }"
            >4</v-btn>
          </li>
        </ul>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import Vue from "vue";
export default Vue.extend({
  name: "Home",
});
</script>

<style scoped>
li {
  list-style: none;
}
</style>