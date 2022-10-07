import Vue from "vue";
import App from "./App.vue";
import vuetify from "./plugins/vuetify";
import router from "./router";
import axios from "axios";
import $ from "jquery";

Vue.config.productionTip = false;

Vue.prototype.axios = axios;
Vue.prototype.$ = $;
axios.defaults.baseURL = "http://localhost:8088/java";

new Vue({
  vuetify,
  router,
  render: (h) => h(App),
}).$mount("#app");
