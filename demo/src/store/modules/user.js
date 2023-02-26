export default {
    state: {
       count: 0,
    },
    getter: {},
    mutations: {
        increment(state) {
            state.count++;
        }
    },
    actions: {
        //check login with local .json file

        //login and jsonplaceholder
        //https://jsonplaceholder.typicode.com/users
        // async login({ commit }, payload) {
        //     const response = await fetch('https://jsonplaceholder.typicode.com/users', {
        //         method: 'POST',
        //         body: JSON.stringify(payload),
        //         headers: {
        //             'Content-Type': 'application/json'
        //         }
        //     });
        //     const data = await response.json();
        //     if (!response.ok) {
        //         const error = new Error(data.message || 'Failed to login. Check your login credentials.');
        //         throw error;
        //     }
        //     commit('setUser', data);
        // },
        // //logout
        // logout({ commit }) {
        //     commit('setUser', null);
        // }
    }
}