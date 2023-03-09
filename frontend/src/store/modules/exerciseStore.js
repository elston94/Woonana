import http from '../../util/index';
// import axios from "axios";
const exerciseStore = {
  state: {
    allExercise: [],
    monthExercise: [],
    heatmapInfo: [],
  },
  getters: {
    get_month_Exercise(state) {
      return state.monthExercise;
    },
    get_all_exercise(state) {
      return state.allExercise;
    },
    GET_HEATMAP_INFO(state) {
      return state.heatmapInfo;
    },
  },
  mutations: {
    SET_ALL_EXERCISE_INFO: (state, payload) => {
      state.allExercise = payload;
    },
  },
  //해당하는 ID의 운동정보를 받아온다.
  actions: {
    async getAllExerciseInfo({ state} ,payload) {
      try {
        let data = localStorage.getItem('vuex');
        let parsedata = JSON.parse(data);
        let token = parsedata.loginStore.jwtToken;
        
        http.defaults.withCredentials = false;
        http.defaults.headers.common['Authorization'] = 'Bearer ' + token;
        http.defaults.headers.common['withCredentials'] = false;
        console.log(http.defaults.headers.common['Authorization']);
        const res = await http.get('api/accounts/myexercise/record/' + payload);
        // let token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaXNzIjoid29vbmFuYSIsImlhdCI6MTY1MjE2NDk4NywiZXhwIjoxNjUyMjUxMzg3fQ.VfPXbqMxqR2Y0rIiBfxSH3byym1lTV7QNrtSosZVteQsLDzXKxAtGY-WiY8ieibO7KkzZz6tmxZlAHaDi0IgMA";
        
        if (res.status === 200) {
          console.log('운동 정보를 가져왔습니다');
          console.log(res);
          state.allExercise = res.data;
        } else {
          console.log('운동정보 에러발생');
        }
      } catch (e) {
        console.log(e);
      }
      

      
    },
    //한달운동기록조회
    async getMonthExerciseInfo({state} ,payload) {
      try {
        let data = localStorage.getItem('vuex');
        let parsedata = JSON.parse(data);
        let token = parsedata.loginStore.jwtToken;
        
        http.defaults.withCredentials = false;
        http.defaults.headers.common['Authorization'] = 'Bearer ' + token;
        http.defaults.headers.common['withCredentials'] = false;
        console.log(http.defaults.headers.common['Authorization']);
        const res = await http.get('api/accounts/myexercise/month/' + payload);
        // let token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaXNzIjoid29vbmFuYSIsImlhdCI6MTY1MjE2NDk4NywiZXhwIjoxNjUyMjUxMzg3fQ.VfPXbqMxqR2Y0rIiBfxSH3byym1lTV7QNrtSosZVteQsLDzXKxAtGY-WiY8ieibO7KkzZz6tmxZlAHaDi0IgMA";
        
        if (res.status === 200) {
          console.log('한달운동 정보를 가져왔습니다');
          state.monthExercise = res.data;
          console.log(state.monthExercise);
        } else {
          console.log('한달운동정보 에러발생');
        }
      } catch (e) {
        console.log(e);
      }
      

      
    },
    async getHeatmapInfo({state} ,payload) {
      try {
        let data = localStorage.getItem('vuex');
        let parsedata = JSON.parse(data);
        let token = parsedata.loginStore.jwtToken;
        
        http.defaults.withCredentials = false;
        http.defaults.headers.common['Authorization'] = 'Bearer ' + token;
        http.defaults.headers.common['withCredentials'] = false;
        console.log(http.defaults.headers.common['Authorization']);
        const res = await http.get('api/accounts/myexercise/likes/' + payload);
        // let token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaXNzIjoid29vbmFuYSIsImlhdCI6MTY1MjE2NDk4NywiZXhwIjoxNjUyMjUxMzg3fQ.VfPXbqMxqR2Y0rIiBfxSH3byym1lTV7QNrtSosZVteQsLDzXKxAtGY-WiY8ieibO7KkzZz6tmxZlAHaDi0IgMA";
        
        if (res.status === 200) {
          console.log('히트맵운동 정보를 가져왔습니다');
          state.heatmapInfo = res.data;
          console.log(state.heatmapInfo);
        } else {
          console.log('히트맵운동정보 에러발생');
        }
      } catch (e) {
        console.log(e);
      }
      

      
    },
    

  }
  
};

export default exerciseStore;