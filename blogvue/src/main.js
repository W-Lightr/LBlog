import { createApp } from 'vue'
import App from './App.vue'
import store from './store/index.js'
import './assets/css/index.less'
import 'font-awesome/css/font-awesome.min.css'
import router from './router'
import http from './axios/index.js'



let token = window.localStorage.getItem("token")

if (token != null) {
    http.http.get('/user/check').then((response) => {
        if (response.data.code != 200) {
            window.localStorage.removeItem("loginState");
            window.localStorage.removeItem("token");
            window.localStorage.removeItem("name");
            window.localStorage.removeItem("userId");
            
        }else{
            store.commit("changeLogState",true);
        }
    })
}

const app = createApp(App)
app.use(router)
app.use(store)
app.mount('#app')

app.provide('http', http)


