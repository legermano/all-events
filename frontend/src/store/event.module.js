import EventService from "@/services/event.service";

export const event = {
  namespaced: true,
  state: {
    events: []
  },
  actions: {
    async getAll({ commit }) {
      const events = await EventService.getAllEvents();
      commit('refreshEvents', events.data);
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