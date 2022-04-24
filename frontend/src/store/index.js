import { createStore } from 'vuex';
import { auth } from './auth.module';
import { event } from './event.module';

export default createStore({
  modules: {
    auth,
    event
  }
});
