import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: () => import( '@/views/index.vue')
    },
    {
      path: '/homePage',
      name: 'homePage',
      meta: {
        breadcrumb: [
          {
            name: '首页',
            path: '/homePage'
          }
        ]
      },
      component: () => import( '@/views/homePage.vue')
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
      path: '/manager',
      name: 'manager',
      meta: {
        breadcrumb: [
          {
            name: '首页',
            path: '/homePage'
          },
          {
            name: '管理员管理',
            path: '/manager'
          }
        ]
      },
      component: () => import( '@/views/manager.vue')
    },
    {
      path: '/order',
      name: 'order',
      meta: {
        breadcrumb: [
          {
            name: '首页',
            path: '/homePage'
          },
          {
            name: '订单管理',
            path: '/order'
          }
        ]
      },
      component: () => import( '@/views/order.vue')
    },
    {
      path: '/user',
      name: 'user',
      meta: {
        breadcrumb: [
          {
            name: '首页',
            path: '/homePage'
          },
          {
            name: '用户管理',
            path: '/user'
          }
        ]
      },
      component: () => import( '@/views/user.vue')
    },
    {
      path: '/commodity',
      name: 'commodity',
      meta: {
        breadcrumb: [
          {
            name: '首页',
            path: '/homePage'
          },
          {
            name: '商品管理',
            path: '/commodity'
          }
        ]
      },
      component: () => import( '@/views/commodity.vue')
    },
    {
      path: '/editCommodity',
      name: 'editCommodity',
      meta: {
        breadcrumb: [
          {
            name: '首页',
            path: '/homePage'
          },
          {
            name: '商品管理',
            path: '/commodity'
          },
          {
            name:'编辑商品',
            path: '/editCommodity',
          }
        ]
      },
      component: () => import( '@/views/editCommodity.vue')
    },

  ]
})
