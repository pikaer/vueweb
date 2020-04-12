const prodPlugins = []
// 如果是production模式，添加插件
if (process.env.NODE_ENV === 'production') {
  prodPlugins.push('transform-remove-console')
}



module.exports = {
  presets: [
    '@vue/cli-plugin-babel/preset'
  ],
  plugins: [
    // 发布的时候用到的插件数组
    // ... 代表展开运算符，表示把数组展开，放到另一个数组中
    ...prodPlugins,
    // 路由懒加载
    '@babel/plugin-syntax-dynamic-import'
  ]
}
