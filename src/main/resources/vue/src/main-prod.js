import Vue from 'vue'
import App from './App.vue'
// 路由
import router from './router'
// vuex
import store from './store'
// axios
import './dependences/axios.js'
// v-charts
import './dependences/vcharts.js'
// 导入全局样式表
import './assets/css/global.css'

Vue.config.productionTip = false

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')
