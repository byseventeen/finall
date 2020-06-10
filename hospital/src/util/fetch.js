import {
  post
}
  from './http.js'
import {
  API
}
  from '../config/index.js'

import QS from 'qs'

// 扫描
export function queryScan (params, urlParams) {
  let urlParam = QS.stringify(urlParams)
  return post(API.QUERY_SCAN + '?' + urlParam, params)
}













