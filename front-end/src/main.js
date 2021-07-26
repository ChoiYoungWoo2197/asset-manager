import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "@/assets/css/layout.css";
import "admin-lte/plugins/jquery-ui/jquery-ui.css";
import "admin-lte/plugins/fontawesome-free/css/all.min.css";
import "admin-lte/plugins/icheck-bootstrap/icheck-bootstrap.min.css";
import "admin-lte/dist/css/adminlte.min.css";
import "daterangepicker/daterangepicker.css";
import "jstree/dist/themes/default/style.min.css";

import "@/assets/js/common.js";
import "admin-lte/plugins/jquery/jquery.min.js";
import "admin-lte/plugins/jquery-ui/jquery-ui.js";
import "admin-lte/plugins/bootstrap/js/bootstrap.bundle.js";
import "admin-lte/plugins/inputmask/jquery.inputmask.min.js";
import "daterangepicker/daterangepicker.js";
import "jstree/dist/jstree.min.js";
import "admin-lte/dist/js/adminlte.min.js";




import { library } from '@fortawesome/fontawesome-svg-core'
import { fas } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

library.add(fas)

createApp(App)
    .component('font-awesome-icon', FontAwesomeIcon)
    .use(store)
    .use(router)
    .mount("#app");

