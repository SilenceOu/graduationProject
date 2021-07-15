import Vue from 'vue'
import Vuex from 'vuex'


Vue.use(Vuex)

const store= new Vuex.Store({
  state:{
    url:'http://localhost:8010'
  },
  mutations:{
    search(state,e){
      state.searchValue=e
    }
  },
  actions:{
    search(context,payload){
      context.commit('search',payload)
    }
  }
})


export default store
