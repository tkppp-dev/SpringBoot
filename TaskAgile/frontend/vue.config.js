const path = require('path')

module.exports = {
  outputDir: path.resolve(__dirname, '../backend/src/main/resources/static'),
  devServer: {
    port: 3000,
    proxy: {
      '/api/*': 'http://localhost:8080'
    }
  }
}