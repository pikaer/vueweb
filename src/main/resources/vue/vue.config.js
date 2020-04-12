// 如果有报错，检查一下是不是空格问题导致的
module.exports = {
    // 设置打包后输出的目录
    outputDir: '../static',
    devServer: {
        // 设置自动打开浏览器
        open: true,
        // 端口号
        port: 9998
    },
    // 打包配置
    chainWebpack: config => {
    // 开发模式
    config.when(process.env.NODE_ENV === 'development', config => {
        config.entry('app').clear().add('./src/main-dev.js')

        // 首页内容定制
        config.plugin('html').tap(args => {
            // 为args增加一个参数
            args[0].isProd = false
            return args
        })
})
// 发布模式
config.when(process.env.NODE_ENV === 'production', config => {
    config.entry('app').clear().add('./src/main-prod.js')

    // 设置加载外部CDN资源，格式-外部资源名：自定义名称
    // eg：import NProgress from 'nprogress'    定义为nprogress: 'NProgress'
    config.set('externals', {
        vue: 'Vue',
        vuex: 'Vuex',
        // 有中划线，所以加了单引号
        'vue-router': 'VueRouter',
        // v-charts不知道为啥要反过来写
        VCharts: 'v-charts',
        axios: 'axios',
        nprogress: 'NProgress'
    })

    // 首页内容定制
    config.plugin('html').tap(args => {
        // 为args增加一个参数
        args[0].isProd = true
        return args
    })
})
}
}
