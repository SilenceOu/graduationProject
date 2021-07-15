import layout from './layout.vue'
import table from './table.vue'
import search from './search.vue'
import upload from './upload.vue'
import order from './order.vue'
const components = {
  install: function(Vue){
    Vue.component('layout',layout)
    Vue.component('z-table',table)
    Vue.component('search',search)
    Vue.component('z-order',order)
    Vue.component('z-upload',upload)
  }
}

export default components

