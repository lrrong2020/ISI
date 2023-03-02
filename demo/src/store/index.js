import { createStore} from 'vuex'
import Product from './modules/product'


export default createStore({
  modules: {
    products: Product,
  },

})