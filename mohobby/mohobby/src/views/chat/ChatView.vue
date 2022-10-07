<template>
  <div>
    <h2>{{this.$route.params.roomId}}</h2>
    <h2>roomId: {{ roomId }}</h2>
    <input type="text" v-model="message" v-on:keypress.enter="sendMessage">
    <button type="button">보내기</button>
    <ul>
      <li class="list-group-item" v-for="message in messages">
        {{message.message}}
      </li>
    </ul>
  </div>
</template>

<script>
import SockJS from 'sockjs-client'
import Stomp from 'webstomp-client'

export default {
  name: "",
  title: "Chat",
  components: {},
  data() {
    return {
      roomId: this.$route.params.roomId,
      room: {},
      message: '',
      messages: []
    }
  },
  created() {
    this.connect()
  },
  methods: {
    connect() {

      let socket = new SockJS("http://localhost:8090/java/sock");
      this.stompClient = Stomp.over(socket);
     
      this.stompClient.connect(
        {},
        frame => {
          console.log('성공', frame);
          this.stompClient.subscribe("/send", res => {
            this.recvList.push(JSON.parse(res.body))
          });
        },
        error => {
          console.log('패', error);
        }
      );        
    }
  

  }



}



</script>