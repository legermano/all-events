<template>
  <div class="container">
    <div class="row">
        <div class="col-lg-4 col-sm-6 mb-4" v-for="event in events" :key="event.id">
          <EventCard :event=event />
        </div>
    </div>
  </div>
</template>

<script>
import EventCard from '@/components/EventCard.vue';

export default {
  name: "EventList",
  components: {
    EventCard
  },
  beforeCreate() {
    this.$store.dispatch('event/getAll');
  },
  computed: {
    events() {
      return this.$store.state.event.events;
    }
  }
}
</script>