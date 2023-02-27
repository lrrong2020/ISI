import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/home/index.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('../views/home/index.vue')
    },
    
    {
      path: '/cart',
      name: 'cart',
      component: () => import('../views/cart/index.vue')
    },
    {
      path: '/order',
      name: 'order',
      component: () => import('../views/order/index.vue')
    },
    {
      path: '/account',
      name: 'account',
      component: () => import('../views/account/index.vue')
    },
    
  ]
})

export default router
