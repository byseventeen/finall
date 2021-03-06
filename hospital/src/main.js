// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import $ from 'jquery'
import "./assets/css/bootstrap.min.css"
import "./assets/js/bootstrap.min.js"
import {get, post} from './assets/js/fetch'
import axios from 'axios'
Vue.prototype.$http = axios
import qs from 'qs';
Vue.prototype.$qs = qs;
Vue.config.productionTip = false;
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
Vue.use(ElementUI)

import VueCropper from 'vue-cropper'
Vue.use(VueCropper)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})


