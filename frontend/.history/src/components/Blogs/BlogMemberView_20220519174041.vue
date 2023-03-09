<template>
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
    <button
      type="button"
      class="btn btn-light Jua"
      style="background-color: #ff0000"
      @click="gohome()"
    >
      뒤로가기
    </button>
    <button
      type="button"
      class="btn btn-light Jua"
      style="background-color: #ffbf00"
      @click="makeChatRoom()"
<<<<<<< HEAD
      v-if="this.masterCheck == 1"
=======
      v-if="this.masterCheck==1"
>>>>>>> 9f00b80e135ff1a4e1db6c2c0101cc909c83f86a
    >
      채팅방 개설
    </button>
    <button
      v-if="this.memberstatus"
      type="button"
      class="btn btn-light Jua"
      style="background-color: #ffbf00"
      @click="getRoomId()"
    >
      채팅하기
    </button>
  </div>
</template>

<script>
import http from "@/util/index";
import http2 from "@/util/indexChat";
export default {
  data() {
    return {
      users: [],
      boardId: "",
      boardTitle: "",
      boardImg: "",
      roomId: "",
      // storeduser:{
      //   userNickname: ""
      // },
      // storedusers: [],
      memberstatus: false,
      masterCheck: 0,
    };
  },
  mounted() {
    this.getuserinfo();
  },
  computed: {
    myinfomation: function () {
      return this.$store.getters.GET_USER_INFO;
    },
    checkButtonStatus: function () {
      //채팅방 있으면 true 없으면 false
      return this.$store.getters.check_activeChatBoardList;
    },
  },
  methods: {
    gohome() {
      //   this.$router.push("/allblogs");
      this.$router.go(-1);
    },
    makeChatRoom() {
      this.boardId = this.$route.query.data;
      this.boardTitle = this.$route.query.title;
      this.boardImg = this.$route.query.boardImg;
      const chatFormData = {
        name: this.boardTitle,
        boardId: this.boardId,
        image: this.boardImg,
      };
      http2
        .post("/chatapi/room", JSON.stringify(chatFormData))
        .then((response) => {
          console.log(response);
          alert("채팅방 개설이 완료되었습니다.");
        })
        .catch((response) => {
          console.log(response);
        });
    },
    getRoomId() {
      this.boardId = this.$route.query.data;
      console.log(this.boardId);
      http2.get("/chatapi/board/" + this.boardId).then((response) => {
        console.log("룸아이디:", response);
        this.roomId = response.data;
        this.gochat(this.roomId);
      });
    },
    gochat: function (roomId) {
      var sender = this.myinfomation.userNickname;
      localStorage.setItem("wschat.sender", sender);
      localStorage.setItem("wschat.roomId", roomId);
      this.$router.push("/chat/chatroom");
      this.$router.go();
    },
    getuserinfo() {
      this.$store.dispatch("getUserInfo");
      this.boardId = this.$route.query.data;
      console.log("다른곳 boardId", this.boardId);
      http
        .get(`api/main/${this.boardId}/members`)
        .then((response) => {
          this.users = response.data;
          // console.log("여기는 users 내용",this.users);
          // console.log("여기는 유저닉네임 내용", this.users[0].userNickname);
          // console.log("여기는 로그인한 사람 이름", this.myinfomation.userNickname);
          this.membercheck();
          this.mastercheck();
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
          console.log("내가 멤버다!");
        }
      }
      console.log("여긴 created 바깥의 유저내용", this.users);
    },
    chatCaution() {
      alert("이미 채팅방이 개설되었습니다.");
    },
    mastercheck(){
      if(this.myinfomation.userNickname == this.users[0].userNickname){
        this.masterCheck = 1;
        console.log(this.masterCheck);
      }
    }
    
  },
};
</script>

<style></style>
