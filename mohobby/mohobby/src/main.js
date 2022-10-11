import Vue from "vue";
import App from "./App.vue";
import vuetify from "./plugins/vuetify";
import router from "./router";
import axios from "axios";
import $ from "jquery";
import ImageViewer from "vue2-viewer";
import store from "./store";
import vueMoment from "vue-moment";
import { BootstrapVue, BootstrapVueIcons } from "bootstrap-vue";
Vue.use(BootstrapVue);
Vue.use(BootstrapVueIcons);

Vue.use(vueMoment);
Vue.use(ImageViewer);
Vue.config.productionTip = false;

Vue.prototype.axios = axios;
Vue.prototype.$ = $;
axios.defaults.baseURL = "http://localhost:8088/java";
window.Kakao.init("157b38874395f658a48c02cc8473066b"); // 카카오 로그인 앱 키

Vue.filter("toFixed", (val, num) => {
  return parseFloat(val), toFixed(num);
});
Vue.filter("comma", (val) => {
  return String(Math.round(val)).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
});
Vue.filter("won", (val) => {
  return `${val} 원`;
});

new Vue({
  vuetify,
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
