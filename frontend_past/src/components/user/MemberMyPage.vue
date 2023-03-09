<template>
  <div id="mypage">
    이름 : 김응철 나중에 별 추가
    <div id="h2">
      나와의 약속
      <div id="appointlist">박seri님 9:30PM</div>
    </div>
    <div id="h2">
      진행중인 모임
      <div id="move">바로가기</div>
    </div>
    <div id="mypageMenu">
      <ul id="nav">
        <li>내 글</li>
        <li>참여내역</li>
        <li>운동기록</li>
        <li>평가페이지</li>
        <li>고객센터</li>
      </ul>
    </div>
  </div>
</template>

<script>
export default { name: "MemberMyPage" };
</script>

<style>
#h2 {
  font-weight: bold;
}
#mypage {
  width: 90%;
  margin: auto;
  text-align: left;
}
#appointlist {
  background-color: gray;
  border-radius: 15px;
  height: 5%;
  width: 20%;
  margin: 2%;
  padding: 1%;
}
#move {
  background-color: gray;
  border-radius: 15px;
  height: 5%;
  width: 20%;
  margin: 2%;
  padding: 3%;
}

#nav li {
  display: block;
  font-weight: bold;
  padding: 3%;
  border-style: solid none;
  border-width: thin;
}
</style>
