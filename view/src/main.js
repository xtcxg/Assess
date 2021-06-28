import { createApp } from 'vue'
import ElementPlus from 'element-plus';
import 'element-plus/lib/theme-chalk/index.css';
import App from './App.vue';
import axios from "axios"

const app = createApp(App)
app.use(ElementPlus)
app.config.globalProperties.$axios = axios;
app.mount('#app')

/** 全局配置 **/
const host = "http://127.0.0.1:777";
app.config.globalProperties.host = host;
app.config.globalProperties.addrs = {
    product:{
        selectAll: host + "/product/select-all"
    }
}