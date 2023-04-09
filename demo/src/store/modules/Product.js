import axios from 'axios';
import router from '../../router';

//localhost cannot run on android studio. 127.0.0.1 can run
const API_HOST_ANDROID_RUNNABLE = "http://127.0.0.1:8080";


export default {
  namespaced: true,
  state: {
    productList: [],
    productListPaging: [],
    errorMsg: "",
    ProductDetail: {},
    page: 0,
    noOfPages: 0,
    noOfTotalElements: 0,
  },
  mutations: {
    //Set productList empty
    SetProductListEmpty(state) {
      state.productList = [];
    },
    //Set productList
    SetProductList(state, data) {
      console.log("setProductList(state, data)");

      console.log("state:");
      console.log(state);

      console.log("data:");
      console.log(data);

      state.productList = data;

      console.log("state.productList");
      console.log(state.productList);
    },

    SetProductListPaging(state, data) {
      console.log("setting product List paging");
      console.log(data);
      state.productListPaging = data;
    },

    SetNoOfItems(state, data) {
      console.log("setting no of items: ", data);
      state.noOfTotalElements = data;
    },

    SetNoOfPages(state, data) {
      console.log("setting no of pages: ", data);
      state.noOfPages = data;
    },

    //Set ProductDetail
    SetProductDetail(state, data) {
      state.ProductDetail = data;
    },
    //search data from productList
    Search(state, data) {
      console.log("In Seach(state, data)");
      console.log("data:");
      console.log(data);
      state.productList = data;

      // state.productList = Object.values(data);


      console.log("state.productList");
      console.log(state.productList);


      console.log("JSON.parse(JSON.stringify(state.productList))");
      console.log(JSON.parse(JSON.stringify(state.productList)));

    },

    displayErrorMsg(state, data) {
      state.errorMsg = data;
    },

    //vendor add new product
    vendorAddProduct(state, data) {
      console.log("[Vendor] Successfully add a new product!");
      console.log("data", data);
      router.push({ name: "VendorProductAddSuccess", params: {} });
    },

  },
  actions: {
    //Get ProductList from Backend
    async getProductList(context) {
      console.log("getProductList");
      await axios.get(`${API_HOST_ANDROID_RUNNABLE}/product/all`)
        .then((response) => {
          context.commit('SetProductList', response.data);
        }).catch((error) => {
          console.log(error.stack);
          context.commit('displayErrorMsg', error.stack.toString());
        });
    },

    //Get ProductList from Backend with Paging
    getProductListPaging(context, params) {
      console.log("getProductListPaging");

      //get product list
      axios.get(`${API_HOST_ANDROID_RUNNABLE}/product/all?page=${params[0]}&size=${params[1]}`)
        .then((response) => {
          console.log(response.data);
          context.commit('SetProductListPaging', response.data);
        }).catch((error) => {
          console.log(error.stack);
          context.commit('displayErrorMsg', error.stack.toString());
        });

      //get no of items
      axios.get(`${API_HOST_ANDROID_RUNNABLE}/product/all/noOfItems?page=${params[0]}&size=${params[1]}`)
        .then((response) => {
          console.log(response.data);
          context.commit('SetNoOfItems', response.data);
        }).catch((error) => {
          console.log(error.stack);
          context.commit('displayErrorMsg', error.stack.toString());
        });

      //get no of pages
      axios.get(`${API_HOST_ANDROID_RUNNABLE}/product/all/noOfPages?page=${params[0]}&size=${params[1]}`)
        .then((response) => {
          console.log(response.data);
          context.commit('SetNoOfPages', response.data);
        }).catch((error) => {
          console.log(error.stack);
          context.commit('displayErrorMsg', error.stack.toString());
        });
    },

    //Get ProductDetail from Backend
    async getProductDetail(context, productId) {
      console.log("getProductDetail");
      console.log(productId);
      await axios.get(`${API_HOST_ANDROID_RUNNABLE}/product/${productId}`)
        .then((response) => {
          console.log("reponse.data in getProductDetail()");
          console.log(response.data);
          context.commit('SetProductDetail', response.data);
        }).catch((error) => {
          alert(error.message);
        });
    },

    //Search Product
    // searchProduct(context,searchValue){
    //   console.log(searchValue);
    //   axios.get(`${API_HOST_ANDROID_RUNNABLE}/product/search?productName=` + searchValue)
    //   .then((response)=>{
    //     console.log(response.data);
    //     context.commit('Search',response.data);
    //   }).catch((error)=>{
    //     alert(error.message);
    //   });
    // },

    async searchProduct(context, searchValue) {
      console.log(searchValue);
      await axios.get(`${API_HOST_ANDROID_RUNNABLE}/product/search?productName=${searchValue}`)
        .then((response) => {
          console.log("reponse.data in searchProduct()");
          console.log(response.data);


          //see if the returned data type: array or object


          //1. if it is a single object, then wrap it with an array
          if (!Array.isArray(response.data)) {
            const responseDataArr = [];
            responseDataArr.push(response.data);
            console.log("responseDataArr");
            console.log(responseDataArr);
            context.commit('Search', responseDataArr);
          }
          else {
            context.commit('Search', response.data);
          }



          //2. else if it is an array, just commit data itself

        }).catch((error) => {
          alert(error.message);
        });
    },


    async searchProductById(context, id) {
      console.log(id);
      await axios.get(`${API_HOST_ANDROID_RUNNABLE}/product/${id}`)
        .then((response) => {
          console.log("reponse.data in searchProductById()");
          console.log(response.data);


          //see if the returned data type: array or object


          //1. if it is a single object, then wrap it with an array
          if (!Array.isArray(response.data)) {
            const responseDataArr = [];
            responseDataArr.push(response.data);
            console.log("responseDataArr");
            console.log(responseDataArr);
            context.commit('Search', responseDataArr);
          }
          else {
            context.commit('Search', response.data);
          }



          //2. else if it is an array, just commit data itself

        }).catch((error) => {
          alert(error.message);
        });
    },


    async searchProductAndFilterByBrand(context, params) {

      var searchValue = params[0];
      var brand = params[1];

      console.log(searchValue);

      await axios.get(`${API_HOST_ANDROID_RUNNABLE}/product/search?productName=${searchValue}&brand=${brand}`)
        .then((response) => {
          console.log("reponse.data in searchProduct()");
          console.log(response.data);


          //see if the returned data type: array or object


          //1. if it is a single object, then wrap it with an array
          if (!Array.isArray(response.data)) {
            const responseDataArr = [];
            responseDataArr.push(response.data);
            console.log("responseDataArr");
            console.log(responseDataArr);
            context.commit('Search', responseDataArr);
          }
          else {
            context.commit('Search', response.data);
          }



          //2. else if it is an array, just commit data itself

        }).catch((error) => {
          alert(error.message);
        });
    },

    async filterProductByBrand(context, params) {
      await axios.get(`${API_HOST_ANDROID_RUNNABLE}/product/filter?brand=${params[0]}&page=${params[1]}&size=${params[2]}`)
        .then((response) => {
          console.log("reponse.data in filterProductByBrand()");
          console.log(response.data);


          //see if the returned data type: array or object
          console.log("typeof response.data");

          var typeOfResponseData = typeof response.data;
          console.log(typeOfResponseData)

          // if(typeof response.data !== "array")
          // {

          // //1. if it is a single object, then wrap it with an array
          // const responseDataArr = [];
          // responseDataArr.push(response.data);
          // console.log("responseDataArr");
          // console.log(responseDataArr);
          // context.commit('SetProductList', responseDataArr);

          // }

          // else
          // {
          context.commit('SetProductListPaging', response.data);

          // }


          //2. else if it is an array, just commit data itself

        }).catch((error) => {
          alert(error.message);
        });


      //get no of items
      axios.get(`${API_HOST_ANDROID_RUNNABLE}/product/all/noOfItems?brand=${params[0]}&page=${params[1]}&size=${params[2]}`)
        .then((response) => {
          console.log(response.data);
          context.commit('SetNoOfItems', response.data);
        }).catch((error) => {
          console.log(error.stack);
          context.commit('displayErrorMsg', error.stack.toString());
        });

      //get no of pages
      axios.get(`${API_HOST_ANDROID_RUNNABLE}/product/all/noOfPages?brand=${params[0]}&page=${params[1]}&size=${params[2]}`)
        .then((response) => {
          console.log(response.data);
          context.commit('SetNoOfPages', response.data);
        }).catch((error) => {
          console.log(error.stack);
          context.commit('displayErrorMsg', error.stack.toString());
        });
    },


    async vendorAddProduct(context, value) {
      console.log("vendorAddProduct()");
      console.log(value.photo);
      await axios.post(`${API_HOST_ANDROID_RUNNABLE}/product/add`, value)
        .then((response) => {
          console.log("vendorAddProduct response: ");
          console.log(response);


          console.log("response.data.photo: ", response.data.photo)

          //navigate to detail page
          //but for testing, navigate to product catalog instead. I'll change it later

          context.commit('vendorAddProduct', response.data);

        }).catch((error) => {
          alert(error.message);
        });
    },


  },
  getters: {

  },
}