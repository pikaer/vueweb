import Vue from 'vue'
// 导入axios
import axios from 'axios'

// 导入NProgress的js
import NProgress from 'nprogress'

// 设置请求根路径,使用环境变量
axios.defaults.baseURL = process.env.VUE_APP_BASE_URL

// axios请求拦截器
axios.interceptors.request.use(config => {
    // 显示进度条
    NProgress.start()
    return config
})

// axios响应拦截器
axios.interceptors.response.use(config => {
    // 隐藏进度条
    NProgress.done()
    return config
})

// 挂载到vue
Vue.prototype.$http = axios
