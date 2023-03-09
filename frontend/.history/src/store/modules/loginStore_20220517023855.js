import http from '../../util/index';
import axios from 'axios';

const loginStore = {
	namespaced: false,
  state: {
    jwtToken: '',
    
    
	},
  getters: {
    isLogin(state) {
      return state.jwtToken == '' ? false : true;
    },
    token: state => state.jwtToken,
	},
  mutations: {
    SET_JWT_TOKEN: (state, payload) => {
      state.jwtToken = payload
  },
  SET_USER_INFO: (state, payload) => {
    state.userInfo.userEmail = payload.userEmail;
  },
  reset(state) {
    state.userInfo = {};
      state.jwtToken = "";
  }
	},
  actions: {
    async doLogin({ commit }, code) {
      let result = false;
      let resultErr = null;
      try {
          let res = await http.post('/api/accounts/signup?' +"code=" + code);
          if (res.status === 200) {
            console.log("로그인되었습니다.");
            
            console.log(res);
            commit('SET_JWT_TOKEN', res.data.token);
            //헤더에 기본적으로 추가!!!! 헤더의 키는 Access-Token 이다.
            // axios.defaults.headers.common['Authorization'] = res.data.token;
            http.defaults.headers.common['Authorization'] = "Bearer " + res.data.token;
            http.defaults.withCredentials = false;

            
            result = true;
          } else {
              alert("로그인되지 않았습니다.");
              let err = new Error("Request failed with status code 401");
              err.response = {data:{"success":false, "errormessage":"로그인되지 않았습니다."}};
              resultErr = err;
          }
      } catch(err) {
          console.log(err);
          if (!err.response) {
            err.response = { data: { "success": false, "errormessage": err.message } };
            
        }
        alert("로그인 에러 발생");
          resultErr = err;
      }
      return new Promise((resolve, reject) => {
          if (result) {
              resolve();
          } else {
              reject(resultErr);
          }
      });
  },
  // 로그아웃합니다.
  doLogout({commit}) {
    commit('reset');
    window.localStorage.removeItem('vuex');
    //헤더에서 엑세스 토큰 삭제
    delete axios.defaults.headers.common['Access-Token'];
  },
	}
};

export default loginStore;