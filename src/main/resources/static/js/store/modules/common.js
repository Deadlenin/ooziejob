export default {
    state: {
        processing:false,
        error:null,
    },
    getters: {
        getProcessing : (state) => state.processing,
        getError : (state) => state.error,
    },
    mutations: {
        setProcessing(state, payload){
            state.processing = payload;
        },
        setConfirmDialogVisible(state, payload){
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