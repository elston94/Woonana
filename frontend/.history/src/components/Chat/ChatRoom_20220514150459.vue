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
import ChatSendForm from "./ChatSendForm.vue";
export default {
  data() {},
  components: {
    ChatList,
    ChatSendForm,
  },
};

ChatSendForm;
</script>

<style></style>
