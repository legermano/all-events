<template>
  <div class="my-4 d-md-flex align-items-center flex-wrap">
    <div class="sub-content">
      <h5 class="text-center text-md-left">
        {{ mSubs.evento.nome }}
        (
        {{ $filters.formatDate(mSubs.evento.dataInicio) }} -
        {{ $filters.formatDate(mSubs.evento.dataFim) }}
        )
        <span v-if="!mSubs.dataCancelamento" class="badge badge-success">Inscrito</span>
        <span v-if="mSubs.dataPresenca" class="badge badge-primary">Presença registrada</span>
        <span v-if="mSubs.dataCancelamento" class="badge badge-danger">Inscrição cancelada</span>
      </h5>
      <ul class="d-md-flex flex-wrap text-capitalize ff-open-sans">
        <li class="mr-md-4" title="Data de inscrição" >
          <font-awesome-icon icon="calendar-check"/>
          {{ $filters.formatDate(mSubs.dataInscricao) }}
        </li>
        <li v-if="mSubs.dataCancelamento" class="mr-md-4" title="Data de inscrição" >
          <font-awesome-icon icon="calendar-xmark"/>
          {{ $filters.formatDate(mSubs.dataCancelamento) }}
        </li>
        <li v-if="mSubs.dataPresenca" class="mr-md-4" title="Data de inscrição" >
          <font-awesome-icon icon="calendar-days"/>
          {{ $filters.formatDate(mSubs.dataPresenca) }}
        </li>
      </ul>
    </div>
  </div>
  <div v-if="!mSubs.dataCancelamento && !mSubs.dataPresenca" class="my-4 flex-shrink-0">
    <a class="btn d-block w-100 d-sm-inline-block btn-danger" @click="cancelSubscription">
      Cancelar
    </a>
  </div>
</template>

<script>
import { createToast } from 'mosha-vue-toastify';
import 'mosha-vue-toastify/dist/style.css';
export default {
  name: 'SubscriptionItem',
  props: ['subscription'],
  data() {
    return {
      mSubs: this.subscription
    }
  },
  methods: {
    cancelSubscription() {
      this.$store.dispatch('user/cancelSubscription', {
        subscription: this.mSubs,
        user: this.$store.state.auth.user,
        allSubscriptions: true
      }).then((sub) => {
        this.mSubs.dataCancelamento = sub.data.dataCancelamento
      },
      (error) => {
        createToast(error.response.data.message, {
          type: 'danger',
          transition: 'slide',
          position: 'top-right',
          showIcon: true,
        });
      })
    }
  }
}
</script>

<style scoped>
body{
  background:#f5f5f5;
  margin-top:20px;
}

ul {
  list-style: none;
}

.sub-box .img-holder {
  height: 65px;
  width: 65px;
  background-color: #4e63d7;
  background-image: -webkit-gradient(linear, left top, right top, from(rgba(78, 99, 215, 0.9)), to(#5a85dd));
  background-image: linear-gradient(to right, rgba(78, 99, 215, 0.9) 0%, #5a85dd 100%);
  font-family: "Open Sans", sans-serif;
  color: #fff;
  font-size: 22px;
  font-weight: 700;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-pack: center;
      -ms-flex-pack: center;
          justify-content: center;
  -webkit-box-align: center;
      -ms-flex-align: center;
          align-items: center;
  border-radius: 65px;
}

@media (min-width: 768px) {
  .sub-content ul li {
    border-bottom: 0;
    padding: 0;
  }
}

.sub-content ul li i {
  font-size: 20px;
  position: relative;
  top: 1px;
}
</style>