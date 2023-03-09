import Vue from "vue";
import VueRouter from "vue-router";
import HomeOne from "../views/HomeOne.vue";
import HomeDark from "../views/HomeDark.vue";
import News from "../views/News.vue";
import SingleNews from "../views/SingleNews.vue";
import Contact from "../views/Contact.vue";
import ServiceDetails from "../views/ServiceDetails";
import Error from "../views/Error.vue";
import AboutUs from "../views/About.vue";
import LoginCallback from "../components/Login/MemberLoginCallback.vue";
import ChatRoom from "../components/Chat/ChatRoom.vue";
import ChatForm from "../components/Chat/ChatForm.vue";
import ChatList from "../components/Chat/ChatList.vue";
import ChatRoomList from "../components/Chat/ChatRoomList.vue";

import Mypage from "../views/Mypage.vue";
import MypageMain from "../components/Mypage/index.vue";
import SingleBlog from "../components/SingleNews/index.vue";
import MypageQna from "../components/Mypage/MypageQna.vue";
import MyEvalLog from "../components/Mypage/MyEvalLog.vue";
import MyJoinLog from "../components/Mypage/MyJoinLog.vue";
import MyPosts from "../components/Mypage/MyPosts.vue";
import MyWorkoutLog from "../components/Mypage/MyWorkoutLog.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "HomeOne",
    component: HomeOne,
  },
  {
    path: "/home-dark",
    name: "HomeDark",
    component: HomeDark,
  },
  {
    path: "/service-details",
    name: "ServiceDetails",
    component: ServiceDetails,
  },
  {
    path: "/about-us",
    name: "AboutUs",
    component: AboutUs,
  },
  {
    path: "/news",
    name: "News",
    component: News,
  },
  {
    path: "/single-news",
    name: "SingleNews",
    component: SingleNews,
  },
  {
    path: "/contact",
    name: "Contact",
    component: Contact,
  },
  {
    path: "/error",
    name: "Error",
    component: Error,
  },
  {
    path: "/oauth/callback",
    name: "LoginCallback",
    component: LoginCallback,
  },
  {
    path: "/chat/chatroom",
    name: "ChatRoom",
    component: ChatRoom,
  },
  {
    path: "/chat/chatform",
    name: "ChatForm",
    component: ChatForm,
  },
  {
    path: "/chat/chatlist",
    name: "ChatList",
    component: ChatList,
  },
  {
    path: "/chat/chatroomlist",
    name: "ChatRoomList",
    component: ChatRoomList,
  },
  {
    path: "/mypage/",
    component: Mypage,
    children: [
      {
        path: "/",
        component: MypageMain,
      },
      {
        path: "main",
        component: MypageMain,
      },
      {
        // /mypage/qna 과 일치 할 때
        // mypageqna가 mypage <router-view> 내에 렌더링 됩니다.
        path: "qna",
        component: MypageQna,
      },
      {
        // /mypage/qna 과 일치 할 때
        // mypageqna가 mypage <router-view> 내에 렌더링 됩니다.
        path: "myevallog",
        component: MyEvalLog,
      },
      {
        // /mypage/qna 과 일치 할 때
        // mypageqna가 mypage <router-view> 내에 렌더링 됩니다.
        path: "myjoinlog",
        component: MyJoinLog,
      },
      {
        // /mypage/qna 과 일치 할 때
        // mypageqna가 mypage <router-view> 내에 렌더링 됩니다.
        path: "myposts",
        component: MyPosts,
      },
      {
        // /mypage/qna 과 일치 할 때
        // mypageqna가 mypage <router-view> 내에 렌더링 됩니다.
        path: "myworkoutlog",
        component: MyWorkoutLog,
      },
    ],
  },
  {
    path: "/singleblog",
    name: "SingleBlog",
    component: SingleBlog,
  },
  // {
  //   path: '/about',
  //   name: 'About',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  // }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
  // stop stop cache
  scrollBehavior() {
    document.getElementById("app").scrollIntoView();
  },
});

export default router;
