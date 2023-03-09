import axios from "axios";

// axios 객체 생성
export default axios.create({
  withCredentials: false,
  baseURL: process.env.VUE_APP_BACK_URI,
  headers: {
    "Content-type": "application/json",
  },
});
