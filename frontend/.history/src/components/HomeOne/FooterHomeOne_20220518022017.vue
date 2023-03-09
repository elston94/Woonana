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
        <p>새 글</p>
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
  display: flex;
  bottom: 0;
  left: 0;
  background-color: #eef1f6;
  width: 100%;
  height: 8%;
  justify-content: space-around;
}
.nav-item {
  display: flex;
  justify-content: center;
  align-items: center;
}
.nav-item-img {
  width: 20px;
  height: 20px;
}
</style>
