<template>
  <div>
    <p>카카오톡 로그인</p>
    <button @click="kakaoLogin">로그인하기</button>
  </div>
</template>

<script>
export default {
  methods: {
    kakaoLogin: function () {
      window.location.href = `https://kauth.kakao.com/oauth/authorize?client_id=76d85e088d5bb875d6e4493b8eda78fd&response_type=code&redirect_uri=${process.env.VUE_APP_LOCAL_URI}oauth/callback`;
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

<style></style>
