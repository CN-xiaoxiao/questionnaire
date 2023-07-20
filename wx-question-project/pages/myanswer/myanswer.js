// pages/myanswer/myanswer.js
const app = getApp();
import {
  getMyPaperListShowApi
} from '../../api/home.js'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    questionId: '',
    // 试题数据列表
    paperList: [],
    // 表单提交的数据
    answer: {
      openid: '',
      questionId: '',
      paperList: [],

    }
  },
  async getPaperList(parm){
    let that = this;
    let res = await getMyPaperListShowApi(parm);
    if (res && res.code == 200) {
      // console.log(res);
      that.setData({
        paperList: res.data.paperList
      })
      console.log(that.data.paperList)
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.setData({
      questionId: options.questionId,
    })
    let parm = {
      questionId: options.questionId,
      openid: wx.getStorageSync('openid')
    }
    this.getPaperList(parm);
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

  selectChange(e) {
    // console.log(e)
    let that = this;
    // 试题的信息和答案
    let obj = {
      questionId: '',
      paperId: '',
      paperType: '',
      paperValue: '',
    }
    obj.questionId = that.data.questionId;
    obj.paperId = e.currentTarget.dataset.paperid;
    obj.paperType = e.currentTarget.dataset.papertype;
    // 获取选中的值
    if (e.currentTarget.dataset.papertype == '2') {
      obj.paperValue = e.detail.value.join(",");
    } else {
      obj.paperValue = e.detail.value;
    }
    // 判断答卷是否存在
    let list = that.data.answer.paperList;
    if (list.length > 0) {
      for (let i = 0; i < list.length; i++) {
        if (list[i].paperId === obj.paperId && list[i].paperType === obj.paperType) {
          // 删除原来的
          list.splice(i,1);
        }
      }
      list.push(obj);
      // console.log(list)
    } else {
      list.push(obj);
    }
  },

  // 问卷提交
  async submitBtn(e) {
    this.data.answer.openid = wx.getStorageSync('openid');
    this.data.answer.questionId = this.data.questionId;
    // console.log(this.data.answer)
    let res = await saveCommitApi(this.data.answer);

    if (res && res.code == 200) {
      console.log(res);
      wx.navigateBack({
        delta: 1
      });
    } else {
      wx.showToast({
        title: '请作答',
        icon: 'error',
        duration: 2000
      })
    }
  }
})