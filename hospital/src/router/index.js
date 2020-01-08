import Vue from 'vue'
import Router from 'vue-router'
import Header from '@/components/mheader'
import Index from '@/components/index'
import Book from '@/components/book'
import Guide from '@/components/guide'
import Person from '@/components/person'
import Mynav from '@/components/mynav'
import Menu from '@/components/menu'
import Carousel from '@/components/Carousel'
import LoginForm from '@/components/loginForm'
import Code from '@/components/Code'

import Home from '@/pages/home'
import Forbook from '@/pages/forbook'
import Login from '@/pages/login'



Vue.use(Router)

export default new Router({
  routes: [
  /* {
      path: '/',
      name: 'index',
      component: Index
    },*/
    {
      path: '/',
      name: 'home',
      component: Home
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
      component:Book
    },
    {
      path:'/guide',
      name:'guide',
      component:Guide
    },
    {
      path:'/person',
      name:'person',
      component:Person
    },
    {
      path:'/mynav',
      name:'mynav',
      component:Mynav
    },
    {
      path:'/home',
      name:'home',
      component:Home
    },
    {
      path:'/menu',
      name:'menu',
      component:Menu
    },
    {
      path:'/forbook',
      name:'forbook',
      component:Forbook
    },
    {
      path:'/Carousel',
      name:'Carousel',
      component:Carousel
    },
    {
      path:'/login',
      name:'login',
      component:Login
    },
    {
      path:'/loginForm',
      name:'loginForm',
      component:LoginForm
    },
    {
      path:'/Code',
      name:'Code',
      component:Code
    }
  ]
})
