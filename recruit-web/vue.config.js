const path = require('path')

function resolve(dir) {
    return path.join(__dirname, dir)
}

function publicPath() {
    if (process.env.NODE_ENV == 'production') {
        return "././";
    } else {
        return "/";
    }
}

// vue.config.js
module.exports = {
    publicPath: publicPath(),
    configureWebpack: {
        resolve: {
            alias: {
                '@': resolve('src')
            }
        }
    },
    devServer: {
        host: "0.0.0.0", //指定使用一个 host。默认是 localhost，这里默认值即可
        port: 8081, //指定端口
        hot: true, // 开启热更新
        https: false, // 是否开启https模式
        proxy: { // 请求代理服务器
            '/resumeDelivery': { //带上api前缀的
                target: 'http://localhost:8082/resumeDelivery/', //代理目标地址
                changeOrigin: true,
                secure: false,
                pathRewrite: { // 在发出请求后将/api替换为''空值，这样不影响接口请求
                    '^/resumeDelivery': ''
                }
            }
        }
    }
}