<template>
  <div class="col-md-12">
    <div class="card card-container">
      <img
        id="profile-img"
        class="profile-img-card"
        src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
      />
      <Form @submit="handleRegister" :validation-schema="schema">
        <div v-if="!successful">
          <div class="form-group">
            <label for="name">Nome</label>
            <Field name="name" type="text" class="form-control"/>
            <ErrorMessage name="name" class="error-feedback"/>
          </div>
          <div class="form-group">
            <label for="email">Email</label>
            <Field name="email" type="email" class="form-control"/>
            <ErrorMessage name="email" class="error-feedback"/>
          </div>
          <div class="form-group">
            <label for="password">Senha</label>
            <Field name="password" type="password" class="form-control"/>
            <ErrorMessage name="password" class="error-feedback"/>
          </div>
          <div class="form-group">
            <button class="btn btn-primary btn-block" :disabled="loading">
              <span v-show="loading" class="spinner-border spinner-border-sm" />
                Sign Up
            </button>
          </div>
        </div>
      </Form>
      <div
        v-if="message"
        class="alert"
        :class="succeccful ? 'alert-success' : 'alert-danger'"
      >
        {{ message }}
      </div>
    </div>
  </div>
</template>

<script>
import { Form, Field, ErrorMessage } from "vee-validate";
import * as yup from "yup";
export default {
  name: "RegisterForm",
  components: {
    Form,
    Field,
    ErrorMessage
  },
  data() {
    const schema = yup.object().shape({
      username: yup
        .string()
        .required("Nome é obrigatório!")
        .min(3, "Deve ter pelo menos 3 caracteres!"),
      email: yup
        .string()
        .required("Email is obrigatório!")
        .email("Email inválido!"),
      password: yup
        .string()
        .required("Must be at least 6 characters!")
        .min(6, "Must be at least 6 characters!")
        .max(40, "Must be maximum 40 characters!")
    });
    return {
      successful: false,
      loading: false,
      message: "",
      schema
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    }
  },
  mounted() {
    if(this.loggedIn) {
      this.$router.push("/about");
    }
  },
  methods: {
    handleRegister(user) {
      this.message = "";
      this.successful = false;
      this.loading = true;
      this.$store.dispatch("auth/register", user).then(
        (data) => {
          this.message = data.message;
          this.successful = true;
          this.loading = false;
        },
        (error) => {
          this.message =
            (error.response &&
             error.response.data &&
             error.response.data.message
            ) ||
            error.message ||
            error.toString();
            this.successful = false;
            this.loading = false;
        }
      )
    }
  }
}
</script>

<style scoped>
@import '@/assets/css/login.css';
</style>