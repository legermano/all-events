<template>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <router-link to="/" class="nav-link">
      <a class="navbar-brand">All Events</a>
    </router-link>
    <button
      class="navbar-toggler"
      type="button"
      data-toggle="collapse"
      data-target="#navBarToggler"
      aria-controls="navBarToggler"
      aria-expanded="false"
      aria-label="Toggle navigation"
    >
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse pl-3" id="navBarToggler">
      <div class="navbar-nav mr-auto">
        <li class="nav-item">
          <router-link v-if="currentUser" to="/" class="nav-link">
            Usuário
          </router-link>
        </li>
      </div>

      <div v-if="!currentUser" class="navbar-nav ml-a">
        <li class="nav-item">
          <router-link to="/register" class="nav-link">
            <font-awesome-icon icon="user-plus" />
            Criar conta
          </router-link>
        </li>
        <li class="nav-item">
          <router-link to="/login" class="nav-link">
            <font-awesome-icon icon="sign-in-alt" />
            Entrar
          </router-link>
        </li>
      </div>

      <div v-if="currentUser" class="navbar-nav ml-auto">
        <li class="nav-item">
          <router-link to="/profile" class="nav-link">
            <font-awesome-icon icon="user" />
            {{ currentUser.nome }}
          </router-link>
        </li>
        <li class="nav-item">
          <a class="nav-link" style="cursor:pointer" @click.prevent="logOut">
            <font-awesome-icon icon="sign-out-alt" />
            Sair
          </a>
        </li>
      </div>
    </div>

  </nav>
</template>

<script>
export default {
  name: 'NavBar',
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    }
  },
  methods: {
    logOut() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/login');
    }
  }
}
</script>