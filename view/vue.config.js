module.exports = {
    devServer: {
        proxy: {
            '/dev': {
                target: '',
                changeOrigin: true,
                ws: true,
                pathRewrite: {
                    '^/dev': ''
                }
            }
        }
    }
}