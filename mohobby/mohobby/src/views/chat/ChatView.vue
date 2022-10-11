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
                      <v-list-item>
                        <v-avatar>
                          <v-img :src="require('@/assets/image/user/'+item.profileImg)" height="100px" width="50px"
                            border-radius:10px></v-img>
                        </v-avatar>
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
                    </v-list-item>
                    <v-divider class="my-0" />
                  </template>
                </v-list-item-group>
              </v-list>
            </v-responsive>
          </v-col>
          <v-col cols="auto" class="flex-grow-1 flex-shrink-0">
            <v-card flat class="d-flex flex-column fill-height">
              <v-card-title>
                {{this.$store.state.id}}
              </v-card-title>
              <v-card-text class="flex-grow-1 overflow-y-auto">
                <template v-for="(msg, i) in messages">
                  <div :class="{ 'd-flex flex-row-reverse': msg.memberId }">
                    <v-menu offset-y>
                      <template v-slot:activator="{ on }">
                        <v-hover v-slot:default="{ hover }">
                          <v-chip :color="msg.memberId ? 'primary' : ''" dark style="height:auto;white-space: normal;"
                            class="pa-4 mb-2" v-on="on">
                            {{ msg.content }}
                            <sub class="ml-2" style="font-size: 0.5rem;">{{ msg.hour }}</sub>
                          </v-chip>
                        </v-hover>
                      </template>
                    </v-menu>
                  </div>
                </template>
              </v-card-text>
              <v-card-text class="flex-shrink-1">
                <v-text-field v-model="message" label="type_a_message" type="text" no-details outlined
                  append-outer-icon="send" @keyup.enter="send()" hide-details />
              </v-card-text>
            </v-card>
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
      subTitle: '',  //수정중
      memberId: this.$store.state.id,  //세션 로그인값
      messages: [], //메세지 내역
      message: "",
      roomId: "", //방번호
      roomList: [], //방목록
      stompClient: "", //소켓서버
      hour: "", //메세지시간
      subscribeRoot: "" //구독정보
    }
  },
  created() {
    this.connect()
    this.getRoom()
  },
  methods: {
    //날짜변환
    todate() {
      var today = new Date();
      var year = today.getFullYear();
      var month = ('0' + (today.getMonth() + 1)).slice(-2);
      var day = ('0' + today.getDate()).slice(-2);
      var hours = ('0' + today.getHours()).slice(-2);
      var minutes = ('0' + today.getMinutes()).slice(-2);
      var seconds = ('0' + today.getSeconds()).slice(-2);
      this.createAt = year + '/' + month + '/' + day + " " + hours + ':' + minutes + ':' + seconds;
    },
    //소켓서버에 채팅전송
    send() {
      this.todate()
      if (this.message !== '') {
        const msg = {
          roomNo: this.roomId,
          content: this.message,
          memberId: this.memberId,
          hour: this.createAt
        }
        const msg1={
          roomNo: this.roomId,
          content: this.message,
          memberId: this.memberId,
          msgTime: this.createAt
        }
        this.axios.post('http://localhost:8088/java/InsertMessage/',  {msg1 
        })
          .then(function (res) {
            console.log(res);
          })
          .catch(function (error) {
            console.log(error);
          })
        this.stompClient.send("/app/chat", JSON.stringify(msg), res => {
        });
      }
      this.message = ""
    },
    // 채팅방에 채팅내역 출력
    openRoom(roomNo) {
      var vm = this;
      //같은방 클릭시 재구독 방지
      vm.stompClient.unsubscribe(vm.subscribeRoot)
      this.roomId = roomNo;
      this.messages = [];
      //채팅내역 불러오기
      this.axios.get('http://localhost:8088/java/ChatList/' + this.roomId, {
      })
        .then(function (res) {
          for (let i = 0; i < res.data.length; i++) {
            if (vm.memberId == res.data[i].memberId) {
              res.data[i].memberId = true;
            }
            else {
              res.data[i].memberId = false;
            }
            if (res.data[i].hour > 12) {
              res.data[i].hour = res.data[i].hour - 12 + ":" + res.data[i].minute + " pm"
            }
            else {
              res.data[i].hour = res.data[i].hour + ":" + res.data[i].minute + " am"
            }
            vm.messages = res.data
          }
        })
        .catch(function (error) {
          console.log(error);
        })
      //클릭한 방 접속시 방번호로 구독
      vm.stompClient.subscribe("/topic/room/" + roomNo, function (res) {
        let rev = JSON.parse(res.body)
        vm.subscribeRoot = res.headers.subscription
        if (rev.memberId == vm.memberId) {
          rev.memberId = true;
        }
        else {
          rev.memberId = false;
        }
        if (rev.hour.substr(11, 2) > 12) {
          rev.hour = rev.hour.substr(11, 2) - 12 + ":" + rev.hour.substr(14, 2) + " pm"
        } else {
          rev.hour = rev.hour.substr(11, 2) + ":" + rev.hour.substr(14, 2) + " am"
        }
        vm.messages.push(rev)
      })
      //구독취소헤더값 가져오기
      this.stompClient.send("/app/chat1", vm.roomId, res => {
      });
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
