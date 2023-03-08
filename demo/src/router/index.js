import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/views/home/index.vue'
import VendorHome from '@/views/Vendor/home/index.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {  
      path: '/Vendor/',
      name: 'VendorHome',
      components: {
        default: VendorHome,
        Footer: () => import('@/components/Footer.vue'),
      }
    },

    {  
      path: '/',
      name: 'Home',
      components: {
        default: Home,
        Footer: () => import('@/components/Footer.vue'),
      }
    },
    
    {
      path: '/cart',
      name: 'Cart',
      components: {
        default: () => import('@/views/cart/index.vue'),
        Footer: () => import('@/components/Footer.vue'),
      }
    },

    {
      path: '/order',
      name: 'Order',
      components: {
        default: () => import('@/views/order/index.vue'),
        Footer: () => import('@/components/Footer.vue'),
      } 
    },

    {
      path: '/account',
      name: 'Account',
      components: {
        default: () => import('@/views/account/index.vue'),
        Footer: () => import('@/components/Footer.vue'),
      } 
    },

    {
      path: '/login',
      name: 'Login',
      components: {
        default: () => import('@/views/account/Login.vue'),
      } 
    },

    {
      path: '/signup',
      name: 'Signup',
      components: {
        default: () => import('@/views/account/SignUp.vue'),
      } 
    },

    {
      path: '/detail/:id',
      name: 'Detail',
      components: {
        default: () => import('@/views/detail/index.vue'),
        Header: () => import('@/components/Header.vue'),
      }
    }
    
  ]
})

export default router
