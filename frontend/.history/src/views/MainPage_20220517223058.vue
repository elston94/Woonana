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
      // const URLSearch = new URLSearchParams(location.search);
      // const code = URLSearch.get("code"); //쿼리스트링의 값을 받아온다.
      // alert(code);
      window.location.href = `https://kauth.kakao.com/oauth/authorize?client_id=76d85e088d5bb875d6e4493b8eda78fd&response_type=code&redirect_uri=${process.env.VUE_APP_LOCAL_URI}oauth/callback`;
      // window.location.href = `https://kauth.kakao.com/oauth/authorize?client_id=76d85e088d5bb875d6e4493b8eda78fd&response_type=code&redirect_uri=http://localhost:8080/oauth/callback`;
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
