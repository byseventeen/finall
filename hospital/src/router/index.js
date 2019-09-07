import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Header from '@/components/mheader'
import Index from '@/components/index'
import Book from '@/components/book'
import Guide from '@/components/guide'
import Person from '@/components/person'


Vue.use(Router)

export default new Router({
  routes: [
   /* {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },*/
    {
      path: '/',
      name: 'index',
      component: Index
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
    }
  ]
})
