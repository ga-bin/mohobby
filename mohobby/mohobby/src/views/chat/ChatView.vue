<template>
  <div id="app">
    <v-app>
      <v-container class="fill-height pa-0 ">
        <v-row class="no-gutters elevation-4">
          <v-col cols="12" sm="3" class="flex-grow-1 flex-shrink-0" style="border-right: 1px solid #0000001f;">
            <v-responsive class="overflow-y-auto fill-height" height="500">
              <v-list subheader>
                <v-list-item-group v-model="activeChat">
                  <template v-for="(item, index) in parents">
                    <v-list-item :key="`parents${index}`" :value="item.id">
                      <v-list-item-avatar color="grey lighten-1 white--text">
                        <v-icon>
                          chat_bubble
                        </v-icon>
                      </v-list-item-avatar>
                      <v-list-item-content>
                        <v-list-item-title v-text="item.title" />
                        <v-list-item-subtitle v-text="'hi'" />
                      </v-list-item-content>
                      <v-list-item-icon>
                        <v-icon :color="item.active ? 'deep-purple accent-4' : 'grey'">
                          chat_bubble
                        </v-icon>
                      </v-list-item-icon>
                    </v-list-item>
                    <v-divider :key="`chatDivider${index}`" class="my-0" />
                  </template>
                </v-list-item-group>
              </v-list>
            </v-responsive>
          </v-col>
          <v-col cols="auto" class="flex-grow-1 flex-shrink-0">
            <v-responsive v-if="activeChat" class="overflow-y-hidden fill-height" height=100%>
              <v-card flat class="d-flex flex-column fill-height">
                <v-card-title>
                  john doe
                </v-card-title>
                <v-card-text class="flex-grow-1 overflow-y-auto">
                  <template v-for="(msg, i) in messages">
                    <div :class="{ 'd-flex flex-row-reverse': msg.me }">
                      <v-menu offset-y>
                        <template v-slot:activator="{ on }">
                          <v-hover v-slot:default="{ hover }">
                            <v-chip :color="msg.me ? 'primary' : ''" dark style="height:auto;white-space: normal;"
                              class="pa-4 mb-2" v-on="on">
                              {{ msg.content }}
                              <sub class="ml-2" style="font-size: 0.5rem;">{{ msg.created_at }}</sub>
                              <v-icon v-if="hover" small>
                                expand_more
                              </v-icon>
                            </v-chip>
                          </v-hover>
                        </template>
                      </v-menu>
                    </div>
                  </template>
                </v-card-text>
                <v-card-text class="flex-shrink-1">
                  <v-text-field v-model="messageForm.content" label="type_a_message" type="text" no-details outlined
                    append-outer-icon="send" @keyup.enter="messages.push(messageForm)"
                    @click:append-outer="messages.push(messageForm)" hide-details />
                </v-card-text>
              </v-card>
            </v-responsive>
          </v-col>
        </v-row>
      </v-container>
    </v-app>
  </div>

</template>

<script>
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'


export default {
  name: 'App',
  data() {
    return {
      activeChat: 1,
      parents: [
        {
          id: 1,
          title: "john doe",
          active: true
        }
       ],
      messages: [
        {
          content: "lorem ipsum",
          me: true,
          created_at: "11:11am"
        },
        {
          content: "lsdwqm ipsum",
          me: false,
          created_at: "11:11am"
        }],
      messageForm: {
        content: "",
        me: true,
        created_at: "11:11am"
      },
      message: "",
      recvList: [],
      roomId: this.$route.params.roomId,
    }
  },

  created() {
   
    this.connect()

  },
  methods: {
    send() {
      if (this.message !== '') {
        console.log("Send message:" + this.message);
        const msg = {
          roomId: this.$route.params.roomId,
          content: this.message
        };
        this.stompClient.send("/app/chat", JSON.stringify(msg), {});
      }
    },
    connect() {
      const serverURL = "http://localhost:8090/java/sock"
      let socket = new SockJS(serverURL);
      this.stompClient = Stomp.over(socket);
      console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`)
      this.stompClient.connect(
        {},
        frame => {
          console.log('소켓 연결 성공', frame);
          this.stompClient.subscribe("/topic/room/" + this.roomId, res => {
            console.log(res);
            console.log('구독으로 받은 메시지 입니다.', res.body);
          });
        },
        error => {
          console.log('소켓 연결 실패', error);
        }
      );

    }
  },

}

</script>
