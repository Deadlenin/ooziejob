import getData from "../../ajax";

export default {
    state: {
        reports: [],
        addEditComponentsVisible: false,
        AddEditMode: null,
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
        getReportName: (state) =>{
            return state.reportName
        },

        AddEditMode: (state) => state.AddEditMode
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
        getReportById({commit, dispatch}, id) {
            commit('setProcessing', true);
            let settings = {
                url: "http://localhost:8090/get?id=" + id,
                method: 'GET',
                responseType: 'json',
            };
            getData(settings).then(result => {
                console.log(result);
                commit('setProcessing', false);
                commit('setReportData', result);
            }).catch(e => {
                commit('setProcessing', false);
                console.log('при получении списка репортов произошла ошибка');
            });
        },
        setAddEditComponentsVisible({commit}, settings) {
            commit('setVisible', settings);
        }
    },
    mutations: {
        setReports(state, payload) {
            state.reports = payload;
        },
        setVisible(state, payload) {
            state.addEditComponentsVisible = payload.visible;
            state.AddEditMode = payload.mode;
        },
        addJob(state, payload) {
            state.jobItems.push(payload);
        },
        addReportName(state, payload) {
            state.reportName = payload;
        },
        setReportData(state, payload) {
            state.reportName = payload.reportName;
            state.jobItems = payload.jobs;
            state.addEditComponentsVisible = true;
            state.AddEditMode = 'edit';
        }
        ,
        cleanJobs(state) {
            state.jobItems.length = 0;
            state.reportName = '';
            state.AddEditMode = null;
        }
    }
}
;
