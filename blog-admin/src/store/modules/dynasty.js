import { login, logout, getInfo } from "@/api/user";
import { getToken, setToken, removeToken } from "@/utils/auth";
import { resetRouter } from "@/router";
import Http from "@/api/http.js";

const state = {
  data: {},
  list: []
};

const mutations = {
  SET_DATA: (state, data) => {
    state.data = data;
  },
  SET_LIST: (state, list) => {
    state.list = list;
  }
};

const actions = {
  initData({ commit }) {
    return new Promise((resolve, reject) => {
      Http.getDynastyList()
        .then(res => {
          commit("SET_LIST", res.data);

          let data = {};
          for (let item of res.data) {
            data[item.id] = item.name;
          }

          commit("SET_DATA", data);

          resolve();
        })
        .catch(error => {
          reject(error);
        });
    });
  }
};

export default {
  namespaced: true,
  state,
  mutations,
  actions
};
