import getData from "../../ajax";

export default {
    state: {
        reports: [],
        addEditComponentsVisible: false,
        AddEditMode: null,
        jobItems: [],
        reportName: '',
        reportId: null,
        newJobItems: [{id: 1}]
    },
    getters: {
        reports(state) {
            return state.reports;
        },
        addEditComponentsVisible(state) {
            return state.addEditComponentsVisible;
        },
        jobItems: (state) => state.jobItems,
        getReportName: (state) => {
            return state.reportName
        },
        AddEditMode: (state) => {
            return state.AddEditMode
        },
        newJobItems: (state) => {
            return state.newJobItems
        }
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
            commit('setVisible', {visible: true, mode: 'edit'});
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
                console.log('при отчета произошла ошибка');
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
            let nextIndex = state.jobItems[state.jobItems.length - 1].id + 1;
            state.jobItems.push({id: nextIndex});
        },
        setJobName(state, payload) {
            let jobs = state.newJobItems;
            let id = payload.id;
            let target = jobs.find(el => el.id === id);
            if (target) {
                target.jobName = payload.value;
            }

        },
        setJobType(state, payload) {
            let jobs = state.newJobItems;
            let id = payload.id;
            let target = jobs.find(el => el.id === id);
            if (target) {
                target.jobType = payload.value;
            }

        },
        removeJob(state, payload) {
            let jobs = [];
            let id = parseInt(payload);
            if (state.AddEditMode === 'add') {
                jobs = state.newJobItems;
                jobs.splice(jobs.findIndex(el => el.id === id), 1);
                state.newJobItems = jobs;
            } else {
                jobs = state.jobItems;
                jobs.splice(jobs.findIndex(el => el.id === id), 1);
                state.jobItems = jobs;
            }


        },
        setReportName(state, payload) {
            state.reportName = payload;
        },
        addNewJob(state, payload) {
            let nextIndex = state.newJobItems[state.newJobItems.length - 1].id + 1;
            state.newJobItems.push({id: nextIndex});
        },
        setReportData(state, payload) {
            state.reportName = payload.reportName;
            state.jobItems = payload.jobs;
        },
        cleanJobs(state) {
            state.jobItems.length = 0;
            state.newJobItems = [{id: 1}];
            state.reportName = '';
            state.AddEditMode = null;
        }
    }
}
;
