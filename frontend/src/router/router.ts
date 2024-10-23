import {createRouter, createWebHistory, NavigationGuardNext, RouteLocationNormalized, RouteRecordRaw} from 'vue-router';
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
        meta: {requiresAuth: true}
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

router.beforeEach((to: RouteLocationNormalized, _from: RouteLocationNormalized, next: NavigationGuardNext) => {
    const isAuthenticated = !!getCookie('JSESSIONID');
    const needAuth = to.matched.some((record: { meta: { requiresAuth?: boolean } }) => record.meta.requiresAuth)

    if (needAuth) {
        if (!isAuthenticated) {
            next({
                name: 'Login',
                query: {redirect: to.fullPath}
            });
        } else {
            next();
        }
    } else {
        if (to.name == 'Login' && isAuthenticated) {
            next({
                name: 'Admin'
            });
        }

        next();
    }
});

function getCookie(name: string): string | null {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);

    if (parts.length === 2) {
        const cookiePart = parts.pop();
        return cookiePart ? cookiePart.split(';').shift() || null : null;
    }

    return null;
}

export default router;
