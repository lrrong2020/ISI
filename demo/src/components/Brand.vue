<script>
export default {
    name: "Brand",
    data() {
        return {
            brands: [],
        }
    },
    mounted() {
        this.getBrandList();
    },
    methods: {
        getBrandList() {
            this.axios.get("http://localhost:3000/brands").then((response) => {
                this.brands = response.data;
                console.log(response.data)
            })
        }
    },
}

</script>

<template>
    <van-grid :column-num="3" :border="false">
        <van-grid-item v-for="brand in brands" :key="brand.id">
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