<script>
import { reactive,toRefs } from 'vue';

export default {
    name: "Product",
    data() {
        return {
            products: [],
        }
    },
    mounted() {
        this.getProductList();
    },
    methods: {
        getProductList() {
            this.axios.get("http://localhost:3000/products").then((response) => {
                this.products = response.data;
                console.log(response.data)
            })
        }
    },
    
}
</script>

<template>
   <van-card
        v-for="item in products"
        :key="item.id"
        :num="item.num"
        :price="item.price"
        :desc="item.desc"
        :title="item.title"
        :thumb="item.thumb"
        >
        <template #tags>
            <van-tag plain type="primary">tag1</van-tag>
            <van-tag plain type="primary">tag2</van-tag>
        </template>
        <template #footer>
            <van-button size="mini">Cart</van-button>
            <van-button size="mini">Buy</van-button>
        </template>
    </van-card>
</template>

<style lang="less" scoped>


</style>