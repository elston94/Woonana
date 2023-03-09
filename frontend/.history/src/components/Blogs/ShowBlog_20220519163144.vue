<template>
  <div>
    <div class="row">
      <div class="col-md-12" id="blog-title">
        <span>게시글</span>
      </div>
    </div>
    <!-- Blog Start -->
    <section class="blogpage-section" id="showblog">
      <div class="container">
        <div class="col-lg-8 col-md-7">
          <single-blog />
        </div>
      </div>
    </section>
    <!-- Blog End -->
    <footer-home-one />
  </div>
</template>

<script>
import SingleBlog from "./SingleBlog.vue";
import FooterHomeOne from "../HomeOne/FooterHomeOne.vue";
export default {
  components: {
    FooterHomeOne,
    SingleBlog,
  },
};
</script>

<style>
#blog-title {
  position: fixed;
  top: 0;
  background: #ffffff;
  box-shadow: 0px 3px 10px rgba(0, 0, 0, 0.05);
  padding: 1rem;
  font-size: 16px;
  font-weight: 700;
  z-index: 10;
  margin-left: 13px;
  text-align: center;
}
#showblog {
  padding: 40px 0;
}
</style>
