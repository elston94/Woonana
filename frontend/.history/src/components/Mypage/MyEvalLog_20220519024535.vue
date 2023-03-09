<template>
  <div class="eveldiv">
    <div v-if="evalLists">
      <b-tabs content-class="mt-3" fill>
        <b-tab title="평가내역" active>
          <div class="card_div m-3" v-for="(item, index) in evalListscomput" :key="index">
            <b-card
              v-bind:title="item.userNickname + ' 모임 ' + item.userId + '님에게 준 평점'"
              :sub-title="item.boardDate.slice(0, 10)"
              style="max-width: 25rem"
              class="mb-3"
            >
              <b-card-text>
                <star-rating v-bind:rating="item.rating" :read-only="true" :increment="1" />
              </b-card-text>

              <!-- <b-button href="#" variant="primary">평가확정</b-button> -->
            </b-card>
          </div></b-tab
        >
        <b-tab title="평가하기">
          <div v-for="(item, index) in evaledListscomput" :key="index">
            <b-card
              v-bind:title="item.userNickname + '님은 어떠셨나요?'"
              :sub-title="item.boardDate.slice(0, 10)"
              style="max-width: 25rem"
              class="mb-3"
            >
              <b-card-text>
                <star-rating v-bind:rating="3" @rating-selected="setRating" :increment="1" />
              </b-card-text>
              <b-button @click="postRating(rating, item.userId, item.boardId, index)"
                >평가하기</b-button
              >
            </b-card>
          </div></b-tab
        >
      </b-tabs>
    </div>
    <div v-else>
      <div class="appie-single-service-2 wow animated fadeInUp">아직 평가 내역이 없습니다.</div>
    </div>
  </div>
</template>

<script>
import http from "../../util/index";
export default {
  data() {
    return {
      evalLists: [],
      evaledLists: [],
      evalpostList: [],
      boardTitle: "",
      rating: 0,
    };
  },
  methods: {
    //평가 한사람들의 목록
    getevallist: async function () {
      let data = localStorage.getItem("vuex");
      let parsedata = JSON.parse(data);
      let token = parsedata.loginStore.jwtToken;

      http.defaults.withCredentials = false;
      http.defaults.headers.common["Authorization"] = "Bearer " + token;
      http.defaults.headers.common["withCredentials"] = false;
      let res = await http.get("/api/accounts/evalue");

      console.log("result", res);
      this.evalLists = res.data;
    },
    getevaledlist: async function () {
      let data = localStorage.getItem("vuex");
      let parsedata = JSON.parse(data);
      let token = parsedata.loginStore.jwtToken;

      http.defaults.withCredentials = false;
      http.defaults.headers.common["Authorization"] = "Bearer " + token;
      http.defaults.headers.common["withCredentials"] = false;
      let res = await http.get("/api/accounts/evalue/person");

      console.log("result", res);
      this.evaledLists = res.data;
    },
    getBoardTitle: async function (boardId) {
      let data = localStorage.getItem("vuex");
      let parsedata = JSON.parse(data);
      let token = parsedata.loginStore.jwtToken;

      http.defaults.withCredentials = false;
      http.defaults.headers.common["Authorization"] = "Bearer " + token;
      http.defaults.headers.common["withCredentials"] = false;
      let res = await http.get("/api/main/" + boardId);

      console.log("result", res);
      return res.data.title;
    },
    postRating: async function (rate, userId, boardId, index) {
      let data = localStorage.getItem("vuex");
      let parsedata = JSON.parse(data);
      let token = parsedata.loginStore.jwtToken;
      http.defaults.withCredentials = false;
      http.defaults.headers.common["Authorization"] = "Bearer " + token;
      http.defaults.headers.common["withCredentials"] = false;
      let param = { rating: 0, userId: 0, boardId: 0 };
      param.rating = rate;
      param.userId = userId;
      param.boardId = boardId;

      let res = await http.post("/api/accounts/evalue", param);
      if (res.status === 200) {
        this.evaledLists.splice(index, 1);
        console.log(this.evaledLists);
        this.getevallist();
        alert("평가완료");
      }
      console.log(res);
    },
    setRating: function (rating) {
      this.rating = rating;
    },
  },
  mounted: function () {
    this.getevallist();
    this.getevaledlist();
    // this.postRating(6, 2);
  },
  computed: {
    evaledListscomput() {
      return this.evaledLists;
    },
    evalListscomput() {
      return this.evalLists;
    },
  },
};
</script>

<style>
.evaldiv {
  text-align: center;
}
.card_div {
  display: inline-block;
}
</style>
