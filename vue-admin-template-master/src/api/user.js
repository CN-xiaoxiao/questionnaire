import request from '@/utils/request'
import http from '@/utils/http'

// export function login(data) {
//   return request({
//     url: '/vue-admin-template/user/login',
//     method: 'post',
//     data
//   })
// }
export async function login(parm) {
  return http.post("/api/user/login", parm)
}

// export function getInfo(token) {
//   return request({
//     url: '/vue-admin-template/user/info',
//     method: 'get',
//     params: { token }
//   })
// }
export function getInfo(token) {
  return http.get("/api/user/getInfo", { userId: token })
}

// export function logout() {
//   return request({
//     url: '/vue-admin-template/user/logout',
//     method: 'post'
//   })
// }
export function logout() {
  return request({
    url: '/vue-admin-template/user/logout',
    method: 'post'
  })
}

// 新增用户
export async function addApi(parm) {
  return await http.post("/api/user", parm)
}
// 获取用户列表
export async function getListApi(parm) {
  return await http.get("/api/user/list", parm)
}
// 编辑用户
export async function editApi(parm) {
  return await http.put("/api/user", parm)
}
// 删除用户
export async function deleteApi(parm) {
  return await http.delete("/api/user", parm)
}