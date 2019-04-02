import getData from "../../ajax";

export default {
    state: {
        reports: [],
        addEditComponentsVisible: false,
        AddEditMode: null,
        jobItems: [],
        reportName: '',
        reportId: null,
        newJobItems: [{ id: 1 }],
        reportToDelete: null
    },
    getters: {
        reports( state ){
            return state.reports;
        },
        addEditComponentsVisible( state ){
            return state.addEditComponentsVisible;
        },
        jobItems: ( state ) =>{
            return state.jobItems;
        },
        getReportName: ( state ) => {
            return state.reportName
        },
        AddEditMode: ( state ) => {
            return state.AddEditMode
        },
        newJobItems: ( state ) => {
            return state.newJobItems
        },
        reportId: state => {
            return state.reportId
        }
    },
    actions: {
        getReports( { commit } ){
            commit( 'setProcessing', true );
            let settings = {
                url: "http://localhost:8090/",
                method: 'GET',
                responseType: 'json',
            };
            getData( settings ).then( result => {
                commit( 'setProcessing', false );
                commit( 'setReports', result );
            } ).catch( e => {
                commit( 'setProcessing', false );
                console.log( 'при получении списка репортов произошла ошибка', e );
            } );
        },
        getReportById( { commit, dispatch }, id ){
            commit( 'setProcessing', true );
            commit( 'setVisible', { visible: true, mode: 'edit' } );
            let settings = {
                url: "http://localhost:8090/get?id=" + id,
                method: 'GET',
                responseType: 'json',
            };
            getData( settings ).then( result => {
                commit( 'setProcessing', false );
                commit( 'setReportData', result );
            } ).catch( e => {
                commit( 'setProcessing', false );
                console.log( 'при отчета произошла ошибка', e );
            } );
        },
        setAddEditComponentsVisible( { commit }, settings ){
            commit( 'setVisible', settings );
        },
        deleteReport( { commit, dispatch, state }, id ){
            commit( 'setProcessing', true );
            let repToDel = state.reportToDelete;
            if( repToDel !== null ){
                let settings = {
                    url: "http://localhost:8090/delete?id=" + repToDel,
                    method: 'DELETE',
                };
                getData( settings ).then( () => {
                    commit( 'setProcessing', false );
                    commit( 'setConfirmDialogVisible', false );
                    dispatch( 'getReports' );
                    
                } ).catch( e => {
                    commit( 'setProcessing', false );
                    commit( 'setConfirmDialogVisible', false );
                    console.log( 'при удалении отчета произошла ошибка', e );
                } );
            }
        },
        saveMapping( { commit, dispatch }, report){
            commit( 'setProcessing', true );
            let formData = new FormData();
            formData.append( 'reportApi', new Blob( [JSON.stringify( report )], { type: "application/json" } ) );
            let settings = {
                url: "http://localhost:8090/save",
                method: 'POST',
                responseType: 'json',
                data: formData,
            };
            getData( settings ).then( result => {
                console.log( result );
                commit( 'setProcessing', false );
                commit( 'setVisible', { visible: false, mode: 'add' } );
                dispatch('getReports');
            } ).catch( e => {
                commit( 'setProcessing', false );
                commit( 'setVisible', { visible: false, mode: 'edit' } );
                console.log( 'при создании нового маппинга произошла ошибка', e );
            } );
        },
        editMapping( { commit, dispatch }, report){
            commit( 'setProcessing', true );
            let formData = new FormData();
            formData.append( 'reportApi', new Blob( [JSON.stringify( report )], { type: "application/json" } ) );
            let settings = {
                url: "http://localhost:8090/edit",
                method: 'PUT',
                responseType: 'json',
                data: formData,
            };
            getData( settings ).then( result => {
                console.log( result );
                commit( 'setProcessing', false );
                commit( 'setVisible', { visible: false, mode: 'edit' } );
                dispatch('getReports');
            } ).catch( e => {
                commit( 'setProcessing', false );
                commit( 'setVisible', { visible: false, mode: 'edit' } );
                console.log( 'при редактировании отчета произошла ошибка', e );
            } );
        },
        cleanReportToDelete( { commit } ){
            commit( 'cleanReportToDelete' );
            commit( 'cleanDialogData' );
        },
        
    },
    mutations: {
        setReports( state, payload ){
            state.reports = payload;
        },
        setVisible( state, payload ){
            state.addEditComponentsVisible = payload.visible;
            state.AddEditMode = payload.mode;
            if(payload.visible === false){
                state.reportId = null;
            }
        },
        addJob( state ){
            let nextIndex = state.jobItems[state.jobItems.length - 1].id + 1;
            state.jobItems.push( { id: nextIndex } );
        },
        setJobName( state, payload ){
            let jobs = state.AddEditMode === 'add' ? state.newJobItems : state.jobItems;
            let id = payload.id;
            let target = jobs.find( el => el.id === id );
            if( target ){
                target.jobName = payload.value;
            }
            if(state.AddEditMode === 'add'){ // необходимо что бы пнуть пересчет на уровне addEdit компонента
                state.newJobItems = null;
                state.newJobItems = jobs;
            }
            else{
                state.jobItems = null;
                state.jobItems = jobs;
            }
            
        },
        setJobType( state, payload ){
            let jobs = state.AddEditMode === 'add' ? state.newJobItems : state.jobItems;
            let id = payload.id;
            let target = jobs.find( el => el.id === id );
            if( target ){
                target.idJobType = payload.value;
            }
            if(state.AddEditMode === 'add'){
                state.newJobItems = null;
                state.newJobItems = jobs;
            }
            else{
                state.jobItems = null;
                state.jobItems = jobs;
            }
            
        },
        removeJob( state, payload ){
            let jobs = [];
            let id = parseInt( payload );
            if( state.AddEditMode === 'add' ){
                jobs = state.newJobItems;
                let jobIndex = jobs.findIndex( el => el.id === id );
                if(!!~jobIndex) {
                    jobs.splice( jobIndex, 1 );
                    state.newJobItems = jobs;
                }
            }
            else{
                jobs = state.jobItems;
                let jobIndex = jobs.findIndex( el => el.id === id );
                if(!!~jobIndex) {
                    jobs.splice( jobIndex, 1 );
                    state.jobItems = jobs;
                }
            }
        },
        setReportName( state, payload ){
            state.reportName = payload;
        },
        addNewJob( state ){
            let nextIndex = state.newJobItems[state.newJobItems.length - 1].id + 1;
            state.newJobItems.push( { id: nextIndex } );
        },
        setReportData( state, payload ){
            state.reportId = payload.id;
            state.reportName = payload.reportName;
            state.jobItems = payload.jobs;
        },
        cleanJobs( state ){
            state.jobItems.length = 0;
            state.newJobItems = [{ id: 1 }];
            state.reportName = '';
            state.AddEditMode = null;
        },
        setReportToDelete( state, payload ){
            state.reportToDelete = payload
        },
        cleanReportToDelete( state ){
            state.reportToDelete = null;
        }
    }
}