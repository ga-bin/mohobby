<template>
  <v-app-bar app color="white" elevate-on-scroll elevation="4">
    <v-toolbar-title
      @click="$router.push('/').catch(() => {})"
      style="cursor: pointer"
      >Mohobby</v-toolbar-title
    >
    <v-spacer />
    <v-btn text class="ml-2" to="/snsmain">sns</v-btn>
    <v-btn text class="ml-2" to="/class/list/all">강의</v-btn>
    <v-btn text class="ml-2" to="/moimmain">소모임</v-btn>
    <v-btn text class="ml-2" to="/challengemain">챌린지</v-btn>
    <v-spacer />
    <v-col lg="4" cols="12">
      <v-form class="mt-5">
        <v-text-field
          rounded
          outlined
          dense
          placeholder="Search Here"
          append-icon="mdi-magnify"
        />
      </v-form>
    </v-col>
    <v-spacer />

    <v-menu offset-y v-if="this.$store.state.id">
      <template v-slot:activator="{ on, attrs }">
        <span id="bellspan" v-bind="attrs" v-on="on" style="cursor: pointer">
          <v-badge offset-x="10" offset-y="10" color="red" content="5">
            <v-icon>mdi-bell</v-icon>
          </v-badge>
        </span>
      </template>
      <v-list three-line width="400">
        <template v-for="(item, index) in items">
          <v-subheader
            v-if="item.header"
            :key="item.header"
            v-text="item.header"
          ></v-subheader>

          <v-divider
            v-else-if="item.divider"
            :key="index"
            :inset="item.inset"
          ></v-divider>

          <v-list-item v-else :key="item.title">
            <v-list-item-avatar>
              <v-img :src="item.avatar"></v-img>
            </v-list-item-avatar>

            <v-list-item-content>
              <v-list-item-title v-html="item.title"></v-list-item-title>
              <v-list-item-subtitle
                v-html="item.subtitle"
              ></v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
        </template>
      </v-list>
    </v-menu>
    <v-btn icon>
      <v-icon v-if="!this.$store.state.id" @click="$router.push('/login')"
        >mdi-arrow-left-box</v-icon
      >
    </v-btn>

    <v-icon v-if="!this.$store.state.id" @click="$router.push('/register')"
      >mdi-account-multiple-plus</v-icon
    >

    <v-btn v-if="this.$store.state.id" icon>
      <v-icon>mdi-chat-processing-outline</v-icon>
    </v-btn>

    <v-btn v-if="this.$store.state.id" icon>
      <v-icon @click="$router.push('/mypageprofile')">mdi-account</v-icon>
    </v-btn>

    <v-btn v-if="this.$store.state.id" @click="logout()" icon>
      <v-icon>mdi-arrow-right-box</v-icon>
    </v-btn>
  </v-app-bar>
</template>
<script>
export default {
  components: {},
  data() {
    return {
      items: [
        { header: "Today" },
        {
          avatar: "https://cdn.vuetifyjs.com/images/lists/1.jpg",
          title: "Brunch this weekend?",
          subtitle: `<span class="text--primary">Ali Connors</span> &mdash; I'll be in your neighborhood doing errands this weekend. Do you want to hang out?`,
        },
        { divider: true, inset: true },
        {
          avatar: "https://cdn.vuetifyjs.com/images/lists/2.jpg",
          title: 'Summer BBQ <span class="grey--text text--lighten-1">4</span>',
          subtitle: `<span class="text--primary">to Alex, Scott, Jennifer</span> &mdash; Wish I could come, but I'm out of town this weekend.`,
        },
        { divider: true, inset: true },
        {
          avatar: "https://cdn.vuetifyjs.com/images/lists/3.jpg",
          title: "Oui oui",
          subtitle:
            '<span class="text--primary">Sandra Adams</span> &mdash; Do you have Paris recommendations? Have you ever been?',
        },
        { divider: true, inset: true },
        {
          avatar: "https://cdn.vuetifyjs.com/images/lists/4.jpg",
          title: "Birthday gift",
          subtitle:
            '<span class="text--primary">Trevor Hansen</span> &mdash; Have any ideas about what we should get Heidi for her birthday?',
        },
        { divider: true, inset: true },
        {
          avatar: "https://cdn.vuetifyjs.com/images/lists/5.jpg",
          title: "Recipe to try",
          subtitle:
            '<span class="text--primary">Britta Holt</span> &mdash; We should eat this: Grate, Squash, Corn, and tomatillo Tacos.',
        },
      ],
    };
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
    logout() {
      this.$store.commit("setIsLoginFalse");
      this.$store.commit("logout");
      this.$store.commit("setUserData", null);
      this.$router.push("/");
    },
  },
};
</script>
<style scoped>
#bellspan {
  width: 3px;
}
</style>
