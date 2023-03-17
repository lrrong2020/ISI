import { createStore} from 'vuex'
import Product from './modules/Product.js'
import Cart from './modules/Cart.js'
import User from './modules/User.js'
import Order from './modules/Order.js'

export default createStore({

  modules: {
    Product: Product,
    Cart: Cart,
    User: User,
    Order: Order,
  }

})