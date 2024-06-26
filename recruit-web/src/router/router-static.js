import Vue from 'vue';
import VueRouter from 'vue-router'
//配置路由文件
Vue.use(VueRouter);
//导入所有组件内容
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import register from '@/views/register'
import basicinfo from '@/views/basicinfo.vue'
import news from '@/views/modules/news/list'
import employment from '@/views/modules/employment/list'
import companyinfo from '@/views/modules/companyinfo/list'
import jobseekerinfo from '@/views/modules/jobseekerinfo/list'
import generaluser from '@/views/modules/generaluser/list'
import configmanagement from '@/views/modules/config/list'
import job_category from '@/views/modules/job_category/list'
import friendly_links from '@/views/modules/friendly_links/list'
import storeup from '@/views/modules/storeup/list'
import educationinfo from '@/views/modules/educationinfo/list'
import application from '@/views/modules/application/list'
import users from '@/views/modules/users/list'
import job_post_comments from '@/views/modules/job_post_comments/list'
import job_seeker_comments from '@/views/modules/job_seeker_comments/list'
import interview from '@/views/modules/interview/list'
import forum from '@/views/modules/forum/list'
//配置路由
const routes = [
    {
        path: '/index', name: 'index', component: Index, children: [{
            path: '/',
            name: 'home',
            component: Home
        }, {
            path: '/updatePassword',
            name: 'updatePassword',
            component: UpdatePassword
        }, {
            path: '/basicinfo',
            name: 'basicinfo',
            component: basicinfo
        }
            , {
                path: '/news',
                name: 'news',
                component: news
            }
            , {
                path: '/employment',
                name: 'employment',
                component: employment
            }
            , {
                path: '/companyinfo',
                name: 'companyinfo',
                component: companyinfo
            }
            , {
                path: '/jobseekerinfo',
                name: 'jobseekerinfo',
                component: jobseekerinfo
            }
            , {
                path: '/generaluser',
                name: 'generaluser',
                component: generaluser
            }
            , {
                path: '/config',
                name: 'config',
                component: configmanagement
            }
            , {
                path: '/job_category',
                name: 'job_category',
                component: job_category
            }
            , {
                path: '/friendly_links',
                name: 'friendly_links',
                component: friendly_links
            }
            , {
                path: '/job_post_comments',
                name: 'job_post_comments',
                component: job_post_comments
            }, {
                path: '/job_seeker_comments',
                name: 'job_seeker_comments',
                component: job_seeker_comments
            }, {
                path: '/storeup',
                name: 'storeup',
                component: storeup
            }
            , {
                path: '/educationinfo',
                name: 'educationinfo',
                component: educationinfo
            }
            , {
                path: '/application',
                name: 'application',
                component: application
            }
            , {
                path: '/users',
                name: 'users',
                component: users
            }
            , {
                path: '/interview',
                name: 'interview',
                component: interview
            }
            , {
                path: '/forum',
                name: 'forum',
                component: forum
            }
        ]
    },
    {path: '/login', name: 'login', component: Login},
    {path: '/register', name: 'register', component: register},
    {path: '/', redirect: '/index/'},      /*默认跳转路由*/
    {path: '*', component: NotFound}
]
//实例化VueRouter
const router = new VueRouter({
    mode: 'hash',
    routes
})

export default router;

