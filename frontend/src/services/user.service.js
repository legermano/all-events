import axios from "axios";
const API_URL = process.env.VUE_APP_API_URL+'usuario';

class UserService {
  getAllUsers() {
    return axios.get(`${API_URL}/todos`);
  }

  getSingleUser(userSearch) {
    return axios.get(API_URL, {
      email: userSearch.email
    });
  }

  createUser(user) {
    const body = {
      id: user.id,
      nome: user.username,
      endereco: user.address,
      telefone: user.phone,
      cpf: user.cpf,
      email: user.email,
      senha: user.password
    };

    return axios.post(API_URL, body).then(user => {
      localStorage.setItem('user', JSON.stringify(user.data));
      return user;
    });
  }

  getUserSubscriptions(userId) {
    return axios.get(`${API_URL}/${userId}/inscricoes`);
  }

  getUserSubscriptionsAll(userId) {
    return axios.get(`${API_URL}/${userId}/inscricoes/todas`);
  }
}

export default new UserService();