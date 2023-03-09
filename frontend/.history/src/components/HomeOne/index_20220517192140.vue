<template>
  <div>
    <div v-if="showloading">
      <loader-appie />
    </div>
    <div v-else>
      <!-- <div class="loader-wrap">
        <div class="preloader"><div class="preloader-close">Preloader Close</div></div>
        <div class="layer layer-one"><span class="overlay"></span></div>
        <div class="layer layer-two"><span class="overlay"></span></div>        
        <div class="layer layer-three"><span class="overlay"></span></div>        
    </div> -->

      <sidebar-home-one :sidebar="sidebar" @toggleSidebar="toggleSidebar" :menuItems="navs" />

      <header-home-one @toggleSidebar="toggleSidebar" :menuItems="navs" />

      <hero-home-one />

      <!-- <team-home-one
        title="Meet our Team Members"
        description="Different layouts and styles for team sections."
        :members="members"
      /> -->

      <footer-home-one />
    </div>
  </div>
</template>
<script>
import testmonialAvater from "@/assets/images/testimonial-user.png";
import traffic_thumb from "@/assets/images/traffic-thumb.png";
import HeaderHomeOne from "./HeaderHomeOne.vue";
import SidebarHomeOne from "./SidebarHomeOne.vue";
import HeroHomeOne from "./HeroHomeOne.vue";
import TeamHomeOne from "./TeamHomeOne.vue";
import FooterHomeOne from "./FooterHomeOne.vue";
import LoaderAppie from "../LoaderAppie.vue";
export default {
  name: "HomeOne",
  props: {
    rtl: {
      type: Boolean,
      default: false,
    },
  },
  components: {
    HeaderHomeOne,
    SidebarHomeOne,
    HeroHomeOne,
    TeamHomeOne,
    FooterHomeOne,
    LoaderAppie,
  },
  data() {
    return {
      traffic_thumb: traffic_thumb,
      testmonialAvater: testmonialAvater,
      sidebar: false,
      showloading: true,
      // navs: [
      //   {
      //     name: "home",
      //     childrens: [
      //       {
      //         name: "Home",
      //         path: "/",
      //       },
      //     ],
      //   },
      //   {
      //     name: "운동목록",
      //     childrens: [
      //       {
      //         name: "운동목록",
      //         path: "/news",
      //       },
      //     ],
      //   },
      //   {
      //     name: "마이페이지",
      //     path: "/mypage",
      //     childrens: [
      //       {
      //         name: "내정보",
      //         path: "/mypage/main",
      //       },
      //       {
      //         name: "내 글",
      //         path: "/mypage/myposts",
      //       },
      //       {
      //         name: "참여내역",
      //         path: "/mypage/myjoinlog",
      //       },
      //       {
      //         name: "운동기록",
      //         path: "/mypage/myworkoutlog",
      //       },
      //       {
      //         name: "평가페이지",
      //         path: "/mypage/myevallog",
      //       },
      //       {
      //         name: "자주묻는질문",
      //         path: "/mypage/qna",
      //       },
      //     ],
      //   },
      // ],

      // memberOne: memberOne,
      // memberTwo: memberTwo,
      // memberThree: memberThree,
      // memberFour: memberFour,
      // members: [
      //   {
      //     name: "Benjamin Evalent",
      //     position: "CEO-Founder",
      //     img: memberTwo,
      //     socials: [
      //       {
      //         name: "facebok",
      //         url: "https://www.facebook.com",
      //         icon: "fab fa-facebook-f", //user font awesome class
      //       },
      //       {
      //         name: "twitter",
      //         url: "https://www.twitter.com",
      //         icon: "fab fa-twitter", //user font awesome class
      //       },
      //       {
      //         name: "pinterest",
      //         url: "https://www.pinterest.com/",
      //         icon: "fab fa-pinterest-p", //user font awesome class
      //       },
      //     ],
      //   },
      //   {
      //     name: "Benjamin Evalent",
      //     position: "CEO-Founder",
      //     img: memberOne,
      //     socials: [
      //       {
      //         name: "facebok",
      //         url: "https://www.facebook.com",
      //         icon: "fab fa-facebook-f", //user font awesome class
      //       },
      //       {
      //         name: "twitter",
      //         url: "https://www.twitter.com",
      //         icon: "fab fa-twitter", //user font awesome class
      //       },
      //       {
      //         name: "pinterest",
      //         url: "https://www.pinterest.com/",
      //         icon: "fab fa-pinterest-p", //user font awesome class
      //       },
      //     ],
      //   },
      //   {
      //     name: "Benjamin Evalent",
      //     position: "CEO-Founder",
      //     img: memberThree,
      //     socials: [
      //       {
      //         name: "facebok",
      //         url: "https://www.facebook.com",
      //         icon: "fab fa-facebook-f", //user font awesome class
      //       },
      //       {
      //         name: "pinterest",
      //         url: "https://www.pinterest.com/",
      //         icon: "fab fa-pinterest-p", //user font awesome class
      //       },
      //     ],
      //   },
      //   {
      //     name: "Benjamin Evalent",
      //     position: "CEO-Founder",
      //     img: memberFour,
      //     socials: [
      //       {
      //         name: "facebok",
      //         url: "https://www.facebook.com",
      //         icon: "fab fa-facebook-f", //user font awesome class
      //       },
      //       {
      //         name: "twitter",
      //         url: "https://www.twitter.com",
      //         icon: "fab fa-twitter", //user font awesome class
      //       },
      //       {
      //         name: "pinterest",
      //         url: "https://www.pinterest.com/",
      //         icon: "fab fa-pinterest-p", //user font awesome class
      //       },
      //     ],
      //   },
      // ],
    };
  },
  created() {
    if (!this.rtl) {
      this.preLoading();
    } else {
      this.showloading = false;
    }
    document.addEventListener("scroll", this.topToBottom);
  },

  methods: {
    preLoading() {
      setTimeout(() => {
        this.showloading = false;
      }, 2000);
    },
    topToBottom() {
      const result = document.querySelector(".back-to-top");
      if (
        document.body.scrollTop > window.innerHeight ||
        document.documentElement.scrollTop > window.innerHeight
      ) {
        result.style.display = "block";
      } else {
        result.style.display = "none";
      }
    },
    toggleSidebar() {
      this.sidebar = !this.sidebar;
    },
  },
};
</script>

<style></style>
