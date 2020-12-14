/**
 * 统一接口管理 统一为post请求
 * 添加前先搜索一下关键词是否重复！！！
 */

const DATA_API = {
  // 用户
  getUsers: 'GET:/users',
  getUser: 'GET:/user',
  deleteUser: 'DELETE:/user',
  postUser: 'POST:/user',

  // 文章
  getBlogs: 'GET:/blogs',
  getBlog: 'GET:/blog',
  postBlog: 'POST:/blog',
  postBlogStatus: 'POST:/blogStatus',
  deleteBlog: 'DELETE:/blog',

  // 朝代
  getDynastyList: 'GET:/dynastyList',
  getDynasty: 'GET:/dynasty',
  deleteDynasty: 'DELETE:/dynasty',
  postDynasty: 'POST:/dynasty',
  getDynastyWithUserCount: 'GET:/dynastyWithUserCount',
  
  // 登录
  login: "POST:/login",
};

export default DATA_API;
