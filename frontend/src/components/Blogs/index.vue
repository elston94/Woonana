<template>
  <div>
    <div v-if="showloading">
      <loader-appie />
    </div>
    <div class="row">
      <div class="col-md-12" id="blog-list-title">
        <span>게시판</span>
      </div>
    </div>
    <!-- Blog Start -->
    <section class="blogpage-section">
      <div class="container">
        <div class="row">
          <div class="col-lg-8 col-md-7">
            <all-blogs />
          </div>
        </div>
      </div>
    </section>
    <footer-home-one />
  </div>
</template>

<script>
import FooterHomeOne from "../HomeOne/FooterHomeOne.vue";
import LoaderAppie from "../LoaderAppie.vue";
import AllBlogs from "./AllBlogs.vue";
export default {
  components: {
    LoaderAppie,
    FooterHomeOne,
    AllBlogs,
  },
  created() {
    if (!this.rtl) {
      this.preLoading();
    } else {
      this.showloading = false;
    }
  },

  methods: {
    preLoading() {
      setTimeout(() => {
        this.showloading = false;
      }, 2000);
    },
  },
  data() {
    return {
      showloading: true,
    };
  },
};
</script>

<style>
#blog-list-title {
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
</style>
