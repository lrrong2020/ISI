export default {
    state: {
       
    },
    mutations: {
      
    },
    actions: {
        Login({commit}, payload){
            return new Promise((resolve, reject) => {
                axios.post('http://localhost:3000/login', payload)
                .then(({data, status}) => {
                    if (status === 200) {
                        resolve(true);
                    }
                })
                .catch((err) => {
                    reject(err);
                });
            })
        }
    },
    getters: {
        
    }
}