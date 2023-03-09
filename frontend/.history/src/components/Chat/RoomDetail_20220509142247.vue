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
        <button class="btn btn-primary" type="button" @click="sendMessage">보내기</button>
      </div>
    </div>
    <ul class="list-group">
      <li class="list-group-item" v-bind:key="message" v-for="message in messages">
        <a>{{ message.sender }} - {{ message.message }}</a>
      </li>
    </ul>
    <div></div>
  </div>
</template>

<script>
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import http from "@/util/index";
var sock = new SockJS("/ws");
var ws = Stomp.over(sock);

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
    },
    sendMessage: function () {
      ws.send(
        "/pub/chat/message",
        {},
        JSON.stringify({
          type: "TALK",
          roomId: this.roomId,
          sender: this.sender,
          message: this.message,
        })
      );
      this.message = "";
    },
    recvMessage: function (recv) {
      this.messages.unshift({
        type: recv.type,
        sender: recv.type == "ENTER" ? "[알림]" : recv.sender,
        message: recv.message,
      });
    },
    connect() {
      ws.connect(
        {},
        (frame) => {
          // 소켓 연결 성공
          this.connected = true;
          console.log("소켓 연결 성공", frame);
          // 서버의 메시지 전송 endpoint를 구독합니다.
          // 이런형태를 pub sub 구조라고 합니다.
          ws.subscribe("/sub/chat/room/" + this.roomId, function (message) {
            var recv = JSON.parse(message.body);
            this.recvMessage(recv);
          });
          ws.send(
            "/pub/chat/message",
            {},
            JSON.stringify({ type: "ENTER", roomId: this.roomId, sender: this.sender })
          );
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
