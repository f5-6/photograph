import {createRouter, createWebHistory, RouteRecordRaw} from 'vue-router';
import Admin from '../views/Admin.vue';
import Login from '../views/Login.vue';

const routes: Array<RouteRecordRaw> = [
    {
        path: '/login',
        name: 'Login',
        component: Login
    },
    {
        path: '/admin',
        name: 'Admin',
        component: Admin,
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
