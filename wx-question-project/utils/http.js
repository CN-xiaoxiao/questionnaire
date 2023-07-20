// 获取应用实例
const app = getApp();
// 请求数据
const requestData = (url, method, parm)=>{
  return new Promise(function(resolve, reject) {
    // 获取token
    let token = app.globalData.token;
    if (!token) { // 全局无token
      token = wx.getStorageSync('token') ? wx.getStorageSync('token') : ''
    }
    // 发送请求
    wx.request({
      url: app.globalData.url + url, // 请求的接口地址
      data: parm,
      method: method,
      header: {
        'content-type': 'application/json',
        'token': token
      },
      success (res) {
        if (res.statusCode == 200) {
          resolve(res.data);
        } else {
          reject(res);
        }
      },
      fail: function (e) {
        e.errMsg = '网络请求失败';
        reject(e);
      }
    })
  })
}

/**
 * get请求
 * @param { } url 请求地址
 * @param {*} parm 请求参数
 */
function getData(url, parm) {
  return requestData(url, 'GET', parm);
}

/**
 * post请求
 * @param {*} url 请求地址
 * @param {*} parm 请求参数
 */
function postData(url, parm) {
  return requestData(url, 'POST', parm);
}
// 导出
module.exports = {
  GET: getData,
  POST: postData
}
