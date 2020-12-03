import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
    mode: 'history',
    base: process.env.Base_URL,
    routes:[
        {
            path:'/',
            name: 'home',
            component: () =>
                import("./views/Home")
        },
        {
            path: "/login",
            name: "login",
            component: ()=>
                import("./views/LogIn")
        },
        {
            path: '/account/new',
            name:'createAccount',
            component: ()=>
                import("./views/CreateAccount")
        },
    ]
})
