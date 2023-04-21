import axios from 'axios';

//localhost cannot run on android studio. 127.0.0.1 can run
const API_HOST_ANDROID_RUNNABLE = "http://127.0.0.1:8080";

export default {
  namespaced: true,
  state: {
    OrderList: [],
    OrderDetail: [],
  },
  mutations: {
    setOrderList(state, OrderList) {
      state.OrderList = OrderList;
    },
    setOrderDetail(state, OrderDetail) {
      state.OrderDetail = OrderDetail;
    },
  },
  actions: {
    async checkOut(context, customerId) {
      await axios.post(`${API_HOST_ANDROID_RUNNABLE}/customer/${customerId}/shoppingcart/checkout`)
      .then((response) => {
        console.log("Checkout:")
        console.log(response.data);
      })
      .catch((error) => {
        console.log(error);
      })
    },
    async getOrderList(context, customerId) {
      await axios.get(`${API_HOST_ANDROID_RUNNABLE}/customer/${customerId}/order/all`)
      .then((response) => {
        console.log("OrderList:")
        console.log(response.data);
        const reverseOrderList = response.data.reverse();
        context.commit('setOrderList', reverseOrderList);
      })
      .catch((error) => {
        console.log(error);
      })
    },
    async getOrderDetail(context, {customerId, orderId}) {
      await axios.get(`${API_HOST_ANDROID_RUNNABLE}/customer/${customerId}/order/${orderId}/detail`)
      .then((response) => {
        console.log("OrderDetail:")
        console.log(response.data);
        context.commit('setOrderDetail', response.data);
      })
      .catch((error) => {
        console.log(error);
      })
    },
    async cancelOrder(context, {customerId, customerName, orderId}) {
      const cancel = { 
        status: "cancelled", 
        cancelPerson: customerName + " " + "(customer)", 
      };
      await axios({
        method: 'post',
        url:`${API_HOST_ANDROID_RUNNABLE}/customer/${customerId}/order/${orderId}`,
        data: JSON.stringify(cancel),
        headers: {
          'Content-Type': 'application/json'
        }
      }) 
      .then((response) => {
        console.log("CancelOrder:")
        console.log(response.data);
        context.dispatch("getOrderList", customerId);
        context.dispatch("getOrderDetail", {customerId, orderId});
      })
      .catch((error) => {
        console.log(error);
      })
    },
  },
  getters: {
  },
};