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
    path: '/forgotPassword',
    name: 'forgotPassword',
    component: ()=>import('../views/ForgotPasswordView.vue')
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
    path: '/admin/addExam',
    name: 'adminAddExam',
    component: ()=>import('../views/Backend/AddExam.vue')
  },
  {
    path: '/admin/examList',
    name: 'adminExamList',
    component: ()=>import('../views/Backend/AdminExamList.vue')
  },
  {
    path: '/admin/editExam/:id',
    name: 'editExam',
    component: ()=>import('../views/Backend/EditExam.vue')
  },
  {
    path: '/admin/addTip',
    name: 'adminAddTip',
    component: ()=>import('../views/Backend/AddTip.vue')
  },
  {
    path: '/admin/tipList',
    name: 'adminTipList',
    component: ()=>import('../views/Backend/AdminTipList.vue')
  },
  {
    path: '/admin/editTip/:id',
    name: 'editTip',
    component: ()=>import('../views/Backend/EditTip.vue')
  },
  {
    path: '/contact',
    name: 'contact',
    component: () => import('../views/ContactView.vue')
  },
  {
    path: '/profile/:id',
    name: 'profile',
    component: () => import('../views/ProfileView.vue')
  },
  {
    path: '/admin/summary',
    name: 'summary',
    component: () => import('../views/Backend/SummaryView.vue')
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
