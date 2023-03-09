<template>
  <div class="blogs-row">
    <div class="col-lg-12">
      <div class="row">
        <div class="col-lg-6" v-for="board in boards" :key="board.boardId">
          <div class="row mt-2" @click="boardDetail(board.boardId)">
            <div class="col-4">
              <img
                :src="board.imageUrl"
                alt=""
                width="120"
                height="120"
                class="rounded"
              />
            </div>
            <div class="col-8 mt-2">
              <h4 class="bp-meta">
                {{ board.title }}
              </h4>
              <div class="bp-meta">
                <div>
                  <i class="fal fa-user"></i>&nbsp;{{ board.userNickname }}
                </div>
              </div>
              <div class="bp-meta">
                <i class="fal fa-clock"></i>&nbsp;{{
                  board.status
                }}
                &nbsp;&nbsp; <i class="fal fa-comments"></i>&nbsp;{{
                  board.allowedNumber
                }}
                /
                {{ board.maxNumber }}
              </div>
            </div>
          </div>
          <hr />
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
