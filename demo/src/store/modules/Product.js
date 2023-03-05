import axios from 'axios';



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
    }
  },
  actions: {
    //Get ProductList from Backend
    getProductList(context){
      console.log("getProductList");    
        axios.get("http://localhost:3000/fakeBackendProducts")
        .then((response)=>{
          console.log("reponse.data in getProductList()");
          console.log(response.data);
          context.commit('SetProductList',response.data);
        }).catch((error)=>{

          alert(error.stack);
          context.commit('displayErrorMsg', error.stack.toString());
        });
    },
    //Search Product
    // searchProduct(context,searchValue){
    //   console.log(searchValue);
    //   axios.get("http://localhost:8080/product/search?productName=" + searchValue)
    //   .then((response)=>{
    //     console.log(response.data);
    //     context.commit('Search',response.data);
    //   }).catch((error)=>{
    //     alert(error.message);
    //   });
    // },
    searchProduct(context,searchValue){

      console.log(searchValue);
      axios.get(`http://127.0.0.1:8080/product/search?productName=${searchValue}`)
      .then((response)=>{
        console.log("reponse.data in searchProduct()");
        console.log(response.data);

        //see if the returned data type: array or object


        //1. if it is a single object, then wrap it with an array
        const responseDataArr = [];
        responseDataArr.push(response.data);
        console.log("responseDataArr");
        console.log(responseDataArr);
        context.commit('Search', responseDataArr);


        //2. else if it is an array, just commit data itself
        
      }).catch((error)=>{
        alert(error.message);
      });
    },

    filterProductByBrand(context, brand){
      console.log();

      axios.get("http://127.0.0.1:8080/product/filter?brand=" + brand)
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
    },


  },
  getters: {

  },
}