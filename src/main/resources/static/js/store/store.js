import Vue from 'vue'
import Vuex from 'vuex'
import CommonModule from './modules/common'
import MappingModule from './modules/mapping'
import ConfirmDialog from './modules/confirmDialog'
import Dashboards from './modules/dashboards'

Vue.use(Vuex);

export default new Vuex.Store({
    modules: {
        CommonModule,
        MappingModule,
        ConfirmDialog,
        Dashboards
    }
})