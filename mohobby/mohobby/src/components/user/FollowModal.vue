<template>
  <main>
    <template>
      <v-row justify="space-around">
        <v-col cols="auto">
          <v-dialog transition="dialog-top-transition" max-width="600">
            <template v-slot:activator="{ on, attrs }">
              <span color="none" v-bind="attrs" v-on="on">{{
                dataList.length / 2 + text
              }}</span>
            </template>
            <template v-slot:default="dialog">
              <v-card>
                <v-toolbar color="#2255b1" dark>{{ text }} list</v-toolbar>


                <div v-if = "dataList.length !== 0">

                  <v-list three-line>
                    <template v-for="(data, index) in dataList">
                      <v-subheader
                        v-if="data.header"
                        :key="data.header"
                        v-text="data.header"
                      />
                      <v-divider
                        v-else-if="data.divider"
                        :key="data.followId"
                        :inset="data.inset"
                      />
                      <v-list-item v-else :key="index">
                        <v-list-item-avatar>
                          <v-img
                            :src="
                            require(`@/assets/image/user/${data.profileImg}`)
                          "
                        ></v-img>
                      </v-list-item-avatar>
                      <v-list-item-content>
                        <v-list-item-title
                          v-if="text == 'follower'"
                          v-html="data.followingId"
                        ></v-list-item-title>
                        <v-list-item-title
                          v-else-if="text == 'following'"
                          v-html="data.followerId"
                        ></v-list-item-title>
                        <v-list-item-subtitle
                          v-html="data.nickname"
                        ></v-list-item-subtitle>
                      </v-list-item-content>
                    </v-list-item>
                  </template>
                </v-list>

              </div>
              <div v-else style="text-align:center; padding: 50px 20px;">
                <v-icon large>mdi-account-multiple</v-icon><br>
                ????????? ????????? ????????????!
              </div>

              
                <v-card-actions class="justify-end">
                  <v-btn text @click="dialog.value = false"> Close </v-btn>
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
/**
 * ????????? ????????? title?????? follower???
 * ????????? ????????? title?????? follwing??? ?????????
 *
 * follower?????????, ????????? ????????? ???????????????
 *
 */
export default {
  name: "",
  components: {},
  props: {
    text: {
      type: String
      //required: true, //props ????????? ????????????.
    },
    dataList : {},
    // divider: true,
    // inset: true,
  },
  beforeCreate() {},
  created() {
  },
  beforeMount() {},
  mounted() {
  },
  beforeUpdate() {},
  updated() {},
  beforeUnmount() {},
  unmounted() {},
  methods: {},
};
</script>
