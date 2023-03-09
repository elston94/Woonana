<template>
  <div class="container" v-cloak>
    <div class="row">
      <div class="col-md-12">
        <h3>채팅방 리스트</h3>
      </div>
    </div>
    <div class="input-group">
      <div class="input-group-prepend">
        <label class="input-group-text">방제목</label>
      </div>
      <input type="text" class="form-control" v-model="room_name" @keyup.enter="createRoom" />
      <div class="input-group-append">
        <button class="btn btn-primary" type="button" @click="createRoom">채팅방 개설</button>
      </div>
    </div>
    <ul class="list-group">
      <li
        class="list-group-item list-group-item-action"
        v-for="item in chatrooms"
        v-bind:key="item.roomId"
        v-on:click="enterRoom(item.roomId)"
      >
        {{ item.name }}
      </li>
    </ul>
  </div>
</template>

<script>
import http from "@/util/index";
export default {
  data() {
    return {
      room_name: "",
      chatrooms: [],
    };
  },
  created() {
    this.findAllRoom();
  },
  methods: {
    findAllRoom: function () {
      http.get("/chat/rooms").then((response) => {
        this.chatrooms = response.data;
      });
    },
    createRoom: function () {
      if ("" === this.room_name) {
        alert("방 제목을 입력해 주십시요.");
        return;
      } else {
        // var params = new URLSearchParams();
        // params.append("name", this.room_name);
        http
          .post("/chat/room", JSON.stringify({ name: this.room_name }))
          .then((response) => {
            console.log(response);
            alert(this.room_name + "방 개설에 성공하였습니다.");
            this.room_name = "";
            this.findAllRoom();
          })
          .catch((response) => {
            alert("채팅방 개설에 실패하였습니다.");
            console.log(response);
          });
      }
    },
    enterRoom: function (roomId) {
      var sender = prompt("대화명을 입력해 주세요.");
      localStorage.setItem("wschat.sender", sender);
      localStorage.setItem("wschat.roomId", roomId);
      this.$router.push("/chat/detail");
    },
  },
};
</script>

<style>
[v-cloak] {
  display: none;
}
</style>
