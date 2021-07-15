import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      component: () => import( '@/views/index.vue')
    },
    {
      path: '/index',
      name: 'index',
      component: () => import( '@/views/index.vue')
    },
    {
      path: '/register',
      name: 'register',
      component: () => import( '@/views/register.vue')
    },
    {
      path: '/homePage',
      name: 'homePage',
      component: () => import( '@/views/homePage.vue')
    },
    {
      path: '/details',
      name: 'details',
      component: () => import( '@/views/details.vue')
    },
    {
      path: '/cart',
      name: 'cart',
      component: () => import( '@/views/cart.vue')
    },
    {
      path: '/address',
      name: 'address',
      component: () => import( '@/views/address.vue')
    },
    {
      path: '/downOrder',
      name: 'downOrder',
      component: () => import( '@/views/downOrder.vue')
    },
    {
      path: '/order',
      name: 'order',
      component: () => import( '@/views/order.vue')
    },
  ]
})
