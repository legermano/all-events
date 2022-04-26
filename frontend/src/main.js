import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import { library } from '@fortawesome/fontawesome-svg-core'
import { faCalendarCheck, faCalendarXmark, faCalendarDay } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from './plugins/font-awesome';
import moment from 'moment';
import axios from 'axios';

library.add(faCalendarCheck);
library.add(faCalendarXmark);
library.add(faCalendarDay);

const app = createApp(App)

app.use(router)
app.use(store)
app.component("font-awesome-icon", FontAwesomeIcon)
app.mount('#app');

app.config.globalProperties.$filters = {
  formatDate(value, format = 'DD/MM/YYYY HH:mm') {
    if(value && format) {
      return moment(String(value)).format(format);
    }
  }
}

// Default header to call the API
axios.defaults.headers.common['Authorization'] = 'frontend';