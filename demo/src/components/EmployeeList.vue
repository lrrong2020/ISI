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
    mounted() {

         //pretend to load data from the api, may rewrite later on   
         const loadData = () => Promise.resolve(Object.values(data));

         //setTimeOut may be added to simulate network congestion

        //  loadData()
        //     .then((res) =>{
        //         console.log("Successfully fetched the data: ");
        //         console.log(res);

        //         //store the data to the local variable
        //         this.dataJson = res;

        //         //if(res.status.code == “404”)

        //         //
        //     });

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

    },
    // methods: {
    //     getPosts() {
    //         fetch('https://jsonplaceholder.typicode.com/todos/1')
    //         .then(response => response.json())
    //         .then(json => console.log(json))
    //     }
    // }
}
</script>