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
            <label for="username">Nome</label>
            <Field name="username" type="text" class="form-control"/>
            <ErrorMessage name="username" class="error-feedback"/>
          </div>
          <div class="form-group">
            <label for="email">Email</label>
            <Field name="email" type="email" class="form-control"/>
            <ErrorMessage name="email" class="error-feedback"/>
          </div>
          <div class="form-group">
            <label for="phone">Telefone</label>
            <Field name="phone" type="phone" class="form-control"/>
            <ErrorMessage name="phone" class="error-feedback"/>
          </div>
          <div class="form-group">
            <label for="cpf">CPF</label>
            <Field name="cpf" type="text" class="form-control"/>
            <ErrorMessage name="cpf" class="error-feedback"/>
          </div>
          <div class="form-group">
            <label for="address">Endereço</label>
            <Field name="address" type="text" class="form-control"/>
            <ErrorMessage name="address" class="error-feedback"/>
          </div>
          <div class="form-group">
            <label for="password">Senha</label>
            <Field name="password" type="password" class="form-control"/>
            <ErrorMessage name="password" class="error-feedback"/>
          </div>
          <div class="form-group">
            <button class="btn btn-primary btn-block" :disabled="loading">
              <span v-show="loading" class="spinner-border spinner-border-sm" />
                Criar conta
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
        .required("Email é obrigatório!")
        .email("Email inválido!"),
      phone: yup
        .string()
        .matches(/^[(]?(\d{2})[)]?\s?(\d{4,5})\s?-?\s?(\d{4})$/, "Telefone inválido!"),
      cpf: yup
        .string()
        .required("CPF é obrigatório")
        .matches(/^([0-9]{3})\.?([0-9]{3})\.?([0-9]{3})-?([0-9]{2})$/, "CPF inválido!"),
      address: yup
        .string(),
      password: yup
        .string()
        .required("Senha é obrigatória!")
        .min(6, "Deve ter pelo menos 6 caracteres!")
        .max(40, "Deve ter no máximo 40 caracteres!")
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
      this.$router.push("/");
    }
  },
  methods: {
    handleRegister(user) {
      this.message = "";
      this.successful = false;
      this.loading = true;
      this.$store.dispatch("auth/register", user).then(
        () => {
          this.$router.push("/");
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

<style scoped src="@/assets/css/login.css"></style>