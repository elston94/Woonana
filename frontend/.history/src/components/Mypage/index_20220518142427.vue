<template>
  <div>
    <div class="row">
      <div class="col-md-12" id="mypage-title">
        <span>마이 페이지</span>
      </div>
    </div>
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

<style>
#mypage-title {
  position: fixed;
  top: 0;
  background: #ffffff;
  box-shadow: 0px 3px 10px rgba(0, 0, 0, 0.05);
  padding: 1.8rem;
  font-size: 16px;
  font-weight: 700;
  z-index: 10;
}
</style>
