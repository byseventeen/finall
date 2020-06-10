// 在http.js中引入axios
import axios from 'axios' // 引入axios
// import QS from 'qs' // 引入qs模块，用来序列化post类型的数据，后面会提到
// vant的toast提示框组件，大家可根据自己的ui组件更改。
// import { Toast } from 'vant'

import { URL_KEY } from '../../config/index.js'
// import { local } from '@/util/storage.util'
import { getUrlParams } from './url.util.js'
/**
 *请求拦截
 */

// const http = axios.create({
//   timeout: 1000 * 6,
//   withCredentials: true,
//   headers: {
//     'Content-Type': 'application/json; charset=utf-8'
//   }
// })

axios.interceptors.request.use(config => {
  let token = getUrlParams(URL_KEY.TOKEN)
  console.log(token)
  if (token) {
    config.headers['token'] = token // 请求头带上token
  }
  return config
}, error => {
  return Promise.reject(error)
})

/**
 *  响应拦截
 */

axios.interceptors.response.use(response => {
  return response.data
}, error => {
  return Promise.reject(error)
})

/**
 * get方法，对应get请求
 * @param {String} url [请求的url地址]
 * @param {Object} params [请求时携带的参数]
 */
export function get (url, params) { // 暴露get出去
  params.rand = Math.random()
  return axios.get(url, { params }).then(checkCode)
};

/**
 * get方法，下载数据流对应get请求
 * @param {String} url [请求的url地址]
 * @param {Object} params [请求时携带的参数]
 */
export function getExport (url) { // 暴露get出去
  window.location.href = url
};

/**
 * 封装post请求
 * @param url
 * @param data
 * @returns {Promise}
 */
export function post (url, data = {}) { // 暴露post出去
  return axios.post(url, data).then(checkCode)
};

// 回调checkCode
function checkCode (data) {
  if (!data) {
    return Promise.reject(new Error('数据异常'))
  }
  if (data.code === '0000' || data === '0000') {
    return data
  } else {
    console.error('返回数据格式有误', data)
    return Promise.reject(data)
  }
}
// function checkCode (data) {
//   if (!data) {
//     return Promise.reject(new Error('数据异常'))
//   }
//   if (data.success === true || data.success === 'true') {
//     return data
//   } else {
//     return Promise.reject(data)
//   }
// }
