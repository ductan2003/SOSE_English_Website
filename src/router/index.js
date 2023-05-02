import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import RegisterView from '../views/RegisterView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/register',
    name: 'resgister',
    component: RegisterView
  },
  {
    path: '/about/:id',
    name: 'test',
    component: ()=>import('../views/TestView.vue')
  },
  {
    path: '/exams',
    name: 'exams',
    component: () => import('../views/ExamList.vue')
  },
  {
    path: '/exam/:id',
    name: 'exam',
    component: () => import('../views/ExamView.vue')
  },
  {
    path: '/tips',
    name: 'tips',
    component: () => import('../views/TipsList.vue')
  },
  {
    path: '/tip/:id',
    name: 'tip',
    component: ()=>import('../views/TipView.vue')
  },
  {
    path: '/admin/addtip',
    name: 'adminaddTip',
    component: ()=>import('../views/Backend/AddTips.vue')
  },
  {
    path: '/admin/tipList',
    name: 'adminTipList',
    component: ()=>import('../views/Backend/AdminTipList.vue')
  },
  {
    path: '/admin/editTips/:id',
    name: 'editTip',
    component: ()=>import('../views/Backend/EditTip.vue')
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
