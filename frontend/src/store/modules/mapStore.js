import http from "@/util/index";

const mapStore = {
    state: {
        places: [],
        postids: [],
        selectPlace: null,
    },
    getters: {
        get_place(state) {
            return state.places;
        },
        get_postids(state) {
            return state.postids;
        }
    },
    mutations: {
        SET_PLACE_LIST(state, places) {
            state.places = [];
            places.forEach((place) => {
                state.places.push(place.place);
            });
            // console.log(state.places);
        },
        SET_POSTID_LIST(state, payload) {
            state.postids = [];
            payload.forEach((ele) => {
                state.postids.push(ele.boardId);
            });
            // console.log(state.postids);
        },
        SET_SELECTPLACE(state, selectPlace) {
            state.selectPlace = selectPlace;
        },
        CLEAN_PLACE_LIST(state) {
            state.places = [];
        }
    },
    actions: {
        
        getPlace({ commit }) {
            let data = localStorage.getItem("vuex");
            let parsedata = JSON.parse(data);
            let token = parsedata.loginStore.jwtToken;

            http.defaults.withCredentials = false;
            http.defaults.headers.common["Authorization"] = "Bearer " + token;
            http.defaults.headers.common["withCredentials"] = false;
            http.get(`api/main`).then(({ data }) => {
                
            commit("SET_PLACE_LIST", data)
            commit("SET_POSTID_LIST", data)      
            }).catch((err) => {
                console.log(err);
            });
        },
        getSelectPlace({ commit }, selectPlace) {
            commit("SET_SELECTPLACE", selectPlace);
        },
        
        }
}

export default mapStore;