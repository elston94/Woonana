<template>
  <div>
    <div v-if="showloading">
      <loader-appie />
    </div>
    <div>
      <footer-home-one />
    </div>
  </div>
</template>
<script>
import FooterHomeOne from "./FooterHomeOne.vue";
import LoaderAppie from "../LoaderAppie.vue";
export default {
  name: "HomeOne",
  props: {
    rtl: {
      type: Boolean,
      default: false,
    },
  },
  components: {
    FooterHomeOne,
    LoaderAppie,
  },
  data() {
    return {
      showloading: true,
    };
  },
  created() {
    if (!this.rtl) {
      this.preLoading();
    } else {
      this.showloading = false;
    }
  },

  methods: {
    preLoading() {
      setTimeout(() => {
        this.showloading = false;
      }, 2000);
    },
  },
};
</script>

<style></style>
