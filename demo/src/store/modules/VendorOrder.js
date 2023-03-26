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
    search(state, data){
      state.VendorOrderList = data;
    }
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

    searchOrderById(context, searchValue){
      console.log(searchValue);
      axios.get(`${API_HOST_ANDROID_RUNNABLE}/order/${searchValue}`)
      .then((response)=>{
        console.log("reponse.data in searchOrderById()");
        console.log(response.data);

        //see if the returned data type: array or object
        //1. if it is a single object, then wrap it with an array
        if(!Array.isArray(response.data)){
          const responseDataArr = [];
          responseDataArr.push(response.data);
          console.log("responseDataArr");
          console.log(responseDataArr);
          context.commit('search', responseDataArr);
        }
        else{
          context.commit('search', response.data);
        }
        //2. else if it is an array, just commit data itself
        
      }).catch((error)=>{
        alert(error.message);
      });
    },
  },
  getters: {
  },
}