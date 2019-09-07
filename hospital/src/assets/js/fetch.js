import axios from 'axios'

const instance = axios.create({
  baseURL: 'http://localhost:8080',
  timeout: 10000
})

instance.interceptors.response.use(function (response) {
  return response.data
}, function (error) {
  Toast({
    txt: '错误:' + typeof (error) === 'string' ? error : JSON.stringify(error),
    type: 'txt'
  })
  return Promise.reject(error)
})

export function get(url, data = {}) {
  // console.log(data)
  return instance.get(url, {
    params: data
  })
}
export function post(url, data = {}) {
  return instance.post(url, data)
}
