import { createRouter, createWebHistory } from "vue-router";

// route level code-splitting
// this generates a separate chunk (about.[hash].js) for this route
// which is lazy-loaded when the route is visited.
const Login = () => { return import("../views/Login.vue");}
const Index = () => { return import("../views/Index.vue");}
const Authority = () => { return import("../views/Authority.vue");}
const Organization = () => { return import("../views/Organization.vue");}
const Category = () => { return import("../views/Category.vue");}
const PartnerCompany = () => { return import("../views/PartnerCompany.vue");}

const routes = [
  {
    path: "/",
    name: "Login",
    component: Login,
  },
  {
    path: "/index",
    name: "Index",
    component: Index,
  },
  {
    path: "/api/authoritys",
    name: "Authority",
    component: Authority,
  },
  {
    path: "/api/organization",
    name: "Organization",
    component: Organization
  },
  {
    path: "/api/categorys",
    name: "Category",
    component: Category
  },
  {
    path: "/api/partner-companys",
    name: "PartnerCompany",
    component: PartnerCompany
  }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
