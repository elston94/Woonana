<template>
  <div class="mainpageCon">
    <div class="mainpage">
      <!-- <img src="@/assets/images/logo/Penguin.png" class="penguin" /> -->
      <div class="mainpage-box">
        <img src="@/assets/images/logo/Woonana-removebg.png" class="penguin" />
      </div>
      <div class="mainpage-box">
        <button type="button" class="btn btn-outline-warning" @click="kakaoLogin">
          카카오 로그인하기
        </button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    kakaoLogin: function () {
      window.location.href = `https://kauth.kakao.com/oauth/authorize?client_id=76d85e088d5bb875d6e4493b8eda78fd&response_type=code&redirect_uri=${process.env.VUE_APP_KAKAO_URI}oauth/callback`;
    },
    logout: function () {
      this.$store.dispatch("doLogout");
      this.$router.push("/");
    },
    mypage: function () {
      this.$router.push({ path: "mypage" });
    },
  },
  computed: {
    isLogin() {
      console.log("islogin : ", this.$store.getters.isLogin);
      return this.$store.getters.isLogin;
    },
  },
};
</script>

<style>
.mainpageCon {
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
}
.mainpage {
}
.penguin {
}
</style>
