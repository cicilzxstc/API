import { createApp } from 'vue'
import {Button, Icon, NavBar} from 'vant';
import App from './App.vue'
import * as VueRouter from 'vue-router';
import routes from "./config/route.ts";
import '../global.css'


const app = createApp(App);
app.use(Button);
app.use(NavBar);
app.use(Icon);

const router = VueRouter.createRouter({
    history: VueRouter.createWebHistory(),
    routes,
});

app.use(router);
app.mount('#app')

