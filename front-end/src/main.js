import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "./assets/js/common.js";
import "admin-lte/plugins/icheck-bootstrap/icheck-bootstrap.min.css";
import "admin-lte/dist/css/adminlte.min.css";

createApp(App)
    .use(store)
    .use(router)
    .mount("#app");

