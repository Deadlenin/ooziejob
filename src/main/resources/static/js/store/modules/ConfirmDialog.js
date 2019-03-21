import getData from "../../ajax";

export default {
    state: {
        okFunction:'',
        confirmMessgeText:'',
        confirmDialogVisible:false,

    },
    getters: {
        okFunction : (state) => state.okFunction,
        confirmMessgeText : (state) => state.confirmMessgeText,
        //getConfirmDialogVisible : (state) => state.confirmDialogVisible,
    },
    actions:{
        deleteReport({commit, dispatch, rootState}, id) {
            commit('setProcessing', true);
            //let id = rootState.ConfirmDialog && rootState.ConfirmDialog.reportToDelete;
            if(id) {
                let settings = {
                    url: "http://localhost:8090/save?id=" + id,
                    method: 'POST',
                    //responseType: 'json',
                };
                getData(settings).then(result => {
                    commit('setProcessing', false);
                    dispatch('getReports');

                }).catch(e => {
                    commit('setProcessing', false);
                    console.log('при удалении отчета произошла ошибка');
                });
            }
        },
    },
    mutations: {
        cleanDialogData(state, payload){
            state.okFunction = '';
            state.confirmMessgeText = '';
            state.confirmDialogVisible = false;
        },
        setConfirmDialogVisible(state, payload){
            state.confirmDialogVisible = payload;
        },
        cleanError(state){
            state.error = null;
        }

    }
}