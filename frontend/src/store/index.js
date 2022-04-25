import { createStore } from 'vuex';
import { auth } from './modules/auth.module';
import { event } from './modules/event.module';
import { user } from './modules/user.module';

export default createStore({
  modules: {
    auth,
    event,
    user
  }
});
