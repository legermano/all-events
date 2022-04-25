import axios from "axios";
const API_URL = process.env.VUE_APP_API_URL+'inscricao';

class SubscriptionService {
  async register(event, user) {
    return await axios.post(`${API_URL}/registrar`, {
      ref_usuario: user.id,
      ref_evento: event.id
    });
  }

  async cancel(subscription) {
    return await axios.post(`${API_URL}/cancelar/${subscription.id}`);
  }
}

export default new SubscriptionService();