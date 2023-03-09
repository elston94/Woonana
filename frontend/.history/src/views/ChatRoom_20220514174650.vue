<template>
  <div class="container">
    <div id="room-name">
      <span>{{ room.name }}</span>
    </div>
    <infinite-loading
      @infinite="infiniteHandler"
      spinner="waveDots"
      direction="top"
    ></infinite-loading>
    <div id="chat-list">
      <chat-list :messages="messages"></chat-list>
    </div>
    <div class="input-group" id="send-message-group">
      <input type="text" class="form-control" v-model="message" @keyup.enter="sendMessage" />
      <div class="input-group-append">
        <button class="btn btn-light" type="button" @click="sendMessage()">전송</button>
      </div>
    </div>
  </div>
</template>

<script>
import InfiniteLoading from "vue-infinite-loading";
import ChatList from "@/components/Chat/ChatList";
import http from "@/util/index";
import SockJS from "sockjs-client";
import Stomp from "stomp-websocket";

var sock = new SockJS("http://localhost:8082/ws"); // endpoint로 sockJS 연결
var ws = Stomp.over(sock); // sockJS 위에서 돌아간다.
export default {
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
  components: {
    ChatList,
    InfiniteLoading,
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
  },
};
</script>

<style>
#room-name {
  background: #ffffff;
  box-shadow: 0px 3px 10px rgba(0, 0, 0, 0.05);
  border-radius: 24px 24px 0px 0px;
  padding: 1.8rem;
  font-size: 16px;
  font-weight: 700;
}
#send-message-group {
  position: fixed;
  bottom: 0;
}
#chat-list {
  padding-top: 100px;
  padding-bottom: 45px;
}
</style>
