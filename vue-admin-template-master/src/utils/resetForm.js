/**
 * 清空表单
 * @param {*} formName : 表单的ref属性
 * @param {*} obj : 表单绑定的数据域
 */
export default function resetForm(formName, obj) {
    // 清空表单
    if (this.$refs[formName]) {
        this.$refs[formName].resetFields();
    }
    // 清空表单数据域
    Object.keys(obj).forEach(key => {
        obj[key] = ''
    })
}