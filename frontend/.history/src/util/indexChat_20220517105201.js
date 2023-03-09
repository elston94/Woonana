import axios from "axios";

// axios 객체 생성
export default axios.create({
  withCredentials: false,
  baseURL: "http://localhost:8081",
  // baseURL: process.env.VUE_APP_CHAT_URI,
  headers: {
    "Content-type": "application/json",
  },
});
