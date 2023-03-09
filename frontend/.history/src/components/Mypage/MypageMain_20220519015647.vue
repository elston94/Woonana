<template>
  <div class="mypage-container">
    <div class="mypage_content">
      <div id="profile-weather">
        <div id="profile">
          <div class="col-md-1" id="profile-img-box">
            <b-img id="profile-img" v-bind:src="myinfomation.userProfileUrl"></b-img>
          </div>
          <div class="col-md-1">
            <p id="profile-nickname">{{ myinfomation.userNickname }}</p>
          </div>
        </div>
        <div id="my-weather">
          <p>
            {{ myinfomation.userNickname }}님의 날씨 :
            <i v-if="this.myrating <= 1" class="fas fa-bolt"></i>
            <i v-if="this.myrating <= 2 && this.myrating > 1" class="fas fa-cloud-rain"></i>
            <i v-if="this.myrating <= 3 && this.myrating > 2" class="fas fa-cloud"></i>
            <i v-if="this.myrating <= 4 && this.myrating > 3" class="fas fa-cloud-sun"></i>
            <i v-if="this.myrating <= 5 && this.myrating > 4" class="fas fa-sun"></i>
          </p>
        </div>
      </div>
      <div id="my-promise">
        <div>나의 약속</div>
        <div>
          <span v-for="(appoint, index) in myappointment" :key="index" class="badge bg-black">{{
            appoint.boardMeetStartDate
          }}</span>
        </div>
      </div>
      <div>모집 진행 중인 모임</div>
      <!-- 버튼 모임 유무에따라 활성화, 비활성화 할것!!! -->
      <b-button v-show="checkBoardid" @click="movepost()"
        >바로가기&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></b-button
      >
      <b-button v-show="!checkBoardid" disabled
        >바로가기&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></b-button
      >
    </div>
    <ul class="list-group list-group-flush">
      <li class="my-page-list list-group-item">
        <router-link to="/mypage/myposts" class="mypage-link-name">내 글</router-link>
      </li>
      <li class="my-page-list list-group-item">
        <router-link to="/mypage/myjoinlog" class="mypage-link-name">참여 내역</router-link>
      </li>
      <li class="my-page-list list-group-item">
        <router-link to="/mypage/myworkoutlog" class="mypage-link-name">운동 기록</router-link>
      </li>
      <li class="my-page-list list-group-item">
        <router-link to="/mypage/myevallog" class="mypage-link-name">평가 페이지</router-link>
      </li>
      <li class="my-page-list list-group-item">
        <router-link to="/mypage/qna" class="mypage-link-name">고객 센터</router-link>
      </li>
    </ul>
  </div>
</template>
<script>
import http from "@/util/index";
export default {
  data() {
    return {
      //내정보
      myinfo: {},
      //약속 뿌려줄때 넣을것!!!
      appointment: [],
      boardId: 0,
      myrating: -1,
    };
  },
  methods: {
    //userStore에서 백에 요청해서 가져오는데 현재 http로 요청하면 헤더셋팅에 withCredentials가 안들어감
    getUserInfo: async function () {
      this.myinfo = await this.$store.dispatch("getUserInfo");
    },
    getParticipateInfo: async function () {
      let data = localStorage.getItem("vuex");
      let parsedata = JSON.parse(data);
      let token = parsedata.loginStore.jwtToken;
      http.defaults.headers.common["Authorization"] = "Bearer " + token;
      http.defaults.headers.common["withCredentials"] = false;
      let res = await http.get("/api/accounts/participate");

      console.log(res);
      this.appointment = res.data;
    },
    getBoardId: function () {
      //appointment배열중 오늘 날짜에 해당하거나 오늘날짜에 가장 가까운놈 하나 리턴해서 그 주소로 이동
      let today = new Date();
      let result = today;
      let boardId = -1;
      console.log(today);
      for (let appoint of this.appointment) {
        let date1 = new Date(appoint.boardMeetStartDate);
        console.log("오늘날짜 ", today);
        console.log(" 비교날짜 ", date1);
        if (today >= date1) {
          continue;
        } else if (result > date1) {
          result = date1;
          boardId = appoint.boardId;
        }
      }
      console.log(boardId);
      this.boardId = boardId;
      return boardId;
    },
    movepost: function () {
      //게시글 상세로 이동
      this.$router.push({
        name: "ShowBlog",
        params: { data: this.boardId },
      });
    },
    getMyRating: async function () {
      //내 평점정보 가져옴
      let data = localStorage.getItem("vuex");
      let parsedata = JSON.parse(data);
      let token = parsedata.loginStore.jwtToken;
      http.defaults.headers.common["Authorization"] = "Bearer " + token;
      http.defaults.headers.common["withCredentials"] = false;
      let res = await http.get("/api/accounts/evalue/me");
      console.log(res);
      this.myrating = await res.data.userRatingScore;
      // console.log(this.myrating);
    },
    //axios로 해야 withcredentials가 셋팅되서 잘됨,,, 현재 이걸루 하는중
  },
  beforemount: function () {},
  mounted: async function () {
    await this.getUserInfo();
    await this.getParticipateInfo();
    console.log(this.myinfo);
    console.log("userinfo", this.$store.getters.GET_USER_INFO);
    this.getBoardId();
    this.getMyRating();

    // this.getUserInfoaxios();
  },
  created() {
    this.getBoardId();
  },
  computed: {
    myinfomation: function () {
      return this.$store.getters.GET_USER_INFO;
    },
    myappointment: function () {
      return this.appointment;
    },
    checkBoardid: function () {
      return this.boardId != -1;
      // return false;
    },
  },
};
</script>

<style>
.mypage-container {
  margin-left: 10px;
}
.mypage_content {
  margin: 1%;
}
#profile-weather {
  margin-top: 60px;
  display: flex;
  justify-content: flex-start;
}
#profile {
  height: 100%;
  display: flex;
  justify-content: flex-start;
  flex-direction: column;
  margin-bottom: 10px;
}
#profile-img-box {
  width: 80px;
  height: 80px;
  margin-top: 10px;
  border-radius: 70%;
  overflow: hidden;
  padding: 0;
}
#profile-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
#profile-nickname {
  font-weight: bold;
  margin-top: 3px;
  margin-left: 14px;
}
#my-weather {
}
#my-promise {
  font-weight: bold;
  margin-bottom: 10px;
}
#promise-list {
  font-weight: bold;
  margin-bottom: 10px;
}
.my-page-list {
  font-weight: 900;
  margin-right: 12px;
  padding-bottom: 12px;
}
.mypage-link-name {
  color: rgb(38, 38, 38);
}
</style>
