import VueRouter from "vue-router";
import Vue from "vue";

import LoginView from "../components/LoginView";
import MainView from "../components/MainView";
import RegisterView from "../components/RegisterView";
Vue.use(VueRouter);


// Widoki
const routes = [
    {
        path: "/",component: LoginView
    },
    {
        path: "/app", component: MainView
    },
    {
        path: "/register", component: RegisterView
    }
];

export default new VueRouter({
    mode: "history",
    routes,
});
