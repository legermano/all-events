<template>
<div class="container">
  <div class="row">
    <div class="col-lg-10 mx-auto">
      <div class="mb-60">
        <div class="sub-box d-md-flex align-items-center justify-content-between mb-30"
             v-for="subscription in subscriptions" :key="subscription.id"
        >
          <SubscriptionItem :subscription=subscription />
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import SubscriptionItem from '@/components/SubscriptionItem.vue';
export default {
  name: 'SubscriptionsList',
  components: {
    SubscriptionItem
  },
  beforeCreate() {
    const user = JSON.parse(JSON.stringify(this.$store.state.auth.user));
    this.$store.dispatch('user/getUserSubscritionsAll', { user: user });
  },
  computed: {
    subscriptions() {
      const sub = this.$store.state.user.subscriptions;
      return sub;
    }
  }
}
</script>

<style scoped>
.sub-box {
  -webkit-box-shadow: 0 0 35px 0 rgba(130, 130, 130, 0.2);
          box-shadow: 0 0 35px 0 rgba(130, 130, 130, 0.2);
  border-radius: 10px;
  margin: 5px 25px;
  padding: 5px 15px;
  border: 1px solid rgba(0,0,0,.125);
}
</style>