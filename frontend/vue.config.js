module.exports = {
    devServer: {
      proxy: process.env.VUE_APP_LOCAL_URI
    }
  }