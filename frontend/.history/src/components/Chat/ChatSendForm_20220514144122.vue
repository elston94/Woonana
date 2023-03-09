<template>
  <div class="container">
    <div class="input-group" id="send-message-group">
      <input type="text" class="form-control" v-model="message" @keyup.enter="sendMessage" />
      <div class="input-group-append">
        <button class="btn btn-light" type="button" @click="sendMessage()">전송</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style></style>
