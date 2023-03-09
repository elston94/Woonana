<template>
  <div>
    <title-area />
    <mypagenav />
    <router-view />

    <footer-home-one />
  </div>
</template>

<script>
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
