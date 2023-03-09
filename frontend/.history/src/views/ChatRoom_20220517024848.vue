<template>
  <div class="chat">
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

    <div id="send-message-box">
      <div class="input-group" id="send-message-group">
        <input
          type="text"
          placeholder="메세지를 입력하세요."
          class="form-control"
          v-model="message"
          @keyup.enter="sendMessage"
          id="input-message"
        />
        <div class="input-group-append">
          <div @click="sendMessage()" class="form__submit">
            <svg
              width="30"
              height="30"
              viewBox="0 0 68 68"
              fill="#CCCCCC"
              xmlns="http://www.w3.org/2000/svg"
            >
              <g clip-path="url(#clip0_26_10)">
                <path
                  fill-rule="evenodd"
                  clip-rule="evenodd"
                  d="M48.0833 19.799C48.619 20.3347 48.806 21.127 48.5665 21.8457L35.8385 60.0294C35.5946 60.7614 34.9513 61.2877 34.1855 61.382C33.4198 61.4763 32.6681 61.1217 32.2539 60.4707L22.593 45.2893L7.41158 35.6285C6.76065 35.2142 6.40604 34.4625 6.50031 33.6968C6.59458 32.931 7.12092 32.2878 7.85287 32.0438L46.0366 19.3159C46.7553 19.0763 47.5476 19.2633 48.0833 19.799ZM26.5903 44.1204L33.3726 54.7782L42.0926 28.6181L26.5903 44.1204ZM39.2642 25.7897L23.7619 41.292L13.1041 34.5097L39.2642 25.7897Z"
                  fill=""
                />
              </g>
              <defs>
                <clipPath id="clip0_26_10">
                  <rect
                    width="48"
                    height="48"
                    fill="white"
                    transform="translate(33.9412) rotate(45)"
                  />
                </clipPath>
              </defs>
            </svg>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import InfiniteLoading from "vue-infinite-loading";
import ChatList from "@/components/Chat/ChatList";
import http from "@/util/indexChat.js";
import SockJS from "sockjs-client";
import Stomp from "stomp-websocket";

var sock = new SockJS(`${process.env.VUE_APP_CHAT_WS_URI}`); // endpoint로 sockJS 연결
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
      http.get("/chatapi/room/" + this.roomId).then((response) => {
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
          ws.subscribe("/sub/chatapi/room/" + this.roomId, (message) => {
            var recv = JSON.parse(message.body);
            console.log(recv);
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
.chat {
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

#room-name {
  position: fixed;
  top: 0;
  background: #ffffff;
  box-shadow: 0px 3px 10px rgba(0, 0, 0, 0.05);
  padding: 1.8rem;
  font-size: 16px;
  font-weight: 700;
  width: 100%;
  z-index: 10;
}

#chat-list {
  padding-top: 13%;
  padding-bottom: 12%;
  z-index: -1;
}

#send-message-box {
  position: fixed;
  bottom: 0;
  height: 7%;
  width: 100%;
  background: #ffffff;
  box-shadow: 3px 0px 30px rgba(0, 0, 0, 0.05);
}

#send-message-group {
  position: fixed;
  bottom: 0.6%;
}

#input-message {
  border: none;
}

#input-message::placeholder {
  color: rgb(193, 193, 193);
}

.form__submit {
  display: flex;
  align-items: center;
  cursor: pointer;
}

svg {
  transition: 0.3s;
}

svg:hover {
  fill: #999999;
}
</style>
