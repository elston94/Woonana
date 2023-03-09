<template>
  <div>
    <div v-if="showloading">
      <loader-appie />
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
      navs: [
        {
          name: "home",
          childrens: [
            {
              name: "Home 1",
              path: "/",
            },
          ],
        },
        {
          name: "news",
          childrens: [
            {
              name: "News",
              path: "/news",
            },
          ],
        },
      ],
    };
  },
};
</script>

<style></style>
