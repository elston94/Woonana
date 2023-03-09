<template>
  <div>
    <div v-if="message.sender === this.sender" class="my-message">
      <p class="my-message-paragraph">{{ message.message }}</p>
    </div>
    <div v-else class="others-message">
      <div class="others-avartar">
        <img :src="avatar" alt="" class="others-img" />
      </div>
      <div>
        <p class="others-name">
          {{ message.sender }}
        </p>
        <div class="others-message-p">
          <p class="others-message-paragraph">
            {{ message.message }}
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ["message"],
  data() {
    return {
      sender: "",
      avatar: require("@/assets/avatar.svg"),
    };
  },
  created() {
    this.sender = localStorage.getItem("wschat.sender");
  },
};
</script>

<style>
.my-message {
  display: flex;
  justify-content: right;
  align-items: flex-end;
  margin: 2%;
  min-height: 40px;
  line-break: anywhere;
}

.my-message-paragraph {
  margin: 0 0 0 1rem;
  border-radius: 13px 13px 0px 13px;
  max-width: 180px;
  background-color: #bbefde;
  color: #4c4c4c;
  padding: 0.7rem;
  font-size: 14px;
}

.others-message {
  display: flex;
  justify-content: left;
  align-items: flex-start;
  margin: 2%;
  min-height: 40px;
  line-break: anywhere;
}

.others-name {
  font-size: 12px;
}

.others-message-paragraph {
  margin: 0.1rem 1rem 0 0;
  border-radius: 0px 13px 13px 13px;
  background-color: #676767;
  max-width: 180px;
  color: #ffffff;
  padding: 0.7rem;
  font-size: 14px;
}

.others-avartar {
  width: 40px;
  height: 70px;
  margin-right: 0.5rem;
}
</style>
