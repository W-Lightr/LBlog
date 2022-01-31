const AutoImport = require('unplugin-auto-import/webpack')
const Components = require('unplugin-vue-components/webpack')
const { ElementPlusResolver } = require('unplugin-vue-components/resolvers')
// 自动导入element组件
module.exports = {
    chainWebpack: config => {
        config.plugin('html').tap(args => {
          args[0].title = "LBlog - 乘风小栈"
          return args
        })
    },
    configureWebpack: {
        plugins: [
            AutoImport({
                resolvers: [ElementPlusResolver()],
            }),
            Components({
                resolvers: [ElementPlusResolver()],
            }),
        ]
    }
}