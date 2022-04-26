import EventService from "@/services/event.service";
import UserService from "@/services/user.service";

export const event = {
  namespaced: true,
  state: {
    events: []
  },
  actions: {
    async getAll({ commit, rootState }) {
      const events = (await EventService.getAllEvents()).data;
      if(rootState.auth.status.loggedIn) {
        const user = JSON.parse(JSON.stringify(rootState.auth.user));
        const userSubs = (await UserService.getUserSubscriptions(user.id)).data;

        userSubs.forEach(sub => {
            var index = events.findIndex(event => event.id == sub.evento.id);
            if(index != -1) {
              events[index].usuarioInscricao = sub;
            }
        });
      }
      commit('refreshEvents', events);
    },
    async getFuture({ commit }) {
      const events = await EventService.getAllFutureEvents();
      commit('refreshEvents', events.data);
    },
    async getActive({ commit }) {
      const events = await EventService.getAllActiveEvents();
      commit('refreshEvents', events.data);
    }
  },
  mutations: {
    refreshEvents(state, events) {
      state.events = events
    }
  }
}