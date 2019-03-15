import getData from "../../ajax";

export default {
    state: {
        reports: [],
        addEditComponentsVisible: true,
        jobItems: [],
        reportName: ''
    },
    getters: {
        reports(state) {
            return state.reports;
        },
        addEditComponentsVisible(state) {
            return state.addEditComponentsVisible;
        },
        jobItems: (state) => state.jobItems,
        reportName: (state) => state.reportName
    },
    actions: {
        getReports({commit, dispatch}, searchObj) {
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
        },
        setAddEditComponentsVisible({commit}, visible) {
            commit('setVisible', visible);
        }
    },
    mutations: {
        setReports(state, payload) {
            state.reports = payload;
        },
        setVisible(state, payload) {
            state.addEditComponentsVisible = payload;
        },
        addJob(state, payload) {
            state.jobItems.push(payload);
        },
        addReportName(state, payload) {
            state.reportName = payload;
        }
        ,
        cleanJobs(state) {
            state.jobItems.length = 0;
            state.reportName = '';
        }
    }
}
;
