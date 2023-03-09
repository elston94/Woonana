<template>
  <div class="container">
    <button type="button" v-on:click="kakaoLogin">카카오로그인하기</button>
    <button type="button" v-on:click="kakaoLogin2">code 카카오서버에 주기</button>
  </div>
</template>

<script src="//developers.kakao.com/sdk/js/kakao.js"></script>
<script>
import axios from "axios";
const URLSearch = new URLSearchParams(location.search);
let code;
export default {
  name: "MemberLogin",
  methods: {
    kakaoLogin: function () {
      code = URLSearch.get("code"); //쿼리스트링의 값을 받아온다.
      alert(code);
      window.location.href =
        "https://kauth.kakao.com/oauth/authorize?client_id=76d85e088d5bb875d6e4493b8eda78fd&response_type=code&redirect_uri=http://localhost:8080/oauth/callback";
    },
    // kakaoLogin() {
    //   window.Kakao.Auth.login({
    //     scope: "profile_nickname,account_email	",
    //     success: this.getProfile,
    //   });
    // },
    // getProfile(authObj) {
    //   console.log(authObj);
    //   window.Kakao.API.request({
    //     url: "/v2/user/me",
    //     success: (res) => {
    //       const kakao_account = res.kakao_account;
    //       console.log(kakao_account);

    //       alert("로그인성공");
    //     },
    //     error: (res) => {
    //       console.log(err);
    //     },
    //   });
    // },

    kakaoLogin2: function () {
      axios
        .post("http://localhost:8081/app/users/kakao/", {
          code: code,
        })
        .then((res) => {
          console.log(res);
        });
    },

    // kakaoLogin2: function () {
    //   axios
    //     .get(
    //       "https://kauth.kakao.com/oauth/authorize?client_id=76d85e088d5bb875d6e4493b8eda78fd&response_type=code&redirect_uri=http://localhost:8080/"
    //     )
    //     .then(function (res) {
    //       console.log(res);
    //     })
    //     .catch((err) => {
    //       console.log(err);
    //     });
    // },
  },
};
</script>

<style></style>
