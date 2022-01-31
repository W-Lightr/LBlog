import { createStore } from 'vuex'

export default createStore({
  state() {
    return {
      loginState: false,
    }
  },
  mutations: {
    changeLogState(state,logState){
      state.loginState = logState;
    }
  },
  actions: {
  },
  modules: {
  }
})
