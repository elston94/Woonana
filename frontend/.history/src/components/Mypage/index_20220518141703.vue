<template>
  <div>
    <router-view />

    <footer-home-one />
  </div>
</template>

<script>
import FooterHomeOne from "../HomeOne/FooterHomeOne.vue";

export default {
  components: {
    FooterHomeOne,
  },
  data() {
    return {};
  },
};
</script>

<style></style>
