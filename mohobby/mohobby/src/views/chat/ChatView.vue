<template>
  <div id="app">
    <v-app>
      <v-container class="fill-height pa-0 ">
        <v-row class="no-gutters elevation-4">
          <v-col cols="12" sm="3" class="flex-grow-1 flex-shrink-0" style="border-right: 1px solid #0000001f;">
            <v-responsive class="overflow-y-auto fill-height" height="500">
              <v-list>
                <v-list-item-group>
                  <template v-for="(item, index) in roomList">
                    <v-list-item v-on:click="openRoom(item.roomNo)">
                      <v-list-item-avatar>
                        <v-list-item-avatar-img>
                          <v-img :src="require('@/assets/image/user/'+item.profileImg)" height="100px" width="50px"
                            border-radius:10px></v-img>
                        </v-list-item-avatar-img>
                      </v-list-item-avatar>
                      <v-list-item-content>
                        <v-list-item-title v-text="item.nickName" />
                        <v-list-item-subtitle v-text="item.content" />
                      </v-list-item-content>
                      <v-list-item-icon>
                        <v-icon :color="item.active ? 'deep-purple accent-4' : 'grey'">
                          chat_bubble
                        </v-icon>
                      </v-list-item-icon>
                    </v-list-item>
                    <v-divider class="my-0" />
                  </template>
                </v-list-item-group>
              </v-list>
            </v-responsive>
          </v-col>
          <v-col cols="auto" class="flex-grow-1 flex-shrink-0">
            <!-- <v-responsive v-if="activeChat" class="overflow-y-hidden fill-height" height=100%> -->
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
                          <v-chip :color="msg.me ? 'primary' : ''" dark style="dfrzheight:auto;white-space: normal;"
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
                <v-text-field  label="type_a_message" type="text" no-details outlined
                  append-outer-icon="send" @keyup.enter="send()" v-on:click="send()" hide-details />
              </v-card-text>
            </v-card>
            <!-- </v-responsive> -->
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
      subCheck: false,
      memberId: this.$store.state.id,
      activeChat: 1,

      messages: [
        
      ],
      messageForm: {
        content: "",
        me: true,
        created_at: ""
      },
      message: "",
      recvList: [],
      roomId: "",
      roomList: []


    }
  },
  created() {

    this.connect()
    this.getRoom()
  },
  methods: {
    send() {
      console.log(this.roomId)
      if (this.message !== '') {
        console.log("Send message:" + this.message);
        const msg = {
          roomId: this.$route.params.roomId,
          content: this.message
        };
        this.stompClient.send("/app/chat", JSON.stringify(msg), {});
      }
    },
    //채팅방 리스트출력
    getRoom() {
      var vm = this;
      this.axios.get('http://localhost:8088/java/ChatRoom/' + this.memberId, {
      })
        .then(function (res) {

          for (let i = 0; i < res.data.length; i++) {
            vm.roomList.push(res.data[i]);
          }
        })
        .catch(function (error) {
          console.log(error);
        })

    },
    // 채팅방에 채팅내역 출력
    openRoom(roomNo) {
      var vm = this;
      this.roomId = roomNo;
      this.axios.get('http://localhost:8088/java/ChatList/' + this.roomId, {
      })
        .then(function (res) {
          for (let i = 0; i < res.data.length; i++) {

            console.log(res.data[i].content)
            if (vm.memberId == res.data[i].memberId) {
              vm.messageForm.me = true;
            }
            else {
              vm.messageForm.me = false;
            }
            if (res.data[i].hour > 12) {
              vm.messageForm.created_at = res.data[i].hour-12+":"+res.data[i].minute +" pm"
            }
            else {
              vm.messageForm.created_at = res.data[i].hour+":"+res.data[i].minute+" am"
            }
            vm.messageForm.content = res.data[i].content;
            console.log(res.data[i].content)
            vm.messages.push(vm.messageForm)
          }
        })
        .catch(function (error) {
          console.log(error);
        })
      this.stompClient.subscribe("/topic/room/" + roomNo, res => {
        this.subCheck = true;
        console.log('구독으로 받은 메시지 입니다.', res.body);
      })
      console.log(vm.messages)
    },
    // 소켓연결
    connect() {
      const serverURL = "http://localhost:8088/java/sock"
      let socket = new SockJS(serverURL);
      this.stompClient = Stomp.over(socket);
      console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`)
      this.stompClient.connect(
        {},
        frame => {
          console.log('소켓 연결 성공', frame);
        },
        error => {
          console.log('소켓 연결 실패', error);
        }
      );
    },
  },
}
</script>