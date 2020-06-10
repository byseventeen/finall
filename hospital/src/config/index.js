export const SESSION_KEY = {
}

export const COOKIE_KEY = {
}

// localStorage 存储key
export const LOCAL_KEY = {
}

export const URL_KEY = {
  // url地址携带的token
  TOKEN: 'token'
}

// 页面名称
export const PAGE_NAME = {
}

/*function urlHandlerForDocumentscan (path) {
  // return [process.env.API_HOST , path].join('')
  // return ['http://localhost:8082/documentscan', path].join('')
  return ['/documentscan', path].join('')
}*/

export const API = {
  //扫描
  // QUERY_SCAN: urlHandler('/twainPdf/scanning'),
  QUERY_SCAN: '/tiff/scanning',

}
