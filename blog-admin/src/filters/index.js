import Vue from "vue";
import Store from "@/store/index.js";

/**
 * 全局过滤器
 */

// 朝代转换
Vue.filter("dynastyFilter", function(dynastyId) {
  return Store.getters.dynastyData[dynastyId];
});
