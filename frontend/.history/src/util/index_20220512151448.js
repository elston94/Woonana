import axios from "axios";

// axios 객체 생성
export default axios.create({
  withCredentials: false,
  baseURL: "http://localhost:8082",
  // baseURL: process.env.VUE_APP_BACK_URI,
  headers: {
    "Content-type": "application/json",
  },
});
/*
const base_url = "http://localhost:8081";
const login = base_url + '/api/accounts/signup?';
export default {base_url, login};*/
