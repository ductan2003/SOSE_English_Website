import { createStore } from 'vuex'

export default createStore({
  state: {
    auth: false
  },
  getters: {
    getUser(state) {
      return state.auth
    }
  },
  mutations: {
    check(state) {
      if (localStorage.getItem("username")) {
        state.auth = true
      } else state.auth = false
    }
  },
  actions: {
    auth (context) {
      context.commit('check')
    }
  },
  modules: {
  }
})
