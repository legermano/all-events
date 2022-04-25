import UserService from "@/services/user.service";
import SubscriptionService from "@/services/subscription.service";

export const user = {
  namespaced: true,
  state: {
    subscriptions: [],
    cancelledSubscriptions: []
  },
  actions: {
    async getUserSubscritions({ commit }, { user }) {
      const subscritions = await UserService.getAllUserSubscription(user.id);
      commit('refreshUserSubscriptions', subscritions.data);
    },
    async registerSubscription({ dispatch }, { event, user }) {
      const sub = await SubscriptionService.register(event, user);
      await dispatch('getUserSubscritions', { user: user });
      return sub;
    },
    async cancelSubscription({ dispatch }, { subscription, user = null }) {
      const sub = await SubscriptionService.cancel(subscription);
      if(user) {
        await dispatch('getUserSubscritions', { user: user });
      }
      return sub;
    }
  },
  mutations: {
    refreshUserSubscriptions(state, subscriptions) {
      state.subscriptions = subscriptions;
    }
  }
}