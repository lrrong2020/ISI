import { createStore} from 'vuex'


const Count = {
  state: {
    sum: 0,
    COD: "COD",
    mazra: "mazra",
  },
  mutations: {
    Increase(state, value){
      console.log("mutation Increase");
      state.sum += value;
    },
    Decrease(state, value){
      console.log("mutation Decrease");
      state.sum -= value;
    },
  },
  actions: {
    //1. context: 上下文，包含了当前store的所有信息
    //2. value: 传递的参数
    // increase(context,value){
    //   console.log("action increase");
    //   context.commit('Increase', value);
    // },
    // decrease(context,value){
    //   console.log("action decrease");
    //   context.commit('Decrease',value);
    // }

    //连续调用dispatch
    // demo1(context,value){
    //   console.log("demo1");
    //   context.dispatch('demo2',value);
    // },
    // demo2(context,value){
    //   console.log("demo2");
    //   context.dispatch('demo3',value);
    // },
    // demo3(context,value){
    //   console.log("demo3");
    // },
  },
  //加工state
  getters: {
    bigSum(state){
      return state.sum * 10;
    },
  },
}

const Product = {
  state: {
    productList: [],
  },
  mutations: {

  },
  actions: {

  },
  getters: {

  },
}

export default createStore({

  modules: {
    C: Count,
    Product: Product,
  }

})