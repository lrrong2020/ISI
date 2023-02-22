<template>
    <div>
        <button @click="getPosts">Show Posts</button>
    <div v-for="item in dataJson" :key="dataJson.num">
        <p>{{ item.num }} | {{ item.price }} | {{ item.desc }} | {{ item.title }} | {{ item.thumb }}</p>
    </div>    
            
        
    </div>
</template>

<script>
import data from '@/product.json'

export default {
    name: "ProductList",
    data(){
        return {
           dataJson:[],
        }
    },
    mounted() {

         //pretend to load data from the api, may rewrite later on   
         const loadData = () => Promise.resolve(Object.values(data));

         //setTimeOut may be added to simulate network congestion

         loadData()
            .then((res) =>{
                console.log("Successfully fetched the data: ");
                console.log(res);

                //store the data to the local variable
                this.dataJson = res;

                //if(res.status.code == “404”)

                //
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