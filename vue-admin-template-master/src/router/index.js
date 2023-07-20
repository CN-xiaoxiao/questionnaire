import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '首页', icon: 'dashboard' }
    }]
  },

  {
    path: '/user',
    component: Layout,
    alwaysShow: true,
    name: 'user',
    meta: { title: '用户管理', icon: 'el-icon-user-solid' },
    children: [
      {
        path: '/userList',
        name: 'userList',
        component: () => import('@/views/user/userList'),
        meta: { title: '用户列表', icon: 'el-icon-s-custom' }
      }
    ]
  },

  {
    path: '/question',
    component: Layout,
    alwaysShow: true,
    name: 'question',
    meta: { title: '问卷管理', icon: 'el-icon-s-tools' },
    children: [
      {
        path: '/questionList',
        name: 'questionList',
        component: () => import('@/views/question/questionList'),
        meta: { title: '问卷列表', icon: 'table' }
      },
      {
        path: '/paper',
        name: 'paper',
        component: () => import('@/views/paper/paperList'),
        meta: { title: '试题管理', icon: 'el-icon-s-help' }
      }
    ]
  },

  {
    path: '/questionCount',
    component: Layout,
    alwaysShow: true,
    name: 'questionCount',
    meta: { title: '问卷统计', icon: 'el-icon-s-data' },
    children: [
      {
        path: '/questionCountList',
        name: 'questionCountList',
        component: () => import('@/views/question/questionCountList'),
        meta: { title: '统计管理', icon: 'el-icon-s-grid' }
      }
    ]
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
