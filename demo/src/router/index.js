import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/views/home/index.vue'
import VendorHome from '@/views/Vendor/home/index.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    //Customer
    {
      path: '/',
      name: 'Home',
      components: {
        default: Home,
        Footer: () => import('@/components/Footer.vue'),
      },
      meta: {
        index: 1
      }
    },
    {
      path: '/search',
      name: 'Search',
      components: {
        default: () => import('@/views/home/SearchPage.vue'),
      },
      meta: {
        index: 2
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
        index: 1,
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
        index: 1,
        requireAuth: true
      }
    },
    {
      path: '/login',
      name: 'Login',
      components: {
        default: () => import('@/views/account/Login.vue'),
      },
      meta: {
        index: 1,
      }
    },
    {
      path: '/signup',
      name: 'Signup',
      components: {
        default: () => import('@/views/account/SignUp.vue'),
      },
      meta: {
        index: 1,
      }
    },
    {
      path: '/detail/:id',
      name: 'Detail',
      components: {
        default: () => import('@/views/detail/index.vue'),
        Header: () => import('@/components/Header.vue'),
      },
      meta: {
        index: 3,
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
        index: 1,
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
        index: 2,
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
        index: 2,
        requireAuth: true
      }
    },

    //Vendor
    {
      path: '/Vendor/',
      name: 'VendorHome',
      components: {
        default: VendorHome,
        Footer: () => import('@/components/Vendor/VendorFooter.vue'),
      }
    },
    {
      path: '/Vendor/detail/:id',
      name: 'VendorProductDetail',
      components: {
        default: () => import('@/views/Vendor/detail/index.vue'),
        Header: () => import('@/components/Vendor/VendorHeader.vue'),
      }
    },
    {
      path: '/Vendor/add',
      name: 'VendorProductAdd',
      components: {
        default: () => import('@/views/Vendor/add/addProduct.vue'),
        Header: () => import('@/components/Vendor/VendorHeader.vue'),
      }
    },
    {
      path: '/Vendor/add/success',
      name: 'VendorProductAddSuccess',
      components: {
        default: () => import('@/views/Vendor/add/addProductSuccess.vue'),
        Header: () => import('@/components/Vendor/VendorHeader.vue'),
      }
    },
    {
      path: '/Vendor/orderList',
      name: 'VendorOrderList',
      components: {
        default: () => import('@/views/Vendor/order/index.vue'),
        Footer: () => import('@/components/Vendor/VendorFooter.vue'),
      },
    },
    {
      path: '/Vendor/orderDetail/:id',
      name: 'VendorOrderDetail',
      components: {
        default: () => import('@/views/Vendor/order/VendorOrderDetail.vue'),
      },
    },


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
