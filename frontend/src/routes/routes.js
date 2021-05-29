import VueRouter from "vue-router";
import Vue from "vue";

import LoginView from "../components/LoginView";
import MainView from "../components/MainView";
import RegisterView from "../components/RegisterView";
import CreateMeetView from "@/components/CreateMeetView";
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
        path: "/create-meet", component: CreateMeetView
    }
];

export default new VueRouter({
    mode: "history",
    routes,
});
