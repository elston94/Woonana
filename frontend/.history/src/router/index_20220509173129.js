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
import Room from "../components/Chat/Room.vue";
import RoomDetail from "../components/Chat/RoomDetail.vue";

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
    path: "/chat",
    name: "Chat",
    component: Room,
  },
  {
    path: "/chat/detail",
    name: "RoomDetail",
    component: RoomDetail,
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
