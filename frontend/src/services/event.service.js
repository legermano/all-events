import axios from "axios";
const API_URL = process.env.VUE_APP_API_URL+'evento';

class EventService {
  getAllEvents() {
    return axios.get(`${API_URL}/todos`);
  }

  getAllFutureEvents() {
    return axios.get(`${API_URL}/futuros`);
  }

  getAllActiveEvents() {
    return axios.get(`${API_URL}/ativos`);
  }
}

export default new EventService();