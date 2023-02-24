<template>
    <div>
        <button @click="getPosts">Show Posts</button>
        data:
    <div v-for="item in dataJson" :key="dataJson.num">
         <!-- <p>{{ item.num }} | {{ item.price }} | {{ item.desc }} | {{ item.title }} | {{ item.thumb }}</p> -->
         <p>{{ item.customerId }} | {{ item.customerName }}</p>

    </div>    

    Errors: {{errors}}
            
        
    </div>
</template>

<script>
import data from '@/product.json'

export default {
    name: "ProductList",
    data(){
        return {
           dataJson:[],
           errors:"",
        }
    },
    mounted(){
        this.axios.get('https://jsonplaceholder.typicode.com/users')
        .then((res) => {
            // console.log(res.data)
            // this.dataJson = res.data
        })

        console.log("I am herererererererer");
        fetch('http://localhost:8080/customer/all')
            .then((response) => {
                console.log("response: ");
                console.log(response.json);
                return response.json()
            })
            .then((data) => {
                console.log("=======================================");
                console.log(data);
                this.dataJson = data;
            }).catch((err) => {
                console.log("error occurred");
                console.log(err.stack);
                this.errors = err.stack;
            });

    }
    // },
    // methods: {
    //     getPosts() {
    //         fetch('https://jsonplaceholder.typicode.com/todos/1')
    //         .then(response => response.json())
    //         .then(json => console.log(json))
    //     }
    // }
}
</script>