<template>
  <div class="single-post-area Jua">
    <blockquote>
      <h4 class="article-title Jua">{{ board.title }}</h4>
      <cite v-if="board.participationOption == 0">모집형태 => 신청제</cite>
      <cite v-if="board.participationOption == 1">모집형태 => 선착순</cite>
      <p>작성자 : {{ board.userNickname }}</p>
      <cite>현재 인원 : ({{ board.allowedNumber }}/{{ board.maxNumber }})</cite>
      <div class="post-thumb">
        <img :src="board.imageUrl" alt="" />
      </div>
      <p>내용 : {{ board.content }}</p>
      <div class="post-tags">
        <h5 class="Jua">운동종목 =></h5>
        <a v-if="board.exerciseId == 1">#탁구</a>
        <a v-if="board.exerciseId == 2">#배드민턴</a>
        <a v-if="board.exerciseId == 3">#캐치볼</a>
        <a v-if="board.exerciseId == 4">#산책</a>
      </div>
      <hr />
      <div>
        <h5 class="Jua">만남 장소</h5>
        <a>{{ board.place }} </a>
      </div>
      <div>
        <h5 class="Jua">만날 시간</h5>
        <a>
          {{ board.meetStartDate.slice(0, 10) }}
          {{ board.meetStartDate.slice(11, 16) }}
        </a>
      </div>
      <div>
        <h5 class="Jua">헤어질 시간</h5>
        <a>
          {{ board.meetEndDate.slice(0, 10) }}
          {{ board.meetEndDate.slice(11, 16) }}
        </a>
      </div>
      <hr />
      <h5 class="Jua">참여</h5>
      <a v-if="this.board.userNickname == myinfomation.userNickname"
        >방장은 신청하기 버튼이 비활성화 됩니다.</a
      >
      <div
        v-if="
          this.board.userNickname != myinfomation.userNickname &&
          this.board.allowedNumber < this.board.maxNumber
        "
        class="post-tags"
      >
        <button
          @click="join()"
          class="btn btn-light Jua"
          style="background-color: #be81f7"
        >
          신청하기
        </button>
      </div>
      <hr />
      <h5 class="Jua">멤버목록</h5>
      <div class="post-tags">
        <button
          v-if="this.board.userNickname == myinfomation.userNickname"
          @click="viewParticipatedMember()"
          class="btn btn-light Jua"
          style="background-color: #2e9afe"
        >
          신청자 조회
        </button>
        <button
          @click="viewMember()"
          class="btn btn-light Jua"
          style="background-color: #ffbf00"
        >
          멤버조회
        </button>
      </div>
      <hr />
      <h5 class="Jua">게시글 삭제</h5>
      <a v-if="this.board.userNickname != myinfomation.userNickname"
        >글 작성자만 삭제할 수 있습니다.</a
      >
      <div
        v-if="this.board.userNickname == myinfomation.userNickname"
        class="post-tags"
      >
        <br />
        <button
          class="btn btn-light Jua"
          @click="blogdelete()"
          style="background-color: #ff0000"
        >
          삭제하기
        </button>
      </div>
    </blockquote>
  </div>
</template>

<script>
import http from "@/util/index";
import http2 from "@/util/indexChat";
export default {
  data() {
    return {
      board: {},
      boardId: "",
    };
  },
  watch() {
    this.checkStatus();
  },
  mounted() {
    this.$store.dispatch("getUserInfo");
    this.boardId = this.$route.query.data;
    // console.log("여긴 게시글번호", this.boardId);
    http.get(`/api/main/${this.boardId}`).then((response) => {
      this.board = response.data;
      // console.log("여긴 상세내용 : ", this.board);
    });
  },
  computed: {
    myinfomation: function () {
      return this.$store.getters.GET_USER_INFO;
    },
  },
  methods: {
    join() {
      // console.log(this.boardId);
      http
        .post(`/api/participate/${this.boardId}`)
        .then((response) => {
          console.log("조인눌렀다", response);
          let msg = "신청이 완료되었습니다.";
          alert(msg);
          this.$router.go();
        })
        .catch((err) => {
          let msg =
            "이미 신청하셨거나, 신청이 불가한 상태입니다. 다시 확인해 주세요";
          console.log(err);
          alert(msg);
        });
    },
    blogdelete() {
      http2.patch(`/chatapi/room/${this.boardId}`);
      http.delete(`/api/main/${this.boardId}`).then((response) => {
        console.log(response);
        let msg = "삭제가 완료되었습니다.";
        alert(msg);
        this.$router.push("/allblogs");
      });
    },
    checkStatus() {
      if (this.board.allowedNumber == this.board.maxNumber) {
        this.board.status = "closed";
      }
    },
    viewMember: function () {
      this.$router.push({
        name: "MemberList",
        query: {
          data: this.boardId,
          title: this.board.title,
          boardImg: this.board.imageUrl,
        },
      });
    },
    viewParticipatedMember: function () {
      this.$router.push({
        name: "WaitMemberList",
        query: { data: this.boardId },
      });
    },
  },
};
</script>

<style></style>
