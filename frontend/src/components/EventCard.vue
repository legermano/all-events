<template>
  <div class="card h-100">
    <img class="card-img-top" src="https://picsum.photos/700/400" alt="">
    <div class="card-body">
      <h4 class="card-title">
        {{ mEvent.nome }}
      </h4>
      <p class="card-text">
        <small class="text-muted">
          Data de inicio: {{ $filters.formatDate(mEvent.dataInicio) }}
        </small>
        <br/>
        <small class="text-muted">
          Data de fim: {{ $filters.formatDate(mEvent.dataFim) }}
        </small>
      </p>
      <p class="card-text">
        {{ mEvent.descricao }}
      </p>
    </div>
    <div v-if="!userSubscription" class="card-footer">
      <button v-if="!loggedIn" type="button" class="btn btn-secondary" data-toggle="modal" :data-target="'#'+notLoggedModalId">
        Inscrever-se
      </button>

      <button v-if="loggedIn" type="button" class="btn btn-secondary" data-toggle="modal" :data-target="'#'+subscriptionModalId">
        Inscrever-se
      </button>
    </div>
    <div v-if="userSubscription" class="card-footer">
      <button type="button" class="btn btn-danger" data-toggle="modal" :data-target="'#'+cancelSubscriptionModalId">
        Cancelar inscrição
      </button>
    </div>
  </div>

  <!-- Modal não logado-->
  <div class="modal fade" :id="notLoggedModalId" tabindex="-1" aria-labelledby="notLoggedLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Inscrição</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          Não é possível se inscrever no evento sem estar logado.
          <a href="#" @click.prevent="goToLogin" data-dismiss="modal">Clique aqui</a>
          para fazer o login ou
          <a href="#" @click.prevent="goToRegister" data-dismiss="modal">aqui</a>
          para criar uma conta.
        </div>
      </div>
    </div>
  </div>

  <!-- Modal realizar inscrição-->
  <div class="modal fade" :id="subscriptionModalId" tabindex="-1" aria-labelledby="subscriptionModal" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Inscrição</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          Você irá realizar uma inscrição no evento {{ mEvent.nome }}. Tem certeza?
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-danger" data-dismiss="modal">Não</button>
          <button type="button" class="btn btn-success" data-dismiss="modal" @click.prevent="registerSubscription">Sim</button>
        </div>
      </div>
    </div>
  </div>

  <!-- Modal cancelar inscrição-->
  <div class="modal fade" :id="cancelSubscriptionModalId" tabindex="-1" aria-labelledby="subscriptionModal" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Inscrição</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          Tem certeza que deseja cancelar a inscrição no evento {{ event.nome }} ?
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-danger" data-dismiss="modal">Não</button>
          <button type="button" class="btn btn-success" data-dismiss="modal" @click.prevent="cancelSubscription">Sim</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "EventCard",
  props: ['event'],
  data() {
    return {
      mEvent: this.event
    }
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
    userSubscription() {
      return this.mEvent.usuarioInscricao;
    },
    currentUser() {
      return this.$store.state.auth.user;
    },
    notLoggedModalId() {
      return "notLoggedModal"+this.event.id;
    },
    subscriptionModalId() {
      return "subscriptionModal"+this.event.id;
    },
    cancelSubscriptionModalId() {
      return "cancelSubscriptionModal"+this.event.id;
    }

  },
  methods: {
    goToLogin() {
      this.$router.push('/login')
    },
    goToRegister() {
      this.$router.push('/register')
    },
    registerSubscription() {
      this.$store.dispatch('user/registerSubscription', {
        event: JSON.parse(JSON.stringify(this.mEvent)),
        user: JSON.parse(JSON.stringify(this.currentUser))
      }).then((sub) => {
        this.mEvent.usuarioInscricao = sub.data.id
      });
    },
    cancelSubscription() {
      this.$store.dispatch('user/cancelSubscription', {
        subscription: JSON.parse(JSON.stringify(this.mEvent.usuarioInscricao)),
        user: JSON.parse(JSON.stringify(this.currentUser))
      }).then(() => { this.mEvent.usuarioInscricao = null });
    }
  }
}
</script>