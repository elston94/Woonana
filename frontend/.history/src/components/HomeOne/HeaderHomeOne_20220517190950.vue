<template>
  <!--====== PART START ======-->
  <header class="appie-header-area appie-sticky">
    <div class="container">
      <div class="header-nav-box">
        <div class="row align-items-center">
          <div class="col-lg-2 col-md-4 col-sm-5 col-6 order-1 order-sm-1">
            <div class="appie-logo-box">
              <a href="/">
                <img src="src\assets\images\logo\Woonana-removebg.png" alt="Woonana" />
              </a>
            </div>
          </div>
          <div class="col-lg-6 col-md-1 col-sm-1 order-3 order-sm-2">
            <div class="appie-header-main-menu">
              <nav-items :menuItems="menuItems" :nasted="nasted" />
            </div>
          </div>
          <div class="col-lg-4 col-md-7 col-sm-6 col-6 order-2 order-sm-3">
            <div class="appie-btn-box" style="text-align: right">
              <template v-if="this.$store.state.enableRtl">
                <span
                  v-if="this.$store.state.rtl"
                  class="align-content-center mr-10"
                  @click="changeLang()"
                >
                  LTR
                </span>
                <span v-else class="align-content-center mr-10" @click="changeLang()"> RTL </span>
              </template>
              <template v-if="enableDark">
                <span @click="toggoleMode" v-if="dark" class="dark__btn__sun">
                  <svg
                    fill="none"
                    stroke="currentColor"
                    viewBox="0 0 24 24"
                    xmlns="http://www.w3.org/2000/svg"
                  >
                    <path
                      strokeLinecap="round"
                      strokeLinejoin="round"
                      strokeWidth="2"
                      d="M12 3v1m0 16v1m9-9h-1M4 12H3m15.364 6.364l-.707-.707M6.343 6.343l-.707-.707m12.728 0l-.707.707M6.343 17.657l-.707.707M16 12a4 4 0 11-8 0 4 4 0 018 0z"
                    ></path>
                  </svg>
                </span>
                <span @click="toggoleMode" v-else class="dark__btn__mon">
                  <svg
                    fill="none"
                    stroke="currentColor"
                    viewBox="0 0 24 24"
                    xmlns="http://www.w3.org/2000/svg"
                  >
                    <path
                      strokeLinecap="round"
                      strokeLinejoin="round"
                      strokeWidth="2"
                      d="M20.354 15.354A9 9 0 018.646 3.646 9.003 9.003 0 0012 21a9.003 9.003 0 008.354-5.646z"
                    ></path>
                  </svg>
                </span>
              </template>
              <div v-if="isLogin == true">
                <!-- <a class="login-btn" @click="Logout()"><i class="fal fa-user"></i> Logout</a> -->
                <b-button @click="mypage" variant="outline-primary">
                  <b-icon icon="person-fill"></b-icon>
                </b-button>
                <b-button @click="logout" v-show="isLogin == true"> Logout</b-button>
              </div>
              <div v-else>
                <b-button @click="kakaoLogin" variant="outline-primary" v-show="isLogin == false"
                  ><b-icon icon="person-fill"></b-icon>Login</b-button
                >
              </div>
              <div class="toggle-btn ml-30 canvas_open d-lg-none d-block">
                <i class="fa fa-bars" @click="showSidebar"></i>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </header>

  <!--====== PART ENDS ======-->
</template>

<script>
import NavItems from "../NavItems.vue";
// import { getters } from "vuex";
export default {
  props: {
    menuItems: {
      type: Array,
      required: true,
    },
    nasted: {
      type: Boolean,
      default: true,
    },
    dark: {
      type: Boolean,
    },
    enableDark: {
      type: Boolean,
    },
  },
  components: { NavItems },
  mounted() {
    document.addEventListener("scroll", this.stickMenu);
  },
  methods: {
    changeLang() {
      this.$store.dispatch("rtlHandler");
    },
    toggoleMode(e) {
      this.$emit("changMode", e);
    },
    showSidebar(e) {
      this.$emit("toggleSidebar", e);
    },
    stickMenu() {
      const result = document.querySelector(".appie-sticky");
      if (document.body.scrollTop > 100 || document.documentElement.scrollTop > 100) {
        result.classList.add("sticky");
      } else {
        result.classList.remove("sticky");
      }
    },
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
