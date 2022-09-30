<template>
  <v-app id="inspire">
    <Header></Header>
    <v-main>
      <v-container class="py-8 px-6" fluid>
        <router-view></router-view>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import Header from "./components/common/Header";

export default {
  components: { Header },
  data: () => ({
    cards: ["Today", "Yesterday"],
    drawer: true,
  }),
};
</script>
