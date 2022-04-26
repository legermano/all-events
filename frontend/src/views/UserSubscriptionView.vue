<template>
  <div class="container">
    <h1 class="text-center">Inscrições</h1>
    <SubscriptionsList />
  </div>
</template>

<script>
import SubscriptionsList from '@/components/SubscriptionsList.vue';
export default {
  name: 'UserSubscription',
  components: {
    SubscriptionsList
  }
}
</script>
