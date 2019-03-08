import Vue from 'vue'
import Router from 'vue-router'
import DashboardPage from './views/DashboardPage.vue'
import EditJobMapping from './views/EditJobMapping.vue'
import LoginPage from './views/LoginPage.vue'

Vue.use(Router);
export default new Router({
    routes : [
        {
            path: '/',
            name: "DashboardPage",
            component: DashboardPage,
        },
        {
            path: '/EditJobMapping',
            name: "EditJobMapping",
            component: EditJobMapping,
        },
        {
            path: '/LoginPage',
            name: "LoginPage",
            component: LoginPage,
        }
    ],
    mode: 'history'
})