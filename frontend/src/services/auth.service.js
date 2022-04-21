import axios from "axios";
const API_URL = process.env.VUE_APP_API_URL+"autenticacao/"

class AuthService {
  login(user) {
    return axios
      .post(API_URL+ 'usuario', {
        email: user.email,
        senha: user.password
      })
      .then(response => {
        localStorage.setItem('user', JSON.stringify(response.data));
        return response;
      })
  }

  logout() {
    localStorage.removeItem('user');
  }
}

export default new AuthService();