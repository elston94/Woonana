<template>
  <div class="container" v-cloak>
    <div class="row">
      <div class="col-md-12" id="chat-room-list-title">
        <span>채팅</span>
      </div>
    </div>
    <!-- <div class="input-group">
      <div class="input-group-prepend">
        <label class="input-group-text">방제목</label>
      </div>
      <input type="text" class="form-control" v-model="room_name" @keyup.enter="createRoom" />
      <div class="input-group-append">
        <button class="btn btn-primary" type="button" @click="createRoom">채팅방 개설</button>
      </div>
    </div> -->
    <ul class="list-group-flush" id="chat-room-list">
      <li
        class="list-group-item list-group-item-action"
        id="chat-room"
        v-for="item in chatrooms"
        v-bind:key="item.roomId"
        v-on:click="enterRoom(item.roomId)"
      >
        <img :src="item.image" id="item-image" />
        &nbsp;&nbsp;&nbsp;{{ item.name }}
      </li>
    </ul>
    <footer-home-one />
  </div>
</template>

<script>
import FooterHomeOne from "../HomeOne/FooterHomeOne.vue";
import http from "@/util/indexChat.js";
import http2 from "@/util/index.js";
export default {
  data() {
    return {
      room_name: "",
      chatrooms: [],
      boards: [],
    };
  },
  components: {
    FooterHomeOne,
  },
  computed: {
    myinfomation: function () {
      return this.$store.getters.GET_USER_INFO;
    },
  },
  created() {
    this.findAllRoom();
    this.getUserInfo();
  },
  methods: {
    findAllRoom: function () {
      http2.get("/api/participate/board").then((response) => {
        console.log("boards", response.data);
        this.boards = response.data.boardId;
        console.log("boardIds", this.boards);
      });
      http.get("/chatapi/rooms", { params: { boards: this.boards } }).then((response) => {
        this.chatrooms = response.data;
      });
    },
    // createRoom: function () {
    //   if ("" === this.room_name) {
    //     alert("방 제목을 입력해 주십시요.");
    //     return;
    //   } else {
    //     // var params = new URLSearchParams();
    //     // params.append("name", this.room_name);
    //     const formData = {
    //       name: this.room_name,
    //     };
    //     http
    //       .post("/chatapi/room", JSON.stringify(formData))
    //       .then((response) => {
    //         console.log(response);
    //         this.room_name = "";
    //         this.findAllRoom();
    //       })
    //       .catch((response) => {
    //         console.log(response);
    //       });
    //   }
    // },
    getUserInfo: async function () {
      await this.$store.dispatch("getUserInfo");
    },
    enterRoom: function (roomId) {
      var sender = this.myinfomation.userNickname;
      localStorage.setItem("wschat.sender", sender);
      localStorage.setItem("wschat.roomId", roomId);
      this.$router.push("/chat/chatroom");
      this.$router.go();
    },
  },
};
</script>

<style>
[v-cloak] {
  display: none;
}
#chat-room-list-title {
  position: fixed;
  top: 0;
  background: #ffffff;
  box-shadow: 0px 3px 10px rgba(0, 0, 0, 0.05);
  padding: 1rem;
  font-size: 16px;
  font-weight: 700;
  z-index: 10;
  text-align: center;
}

#chat-room-list {
  margin-top: 20%;
}

#chat-room {
  margin: 10px 0px 10px 0px;
  padding-bottom: 12px;
}

#item-image {
  width: 40px;
  height: 40px;
  border-radius: 70%;
}
</style>
