import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/views/home/index.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
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
      },
      meta: {
        requireAuth: true
      }
    },

    {
      path: '/account/:id',
      name: 'Account',
      components: {
        default: () => import('@/views/account/index.vue'),
        Footer: () => import('@/components/Footer.vue'),
      },
      meta: {
        requireAuth: true
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
    },
    
    {
      path: '/orderList',
      name: 'OrderList',
      components: {
        default: () => import('@/views/order/index.vue'),
        Footer: () => import('@/components/Footer.vue'),
      },
      meta: {
        requireAuth: true
      }
    },

    {
      path: '/generateOrder',
      name: 'GenerateOrder',
      components: {
        default: () => import('@/views/order/GenerateOrder.vue'),
      },
      meta: {
        requireAuth: true
      }
    },

    {
      path: '/orderDetail/:id',
      name: 'OrderDetail',
      components: {
        default: () => import('@/views/order/OrderDetail.vue'),
      },
      meta: {
        requireAuth: true
      }
    }
    
  ]
})
router.beforeEach((to, from, next) => {
  if (to.meta.requireAuth) {
    //登录后会在localStorage中存储标识

    if (localStorage.isLogin === 'login') {
      next()
    } else {
      next('/login')
    }
  } else {
    next()
  }
})
export default router
