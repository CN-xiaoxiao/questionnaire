// pages/detail/detail.js

const app = getApp();
// 引入api
import {
  getDetailsApi
} from '../../api/home.js'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    questionDesc: '',
    questionId: '',
    questionImg: '',
    questionTitle: '',
    status: ''
  },

  async getDetails() {
    let that = this;
    let parm = {
      questionId: that.data.questionId,
      openid: wx.getStorageSync('openid')
    }
    let res = await getDetailsApi(parm);
    // console.log(res);
    if (res && res.code == 200) {
      that.setData({
        questionDesc: res.data.questionDesc,
        questionId: res.data.questionId,
        questionImg: res.data.questionImg,
        questionTitle: res.data.questionTitle,
        status: res.data.status
      })
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    // console.log(options);
    this.setData({
      questionId: options.questionId
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {
    this.getDetails();
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  },

  gotoAnswer(e) {
    // console.log(e)
    wx.navigateTo({
      url: '../answer/answer?questionId=' + e.currentTarget.dataset.questionid,
    })
  },

  hasAnswer(e) {
    wx.showToast({
      title: '您已答卷',
      duration: 2000
    })
  }
})