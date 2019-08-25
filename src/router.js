import Vue from 'vue'
import Router from 'vue-router'

//一级路由
import Home from './views/Home/Home'
import Login from './views/Login'
import Student from './views/Student/Student'
import Manager from './views/Manager/Manager'
import Organization from './views/Organization/Organization'
import Activity from './views/Activity'


//二级路由
import Hotactivity from './views/Home/Hotactivity'
import Newactivity from './views/Home/Newactivity'
import Myactivity from './views/Home/Myactivity'

import Stu_news from './views/Student/News'
import Stu_info from './views/Student/Information'
import Stu_organ from './views/Student/Organization'
import Stu_cences from './views/Student/Cences'
import Stu_passmod from './views/Student/Passmod'

import Organ_info from './views/Organization/Information'
import Organ_news from './views/Organization/News'
import Organ_pass from './views/Organization/Passmod'
import Organ_vip from './views/Organization/Vip'
import Organ_act from './views/Organization/Activity'
import Organ_detail from './views/Organization/Detail/Detail'


import Man_student from './views/Manager/Student.vue'
import Man_organ from './views/Manager/Organ.vue'
import Man_approval from './views/Manager/Approval.vue'
import Man_news from './views/Manager/News.vue'
import Man_passmod from './views/Manager/Passmod.vue'




//三级路由
import Detail_Content from './views/Organization/Detail/Content.vue'
import Detail_Comment from './views/Organization/Detail/Comment.vue'
import Detail_Grade from './views/Organization/Detail/Grade.vue'
import Detail_Apply from './views/Organization/Detail/Apply.vue'
import Detail_Sign from './views/Organization/Detail/Sign.vue'

Vue.use(Router)

export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [{
            path: '/',
            component: Home,
            children: [
                { path: '/hotactivity', component: Hotactivity },
                { path: '/newactivity', component: Newactivity },
                { path: '/myactivity', component: Myactivity }
            ],
            redirect: '/hotactivity'
        },
        {
            path: '/login',
            component: Login
        },
        {
            path: '/activity',
            component: Activity
        },
        {
            path: '/student',
            component: Student,
            children: [
                { path: '/student/news', component: Stu_news },
                { path: '/student/information', component: Stu_info },
                { path: '/student/organization', component: Stu_organ },
                { path: '/student/statistics', component: Stu_cences },
                { path: '/student/passmod', component: Stu_passmod }
            ],
            redirect: '/student/information'
        },
        {
            path: '/organization',
            component: Organization,
            children: [
                { path: '/organization/news', component: Organ_news },
                { path: '/organization/information', component: Organ_info },
                { path: '/organization/activity', component: Organ_act },
                { path: '/organization/vip', component: Organ_vip },
                { path: '/organization/passmod', component: Organ_pass },
                {
                    path: '/organization/detail',
                    component: Organ_detail,
                    children: [
                        { path: '/organization/detail/content', component: Detail_Content },
                        { path: '/organization/detail/comment', component: Detail_Comment },
                        { path: '/organization/detail/apply', component: Detail_Apply },
                        { path: '/organization/detail/grade', component: Detail_Grade },
                        { path: '/organization/detail/sign', component: Detail_Sign }
                    ],
                    redirect: '/organization/detail/content'
                }
            ],
            redirect: '/organization/information'
        },
        {
            path: '/manager',
            component: Manager,
            children: [
                { path: '/manager/student', component: Man_student },
                { path: '/manager/organization', component: Man_organ },
                { path: '/manager/approval', component: Man_approval },
                { path: '/manager/news', component: Man_news },
                { path: '/manager/passmod', component: Man_passmod }
            ],
            redirect: '/manager/student'
        },
        { path: '*', redirect: '/' }
    ]
})