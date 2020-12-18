import Http from "@/api/http.js";
/**
 * 自定义上传图片插件
 */
class MyUploadAdapter {
  constructor(loader) {
    this.loader = loader;
  }

  async upload() {
    const data = new FormData();
    data.append("file", await this.loader.file);

    const res = await Http.postUploadFile(data)

    // 后台返回数据：
    // {"code":0,"msg":"success","data":{"url":"/upload/struts2.jpeg"}}

    // 方法返回数据格式： {default: "url"}
    return {
      default: res.data.url
    };
  }
}


// 自定义上传图片插件， 剪切板粘贴也可以上传
function MyUploadAdapterPlugin(editor){
  editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
    return new MyUploadAdapter(loader);
  };
}

export default MyUploadAdapterPlugin;
