<template>
  <div class="col-lg-12">
    <div class="col-lg-6" v-for="board in boards" :key="board.id">
      <div class="post-item-1">
        <div class="b-post-details">
          <div class="bp-meta">
            <a href="#"><i class="fal fa-clock"></i>{{ board.status }}</a>
            <a href="#"
              ><i class="fal fa-comments"></i>{{ board.allowedNumber }} / {{ board.maxNumber }}</a
            >
          </div>
          <h3>
            <!-- <a href="http://localhost:8080/api/main/4">{{ board.title }}</a> -->
            <a @click="movePost(board.boardId)">{{ board.title }}</a>
          </h3>
          <a class="read-more" href="/singleblog">자세히 보기<i class="fal fa-arrow-right"></i></a>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/util/index";

export default {
  data() {
    return {
      boards: [],
    };
  },
  components: {},
  mounted() {
    let data = localStorage.getItem("vuex");
    let parsedata = JSON.parse(data);
    let token = parsedata.loginStore.jwtToken;

    http.defaults.withCredentials = false;
    http.defaults.headers.common["Authorization"] = "Bearer " + token;
    http.defaults.headers.common["withCredentials"] = false;
    http
      .get(`/api/main/mypage`)
      .then(({ data }) => {
        this.boards = data;
        console.log(this.boards);
      })
      .catch((err) => {
        alert(err);
      });
  },
  methods: {
    movePost: function (data) {
      this.$router.push({
        name: "ShowBlog",
        params: { data: data },
      });
    },
  },
  computed: {},
};
</script>

<style></style>
