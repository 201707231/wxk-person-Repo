import Vue from 'vue'
import Router from 'vue-router'
// in development env not use Lazy Loading,because Lazy Loading too many pages will cause webpack hot update too slow.so only in production use Lazy Loading
/* layout */
import Layout from '../views/layout/Layout'

const _import = require('./_import_' + process.env.NODE_ENV)
Vue.use(Router)
export const constantRouterMap = [
  { path: '/login', component: _import('login/index'), hidden: true },
  { path: '/404', component: _import('404'), hidden: true },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: '首页',
    hidden: true,
    children: [{
      path: 'dashboard',
      component: _import('dashboard/index')
    }]
  },

]
export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
export const asyncRouterMap = [
  {
    path: '/device',
    component: Layout,
    redirect: '/device/',
    meta: { title: '设备管理', icon: 'tree' },
    children: [
      {
        path: '',
        name: '设备',
        component: _import('device/devMgr'),
        meta: { title: '设备管理', icon: 'example' },
        menu: 'device',
        hidden: false
      },
      {
        path: 'devParts',
        name: '设备配件',
        component: _import('device/devParts'),
        menu: 'device',
        hidden: true
      }

    ]
  },
  {
    path: '/part',
    component: Layout,
    redirect: '/part/',
    meta: { title: '配件管理', icon: 'tree' },
    children: [
      {
        path: '',
        name: '配件',
        component: _import('part/partMgr'),
        meta: { title: '配件管理', icon: 'example' },
        menu: 'part',
        hidden: false
      },
    ]
  },
  {
    path: '/event',
    component: Layout,
    redirect: '/event/',
    meta: { title: '事件管理', icon: 'tree' },
    children: [
      {
        path: '',
        name: '事件',
        component: _import('event/eventMgr'),
        meta: { title: '事件管理', icon: 'example' },
        menu: 'event',
        hidden: false,
      },
      {
        path: 'addEvent',
        name: '添加事件',
        component: _import('event/addEvent'),
        meta: { title: '添加事件', icon: 'example' },
        menu: 'event',
        hidden: false,
        hidden: true
      }

    ]
  },

  {
    path: '/user',
    component: Layout,
    redirect: '/user/',
    name: '',
    meta: { title: '用户权限', icon: 'table' },
    children: [
      {
        path: '',
        name: '用户列表',
        component: _import('user/user'),
        meta: { title: '用户列表', icon: 'user' },
        menu: 'user'
      },
      {
        path: 'role',
        name: '权限管理',
        component: _import('user/role'),
        meta: { title: '权限管理', icon: 'password' },
        menu: 'role'
      },
    ]
  },
  {
    path: '/defs',
    component: Layout,
    redirect: '/defs/',
    meta: { title: '定义管理', icon: 'tree' },
    children: [
      {
        path: '',
        name: '定义',
        component: _import('defs/defsMgr'),
        meta: { title: '定义管理', icon: 'example' },
        menu: 'defs',
        hidden: false
      },
    ]
  },
  { path: '*', redirect: '/404', hidden: true }
]
