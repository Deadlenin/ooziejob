import getData from "../../ajax";

export default {
    state: {
        formIsShown: false,
        hasAllRegion: false,
        availableCriteria: [],
        availableSearchValue: '',
    },
    getters: {
        formIsShown( state ){
            return state.formIsShown;
        },
        availableSearchValue( state ){
            return state.availableSearchValue
        },
        availableCriteria( state ){
            return state.availableCriteria
        },
        hasAllRegion( state ){
            return state.hasAllRegion
        },
    },
    actions: {
        getAllRegionMarker( { commit, dispatch, rootState }, searchObj ){
            commit( 'setProcessing', true );
            let value  = searchObj.searchValue || '*';
            let settings = {
                url: getBaseUrl + "uploading/criteria/searchAll?reportId="
                + rootState.uploadingSateModule.selectedReportId + "&search="+value+"&page=1&size=1",
                method: 'GET',
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
            };
            getData( settings ).then( result =>{
                if(result.content && result.content.length === 0){
                    commit( 'setHasAllRegion', true );
                }
                else{
                    commit( 'setHasAllRegion', false );
                }
                dispatch( 'getAvailableCriteria', searchObj )
            } ).catch( e =>{
                commit( 'setProcessing', false );
                console.log('при получения маркера hasAllRegion произошла ошибка');
            } );
        },
        getAvailableCriteria( { state, commit, dispatch, rootState }, searchObj ){
            let itemsOnPage = !state.hasAllRegion ? rootState.paginatorModule.itemsOnPage : rootState.paginatorModule.itemsOnPage;
            let selectedReportId = rootState.uploadingSateModule.selectedReportId;
            let page = ( typeof ( searchObj ) !== 'undefined' && ( searchObj.page !== undefined || searchObj.page === 0 ) ) ? searchObj.page : 1;

            let searchValue = '';
            if( typeof ( searchObj ) !== 'undefined' && (searchObj.searchValue || searchObj.searchValue === '')) {
                searchValue = searchObj.searchValue;
                page = 1;

                commit('setAvailableSearchValue', searchValue);
            }
            else{
                searchValue = state.availableSearchValue || '';
            }
            let settings = {
                url: getBaseUrl + "uploading/criteria/searchAvailableForReport?reportId="
                + selectedReportId + "&search="+searchValue+"&page=" + page + "&size=" + itemsOnPage,
                method: 'GET',
                dataType: 'json',
                contentType: "application/json; charset=utf-8"
            };
            commit( 'setProcessing', true );
            getData( settings ).then( result =>{
                commit( 'setProcessing', false );
                commit( 'setAvailableCriteria', result );
                commit( 'setAvailableCriteriaPaginatorParams', result );
            } ).catch( e =>{
                commit( 'setProcessing', false );

            } );
        }
    },
    mutations: {
        setFormVisible( state, payload ){
            if(payload === undefined){
                state.formIsShown = !state.formIsShown;
            }
            else{
                state.formIsShown = payload;
            }
        },
        setHasAllRegion( state, payload ){
            state.hasAllRegion = payload;
        },
        setAvailableCriteria( state, payload ){
            if(payload.totalElements < 2){
                state.searchDisabled = true;
            }
            else{
                state.searchDisabled = false;
            }
            state.availableCriteria = payload.content;
        },
        clearAvailableCriteria( state ){
            state.availableCriteria = [];
        },
        setAvailableSearchValue(state, payload){
            state.availableSearchValue = payload;
        }

    }
}
