import axios from "axios";
import dataApi from "./dataApi.js";
import { Message } from 'element-ui';

const instance = axios.create({
  baseURL: process.env.VUE_APP_BASE_API,
  timeout: 10000 // 毫秒
});

// 请求拦截器
instance.interceptors.request.use(
  config => {
    return config;
  },
  err => {
    console.log("请求出错");
  }
);

// 添加响应拦截器
instance.interceptors.response.use(
  res => {
    return res.data;
  },
  err => {
    Message.error("网络请求出错, 请检查网络");
  }
);

// 包装请求方法的容器
const Http = {};

for (const [key, value] of Object.entries(dataApi)) {
  // async 作用，避免进入回调地狱
  const [method, url] = value.split(":");

  Http[key] = async function(body) {
    let params, data;

    if (["POST"].indexOf(method) > -1) {
      data = body;
    } else {
      params = body;
    }

    return await instance.request({
      url: url,
      method: method,
      params: params,
      data: data
    });
  };
}

export default Http;
