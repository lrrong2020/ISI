import { createStore} from 'vuex'
import Count from './modules/Count.js'
import Product from './modules/Product.js'
import Cart from './modules/Cart.js'

export default createStore({

  modules: {
    C: Count,
    Product: Product,
    Cart: Cart,
  }

})