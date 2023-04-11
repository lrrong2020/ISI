export default {
  namespaced: true,
  state: {
    include: [],
  },
  mutations: {
    AddInclude(state) {
      const data = ['Home', 'SearchPage', 'OrderList', 'VendorHome', 'VendorOrderList'];
      state.include = data;
    },
    ClearInclude(state) {
      state.include = [];
    },
  },
  actions: {
  
  },
  getters: {
   
  },
}