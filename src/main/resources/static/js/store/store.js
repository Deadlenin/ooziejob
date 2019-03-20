import Vue from 'vue'
import Vuex from 'vuex'
import commonModule from './modules/common'
import mappingModule from './modules/mapping'
import ConfirmDialog from './modules/ConfirmDialog'

Vue.use(Vuex);

export default new Vuex.Store({
    modules: {
        commonModule,
        mappingModule,
        ConfirmDialog
    }
})