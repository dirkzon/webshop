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
        {
            path:'/product/:id',
            name:'product',
            component: () =>
                import("./views/ViewProduct")
        },
        {
          path:'/browse',
          name:'broseProducts',
          component:() =>
            import("./views/BrowseProduct")
        },
        {
            path:'/customer/me',
            name:'customerAccount',
            component:() =>
                import("./views/ViewCustomerAccount")
        },
        {
            path:'/retailer/me',
            name:'retailerAccount',
            component:() =>
                import("./views/ViewRetailerAccount")
        },
        {
          path: '/products/new',
          name:'createProduct',
          component:() =>
                import("./views/CreateProduct")
        },
        {
            path: '/cart',
            name:'shoppingCart',
            component:() =>
                import("./views/ShoppingCart")
        },
        {
            path: '/product/:id/edit',
            name:'editProduct',
            component:() =>
                import("./views/EditProduct")
        },
        {
            path: '/reports',
            name:'viewReports',
            component:() =>
                import("./views/ViewReports")
        },
    ]
})
