<template>
  <div class="container">
    <div>
      <h2>{{ room.name }}</h2>
    </div>
  </div>
</template>

<script>
import ChatList from "@/components/Chat/ChatList";
import ChatSendForm from "@/components/Chat/ChatSendForm";
export default {
  data() {},
  components: {
    ChatList,
    ChatSendForm,
  },
};
</script>

<style></style>
