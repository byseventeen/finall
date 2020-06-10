import Vue from 'vue'
import Router from 'vue-router'

import Header from '@/components/mheader'
import LoginForm from '@/components/loginForm'
import Index from '@/pages/index'
import Book from '@/pages/book'
import Person from '@/pages/person'
import Guide from '@/pages/guide'
import Login from '@/pages/login'
import Sign from '@/pages/sign'
import Test from '@/pages/test'
import Cut from '@/pages/cut'



Vue.use(Router)
//路由匹配规则
let router = new Router({
  routes: [
   {
      path: '/',
      name: 'test',
      component: Test
    },
    {
      path:'/test',
      name:'test',
      component:Test
    },
    {
      path:'/index',
      name:'index',
      component:Index
    },
    {
      path:'/mheader',
      name:'mheader',
      component:Header
    },
    {
      path:'/book',
      name:'book',
      component:Book,
      meta:{
        requireAuth:true
      }
    },
    {
      path:'/guide',
      name:'guide',
      component:Guide,
      meta:{
        requireAuth:true
      }
    },
    {
      path:'/person',
      name:'person',
      component:Person,
      meta:{
        requireAuth:true
      }
    },
    {
      path:'/login',
      name:'login',
      component:Login
    },
    {
      path:'/sign',
      name:'sign',
      component:Sign
    },
    {
      path:'/loginForm',
      name:'loginForm',
      component:LoginForm
    }
  ]
})

//全局路由守卫
router.beforeEach((to, from, next) => {
  if (to.meta.requireAuth) { // 判断页面是否需要登录验证
    // 判断是否登录
    if (sessionStorage.getItem("userToken")=="true") {
      console.log("已登录")
      next()
    } else {
      alert("请先登录！")
      next({
        name: 'index'
      });
    }
  } else {
    next()
  }
})

export default router
