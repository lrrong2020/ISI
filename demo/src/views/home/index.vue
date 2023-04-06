<script>
// import Search from '@/components/Search.vue';
import Brands from '@/components/HomeBrands.vue';
import Product from '@/components/Product.vue';
import Swipe from '@/components/HomeSwipe.vue';
import { mapState } from 'vuex';

export default {
  name: "Home",
  components: {
    // Search,
    Brands,
    Product,
    Swipe,
  },
  computed: {
    ...mapState(['User']),
  },
  methods: {
    toAccount() {
      if (!this.User.currentUser.customerId) {
        this.$router.push({ name: 'Login' });
        return;
      } else {
        this.$router.push({ name: 'Account', params: { id: this.User.currentUser.customerId } });
      }  
    },
    toSearch() {
      this.$router.push({ name: 'Search' });
    },
  },
};
</script>
<template>
  <div class="bg">
  <!--Header-->
    <div class="header">
      <van-nav-bar title="Niubility" fixed placeholder>
        <template #left>
          <van-icon name="user-o" size="25" @click="toAccount"/>
        </template>
        <template #right>
          <van-icon name="search" size="25" @click="toSearch"/>
        </template>
      </van-nav-bar>
    </div>
  <!-- Swipe -->
    <div class="swipe">
      <Swipe />
    </div>
  <!-- Brands -->
    <div class="brands">
      <Brands />
    </div>
  <!-- Product -->
    <!-- <div class="product">
      <Product />
    </div> -->
  </div>
</template>

<style lang="less" scoped>
.bg {
  background-color: rgb(255, 255, 255);
  height: 100%;
  font-family: "Comic Sans MS";
  
}
// .bg {
//   display: flex;
//   flex-flow: column;
//   height: 100%;
//   .content {
//     flex: 1;
//     overflow-y: auto;
//     .header {
//       background-image: linear-gradient(#7232dd, #ffffff);
//       // display: flex;
//       // justify-content: space-between;
//       // align-items: center;
//       text-align: center;
//       padding: 20px 20px 40px 20px;
//       .title {
//         font-size: 30px;
//         font-weight: 600;
//         font-family: "Comic Sans MS"
//       }
//     }
//     .main{
//       margin-top: -30px;
//       .bg{
//         background-color: #f1f1f1;
//         padding: 10px 20px 0px 20px;
//         border-radius: 30px 30px 0px 0px;
        
//       }
//     }
//   }
// }
</style>