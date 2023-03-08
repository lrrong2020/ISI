import { createStore} from 'vuex'
import Product from './modules/Product.js'
import Cart from './modules/Cart.js'
import User from './modules/User.js'

export default createStore({

  modules: {
    Product: Product,
    Cart: Cart,
    User: User,
  }

})