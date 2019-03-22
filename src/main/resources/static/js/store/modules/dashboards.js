import getData from "../../ajax";

export default {
    state: {
        dashboards: [],
        statuses: []
    },
    getters: {
        getDashboards: (state) => state.dashboards,
        getStatuses: (state) => state.statuses,
    },
    actions: {
        getStatusesForDashboardPage({commit}) {
            commit('setProcessing', true);
            let settings = {
                url: "http://localhost:8090/dashboards/",
                method: 'GET',
                responseType: 'json',
            };
            getData(settings).then(result => {
                commit('setProcessing', false);
                //commit('setReports', result);
            }).catch(() => {
                commit('setProcessing', false);
                console.log('при получении списка статусов произошла ошибка');
            });
        }
    },
    mutations: {
        setDashboards(state, payload) {
            state.dashboards = payload;
        },
        setStatuses(state, payload) {
            state.statuses = payload;
        }
    }
}