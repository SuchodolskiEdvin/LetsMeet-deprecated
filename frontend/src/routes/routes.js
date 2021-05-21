import VueRouter from "vue-router";
import Vue from "vue";

import LoginView from "../components/LoginView";
import MainView from "../components/MainView";
Vue.use(VueRouter);


// Widoki
const routes = [
    {
        path: "/",component: LoginView
    },
    {
        path: "/app", component: MainView
    },
];

export default new VueRouter({
    mode: "history",
    routes,
});
