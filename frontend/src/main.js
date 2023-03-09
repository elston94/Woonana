import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import { BootstrapVue, IconsPlugin, BadgePlugin  } from 'bootstrap-vue'
import vueStarRating from 'vue-star-rating'
import VuejsHeatmap from "vuejs-heatmap";
import { Pie } from 'vue-chartjs/legacy'
import {store} from './store/index'
// Import Bootstrap an BootstrapVue CSS files (order is important)
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import '../src/assets/css/magnific-popup.css'
import '../src/assets/css/magnific-popup.css'
import '../src/assets/css/font-awesome.min.css'
import '../src/assets/css/custom-animated.css'
import '../src/assets/css/default.css'
import '../src/assets/css/magnific.dark.css'
import '../src/assets/css/style.css'
import '../src/assets/css/main.css'

// Make BootstrapVue available throughout your project
Vue.component("StarRating",vueStarRating)
Vue.component("VuejsHeatmap",VuejsHeatmap)
Vue.component("Pie",Pie)
Vue.use(BootstrapVue)
Vue.use(BadgePlugin)
// Optionally install the BootstrapVue icon components plugin
axios.defaults.headers.common["Access-Control-Allow-Origin"] = "*";

Vue.use(IconsPlugin);

Vue.config.productionTip = false;

new Vue({
  store,
  router,
  render: (h) => h(App),
}).$mount("#app");
