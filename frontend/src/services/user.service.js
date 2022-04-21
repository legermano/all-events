import axios from "axios";
const API_URL = process.env.VUE_APP_API_URL+'usuario';

class UserService {
  getAllUsers() {
    return axios.get(API_URL + '/todos');
  }

  getSingleUser(userSearch) {
    return axios.get(API_URL, {
      email: userSearch.email
    });
  }

  createUser(user) {
    return axios.post(API_URL, JSON.stringify(user));
  }
}

export default new UserService();