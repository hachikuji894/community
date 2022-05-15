import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'

const routes: Array<RouteRecordRaw> = [
    {
        path: '/',
        redirect: '/home'
    },
    {
        path: '/home',
        name: 'home',
        component: () => import('@/views/pages/Home.vue'),
    },
    {
        path: '/letter',
        name: 'letter',
        component: () => import('@/views/pages/Letter.vue'),
    },
    {
        path: '/register',
        name: 'register',
        component: () => import('@/views/pages/Register.vue'),
    },
    {
        path: '/login',
        name: 'login',
        component: () => import('@/views/pages/Login.vue'),
    },
    {
        path: '/tip/id/:id',
        name: 'tip',
        component: () => import('@/views/pages/Activation.vue'),
    }, 
    {
        path: '/activation/id/:id/activationCode/:activationCode',
        name: 'activation',
        component: () => import('@/views/pages/Activation.vue'),
    },

]

export const router = createRouter({

    history: createWebHashHistory(),
    routes,
})

