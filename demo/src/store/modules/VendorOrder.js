import axios from 'axios';

//localhost cannot run on android studio. 127.0.0.1 can run
const API_HOST_ANDROID_RUNNABLE = "http://127.0.0.1:8080";

export default{
  namespaced: true,
  state: {
    VendorOrderList: [],
    VendorOrderDetail: [],
  },
  mutations: {
    setVendorOrderList(state, VendorOrderList) {
      state.VendorOrderList = VendorOrderList;
    },
    setVendorOrderDetail(state, VendorOrderDetail) {
      state.VendorOrderDetail = VendorOrderDetail;
    },
  },
  actions: {
    async getVendorOrderList(context) {
      await axios.get(`${API_HOST_ANDROID_RUNNABLE}/order/all`)
      .then((response) => {
        console.log("VendorOrderList:")
        console.log(response.data);
        context.commit('setVendorOrderList', response.data);
      })
      .catch((error) => {
        console.log(error);
      })
    },
    async getVendorOrderDetail(context, {customerId, orderId}) {
      await axios.get(`${API_HOST_ANDROID_RUNNABLE}/customer/${customerId}/order/${orderId}/detail`)
      .then((response) => {
        console.log("VendorOrderDetail:")
        console.log(response.data);
        context.commit('setVendorOrderDetail', response.data);
      })
      .catch((error) => {
        console.log(error);
      })
    },
    async cancelOrder(context, {customerId, orderId}) {
      const cancel = { 
        status: "cancelled", 
        cancelPerson: "Vendor", 
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
        context.dispatch("getVendorOrderList");
        context.dispatch("getVendorOrderDetail", {customerId, orderId});
      })
      .catch((error) => {
        console.log(error);
      })
    },
    async shipOrder(context, {customerId, orderId}) {
      const cancel = { 
        status: "shipped",
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
        console.log("ShipOrder:")
        console.log(response.data);
        context.dispatch("getVendorOrderList");
        context.dispatch("getVendorOrderDetail", {customerId, orderId});
      })
      .catch((error) => {
        console.log(error);
      })
    },
  },
  getters: {
  },
}