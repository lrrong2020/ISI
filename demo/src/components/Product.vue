<script>
import { reactive,toRefs } from 'vue';

export default {
    name: "Product",
    setup() {
      let data = reactive({
        product: [
            {
                num: "2",
                price: "3.00",
                desc: "描述信息",
                title: "商品标题",
                thumb: "https://fastly.jsdelivr.net/npm/@vant/assets/ipad.jpeg",
            },
            {
                num: "3",
                price: "13.00",
                desc: "描述信息",
                title: "商品标题",
                thumb: "https://fastly.jsdelivr.net/npm/@vant/assets/ipad.jpeg",
            },
            {
                num: "4",
                price: "15.00",
                desc: "描述信息",
                title: "商品标题",
                thumb: "https://fastly.jsdelivr.net/npm/@vant/assets/ipad.jpeg",
            },
            {
                num: "5",
                price: "6.00",
                desc: "描述信息",
                title: "商品标题",
                thumb: "https://fastly.jsdelivr.net/npm/@vant/assets/ipad.jpeg",
            },
            {
                num: "6",
                price: "334.00",
                desc: "描述信息",
                title: "商品标题",
                thumb: "https://fastly.jsdelivr.net/npm/@vant/assets/ipad.jpeg",
            }
            
        ]
      });

      return {
        ...toRefs(data),
      }
    },
    
}
</script>

<template>
    Product
</template>

<style lang="less" scoped>


</style>