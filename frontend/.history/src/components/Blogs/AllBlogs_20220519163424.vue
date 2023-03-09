<template>
  <div class="blogs-row">
    <div class="col-lg-12">
      <div class="row">
        <div class="col-lg-6" v-for="board in boards" :key="board.boardId">
          <div class="post-item-1" @click="boardDetail(board.boardId)">
            <img :src="board.imageUrl" alt="" />
            <div class="b-post-details">
              <div class="bp-meta">
                <a href="#"><i class="fal fa-user"></i>{{ board.userNickname }}</a>
                <a href="#"><i class="fal fa-clock"></i>{{ board.status }}</a>
                <a href="#"
                  ><i class="fal fa-comments"></i>{{ board.allowedNumber }} /
                  {{ board.maxNumber }}</a
                >
              </div>
              <h3>
                <a>{{ board.title }}</a>
              </h3>
            </div>
          </div>
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
  mounted() {
    let data = localStorage.getItem("vuex");
    let parsedata = JSON.parse(data);
    let token = parsedata.loginStore.jwtToken;

    http.defaults.withCredentials = false;
    http.defaults.headers.common["Authorization"] = "Bearer " + token;
    http.defaults.headers.common["withCredentials"] = false;
    http
      .get(`/api/main`)
      .then(({ data }) => {
        this.boards = data;
        console.log(this.boards);
      })
      .catch((err) => {
        alert(err);
      });
  },
  methods: {
    boardDetail: function (data) {
      this.$router.push({
        name: "ShowBlog",
        query: { data: data },
      });
    },
  },
};
</script>

<style>
.blogs-row {
  overflow-x: hidden;
}
</style>
