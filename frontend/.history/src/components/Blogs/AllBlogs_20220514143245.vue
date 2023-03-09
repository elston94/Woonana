<template>
  <div class="row">
    <div class="col-lg-12">
      <div class="row">
        <div class="col-lg-6">
          <div class="post-item-1">
            <img src="@/assets/images/blog/1.jpg" alt="" />
            <div class="b-post-details">
              <div class="bp-meta">
                <a href="#"><i class="fal fa-clock"></i>April 22, 2020</a>
                <a href="#"><i class="fal fa-comments"></i>6 Comments</a>
              </div>
              <router-link to="/chat/chatroomlist">
                <h3><a>Build A Full Web Chat App From Scratch.</a></h3>

                <a class="read-more">Read More<i class="fal fa-arrow-right"></i></a>
              </router-link>
            </div>
          </div>
        </div>
        <div class="col-lg-6" v-for="board in boards" :key="board.id">
          <div class="post-item-1">
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
                <a href="/singleblog">{{ board.title }}</a>
              </h3>
              <a class="read-more" href="/singleblog"
                >Read More<i class="fal fa-arrow-right"></i
              ></a>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12">
          <div class="bisylms-pagination">
            <span class="current">01</span>
            <a href="#">02</a>
            <a class="next" href="#">next<i class="fal fa-arrow-right"></i></a>
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
  methods: {},
};
</script>

<style></style>
