<template>
  <div class="footer-row">
    <ul class="bottomBar">
      <li class="barItem">
        <a class="button" href="/AllBlogs"><i class="fas fa-home"></i></a>
      </li>
      <li class="barItem">
        <a><i class="fas fa-location"></i></a>
      </li>
      <li class="barItem">
        <a href="/newpost"><i class="fas fa-plus"></i></a>
      </li>
      <li class="barItem">
        <a href="/chat/chatroomlist"><i class="fas fa-comment"></i></a>
      </li>
      <li class="barItem">
        <a href="/mypage"><i class="fas fa-user"></i></a>
      </li>
    </ul>
  </div>
</template>

<script>
export default {};
</script>

<style>
.bottomBar {
  position: fixed;
  bottom: 0;
  background-color: #eef1f6;
  width: 100%;
  height: 70px;
  align-items: center;
}
.barItem {
  float: left;
}
</style>
