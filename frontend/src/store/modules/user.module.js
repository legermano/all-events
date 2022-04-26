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
      const subscritions = await UserService.getUserSubscriptions(user.id);
      commit('refreshUserSubscriptions', subscritions.data);
    },
    async getUserSubscritionsAll({ commit }, { user }) {
      const subscritions = await UserService.getUserSubscriptionsAll(user.id);
      commit('refreshUserSubscriptions', subscritions.data);
    },
    async registerSubscription({ dispatch }, { event, user }) {
      const sub = await SubscriptionService.register(event, user);
      await dispatch('getUserSubscritions', { user: user });
      return sub;
    },
    async cancelSubscription({ dispatch }, { subscription, user = null, allSubscriptions = false }) {
      const sub = await SubscriptionService.cancel(subscription);
      if(user) {
        if(allSubscriptions) {
          await dispatch('getUserSubscritionsAll', { user: user });
        } else {
          await dispatch('getUserSubscritions', { user: user });
        }
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