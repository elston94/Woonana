<template>
  <div>
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
import AllBlogs from "../Blogs/AllBlogs.vue";
export default {
  components: {
    FooterHomeOne,
    AllBlogs,
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
  mounted() {
    document.addEventListener("scroll", this.topToBottom);
  },
  methods: {
    topToBottom() {
      const result = document.querySelector(".back-to-top");
      if (
        document.body.scrollTop > window.innerHeight ||
        document.documentElement.scrollTop > window.innerHeight
      ) {
        result.style.display = "block";
      } else {
        result.style.display = "none";
      }
    },
    toggleSidebar() {
      this.sidebar = !this.sidebar;
    },
  },
};
</script>

<style></style>
