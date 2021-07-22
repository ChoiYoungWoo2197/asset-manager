import { createRouter, createWebHistory } from "vue-router";

// route level code-splitting
// this generates a separate chunk (about.[hash].js) for this route
// which is lazy-loaded when the route is visited.
const Login = () => { return import("../views/Login.vue");}
const Index = () => { return import("../views/Index.vue");}
const Authority = () => { return import("../views/Authority.vue");}
const Organization = () => { return import("../views/Organization.vue");}

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
    path: "/authoritys",
    name: "Authority",
    component: Authority,
  },
  {
    path: "/organization",
    name: "Organization",
    component: Organization
  }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
