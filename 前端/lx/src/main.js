// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import store from './store'
import less from 'less'
Vue.use(less)
Vue.use(ElementUI);
import components from '@/components'
Vue.use(components);
Vue.config.productionTip = false
import {post} from '@/request/axios'

Vue.prototype.$post=post;
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})

