// pages/home/home.js

// 引入api
import { getHomeListApi } from '../../api/home.js'

Page({

  /**
   * 页面的初始数据
   */
  data: {
    // 列表的数据
    tableList: [],
    parms: {
      currentPage: 1, // 从第几页开始
      pageSize: 10, // 每页查询的条数
    },
    total: 0, // 总条数
  },

  /**
   * 获取列表
   */
  async getHomeListApi() {
    let that = this;
    let res = await getHomeListApi(that.data.parms);
    if (res && res.code == 200) {
      // console.log(res);
      // 返回成功，把值赋值到data中
      // 非响应式
      // that.data.tableList = res.data.list;
      // that.data.total = res.data.total;
      // console.log(that.data);
      that.setData({
        total: res.data.total,
        tableList: that.data.tableList.concat(res.data.list)
      })
      // console.log(that.data);
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    // 获取数据列表
    this.getHomeListApi();
    // 数据加载完，停止下拉刷新
    wx.stopPullDownRefresh()
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
    // 从第一页开始加载，把原来列表里面的数据清空
    this.data.parms.currentPage = 1;
    this.setData({
        tableList: [],
        total: 0
      })
      // 重新加载列表
      this.onLoad();
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {
    // 判断是否还有下一页，如果有，继续加载，没有，停止加载，信息提示
    let that = this;
    // 总页数
    let totalPage = Math.ceil(that.data.total / that.data.parms.pageSize);
    if (that.data.parms.currentPage >= totalPage) { // 最后一页
      // 信息提示
      wx.showToast({
        title: '没有更多数据了',
      })
    } else {
      that.setData({
        currentPage: that.data.parms.currentPage++,
      })
      // 重新加载数据
      that.onLoad();
    }
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  },

  gotoDetail:function(e) {
    //  console.log(e)
    wx.navigateTo({
      url: '../detail/detail?questionId=' + e.currentTarget.dataset.questionid,
    })
  }
})