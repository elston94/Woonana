<template>
  <div class="bottom-nav">
    <div class="nav-item" href="/AllBlogs">
      <img src="@/assets/images/icon/house-fill.svg" />
    </div>
    <div class="nav-item">
      <img src="@/assets/images/icon/plus-square-fill.svg" />
    </div>
    <div class="nav-item">
      <img src="@/assets/images/icon/chat-dots-fill.svg" />
    </div>
    <div class="nav-item">
      <img src="@/assets/images/icon/person-fill.svg" />
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
  height: 7%;
  align-items: center;
}
.nav-item {
  float: left;
}
</style>
