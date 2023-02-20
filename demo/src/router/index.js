import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Brazil from '../views/Brazil.vue'
import Hawaii from '../views/Hawaii.vue'
import Panama from '../views/Panama.vue'
import Jamaica from '../views/Jamaica.vue'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AboutView.vue')
    },
    {
      path: '/brazil',
      name: 'brazil',
      component: Brazil
    },
    {
      path: '/hawaii',
      name: 'hawaii',
      component: Hawaii
    },
    {
      path: '/panama',
      name: 'panama',
      component: Panama
    },
    {
      path: '/jamaica',
      name: 'jamaica',
      component: Jamaica
    },
  ]
})

export default router
