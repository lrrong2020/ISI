import axios from 'axios';

//localhost cannot run on android studio. 127.0.0.1 can run
const API_HOST_ANDROID_RUNNABLE = "http://127.0.0.1:8080";

export default {
  namespaced: true,
  state: {
    user: [],
    currentUser: {},
  },
  mutations: {
    setUser(state, data) {
      state.user = data;
      state.currentUser = data[data.length - 1];
      // console.log(state.user);
    },
    setCurrentUser(state, data) {
      state.currentUser = data;
    }

  },
  actions: {
    getUser(context) {
      axios.get(`${API_HOST_ANDROID_RUNNABLE}/customer/all`)
        .then((response) => {
          context.commit("setUser", response.data);
        })
        .catch((error) => {
          console.log(error);
        })
    },
    deleteUser(context, data) {
      axios.post(`${API_HOST_ANDROID_RUNNABLE}/customer/${data}/delete`)
        .then((response) => {
          context.dispatch("getUser", response.data);
        })
        .catch((error) => {
          console.log(error);
        })
    },
    updateUser(context, data) {
      axios.post(`${API_HOST_ANDROID_RUNNABLE}/customer/${data}`)
        .then((response) => {
          context.dispatch("getUser", response.data);
        })
        .catch((error) => {
          console.log(error);
        })
    },
    login(context, data) {
      axios.post(`${API_HOST_ANDROID_RUNNABLE}/customer/login`, data)
        .then((response) => {
          context.dispatch("getUser", response.data);
        })
        .catch((error) => {
          console.log(error);
        })
    },
    signup(context, data) {
      axios.post(`${API_HOST_ANDROID_RUNNABLE}/customer/add`, data)
        .then((response) => {
          context.dispatch("getUser", response.data);
        })
        .catch((error) => {
          console.log(error);
        })
    },
  },
  getters: {

  },
}