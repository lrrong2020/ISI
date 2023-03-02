<script>
import Search from '@/components/Search.vue';
import Brand from '@/components/Brand.vue';
import Product from '@/components/Product.vue';

export default {
    name: "Home",
    components: {
        Search,
        Brand,
        Product,
    },
};
</script>
<template>
    <div class="home">
        <div class="content">
        <!--Header-->
            <div class="header">
                <div class="title">Product List</div>
            </div>
        <!--Main-->
            <div class="main">
                <div class="bg">
                    <div class="search">
                        <!-- <Search /> -->
                    </div>
                    <div class="sort">
                        <!-- <Brand /> -->
                    </div>
                    <div class="product">
                        <Product />
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<style lang="less" scoped>
.home {
    display: flex;
    flex-flow: column;
    height: 100%;
    .content {
        flex: 1;
        overflow-y: auto;
        .header {
            background-image: linear-gradient(#7232dd, #ffffff);
            // display: flex;
            // justify-content: space-between;
            // align-items: center;
            text-align: center;
            padding: 20px 20px 40px 20px;
            .title {
                font-size: 30px;
                font-weight: 600;
                font-family: "Comic Sans MS"
            }
        }
        .main{
            margin-top: -30px;
            .bg{
                background-color: #c9c9c9;
                padding: 10px 20px 0px 20px;
                border-radius: 30px 30px 0px 0px;
                
            }
        }
    }
}
</style>