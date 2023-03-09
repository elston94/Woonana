<template>
  <div>
      <div class="single-post-area Jua">
        <!-- <blockquote v-for="user in users" :key="user.id">
            <p>{{user.userNickname}}</p>
            <p>{{user.userSex}}</p>
            <cite>{{user.userNickname}}님의 날씨 : </cite>
        </blockquote> -->
        <h4 class="article-title Jua">신청자 목록</h4>
        <h5 v-if="this.users.length == 0" class="article-content Jua">신청자 목록이 없습니다.</h5>
        <div class="post-admin" v-for="user in users" :key="user.userId">
            <div>
            <img
            v-bind:src="user.profileUrl"
            >
            <a>이름 : {{user.userNickname}}</a>
            <p>
               {{user.userNickname}}님의 날씨 : 
               <i v-if="user.userRatingScore <= 1" class="fas fa-bolt"></i>
               <i v-if="user.userRatingScore <= 2 && user.userRatingScore > 1" class="fas fa-cloud-rain"></i>
               <i v-if="user.userRatingScore <= 3 && user.userRatingScore > 2" class="fas fa-cloud"></i>
               <i v-if="user.userRatingScore <= 4 && user.userRatingScore > 3" class="fas fa-cloud-sun"></i>
               <i v-if="user.userRatingScore <= 5 && user.userRatingScore > 4" class="fas fa-sun"></i>
            </p>
            <button type="button" class="btn btn-light Jua" style="background-color: #2E9AFE" @click="approve(user.participationId)">승인</button>
            <button type="button" class="btn btn-light Jua" style="background-color: #FF0000" @click="refuse(user.participationId)">거절</button>
            </div>
        </div>
      </div>
      <button type="button" class="btn btn-light Jua" style="background-color: #FF0000" @click="goback()">뒤로가기</button>
  </div>
</template>

<script>
import http from "@/util/index"
export default {
    data(){
        return{
            users:[],
            boardId: "",
        }
    },
    mounted(){
        this.boardId = this.$route.query.data;
        http.get(`/api/participate/pick/${this.boardId}`).then((response) =>{
            console.log(response.data.allAppliedUsers);
            this.users = response.data.allAppliedUsers;
            console.log("여기다",this.users);
        })
        .catch((err) => {
          alert(err);
      });
    },
    methods:{
      approve: function(data){
          console.log(data);
          http.patch(`/api/participate/pick/approve/${data}`).then((response)=>{
              console.log(response);
              let msg = "승인되었습니다.";
              alert(msg);
              this.$router.go(-1);
              
          }).catch((err) => {
              alert(err);
          })
      },
      refuse: function(data){
          http.delete(`/api/participate/pick/refuse/${data}`).then((response)=>{
              console.log(response);
              this.$router.go(-1);
          }).catch((err)=>{
              alert(err);
          })
      },
      goback(){
        //   this.$router.push("/allblogs");
        this.$router.go(-1);
      },

    },
}
</script>

<style>

</style>