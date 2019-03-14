import Vue from 'vue'
import Vuex from 'vuex'
import commonModule from './modules/common'
import mappingModule from './modules/mapping'

Vue.use(Vuex);

export default new Vuex.Store({
    modules: {
        commonModule,
        mappingModule
    }
})