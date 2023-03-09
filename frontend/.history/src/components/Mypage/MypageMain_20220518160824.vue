<template>
  <div>
    <div class="mypage_content">
      <div class="profile" id="profile">
        <div class="col-md-1" id="profile-img-box">
          <b-img id="profile-img" v-bind:src="myinfomation.userProfileUrl"></b-img>
        </div>
        <div class="col-md-1" id="profile-nickname">
          <p>{{ myinfomation.userNickname }}</p>
        </div>
      </div>
      <div>나의 약속</div>
      <div>
        <div>
          <span class="badge bg-secondary">9: 30</span>
        </div>
      </div>
      <div>모집진행중인모임</div>
      <!-- 버튼 모임 유무에따라 활성화, 비활성화 할것!!! -->
      <b-button size="lg">바로가기</b-button>
      <b-button disabled size="lg">바로가기</b-button>
    </div>
    <ul class="list-group list-group-flush">
      <li class="my-page-list list-group-item">내 글</li>
      <li class="my-page-list list-group-item">참여 내역</li>
      <li class="my-page-list list-group-item">운동 기록</li>
      <li class="my-page-list list-group-item">평가 페이지</li>
      <li class="my-page-list list-group-item">고객 센터</li>
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
}
#profile-img-box {
  width: 80px;
  height: 80px;
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
}
</style>
