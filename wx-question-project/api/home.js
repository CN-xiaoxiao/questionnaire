// 引入http
const http = require('../utils/http.js');
// 获取列表
export async function getHomeListApi(parm) {
  return await http.GET("/wxapi/home/getList", parm);
}
// 查询问卷详情表
export async function getDetailsApi(parm) {
  return await http.GET("/wxapi/home/getDetails", parm);
}
// 根据问卷id查询试题列表
export async function getPaperListApi(parm) {
  return await http.GET("/wxapi/home/getPaperList", parm)
}
// 问卷提交
export async function saveCommitApi(parm) {
  return await http.POST("/wxapi/home/saveCommit", parm)
}
// 我的页面列表
export async function getMyQuestonListApi(parm) {
  return await http.GET("/wxapi/home/getMyQuestionList", parm)
}

// 回答试题答案回显
export async function getMyPaperListShowApi(parm) {
  return await http.GET("/wxapi/home/getMyPaperListShow", parm)
}