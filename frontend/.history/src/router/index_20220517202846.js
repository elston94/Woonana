import Vue from "vue";
import VueRouter from "vue-router";
import HomeOne from "../views/HomeOne.vue";
import LoginCallback from "../components/Login/MemberLoginCallback.vue";
import ChatRoomList from "../components/Chat/ChatRoomList.vue";
import ChatRoom from "../views/ChatRoom.vue";
import ChatList from "../components/Chat/ChatList.vue";
import ChatMessage from "../components/Chat/ChatMessage.vue";
import Mypage from "../views/Mypage.vue";
import MypageMain from "../components/Mypage/MypageMain.vue";
import ShowBlog from "../components/Blogs/ShowBlog.vue";
import MypageQna from "../components/Mypage/MypageQna.vue";
import MyEvalLog from "../components/Mypage/MyEvalLog.vue";
import MyJoinLog from "../components/Mypage/MyJoinLog.vue";
import MyPosts from "../components/Mypage/MyPosts.vue";
import MyWorkoutLog from "../components/Mypage/MyWorkoutLog.vue";
import NewPost from "../components/Blogs/NewPost.vue";
import AllBlogs from "../components/Blogs/index.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "AllBlogs",
    component: AllBlogs,
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
    path: "/chat/chatlist",
    name: "ChatList",
    component: ChatList,
  },
  {
    path: "/newpost",
    name: "NewPost",
    component: NewPost,
  },
  {
    path: "/chat/chatroomlist",
    name: "ChatRoomList",
    component: ChatRoomList,
  },
  {
    path: "/chat/chatmessage",
    name: "ChatMessage",
    component: ChatMessage,
  },
  {
    path: "/mypage",
    component: Mypage,
    children: [
      {
        path: "",
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
    path: "/showblog",
    name: "ShowBlog",
    component: ShowBlog,
  },
  {
    path: "/allblogs",
    name: "AllBlogs",
    component: AllBlogs,
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
