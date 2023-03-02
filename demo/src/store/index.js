import { createStore} from 'vuex'
import Count from './modules/Count.js'
import Product from './modules/Product.js'

export default createStore({

  modules: {
    C: Count,
    Product: Product,
  }

})