<template>
  <div class="card h-100">
    <img class="card-img-top" src="https://picsum.photos/700/400" alt="">
    <div class="card-body">
      <h4 class="card-title">
        {{ event.nome }}
      </h4>
      <p class="card-text">
        <small class="text-muted">
          Data de inicio: {{ $filters.formatDate(event.dataInicio) }}
        </small>
        <br/>
        <small class="text-muted">
          Data de fim: {{ $filters.formatDate(event.dataFim) }}
        </small>
      </p>
      <p class="card-text">
        {{ event.descricao }}
      </p>
    </div>
    <div class="card-footer">
      <button v-if="!loggedIn" type="button" class="btn btn-secondary" data-toggle="modal" data-target="#notLoggedModal">
        Inscrever-se
      </button>

      <button v-if="loggedIn" type="button" class="btn btn-secondary" data-toggle="modal" data-target="#subscriptionModal">
        Inscrever-se
      </button>
    </div>
  </div>

  <!-- Modal não logado-->
  <div class="modal fade" id="notLoggedModal" tabindex="-1" aria-labelledby="notLoggedLabel" aria-hidden="true">
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

  <!-- Modal não logado-->
  <div class="modal fade" id="subscriptionModal" tabindex="-1" aria-labelledby="subscriptionModal" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Inscrição</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          Você irá realizar uma inscrição no evento {{ event.nome }}. Tem certeza?
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-danger" data-dismiss="modal">Não</button>
          <button type="button" class="btn btn-success" data-dismiss="modal">Sim</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "EventCard",
  props: ['event'],
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    }
  },
  methods: {
    goToLogin() {
      this.$router.push('/login')
    },
    goToRegister() {
      this.$router.push('/register')
    }
  }
}
</script>