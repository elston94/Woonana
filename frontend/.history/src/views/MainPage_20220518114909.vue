<template>
  <div>
    <div class="mainpage">
      <img src="@/assets/images/logo/Penguin.png" class="penguin" />
      <button type="button" class="btn btn-warning" @click="kakaoLogin">카카오 로그인하기</button>
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
.mainpage {
}
.penguin {
  display: flex;
  justify-content: center;
}
</style>
