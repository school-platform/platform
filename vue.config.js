module.exports = {
    lintOnSave: false,
    devServer: {
        host: '127.0.0.1',
        port: 8081,
        proxy: {
            '/api': {
                target: 'http://127.0.0.1:8080',
                changeOrigin: true,
                pathRewrite: {
                    '^/api': ''
                }
            }
        }
    }
}