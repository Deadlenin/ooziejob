import getData from "../../ajax";

export default {
    state: {
        reports: []
    },
    getters: {
        reports(state) {
            return state.reports;
        }
    },
    actions: {
        getReports({commit, dispatch }, searchObj) {
            commit('setProcessing', true);
            let settings = {
                url: "http://localhost:8090/",
                method: 'GET',
                responseType: 'json',
            };
            getData(settings).then(result => {
                commit('setProcessing', false);
                commit('setReports', result);
            }).catch(e => {
                commit('setProcessing', false);
                console.log('при получении списка репортов произошла ошибка');
            });
        }
    },
    mutations: {
        setReports(state, payload) {
            state.reports = payload;
        }
    }
};
