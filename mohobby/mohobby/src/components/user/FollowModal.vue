<template>
    <main>
<template>
  <v-row justify="space-around">

    <v-col cols="auto">
      <v-dialog
        transition="dialog-top-transition"
        max-width="600"
      >
        <template v-slot:activator="{ on, attrs }">
          <span color="none"
            v-bind="attrs"
            v-on="on">{{ dataList.length / 2 + text }}</span>
        </template>
        <template v-slot:default="dialog">
          <v-card>
            <v-toolbar
              color="#2ac187"
              dark
            >Follow list</v-toolbar>
            <v-list three-line>
      <template v-for="(data, index) in dataList">
        <v-subheader
          v-if="data.header"
          :key="data.header"
          v-text="data.header"
        ></v-subheader>

        <v-divider
          v-else-if="data.divider"
          :key="data.followId"
          :inset="data.inset"
        ></v-divider>

        <v-list-item
          v-else
          :key="data.index"
        >
          <v-list-item-avatar>
            <v-img :src="require(`@/assets/image/user/${data.profileImg}`)"></v-img>
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title v-if='text="follower"' v-html="data.followerId"></v-list-item-title>
            <v-list-item-title v-else-if='text="following"' v-html="data.followingId"></v-list-item-title>
            <v-list-item-subtitle v-html="data.nickname"></v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
      </template>
    </v-list>
            <v-card-actions class="justify-end">
              <v-btn
                text
                @click="dialog.value = false"
              >Close</v-btn>
            </v-card-actions>
          </v-card>
        </template>
      </v-dialog>
    </v-col>
  </v-row>
</template>
</main>
</template>
<script>
export default {
    name: '',
    components: {},
    props: {
      text: {
        type: String,
        required: true //props 무조건 받아야함.
      },
      dataList: {
        type: Object,
      },
      divider : true,
      inset : true,
  },  
//     data() {
//         return {
//             items: [
//         {
//           avatar: 'https://cdn.vuetifyjs.com/images/lists/1.jpg',
//           title: 'Brunch this weekend?',
//         },
//         { divider: true, inset: true },
//         {
//           avatar: 'https://cdn.vuetifyjs.com/images/lists/2.jpg',
//           title: 'Summer BBQ <span class="grey--text text--lighten-1">4</span>',
//         },
//         { divider: true, inset: true },
//         {
//           avatar: 'https://cdn.vuetifyjs.com/images/lists/3.jpg',
//           title: 'Oui oui',
//           subtitle: '<span class="text--primary">Sandra Adams</span> &mdash; Do you have Paris recommendations? Have you ever been?',
//         },
//         { divider: true, inset: true },
//         {
//           avatar: 'https://cdn.vuetifyjs.com/images/lists/4.jpg',
//           title: 'Birthday gift',
//           subtitle: '<span class="text--primary">Trevor Hansen</span> &mdash; Have any ideas about what we should get Heidi for her birthday?',
//         },
//         { divider: true, inset: true },
//         {
//           avatar: 'https://cdn.vuetifyjs.com/images/lists/5.jpg',
//           title: 'Recipe to try',
//           subtitle: '<span class="text--primary">Britta Holt</span> &mdash; We should eat this: Grate, Squash, Corn, and tomatillo Tacos.',
//         },
//       ],
//     }
// },
    beforeCreate() {},
    created() {},
    beforeMount() {},
    mounted() {
      console.log("자식컴포넌트" + this.dataList);
    },
    beforeUpdate() {},
    updated() {},
    beforeUnmount() {},
    unmounted() {},
    methods: {

        
    }
}
</script>