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
            path:'/product/:id',
            name:'product',
            component: () =>
                import("./views/ViewProduct")
        },
        {
            path: '/product/:id/edit',
            name: 'editProduct',
            component: ()=>
                import("./views/EditProduct")
        },
        {
            path: '/account/me',
            name: 'viewAccount',
            component: () =>
                import("./views/ViewAccount")
        },
        {
            path: '/product/new',
            name: 'createProduct',
            component:() =>
                import("./views/CreateProduct")
        },
        {
            path: '/search',
            name: 'search',
            component: () =>
                import("./views/SearchProducts")
        },
        {
            path: '/cart',
            name: 'shoppingCart',
            component: () =>
                import("./views/ViewCart")
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
