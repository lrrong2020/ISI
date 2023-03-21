import axios from 'axios';
import router from '../../router';

//localhost cannot run on android studio. 127.0.0.1 can run
const API_HOST_ANDROID_RUNNABLE = "http://127.0.0.1:8080";


export default {
  namespaced: true,
  state: {
    productList: [],
    errorMsg:"",
  },
  mutations: {
    //Set productList
    SetProductList(state, data){
      console.log("setProductList(state, data)");

      console.log("state:");
      console.log(state);

      console.log("data:");
      console.log(data);

      state.productList = data;
      
      console.log("state.productList");
      console.log(state.productList);



    },
    //search data from productList
    Search(state, data){
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

    displayErrorMsg(state, data){
      state.errorMsg = data;
    },

    //vendor add new product
    vendorAddProduct(state, data){
      console.log("[Vendor] Successfully add a new product!");
      console.log("data", data);
      router.push({ name: "VendorProductAddSuccess", params: { } });
    },

  },
  actions: {
    //Get ProductList from Backend
    getProductList(context){
      console.log("getProductList");    
        axios.get(`${API_HOST_ANDROID_RUNNABLE}/product/all`)
        .then((response)=>{
          console.log("reponse.data in getProductList()");
          console.log(response.data);
          context.commit('SetProductList',response.data);

          console.log(response.data[0].photo)
        }).catch((error)=>{

          alert(error.stack);
          context.commit('displayErrorMsg', error.stack.toString());
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
    searchProduct(context,searchValue){
      console.log(searchValue);
      axios.get(`${API_HOST_ANDROID_RUNNABLE}/product/search?productName=${searchValue}`)
      .then((response)=>{
        console.log("reponse.data in searchProduct()");
        console.log(response.data);

        //see if the returned data type: array or object


        //1. if it is a single object, then wrap it with an array
        if(!Array.isArray(response.data)){
          const responseDataArr = [];
          responseDataArr.push(response.data);
          console.log("responseDataArr");
          console.log(responseDataArr);
          context.commit('Search', responseDataArr);
        }
        else{
          context.commit('Search', response.data);
        }



        //2. else if it is an array, just commit data itself
        
      }).catch((error)=>{
        alert(error.message);
      });
    },

    filterProductByBrand(context, params, isSearching){

      var brand = params[0];
      var isSearching = params[1];

      console.log("brand from params", params);

      if(isSearching){
        console.log(context);
        console.log("isSearching: ", params[1]);
        context.commit('SetProductList', state.productList.filter(x => x.brand == brand));
      }
      else{
        console.log(context);
        console.log("isNotSearching: ", params[1]);
        axios.get(`${API_HOST_ANDROID_RUNNABLE}/product/filter?brand=` + brand)
        .then((response)=>{
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
            context.commit('SetProductList', response.data);
  
          // }
  
  
          //2. else if it is an array, just commit data itself
          
        }).catch((error)=>{
          alert(error.message);
        });
  
        
        context.commit();
      }
    },

    vendorAddProduct(context, value){
      console.log("vendorAddProduct()");
      console.log(value.photo);
      axios.post(`${API_HOST_ANDROID_RUNNABLE}/product/add`, value)
      .then((response)=>{
        console.log("vendorAddProduct response: ");
        console.log(response);

        
        console.log("response.data.photo: ",response.data.photo)

        //navigate to detail page
        //but for testing, navigate to product catalog instead. I'll change it later

        context.commit('vendorAddProduct', response.data);

      }).catch((error)=>{
        alert(error.message);
      });
    },


  },
  getters: {

  },
}