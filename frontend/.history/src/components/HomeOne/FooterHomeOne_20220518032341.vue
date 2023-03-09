<template>
  <div class="bottom-nav">
    <!--All Blogs-->
    <div class="nav-item">
      <router-link to="/AllBlogs">
        <div v-if="this.$route.path === `/AllBlogs`">
          <img src="@/assets/images/icon/house-fill.svg" class="nav-item-img" />
        </div>
        <div v-else>
          <img src="@/assets/images/icon/house.svg" class="nav-item-img" />
        </div>
        <p class="nav-item-name">홈</p>
      </router-link>
    </div>

    <!--New Post-->
    <div class="nav-item">
      <router-link to="/newpost">
        <div v-if="this.$route.path === `/newpost`">
          <img src="@/assets/images/icon/plus-square-fill.svg" class="nav-item-img" />
        </div>
        <div v-else>
          <img src="@/assets/images/icon/plus-square.svg" class="nav-item-img" />
        </div>
        <p class="nav-item-name">글 작성</p>
      </router-link>
    </div>

    <!--Chatting-->
    <div class="nav-item">
      <router-link to="/chat/chatroomlist">
        <div v-if="this.$route.path === `/chat/chatroomlist`">
          <img src="@/assets/images/icon/chat-dots-fill.svg" class="nav-item-img" />
        </div>
        <div v-else>
          <img src="@/assets/images/icon/chat-dots.svg" class="nav-item-img" />
        </div>
        <p class="nav-item-name">채팅</p>
      </router-link>
    </div>

    <!--My Page-->
    <div class="nav-item">
      <router-link to="/mypage">
        <div v-if="this.$route.path === `/mypage`">
          <img src="@/assets/images/icon/person-fill.svg" class="nav-item-img" />
        </div>
        <div v-else>
          <img src="@/assets/images/icon/person.svg" class="nav-item-img" />
        </div>
        <p class="nav-item-name">마이</p>
      </router-link>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style>
.bottom-nav {
  position: fixed;
  display: flex;
  bottom: 0;
  left: 0;
  border-top: 1px solid;
  border-color: rgb(213, 213, 213);
  background-color: #ffffff;
  width: 100%;
  height: 8%;
  justify-content: space-around;
}
.nav-item {
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
}
.nav-item-img {
  width: 20px;
  height: 20px;
}
.nav-item-name {
  font-size: 11px;
  font-family: solid !important;
  margin-bottom: -8px;
}
</style>
