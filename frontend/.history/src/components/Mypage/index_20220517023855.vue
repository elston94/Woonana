<template>
  <div>
    <!--====== OFFCANVAS MENU PART START ======-->

    <!--====== OFFCANVAS MENU PART ENDS ======-->
    <!--====== APPIE HEADER PART START ======-->

    <header-home-one :menuItems="navs" />

    <!--====== APPIE HEADER PART ENDS ======-->
    <!--====== APPIE PAGE TITLE PART START ======-->

    <!-- reference by news -->
    <title-area />
    <!-- <star-rating
      v-bind:increment="0.5"
      v-bind:max-rating="5"
      inactive-color="#000"
      active-color="#f00"
      v-bind:star-size="90"
    >
    </star-rating> -->
    <mypagenav />
    <!--====== APPIE PAGE TITLE PART ENDS ======-->
    <router-view />

    <!--====== APPIE FOOTER PART START ======-->
    <footer-home-one />

    <!--====== APPIE FOOTER PART ENDS ======-->
  </div>
</template>

<script>
import HeaderHomeOne from "../HomeOne/HeaderHomeOne.vue";

import Mypagenav from "./Mypagenav.vue";
import FooterHomeOne from "../HomeOne/FooterHomeOne.vue";
// import MypageQna from "./MypageQna.vue";
import TitleArea from "./TitieArea.vue";

export default {
  components: {
    HeaderHomeOne,
    FooterHomeOne,
    // MypageQna,
    TitleArea,
    Mypagenav,
  },
  data() {
    return {
      sidebar: false,
      navs: [
        {
          name: "home",
          childrens: [
            {
              name: "Home",
              path: "/",
            },
            {
              name: "Home Dark",
              path: "/home-dark",
            },
          ],
        },
        {
          name: "운동목록",
          childrens: [
            {
              name: "운동목록",
              path: "/news",
            },
          ],
        },
        {
          name: "마이페이지",
          path: "/",
          childrens: [
            {
              name: "내정보",
              path: "/mypage/main",
            },
            {
              name: "내 글",
              path: "/mypage/myposts",
            },
            {
              name: "참여내역",
              path: "/mypage/myjoinlog",
            },
            {
              name: "운동기록",
              path: "/mypage/myworkoutlog",
            },
            {
              name: "평가페이지",
              path: "/mypage/myevallog",
            },
            {
              name: "자주묻는질문",
              path: "/mypage/qna",
            },
          ],
        },
      ],
    };
  },
};
</script>

<style>
.faq-wrapper[data-v-5d9392b3] {
  margin: auto;
}
</style>
