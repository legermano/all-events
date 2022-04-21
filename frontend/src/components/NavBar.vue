<template>
  <nav class="navbar navbar-expand navbar-dark bg-dark">
    <router-link to="/" class="nav-link">
      <a class="navbar-brand">AllEvents</a>
    </router-link>
    <div class="navbar-nav mr-auto">
      <li class="nav-item">
        <router-link v-if="currentUser" to="/" class="nav-link">
          User
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
  </nav>
</template>

<script>
export default {
  name: 'NavBar',
  computed: {
    currentUser() {
      var currentUser = this.$store.state.auth.user;

      if(currentUser && currentUser.data) {
        currentUser = currentUser.data;
      }

      return currentUser;
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