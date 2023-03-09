<template>
  <div class="bottom-nav">
    <div class="nav-item">
      <router-link to="/AllBlogs">
        <img src="@/assets/images/icon/house-fill.svg" class="nav-item-img" />
      </router-link>
    </div>
    <div class="nav-item">
      <router-link to="/newpost">
        <img src="@/assets/images/icon/plus-square-fill.svg" class="nav-item-img" />
      </router-link>
    </div>
    <div class="nav-item">
      <router-link to="/chat/chatroomlist">
        <img src="@/assets/images/icon/chat-dots-fill.svg" class="nav-item-img" />
      </router-link>
    </div>
    <div class="nav-item">
      <router-link to="/mypage">
        <img src="@/assets/images/icon/person-fill.svg" class="nav-item-img" />
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
  bottom: 0;
  background-color: #eef1f6;
  width: 100%;
  height: 9%;
  align-items: center;
}
.nav-item {
  display: inline-block;
  width: 10%;
  height: 10%;
}
.nav-item-img {
  width: 30px;
  height: 30px;
}
</style>
