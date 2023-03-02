export default {
    state: {
        products: []
    },
    mutations: {
        setProduct(state, payload) {
            state.products = payload
        }
    },
    actions: {
       getProduct({commit}) {
        this.axios.get('http://localhost:3000/products')
        .then((response) => {
            commit('setProduct', response.data)
            console.log(response)
        })
       }
    },
    getters: {
        
    }
}