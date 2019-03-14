export default {
    state: {
        processing:false,
        error:null,
        confirmDialogVisible:false
    },
    getters: {
        getProcessing : (state) => state.processing,
        getError : (state) => state.error,
        getConfirmDialogVisible : (state) => state.confirmDialogVisible,
    },
    mutations: {
        setProcessing(state, payload){
            state.processing = payload;
        },
        getConfirmDialogVisible(state, payload){
            state.confirmDialogVisible = payload;
        },
        setError(state, payload){
            state.error = payload;
        },
        cleanError(state){
            state.error = null;
        }

    }
}