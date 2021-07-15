import layout from './layout.vue'
import table from './table.vue'
import search from './search.vue'
import upload from './upload.vue'
import editor from './editor.vue'
import order from './order.vue'
const components = {
  install: function(Vue){
    Vue.component('layout',layout)
    Vue.component('z-table',table)
    Vue.component('search',search)
    Vue.component('z-upload',upload)
    Vue.component('editor',editor)
    Vue.component('z-order',order)
  }
}
export default components

