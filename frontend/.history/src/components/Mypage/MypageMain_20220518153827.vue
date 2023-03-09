<template>
  <div>
    <div class="mypage_content">
      <div class="row profile" id="profile">
        <div class="col-md-1" id="profile-img-box">
          <b-img id="profile-img" v-bind:src="myinfomation.userProfileUrl" rounded="circle"></b-img>
        </div>
        <div class="col-md-1">
          <p>{{ myinfomation.userNickname }}</p>
        </div>
        <div class="col-md-2">
          <star-rating
            :show-rating="false"
            @rating-selected="setRating"
            :read-only="true"
            :rating="3"
          ></star-rating>
        </div>
      </div>

      <div class="row">나와약속</div>
      <div class="row">
        <div class="col-md-1">
          <span class="badge bg-secondary">9: 30 누구랑 약속</span>
        </div>
      </div>
      <div class="row">모집진행중인모임</div>
      <!-- 버튼 모임 유무에따라 활성화, 비활성화 할것!!! -->
      <b-button size="lg">바로가기</b-button>
      <b-button disabled size="lg">바로가기</b-button>
    </div>

    <div style="margin-top: 10px; font-weight: bold">{{ rating }}</div>
    <mypagenav />
  </div>
</template>
<script>
import StarRating from "vue-star-rating";
import Mypagenav from "./Mypagenav.vue";

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
    components: { StarRating, Mypagenav },
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
.profile {
  height: 100%;
}
#profile {
  margin-top: 60px;
}
#profile-img-box {
  max-width: 50px;
  overflow: hidden;
}
#profile-img {
  width: 50px;
  max-width: initial;
  margin-left: 50%;
}
</style>
