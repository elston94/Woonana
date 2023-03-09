<template>
  <div class="container">
    <div class="row align-items-end">
      <div class="col-lg-6 col-md-8">
        <div class="appie-section-title mt-3">
          <h3 class="appie-title">내 참여내역</h3>
        </div>
      </div>
    </div>

    <div class="row" v-if="logs.length">
      <div
        class="col-lg-4 col-md-6"
        v-for="log in logs"
        :key="log.id"
        @click="moveDetail(log.boardId)"
      >
        <div
          class="appie-single-service-2 item-3 wow animated fadeInUp"
          data-wow-duration="2000ms"
          data-wow-delay="200ms"
        >
          <div class="icon">
            <i
              :class="{
                'fal fa-table-tennis': isPingPong(log.exerciseId),
                'fal fa-shuttlecock': isBadminton(log.exerciseId),
                'fal fa-baseball-ball': isCatchball(log.exerciseId),
                'fal fa-running': isWalking(log.exerciseId),
              }"
              :id="log.exerciseId"
            ></i>
          </div>
          <h4 class="title">
            {{ log.boardMeetStartDate }} {{ log.place }} {{ log.exerciseName }}
          </h4>
        </div>
      </div>
    </div>
    <div class="row" v-else>
      <div class="appie-single-service-2 wow animated fadeInUp">
        아직 참여 내역이 없습니다.
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/util/index";

export default {
  data() {
    return {
      logs: [],
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
      .get(`/api/accounts/participate`)
      .then(({ data }) => {
        this.logs = data;
        console.log(this.logs);
      })
      .catch((err) => {
        alert(err);
      });
  },
  methods: {
    isPingPong(exerciseId) {
      if (exerciseId == 1) return true;
    },
    isBadminton(exerciseId) {
      if (exerciseId == 2) return true;
    },
    isCatchball(exerciseId) {
      if (exerciseId == 3) return true;
    },
    isWalking(exerciseId) {
      if (exerciseId == 4) return true;
    },
    moveDetail: function (data) {
      this.$router.push({ name: "ShowBlog", query: { data: data } });
    },
  },
};
</script>

<style></style>
