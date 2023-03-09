//📁router/router.js
import Vue from 'vue';
import VueRouter from 'vue-router';
import MemberLogin from "../components/user/MemberLogin.vue";
import MemberLoginCallback from "../components/user/MemberLoginCallback.vue";
import MemberMyPage from "../components/user/MemberMyPage.vue";
import MemberMyPost from "../components/user/MemberMyPost.vue";

Vue.use(VueRouter);

export default new VueRouter({
	mode:'history', //해쉬값 제거 방식
    routes: [{
        path: '/', 
        redirect: '/login' 
    }, {
        path: '/login',
        component: MemberLogin
    }, {
        path: '/oauth/callback',
        component: MemberLoginCallback
    }, {
        path: '/mypage',
        component: MemberMyPage
    }, {
        path: '/mypost',
        component: MemberMyPost
    }]
});
