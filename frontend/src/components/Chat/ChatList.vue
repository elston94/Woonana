<template>
  <div class="chatlist-container">
    <!-- <ul class="list-group" id="message-list">
      <li class="list-group-item" v-for="(message, idx) in messages" :key="idx" >
        <a>{{ message.sender }} - {{ message.message }}</a>
      </li>
    </ul> -->
    <chat-message v-for="(message, idx) in messages" :key="idx" :message="message"></chat-message>
  </div>
</template>

<script>
import ChatMessage from "@/components/Chat/ChatMessage";
export default {
  props: ["messages"],
  data() {
    return {
      sender: "",
    };
  },
  components: {
    ChatMessage,
  },
};
</script>

<style>
.chatlist-container {
  display: flex;
  flex-direction: column-reverse;
}
</style>
