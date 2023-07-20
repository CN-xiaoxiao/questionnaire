module.exports = {

  title: '问卷调查系统',

  /**
   * @type {boolean} true | false
   * @description Whether fix the header
   */
  fixedHeader: true,

  /**
   * @type {boolean} true | false
   * @description Whether show the logo in sidebar
   */
  sidebarLogo: true,


  baseUrl: 'http://localhost:8089',
  
  /**
   * 图片上传路径
   */
  imageUrl: 'http://localhost:8089/api/upload/uploadImage',

  /** 
   * 文件导出路径
   */
  exportUrl: 'http://localhost:8089/docxs/',

}
