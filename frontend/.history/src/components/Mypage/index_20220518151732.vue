<template>
  <div>
    <div class="row">
      <div class="col-md-12" id="mypage-title">
        <span>마이 페이지</span>
      </div>
    </div>
    <mypage-main />
    <footer-home-one />
  </div>
</template>

<script>
import FooterHomeOne from "../HomeOne/FooterHomeOne.vue";
import MypageMain from "../components/Mypage/MypageMain.vue";
export default {
  components: {
    MypageMain,
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
  padding: 1rem;
  font-size: 16px;
  font-weight: 700;
  z-index: 10;
  margin-left: 4px;
  text-align: center;
}
</style>
