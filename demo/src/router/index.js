import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/views/home/index.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      components: {
        default: Home,
        Footer: () => import('@/components/Footer.vue'),
      }
    },
    
    {
      path: '/cart',
      name: 'cart',
      components: {
        default: () => import('@/views/cart/index.vue'),
        Footer: () => import('@/components/Footer.vue'),
      }
    },

    {
      path: '/order',
      name: 'order',
      components: {
        default: () => import('@/views/order/index.vue'),
        Footer: () => import('@/components/Footer.vue'),
      } 
    },

    {
      path: '/account',
      name: 'account',
      components: {
        default: () => import('@/views/account/index.vue'),
        Footer: () => import('@/components/Footer.vue'),
      } 
    },
    {
      path: '/detail',
      name: 'detail',
      components: {
        default: () => import('@/views/detail/index.vue'),
        Header: () => import('@/components/Header.vue'),
        DetailFooter: () => import('@/components/DetailFooter.vue'),
      }
    }
    
  ]
})

export default router
