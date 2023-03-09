<template>
  <div class="mypage-container">
    <div class="mypage_content">
      <div class="profile" id="profile">
        <div class="col-md-1" id="profile-img-box">
          <b-img id="profile-img" v-bind:src="myinfomation.userProfileUrl"></b-img>
        </div>
        <div class="col-md-1">
          <p id="profile-nickname">{{ myinfomation.userNickname }}</p>
        </div>
      </div>

      <div id="my-promise">
        <div>나의 약속</div>
        <div>
          <span class="badge bg-secondary">박싸피님 9: 30 pm</span>
        </div>
      </div>
      <div id="promise-list">
        <div>모집 진행 중인 모임</div>
        <!-- 버튼 모임 유무에따라 활성화, 비활성화 할 것!!! -->
        <b-button disabled size="lg"
          >바로가기&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; >
        </b-button>
      </div>
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
export default {
  data() {
    return {
      //내정보
      myinfo: {},
      //약속 뿌려줄때 넣을것!!!
      appointment: {},
    };
  },
  methods: {
    //userStore에서 백에 요청해서 가져오는데 현재 http로 요청하면 헤더셋팅에 withCredentials가 안들어감
    getUserInfo: async function () {
      this.myinfo = await this.$store.dispatch("getUserInfo");
    },
    //axios로 해야 withcredentials가 셋팅되서 잘됨,,, 현재 이걸루 하는중
  },
  beforemount: function () {},
  mounted: async function () {
    await this.getUserInfo();
    console.log(this.myinfo);
    console.log("userinfo", this.$store.getters.GET_USER_INFO);
    // this.getUserInfoaxios();
  },
  computed: {
    myinfomation: function () {
      return this.$store.getters.GET_USER_INFO;
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
#profile-and-rating {
  display: flex;
  align-items: baseline;
}
.profile {
  height: 100%;
}
#profile {
  display: flex;
  justify-content: flex-start;
  flex-direction: column;
  margin-top: 60px;
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
#my-promise {
  font-weight: bold;
  margin-bottom: 10px;
}
#promise-list {
  font-weight: bold;
  margin-bottom: 10px;
}
.my-page-list {
  font-weight: bold;
  margin-right: 10px;
  padding-bottom: 12px;
}
.mypage-link-name {
  color: rgb(83, 83, 83);
}
</style>
