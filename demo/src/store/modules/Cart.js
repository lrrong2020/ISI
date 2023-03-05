import axios from "axios";

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
      state.CartTotalQuantity = data.reduce((acc, item) => {
        return acc + item.quantity;
      }, 0);
      state.CartTotalPrice = data.reduce((acc, item) => {
        return acc + item.quantity * item.price;
      }, 0);
    },
  },
  actions: {
    getCartItems(context) {
      axios.get("http://localhost:3000/cart")
      .then((response) => {
        context.commit("UpdateCart", response.data);
      })
    },
    addCartItem(context, data) {
      axios.post("http://localhost:3000/cart", data)
      .then((response) => {
        context.dispatch("getCartItems", response.data);
      })
    },
    deleteCartItem(context, data) {
      axios.delete(`http://localhost:3000/cart/${data}`)
      .then((response) => {
        context.dispatch("getCartItems", response.data);
      })
    },
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