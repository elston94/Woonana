<template>
  <!-- <div class="col-md-8 Jua"> -->
  <div>
    <div class="row">
      <div class="col-md-12" id="newpost-title">
        <span>게시글 등록</span>
      </div>
    </div>
    <div class="contact-form">
      <p>함께 운동할 사람을 찾아보세요.</p>
      <form action="#" method="post" class="row">
        <div class="col-md-6">
          <label>게시글 제목</label>
          <input type="text" name="title" placeholder="제목을 입력하세요" v-model="board.title" />
        </div>
        <div class="col-md-12">
          <label>게시글 내용</label>
          <textarea
            name="content"
            placeholder="내용을 입력하세요"
            v-model="board.content"
          ></textarea>
        </div>
        <div class="col-md-6">
          <label>운동 시작 날짜</label><br />
          <input type="datetime-local" name="meetStartDate" v-model="board.meetStartDate" />
        </div>
        <div class="col-md-6">
          <label>운동 종료 날짜</label><br />
          <input type="datetime-local" name="meetEndDate" v-model="board.meetEndDate" />
        </div>
        <br />
        <div class="col-md-6">
          <br />
          <label>최대 인원 설정</label>
          <input type="number" name="maxNumber" min="1" max="6" v-model="board.maxNumber" />
        </div>
        <div class="col-md-12">
          <label style="font-family: sans; font-size: 18px">운동 종류 설정</label><br />
          <input type="radio" id="산책" name="exerciseId" value="4" v-model="board.exerciseId" />
          <label for="산책">&nbsp;산책</label><br />
          <input type="radio" id="캐치볼" name="exerciseId" value="3" v-model="board.exerciseId" />
          <label for="캐치볼">&nbsp;캐치볼</label><br />
          <input type="radio" id="탁구" name="exerciseId" value="1" v-model="board.exerciseId" />
          <label for="탁구">&nbsp;탁구</label><br />
          <input
            type="radio"
            id="배드민턴"
            name="exerciseId"
            value="2"
            v-model="board.exerciseId"
          />
          <label for="배드민턴">&nbsp;배드민턴</label><br />
        </div>
        <br />
        <div class="col-md-12">
          <br />
          <label style="font-family: sans; font-size: 18px">모집 형태</label><br />
          <input
            type="radio"
            id="신청제"
            name="신청제"
            value="0"
            v-model="board.participationOption"
          />
          <label for="신청제">&nbsp;신청제 </label><br />
          <input
            type="radio"
            id="선착순"
            name="선착순"
            value="1"
            v-model="board.participationOption"
          />
          <label for="선착순">&nbsp;선착순 </label><br />
        </div>
        <div>
          <br />
          <div class="col-md-12">
            <div class="form-group">
              <input type="text" id="postcode" v-model="postcode" placeholder="우편번호" />
              <input type="text" id="address" v-model="address" placeholder="주소" /><br />
              <input type="text" id="extraAddress" v-model="extraAddress" placeholder="상세주소" />
              <input type="text" v-model="extra" placeholder="추가사항" />
            </div>
            <button
              type="button"
              class="btn btn-light"
              style="background-color: #ff8989"
              @click="execDaumPostcode()"
            >
              주소 검색
            </button>
          </div>
        </div>
        <br />
        <ul class="footer-button-plus">
          <br />
          <input
            @change="upload"
            type="file"
            id="file"
            class="inputfile"
            ref="img"
            accept=".jpg, .png"
          />
        </ul>
        <div class="col-md-6 text-right">
          <br />
          <button
            type="button"
            class="btn btn-light"
            style="background-color: skyblue"
            @click="join()"
          >
            등록하기
          </button>
        </div>
      </form>
    </div>
    <footer-home-one />
  </div>
</template>

<script>
import http from "@/util/index";
import FooterHomeOne from "../HomeOne/FooterHomeOne.vue";
export default {
  components: {
    FooterHomeOne,
  },
  data() {
    return {
      board: {
        title: "",
        content: "",
        file: "",
        maxNumber: 0,
        meetEndDate: "",
        meetStartDate: "",
        place: "",
        participationOption: 0,
        exerciseId: 0,
      },
      postcode: "",
      address: "",
      extraAddress: "",
      extra: "",
    };
  },
  methods: {
    execDaumPostcode() {
      new window.daum.Postcode({
        oncomplete: (data) => {
          if (this.extraAddress !== "") {
            this.extraAddress = "";
          }
          if (data.userSelectedType === "R") {
            // 사용자가 도로명 주소를 선택했을 경우
            this.address = data.roadAddress;
          } else {
            // 사용자가 지번 주소를 선택했을 경우(J)
            this.address = data.jibunAddress;
          }

          // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
          if (data.userSelectedType === "R") {
            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
              this.extraAddress += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if (data.buildingName !== "" && data.apartment === "Y") {
              this.extraAddress +=
                this.extraAddress !== "" ? `, ${data.buildingName}` : data.buildingName;
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if (this.extraAddress !== "") {
              this.extraAddress = `(${this.extraAddress})`;
            }
          } else {
            this.extraAddress = "";
          }
          // 우편번호를 입력한다.
          this.postcode = data.zonecode;
        },
      }).open();
    },
    upload() {
      console.log(this.$refs);
      this.board.file = this.$refs.img.files[0];
    },
    join() {
      this.board.place = this.address + " " + this.extra;
      const formData = new FormData();
      if (this.board.file == null) {
        this.board.file = "";
      }
      formData.append("title", this.board.title);
      formData.append("content", this.board.content);
      formData.append("place", this.board.place);
      formData.append("meetStartDate", this.board.meetStartDate);
      formData.append("meetEndDate", this.board.meetEndDate);
      formData.append("maxNumber", this.board.maxNumber);
      formData.append("participationOption", this.board.participationOption);
      formData.append("exerciseId", this.board.exerciseId);
      formData.append("file", this.board.file);
      let data = localStorage.getItem("vuex");
      let parsedata = JSON.parse(data);
      let token = parsedata.loginStore.jwtToken;
      http.defaults.withCredentials = false;
      http.defaults.headers.common["Authorization"] = "Bearer " + token;
      http.defaults.headers.common["withCredentials"] = false;
      http.defaults.headers.common["Content-Type"] = "multipart/form-data";
      http
        .post(`api/main`, formData)
        .then(({ response }) => {
          let msg = "게시글 등록을 완료하였습니다.";
          console.log(response);
          alert(msg);
          this.$router.push("/news");
        })
        .catch((err) => {
          alert(err);
        });
    },
  },
};
</script>

<style>
#newpost-title {
  position: fixed;
  top: 0;
  background: #ffffff;
  box-shadow: 0px 3px 10px rgba(0, 0, 0, 0.05);
  padding: 1.8rem;
  font-size: 16px;
  font-weight: 700;
  z-index: 10;
}
.img {
  display: none;
}
</style>
