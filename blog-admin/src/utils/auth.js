import Cookies from "js-cookie";

const TokenKey = "token";

export function getToken() {
  return Cookies.get(TokenKey);
}

export function setToken(token) {
  //  有效时间设置为7天
  return Cookies.set(TokenKey, token, { expires: 7 });
}

export function removeToken() {
  return Cookies.remove(TokenKey);
}
