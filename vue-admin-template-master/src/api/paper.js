import http from '@/utils/http'

// 保存试题
export async function savePaperApi(parm){
    return await http.post("/api/paper/savePaper", parm);
}
// 查询试题列表
export async function getPaperListApi(parm) {
    return await http.get("/api/paper/getPaperList", parm);
}