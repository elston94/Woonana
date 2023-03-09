<!--
<template>
  <div class="container">
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
    <ul class="list-group" id="message-list">
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
import InfiniteLoading from "vue-infinite-loading";
var sock = new SockJS("http://localhost:8082/ws"); // endpoint로 sockJS 연결
var ws = Stomp.over(sock); // sockJS 위에서 돌아간다.

export default {
  components: {
    InfiniteLoading
  },
  data() {
    return {
      limit: 0,
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
      http.get("/chatapi/messages/" + this.roomId).then((response) => {
        this.messages = response.data;
      });
    },
    sendMessage: function () {
      // ChatController에 메세지를 보냄.
      ws.send(
        "/pub/chatapi/message",
        {},
        JSON.stringify({ roomId: this.roomId, sender: this.sender, message: this.message })
      );
      this.message = "";
    },
    recvMessage: function (recv) {
      this.messages.unshift({
        sender: recv.sender,
        message: recv.message,
      });
    },
    connect: function () {
      ws.connect(
        {},
        (frame) => {
          this.connected = true;
          console.log("소켓 연결 성공", frame);
          // 서버의 메시지 전송 endpoint를 구독
          ws.subscribe("/sub/chat/room/" + this.roomId, (message) => {
            var recv = JSON.parse(message.body);
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
#message-list {
  display: flex;
  flex-direction: column-reverse;
}
</style>
-->
<template>
  <div class="container">
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
    <ul class="list-group" id="message-list">
      <li class="list-group-item" v-bind:key="idx" v-for="(message, idx) in messages">
        <a>{{ message.sender }} - {{ message.message }}</a>
      </li>
    </ul>
    <infinite-loading @infinite="infiniteHandler" spinner="default"></infinite-loading>
    <div></div>
  </div>
</template>

<script>
import SockJS from "sockjs-client";
import Stomp from "stomp-websocket";
import http from "@/util/index";
import InfiniteLoading from "vue-infinite-loading";
var sock = new SockJS("http://localhost:8082/ws"); // endpoint로 sockJS 연결
var ws = Stomp.over(sock); // sockJS 위에서 돌아간다.

export default {
  components: {
    InfiniteLoading,
  },
  data() {
    return {
      pageNo: 0,
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
      // ChatController에 메세지를 보냄.
      ws.send(
        "/pub/chatapi/message",
        {},
        JSON.stringify({ roomId: this.roomId, sender: this.sender, message: this.message })
      );
      this.message = "";
    },
    recvMessage: function (recv) {
      this.messages.unshift({
        sender: recv.sender,
        message: recv.message,
      });
    },
    infiniteHandler($state) {
      http
        .get("/chatapi/messages/" + this.roomId, {
          params: {
            pageNo: this.pageNo,
          },
        })
        .then((response) => {
          setTimeout(() => {
            if (response.data.length) {
              this.messages = this.messages.concat(response.data);
              this.pageNo += 1;
              $state.loaded();
            } else {
              $state.complete();
            }
          }, 1000);
        })
        .catch((error) => {
          alert("error" + error);
        });
    },
    connect: function () {
      ws.connect(
        {},
        (frame) => {
          this.connected = true;
          console.log("소켓 연결 성공", frame);
          // 서버의 메시지 전송 endpoint를 구독
          ws.subscribe("/sub/chat/room/" + this.roomId, (message) => {
            var recv = JSON.parse(message.body);
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

<style></style>
