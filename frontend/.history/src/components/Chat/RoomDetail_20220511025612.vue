<template>
  <div class="container" v-cloak>
    <div>
      <h2>{{ room.name }}</h2>
    </div>
    <div class="input-group">
      <div class="input-group-prepend">
        <label class="input-group-text">내용</label>
      </div>
      <input type="text" class="form-control" v-model="message" @keyup.enter="sendMessage" />
      <div class="input-group-append">
        <button class="btn btn-primary" type="button" @click="sendMessage()">보내기</button>
      </div>
    </div>
    <ul class="list-group">
      <li class="list-group-item" v-bind:key="idx" v-for="(message, idx) in messages">
        <a>{{ message.sender }} - {{ message.message }}</a>
      </li>
    </ul>
    <div></div>
  </div>
</template>

<script>
import SockJS from "sockjs-client";
import Stomp from "stomp-websocket";
import http from "@/util/index";
var sock = new SockJS("http://localhost:8081/ws"); // endpoint로 sockJS 연결
var ws = Stomp.over(sock); // sockJS 위에서 돌아간다.

export default {
  data() {
    return {
      roomId: "",
      room: {},
      sender: "",
      message: "",
      messages: [],
    };
  },
  created() {
    this.roomId = localStorage.getItem("wschat.roomId");
    this.sender = localStorage.getItem("wschat.sender");
    this.findRoom();
    this.connect();
  },
  methods: {
    findRoom: function () {
      http.get("/chat/room/" + this.roomId).then((response) => {
        this.room = response.data;
      });
      http.get("/chat/messages/" + this.roomId).then((response) => {
        this.messages = response.data;
      });
    },
    sendMessage: function () {
      // ChatController에 메세지를 보냄.
      ws.send(
        "/pub/chat/message",
        {},
        JSON.stringify({ roomId: this.roomId, sender: this.sender, message: this.message })
      );
      this.message = "";
    },
    recvMessage: function (recv) {
      this.messages.unshift({
        roomId: recv.roomId,
        sender: recv.sender,
        message: recv.message,
        createdAt: "",
      });
    },
    connect() {
      console.log("소켓 연결 시도");
      ws.connect(
        {},
        (frame) => {
          this.connected = true;
          console.log("소켓 연결 성공", frame);
          // 서버의 메시지 전송 endpoint를 구독
          ws.subscribe("/sub/chat/room/" + this.roomId, function (message) {
            var recv = JSON.parse(message.body);
            console.log(recv);
            recv.createdAt = "1";
            this.recvMessage(recv);
          });
        },
        (error) => {
          // 소켓 연결 실패
          console.log("소켓 연결 실패", error);
          alert("error" + error);
        }
      );
    },
  },
};
</script>

<style>
[v-cloak] {
  display: none;
}
</style>
