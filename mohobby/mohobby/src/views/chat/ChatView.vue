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
            <v-card flat class="d-flex flex-column fill-height" style="max-width: 100%">
              <v-card-title>
                {{this.$store.state.id}}
              </v-card-title>
              <v-card-text class="flex-grow-1 overflow-y-auto">
                <template v-for="(msg, i) in messages">
                  <div :class="{ 'd-flex flex-row-reverse': msg.memberId==memberId }">
                    <v-menu offset-y>
                      <template v-slot:activator="{ on }">
                        <v-chip :color="msg.memberId==memberId ? 'primary' : ''" dark
                          style="height:auto;white-space: normal;" class="pa-4 mb-2" v-on="on">
                          <!-- <v-avatar class="mr-2">
                            <v-img :src="require('@/assets/image/user/'+msg.profileImg)" height="100px" width="50px"
                              border-radius:10px></v-img>
                          </v-avatar> -->
                          {{ msg.content }}
                          <sub class="ml-2" style="font-size: 0.5rem;">{{ msg.hour }}</sub>
                        </v-chip>
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
      subscribeRoot: "", //구독정보
      targetId: [], //대화상대id
      profileImg:""
    }
  },
  created() {
    this.connect()
    this.getRoom()
    this.sortRoom()
    this.setProfileImg()
  },
  methods: {
    //프로필 이미지 설정
    setProfileImg(){
      this.axios.post({
        memberId:this.$store.state.id
      })
      .then(function(res){
        this.profileImg =res.data
      })
      .catch(function(err){
        console.log(err)
      })
      .finally(function(res){
        console.log(this.profileImg)
      })
    },
    //채팅내역 정렬
    sortRoom() {

    },
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
          hour: this.createAt,
 
        }
        this.axios.post('/InsertMessage/', {
          roomNo: this.roomId,
          msgTime: new Date(),
          content: this.message,
          memberId: this.memberId,
        })
          .then(function (res) {
            console.log(res);
          })
          .catch(function (error) {
            console.log(error);
          })
        this.stompClient.send("/app/send", JSON.stringify(msg), res => {
          console.log(res)
        });
        // this.stompClient.send("/app/chatNotice", JSON.stringify(msg), res => {
        //   console.log(res)
        // });
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
      vm.targetId=[];
      //상대방 id 불러오기
      this.axios.get('/getTargetId/' + this.roomId, {
      })
        .then(function (res) { 
       vm.targetId=res.data
        })
        .catch(function (err) { console.log(error) })
        .finally(function (res) { console.log(vm.targetId) })
      //채팅내역 불러오기
      this.axios.get('/getChatList/' + this.roomId, {
      })
        .then(function (res) {
          for (let i = 0; i < res.data.length; i++) {
            if (res.data[i].hour >= 12) {
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
        if (rev.hour.substr(11, 2) >= 12) {
          rev.hour = rev.hour.substr(11, 2) - 12 + ":" + rev.hour.substr(14, 2) + " pm"
        } else {
          rev.hour = rev.hour.substr(11, 2) + ":" + rev.hour.substr(14, 2) + " am"
        }
        vm.messages.push(rev)
      })
      //구독취소헤더값 가져오기
      this.stompClient.send("/app/getSubscribeInfo", vm.roomId, res => {
      });
    },
    //채팅방 리스트출력
    getRoom() {
      var vm = this;
      //1:1리스트
      this.axios.get('/ChatRoom/' + this.memberId, {
      })
        .then(function (res) {
          for (let i = 0; i < res.data.length; i++) {
            vm.roomList.push(res.data[i]);
          }
        })
        .catch(function (error) {
          console.log(error);
        })
        .finally(function (res) {
          //소모임 채팅방 리스트
          vm.axios.get('/ChatMoimRoom/' + vm.memberId, {
          })
            .then(function (res) {
              for (let i = 0; i < res.data.length; i++) {
                vm.roomList.push(res.data[i]);
              }
            })
            .catch(function (error) {
              console.log(error);
            })
            .finally(function (res) {
              //목록을 최신순으로 정렬
              vm.roomList.sort(function (a, b) {
                //return a.msgTime-b.msgTime
                if (a.msgTime > b.msgTime) return -1;
                if (a.msgTime < b.msgTime) return 1;
                return 0;
              })
            })
        })
    },
    connect() {
      let vm = this
      const serverURL = " http://192.168.0.85:8088//java/sock"
      let socket = new SockJS(serverURL);
      this.stompClient = Stomp.over(socket);
      this.stompClient.connect(
        {},
        frame => {
          vm.stompClient.subscribe("/" + this.$store.state.id, function (res) {
            let rev = JSON.parse(res.body)
            console.log(rev)
          })
          console.log('소켓 연결 성공', frame);
        },
        error => {
          console.log('소켓 연결 실패', error);
        }
      );
    },
  }
}
</script>
