// vue.config.js
module.exports = {
  baseUrl: '/static/main/',
  outputDir: '../resources/static/main/',
  pages: {
    index: {
      entry: 'src/main.ts',
      template: 'public/index.html',
      filename: '../../templates/home.html'
    }
  }
}