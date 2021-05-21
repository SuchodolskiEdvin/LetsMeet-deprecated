import Vue from 'vue';
import App from './App.vue';
import router from "./routes/routes.js";
import store from "./store/store.js";
import axios from "axios";
import Vuetify from "vuetify";
import {vuetify} from "./plugins/vuetify";
import "material-design-icons-iconfont/dist/material-design-icons.css";

Vue.use(Vuetify);

let api = axios.create({
  baseURL: process.env.VUE_APP_API_URL,
});

axios.interceptors.request.use((config) => {
  if (localStorage.getItem("access_token") !== null) {
    config.headers.Authorization = "Bearer " + localStorage.getItem("access_token");
  }
  return config;
}, (error) => {
  return Promise.reject(error);
});

Vue.prototype.$api = api;

new Vue({
  router,
  store,
  vuetify: vuetify,
  api,
  render: h => h(App),
}).$mount('#app')