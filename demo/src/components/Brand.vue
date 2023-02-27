<script>
import { reactive,toRefs } from 'vue';

export default {
    name: "Brand",
    setup() {
      let data = reactive({
        brands: [
            {
                id: 1,
                name: "Apple",
                icon: "icon-apple"
            },
            {
                id: 2,
                name: "HuaWei",
                icon: "icon-huawei"
            },
            {
                id: 3,
                name: "Xiaomi",
                icon: "icon-xiaomi"
            },
            {
                id: 4,
                name: "Lenovo",
                icon: "icon-lenovo"
            },
            {
                id: 5,
                name: "Samsung",
                icon: "icon-samsung"
            },
            {
                id: 6,
                name: "Oppo",
                icon: "icon-oppo-fill-round"
            },
            {
                id: 7,
                name: "Vivo",
                icon: "icon-vivo-fill-round"
            },
            {
                id: 8,
                name: "Realme",
                icon: "icon-xiaomi1"
            },
            {
                id: 9,
                name: "Nokia",
                icon: "icon-nokia"
            },
            {
                id: 10,
                name: "Asus",
                icon: "icon-asus1193370easyiconnet"
            }
        ],
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
    <van-grid :column-num="4">
        <van-grid-item v-for="(brand,index) in brands" :key="index">
            <svg class="icon" aria-hidden="true">
                <use v-bind:xlink:href="'#'+ brand.icon"></use>
            </svg>
            <p style="font-size: 15px;margin: 5px 0px 0px 0px;">{{ brand.name }}</p>
        </van-grid-item>
    </van-grid>
</template>

<style lang="less" scoped>
    .icon {
        width: 1em; 
        height: 1em;
        vertical-align: -0.15em;
        fill: currentColor;
        overflow: hidden;
    }
</style>