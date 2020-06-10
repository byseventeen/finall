import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

export default new Vuex.Store({
  //这里放全局参数
  state: {
    currentUser: null, //当前用户
    isLogin: false, //标记当前用户是否登录
    token: "",
  },
  getters: {
    //这里放get方法  取值
    getcurrentUser: state => state.currentUser,
    getisLogin: state => state.isLogin,
    gettoken: state => state.token,
  },
  mutations: {
    //这里放set方法  只支持同步
    setcurrentUser (state, value) {
      if(value){
        state.currentUser = value
        state.isLogin=true
      }else if(value==null){
        //当用户登出的时候，清空sessionStroage里面的东西
        sessionStorage.setItem("userName",null);
        sessionStorage.setItem("userToken", "");
        state.currentUser=null;
        state.isLogin=false;
        state.token="";
      }

    }
  },
  actions: {
    //支持异步操作


  },
  modules:{
    //分隔store
    //未来给全局变量分组，需要提前申明其他的store文件，并且引用
  }
})
