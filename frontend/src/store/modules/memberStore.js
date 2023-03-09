//이부분 아직 수정중
const memberStore = {
    state: {
        masterCheck: false,
        chatBoardIdList: [
            // 0,1,2,3,4,
        ],
    },
    getters: {
        check_master(state) {
            return state.masterCheck;
        },
        check_buttonClicked(state) {
            return state.buttonClicked;
        },
        check_activeChatBoardList: (state) => (boardId) => {
            for (var i = 0; i < state.chatBoardIdList.length; i++) {
                if (state.chatBoardIdList[i] == boardId) {//해당하는 아이디가있다면
                    return true;
                }
            }
            return false;
        },

    },
    mutations: {
        pushChatBoardIdList(state, boardId) {//활성화된 채팅보드아이디목록
            state.chatBoardIdList.push(boardId);
        }
    },
    actions: {

    }
};

export default memberStore;