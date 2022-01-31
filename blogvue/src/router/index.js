import { createRouter, createWebHashHistory } from "vue-router";
import blog from '../components/Blog.vue'
import user from '../components/User.vue'
import article from '../components/Content.vue'
import edit from '../components/Edit.vue'
import manage from '../components/BlogManage.vue'


const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        { path: '/', redirect: '/blog' },
        { path: '/blog', component: blog },
        {path: '/user', component: user,redirect:'/user/manage',children: [
            {name:'edit', path: '/user/edit/', component:edit,props:true},
            { path: '/user/manage', component:manage},
        ]},
        {path:'/article/:id',component: article,props:true},
    ]

})


export default router