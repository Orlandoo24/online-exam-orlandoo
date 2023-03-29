import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)
export default new Router({
  routes: [
    {path: '/', name: 'login', component: () => import('@/components/common/login')},//登录界面
    {path: '/index', component: () => import('@/components/admin/index'),//教师主页
      children: [{path: '/', component: () => import('@/components/common/hello')},//首页默认路由
        {path:'/grade', component: () => import('@/components/charts/grade')},//学生成绩
        {path: '/selectExamToPart', component: () => import('@/components/teacher/selectExamToPart')},//学生分数段
        {path: '/scorePart', component: () => import('@/components/charts/scorePart')},
        {path: '/allStudentsGrade', component: () => import('@/components/teacher/allStudentsGrade')},//所有学生成绩统计
        {path: '/examDescription', component: () => import('@/components/teacher/examDescription')},//考试管理功能描述
        {path: '/selectExam', component: () => import('@/components/teacher/selectExam')},//查询所有考试
        {path: '/addExam', component: () => import('@/components/teacher/addExam')},//添加考试
        {path: '/answerDescription', component: ()=> import('@/components/teacher/answerDescription')},//题库管理功能介绍
        {path: '/selectAnswer', component: () => import('@/components/teacher/selectAnswer')},//查询所有题库
        {path: '/addAnswer', component: () => import('@/components/teacher/addAnswer')},//增加题库主界面
        {path: '/addAnswerChildren', component: () => import('@/components/teacher/addAnswerChildren')},//点击试卷跳转到添加题库页面
        {path: '/studentManage', component: () => import('@/components/teacher/studentManage')},//学生管理界面
        {path: '/addStudent', component: () => import('@/components/teacher/addStudent')},//添加学生
        {path: '/teacherManage', component: () => import('@/components/admin/teacherManage')},//教师管理页面
        {path: '/addTeacher', component: () => import ('@/components/admin/addTeacher')}//添加分数
      ]
    },
  ]
})

// import Router from 'vue-router'
// Vue.use(Router)
// export default new Router({
//   routes: [
//     {
//       path: '/',
//       name: 'login', //登录界面
//       component: () => import('@/components/common/login')
//     },
//     {
//       path: '/index', //教师主页
//       component: () => import('@/components/admin/index'),
//       children: [
//         {
//           path: '/', //首页默认路由
//           component: () => import('@/components/common/hello')
//         },
//         {
//           path:'/grade', //学生成绩
//           component: () => import('@/components/charts/grade')
//         },
//         {
//           path: '/selectExamToPart', //学生分数段
//           component: () => import('@/components/teacher/selectExamToPart')
//         },
//         {
//           path: '/scorePart',
//           component: () => import('@/components/charts/scorePart')
//         },
//         {
//           path: '/allStudentsGrade', //所有学生成绩统计
//           component: () => import('@/components/teacher/allStudentsGrade')
//         },
//         {
//           path: '/examDescription', //考试管理功能描述
//           component: () => import('@/components/teacher/examDescription')
//         },
//         {
//           path: '/selectExam', //查询所有考试
//           component: () => import('@/components/teacher/selectExam')
//         },
//         {
//           path: '/addExam', //添加考试
//           component: () => import('@/components/teacher/addExam')
//         },
//         {
//           path: '/answerDescription', //题库管理功能介绍
//           component: ()=> import('@/components/teacher/answerDescription')
//         },
//         {
//           path: '/selectAnswer', //查询所有题库
//           component: () => import('@/components/teacher/selectAnswer')
//         },
//         {
//           path: '/addAnswer', //增加题库主界面
//           component: () => import('@/components/teacher/addAnswer')
//         },
//         {
//           path: '/addAnswerChildren', //点击试卷跳转到添加题库页面
//           component: () => import('@/components/teacher/addAnswerChildren')
//         },
//         {
//           path: '/studentManage', //学生管理界面
//           component: () => import('@/components/teacher/studentManage')
//         },
//         {
//           path: '/addStudent', //添加学生
//           component: () => import('@/components/teacher/addStudent')
//         },
//         {
//           path: '/teacherManage',
//           component: () => import('@/components/admin/teacherManage')
//         },
//         {
//           path: '/addTeacher',
//           component: () => import ('@/components/admin/addTeacher')
//         }
//       ]
//     },
//
//     // {path: '/answer',component: () => import('@/components/student/answer')}
//   ]
// })
