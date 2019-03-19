import Vue from 'vue'
import VueRouter from 'vue-router'
import DashboardPage from './views/DashboardPage.vue'
import EditJobMapping from './views/EditJobMapping.vue'
import LoginPage from './views/LoginPage.vue'

let routes = [
    {
        path: '/',
        component: EditJobMapping,
        meta: {title: 'Mapping'}
    },
    {
        path: '/dashboards',
        component: DashboardPage,
        meta: {title: 'DashboardPage'}
    },
    {
        path: '/login',
        name: "LoginPage",
        component: LoginPage,
    }
];

Vue.use(VueRouter);
export default new VueRouter({
    routes : routes,
    mode: 'history',
    linkActiveClass: "active",
})