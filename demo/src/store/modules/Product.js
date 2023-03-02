import axios from 'axios';
export default {
  namespaced: true,
  state: {
    productList: [],
  },
  mutations: {
    //Set productList
    SetProductList(state, data){
      state.productList = data;
    },
    //search data from productList
    Search(state, data){
      state.productList = data;
    },
  },
  actions: {
    //Get ProductList from Backend
    getProductList(context){
      // console.log("getProductList");
      setTimeout(()=>{
        axios.get("http://localhost:3000/fakeBackendProducts")
        .then((response)=>{
          // console.log(response.data);
          context.commit('SetProductList',response.data);
        }).catch((error)=>{
          alert(error.message);
        });
      },500);
    },
    //Search Product
    searchProduct(context,searchValue){
      console.log(searchValue);
      axios.get("http://localhost:8080/product/search?productName=" + searchValue)
      .then((response)=>{
        console.log(response.data);
        context.commit('Search',response.data);
      }).catch((error)=>{
        alert(error.message);
      });
    },
  },
  getters: {

  },
}