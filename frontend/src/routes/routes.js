import VueRouter from "vue-router";
import Vue from "vue";

import LoginView from "../components/LoginView";
import MainView from "../components/MainView";
import RegisterView from "../components/RegisterView";
import EditProfile from "@/components/EditProfile";
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
    },
    {
        path: "/edit-profile", component: EditProfile
    }
];

export default new VueRouter({
    mode: "history",
    routes,
});
