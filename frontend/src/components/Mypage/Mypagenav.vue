<template>
  <div class="mypage">
    <b-navbar toggleable="lg" type="dark" variant="info">
      <b-navbar-brand href="#"
        ><router-link to="/mypage/main"
          >{{ myinfomation.userNickname }}의 마이페이지</router-link
        ></b-navbar-brand
      >

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#"><router-link to="/mypage/myposts">내 글</router-link></b-nav-item>
          <b-nav-item href="#"
            ><router-link to="/mypage/myjoinlog">참여 내역</router-link></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link to="/mypage/myworkoutlog">운동 기록</router-link></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link to="/mypage/myevallog">평가 페이지</router-link></b-nav-item
          >
          <b-nav-item href="#"><router-link to="/mypage/qna">고객 센터</router-link></b-nav-item>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
export default {
  computed: {
    myinfomation: function () {
      return this.$store.getters.GET_USER_INFO;
    },
  },
};
</script>

<style></style>
