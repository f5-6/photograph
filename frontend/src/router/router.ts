import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router';
import Home from '../views/Home.vue';
import Test from '../views/Test.vue';
import AuthSuccess from '../views/AuthSuccess.vue';

const routes: Array<RouteRecordRaw> = [
    {
        path: '/',
        name: 'Home',
        component: Home
    },
    {
        path: '/test',
        name: 'test',
        component: Test
    },
    {
        path: '/auth/redirect',
        name: 'auth-redirect',
        component: AuthSuccess
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;