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
                팔로우 내역이 없습니다!
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
 * 팔로워 누르면 title쪽에 follower가
 * 팔로잉 누르면 title쪽에 follwing이 뜨도록
 *
 * follower닉네임, 아이디 제대로 출력되도록
 *
 */
export default {
  name: "",
  components: {},
  props: {
    text: {
      type: String
      //required: true, //props 무조건 받아야함.
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
