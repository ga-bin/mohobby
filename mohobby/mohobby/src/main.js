import Vue from "vue";
import App from "./App.vue";
import vuetify from "./plugins/vuetify";
import router from "./router";
import axios from "axios";
import $ from "jquery";
import ImageViewer from 'vue2-viewer';

Vue.use(ImageViewer);
Vue.config.productionTip = false;

Vue.prototype.axios = axios;
Vue.prototype.$ = $;
axios.defaults.baseURL = "http://localhost:8088/java";
window.Kakao.init("157b38874395f658a48c02cc8473066b"); // 카카오 로그인 앱 키

new Vue({
  vuetify,
  router,
  render: (h) => h(App),
}).$mount("#app");
