import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import { FontAwesomeIcon } from './plugins/font-awesome';
import moment from 'moment';

const app = createApp(App)

app.use(router)
app.use(store)
app.component("font-awesome-icon", FontAwesomeIcon)
app.mount('#app');

app.config.globalProperties.$filters = {
  formatDate(value, format = 'DD/MM/YYYY hh:mm') {
    if(value && format) {
      return moment(String(value)).format(format);
    }
  }
}
