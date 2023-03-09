<template>
  <div class="container">
    <div>
      <h2>{{ room.name }}</h2>
    </div>
    <chat-list></chat-list>
    <chat-send-form></chat-send-form>
  </div>
</template>

<script>
import ChatList from "@/components/Chat/ChatList";
import ChatSendForm from "@/components/Chat/ChatSendForm";
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
    ChatSendForm,
  },
  created() {
    this.roomId = localStorage.getItem("wschat.roomId");
    this.sender = localStorage.getItem("wschat.sender");
  },
  methods: {
    findRoom: function () {
      http.get("/chat/room/" + this.roomId).then((response) => {
        this.room = response.data;
      });
    },
  },
};
</script>

<style></style>
