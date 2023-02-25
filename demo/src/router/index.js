import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      components: {
        default: HomeView,
        BottomNavBar: () => import('@/components/BottomNavBar.vue'),
        TopNavBar: () => import('@/components/TopNavBar.vue'),
      } 
    },

    {
      path: '/:pathMatch(.*)*',
      name: 'NotFound',
      component: () => import('../views/NotFound.vue')
    },

    {
      path: '/cart',
      name: 'cart',
      components: {
        default: () => import('../views/Cart.vue'),
        BottomNavBar: () => import('@/components/BottomNavBar.vue'),
        TopNavBar: () => import('@/components/TopNavBar.vue'),
      }
    },

    {
      path: '/account',
      name: 'account',
      components: {
        default: () => import('../views/Account.vue'),
        BottomNavBar: () => import('@/components/BottomNavBar.vue'),
        TopNavBar: () => import('@/components/TopNavBar.vue'),
      }
    },

    {
      path: '/login',
      name: 'login',
      component: () => import('../views/login/Login.vue'),
    },
    
    {
      path: '/signup',
      name: 'signup',
      component: () => import('../views/signup/SignUp.vue'),
    },
    
    {
      path: '/user',
      name: 'user',
      component: () => import('../views/user/User.vue'),
    },
  ]
})

export default router
