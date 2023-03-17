import axios from 'axios';

//localhost cannot run on android studio. 127.0.0.1 can run
const API_HOST_ANDROID_RUNNABLE = "http://127.0.0.1:8080";

export default {
  namespaced: true,
  state: {
    Cart: [],
    CartTotal: 0,
    CartTotalQuantity: 0,
    CartTotalPrice: 0,
  },
  mutations: {
    UpdateCart(state, data) {
      state.Cart = data;
      state.CartTotal = data.length;
      state.CartTotalQuantity = state.Cart.reduce((a, b) => a + b.quantity, 0);
      state.CartTotalPrice = state.Cart.reduce((a, b) => a + b.quantity * b.product.price, 0);
    },
    //For Log out
    clearCart(state) {
      state.Cart = [];
      state.CartTotal = 0;
      state.CartTotalQuantity = 0;
      state.CartTotalPrice = 0;
    },
  },
  actions: {
    async getCartItems(context, customerId) {
      await axios.get(`${API_HOST_ANDROID_RUNNABLE}/customer/${customerId}/shoppingcart/`)
      .then((response) => {
        context.commit("UpdateCart", response.data);
        console.log("Cart:")
        console.log(response.data);
        console.log(response.data.length);
      })
      .catch((error) => {
        console.log(error);
      })
    },
    async addCartItems(context, {customerId, productId}) {
      await axios({
        method: 'post',
        url:`${API_HOST_ANDROID_RUNNABLE}/customer/${customerId}/shoppingcart/add`,
        data: productId,
        headers: {
          'Content-Type': 'application/json'
        }
      })
      .then((response) => {
        console.log("Add Cart:")
        console.log(response.data);
        context.dispatch("getCartItems", customerId);
        // context.dispatch("getCartItems", response.data);
      })
      .catch((error) => {
        console.log(error);
      })
    },
    async updateCartItems(context, {customerId, productId, quantity}) {
      console.log(customerId + " ID " + productId + " quan: " + quantity);
      await axios({
        method: 'post',
        url: `${API_HOST_ANDROID_RUNNABLE}/customer/${customerId}/shoppingcart/${productId}/update`,
        data: quantity,
        headers: {
          'Content-Type': 'application/json'
        }
      })
      .then((response) => {
        console.log("Update Cart:")
        console.log(response.data);
        context.dispatch("getCartItems", customerId);
        // context.dispatch("getCartItems", response.data);
      })
      .catch((error) => {
        console.log(error);
      })
    },

      




    // getCartItems(context) {
    //   axios.get("http://localhost:3000/cart")
    //   .then((response) => {
    //     context.commit("UpdateCart", response.data);
    //   })
    // },
    // addCartItem(context, data) {
    //   axios.post("http://localhost:3000/cart", data)
    //   .then((response) => {
    //     context.dispatch("getCartItems", response.data);
    //   })
    // },
    // deleteCartItem(context, data) {
    //   axios.delete(`http://localhost:3000/cart/${data}`)
    //   .then((response) => {
    //     context.dispatch("getCartItems", response.data);
    //   })
    // },
    // updateCartItem(context, data) {
    //   axios.put(`http://localhost:3000/cart/${data.id}`, data)
    //   .then((response) => {
    //     context.dispatch("UpdateCart", response.data);
    //   })
    // },
  },
  getters: {

  },
}