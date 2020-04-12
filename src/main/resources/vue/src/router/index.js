import Vue from 'vue'
import VueRouter from 'vue-router'
// 登录组件
import Login from '../components/Login.vue'
// 图表组件演示
import VChartsDemo from '../components/VChartsDemo.vue'
// 路由懒加载Home组件
const Home = () => import(/* webpackChunkName: "home" */ '../components/Home.vue')
// 系统管理
const Users = () => import(/* webpackChunkName: "users_roles_rights" */ '../components/system/Users.vue')
const Roles = () => import(/* webpackChunkName: "users_roles_rights" */ '../components/system/Roles.vue')
const Rights = () => import(/* webpackChunkName: "users_roles_rights" */ '../components/system/Rights.vue')
const ServiceLog = () => import(/* webpackChunkName: "users_logs_rights" */ '../components/log/ServiceLog.vue')
const SystemLog = () => import(/* webpackChunkName: "users_logs_rights" */ '../components/log/SystemLog.vue')

Vue.use(VueRouter)

const routes = [
    // 根路径重定向
    { path: '/', redirect: '/home' },
    // 登录
    { path: '/login', component: Login },
    // 图表演示
    { path: '/vcharts', component: VChartsDemo },
    // Home
    {
        path: '/home',
        component: Home,
        children: [
            { path: '/users', component: Users },
            { path: '/roles', component: Roles },
            { path: '/rights', component: Rights },
            { path: '/servicelog', component: ServiceLog },
            { path: '/systemlog', component: SystemLog }
        ]
    }
]

const router = new VueRouter({
    routes
})

// 挂载路由导航守卫
router.beforeEach((to, from, next) => {
    // to 代表将要访问的路径
    // from 代表从哪个路径跳转而来
    // next 是一个函数，表示放行 1.next()表示放行 2.next('/login')表示强制跳转到指定路径
    // 访问登录页面，直接放行
    if (to.path === '/login') {
        return next()
    }

    // todo 自定义校验逻辑
    return next()
})

export default router
