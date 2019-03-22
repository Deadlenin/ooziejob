import getData from "../../ajax";

export default {
    state: {
        confirmOkFunction: '',
        confirmCloseFunction: '',
        confirmDialogVisible: false,
        confirmDialogText: false,

    },
    getters: {
        okFunction: (state) => state.okFunction,
        getConfirmDialogVisible: (state) => state.confirmDialogVisible,
        getConfirmDialogText: (state) => state.confirmDialogText,
        getConfirmOkFunction: (state) => state.confirmOkFunction,
        getConfirmCloseFunction: (state) => state.confirmCloseFunction,


    },
    actions: {},
    mutations: {
        cleanDialogData(state) {
            state.confirmOkFunction = '';
            state.confirmCloseFunction = '';
            state.confirmDialogText = '';
            state.confirmDialogVisible = false;
        },
        setConfirmDialogVisible(state, payload) {
            state.confirmDialogVisible = payload;
        },
        setConfirmDialogText(state, payload) {
            state.confirmDialogText = payload;
        },
        setConfirmOkFunction(state, payload) {
            state.confirmOkFunction = payload;
        },
        setConfirmCloseFunction(state, payload) {
            state.confirmCloseFunction = payload;
        },

        cleanError(state) {
            state.error = null;
        }

    }
}