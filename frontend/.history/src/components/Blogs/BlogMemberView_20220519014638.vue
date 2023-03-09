<template>
  <div>
    <div class="single-post-area Jua">
      <!-- <blockquote v-for="user in users" :key="user.id">
            <p>{{user.userNickname}}</p>
            <p>{{user.userSex}}</p>
            <cite>{{user.userNickname}}님의 날씨 : </cite>
        </blockquote> -->
      <h4 class="article-title Jua">참여자 목록</h4>
      <div class="post-admin" v-for="user in users" :key="user.userid">
        <img v-bind:src="user.userProfileUrl" />
        <a>이름 : {{ user.userNickname }}</a>
        <span>성별 : {{ user.userSex }}</span>
        <p>
          {{ user.userNickname }}님의 날씨 :
          <i v-if="user.userRatingScore <= 1" class="fas fa-bolt"></i>
          <i
            v-if="user.userRatingScore <= 2 && user.userRatingScore > 1"
            class="fas fa-cloud-rain"
          ></i>
          <i v-if="user.userRatingScore <= 3 && user.userRatingScore > 2" class="fas fa-cloud"></i>
          <i
            v-if="user.userRatingScore <= 4 && user.userRatingScore > 3"
            class="fas fa-cloud-sun"
          ></i>
          <i v-if="user.userRatingScore <= 5 && user.userRatingScore > 4" class="fas fa-sun"></i>
        </p>
      </div>
    </div>
    <button
      type="button"
      class="btn btn-light Jua"
      style="background-color: #ff0000"
      @click="gohome()"
    >
      뒤로가기
    </button>
    <button
      v-if="this.memberstatus"
      type="button"
      class="btn btn-light Jua"
      style="background-color: #ffbf00"
      @click="gochat()"
    >
      채팅하기
    </button>
  </div>
</template>

<script>
import http from "@/util/index";
export default {
  data() {
    return {
      users: [],
      boardId: "",
      // storeduser:{
      //   userNickname: ""
      // },
      // storedusers: [],
      memberstatus: false,
    };
  },
  mounted() {
    this.getuserinfo();
  },
  computed: {
    myinfomation: function () {
      return this.$store.getters.GET_USER_INFO;
    },
  },
  methods: {
    gohome() {
      //   this.$router.push("/allblogs");
      this.$router.go(-1);
    },
    gochat() {},
    getuserinfo() {
      this.$store.dispatch("getUserInfo");
      this.boardId = this.$route.query.data;
      http
        .get(`api/main/${this.boardId}/members`)
        .then((response) => {
          this.users = response.data;
          // console.log("여기는 users 내용",this.users);
          // console.log("여기는 유저닉네임 내용", this.users[0].userNickname);
          // console.log("여기는 로그인한 사람 이름", this.myinfomation.userNickname);
          this.membercheck();
        })
        .catch((err) => {
          alert(err);
        });
    },
    membercheck() {
      for (var i = 0; i < this.users.length; i++) {
        // this.storeduser.userNickname = this.users[i].userNickname;
        // this.storedusers.push(this.storeduser);
        // this.storeduser = {};
        if (this.myinfomation.userNickname == this.users[i].userNickname) {
          this.memberstatus = true;
        } else {
          this.memberstatus = false;
        }
      }
      console.log("여긴 created 바깥의 유저내용", this.users);
    },
  },
};
</script>

<style></style>
