<script>
import { ref } from 'vue';
import { onMounted } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';
import { mapState } from 'vuex';

import { showSuccessToast, showFailToast } from 'vant';
// import 'vant/es/toast/style';

export default {
  name: "Detail",
  setup() {
    const active = ref(0);

    return { active };
  },
  data() {
    return {
      // id: '',
      Detail: {},
    }
  },
  created() {
    // this.id = this.$route.params.id;
    this.getDetail();
    this.getCartItems();
  },
  // mounted() {
  //   console.log(this.Detail);
  // },
  computed: {
    ...mapState(['User','Cart', 'Product']),
  },
  methods: {
    getDetail() {
      // axios.get(`http://127.0.0.1:8080/product/${this.$route.params.id}`).then((response) => {
      //   this.Detail = response.data;
      // })
      // this.$store.dispatch('Product/getProductDetail', this.$route.params.id);
      this.Detail = this.Product.ProductDetail;
    },
    //Cart
    getCartItems() {
      if (this.User.currentUser.customerId == null) {
        showFailToast({
          message: 'You are not login!',
          wordBreak: 'break-word',
        });
      } else {
        this.$store.dispatch('Cart/getCartItems', this.User.currentUser.customerId);
      } 
    },
    addCartItem(productId) {
      // const payload = {
      //   customerId: this.User.currentUser.customerId,
      //   productId: productId,
      //   quantity: 1,
      // };
      if (this.User.currentUser.customerId == null) {
        showFailToast({
          message: 'You are not login!',
          wordBreak: 'break-word',
        });
        return;
      }
      const payload = {
        customerId: this.User.currentUser.customerId,
        productId: productId,
      };
      //判断如果有这个商品就不添加
      //若果没有这个商品就添加
      if (this.Cart.Cart.some(item => item.product.productId == productId)) {
        showFailToast({
          message: 'You have added this product!',
          wordBreak: 'break-word',
        });
      } else {
        this.$store.dispatch('Cart/addCartItems', payload);
        showSuccessToast({
          message: 'Added successfully!',
          wordBreak: 'break-word',
        });
      }
    },
    onClickButtonBuy(productId) {
      if (this.User.currentUser.customerId == null) {
        showFailToast({
          message: 'You are not login!',
          wordBreak: 'break-word',
        });
        return;
      }
      const payload = {
        customerId: this.User.currentUser.customerId,
        productId: productId,
      };
      if (this.Cart.Cart.some(item => item.product.productId == productId)) {
        showFailToast({
          message: 'You have added this product!',
          wordBreak: 'break-word',
        });
        this.$router.push({ name: 'Cart' });
      } else {
        this.$store.dispatch('Cart/addCartItems', payload);
        showSuccessToast({
          message: 'Added successfully!',
          wordBreak: 'break-word',
        });
        this.$router.push({ name: 'Cart' });
      }
    },
    goToCart() {
      this.$router.push({ name: 'Cart' });
    },
  }
}
</script>

<template>
  <div class="bg">
    <div class="swipe">
      <van-swipe class="my-swipe" :autoplay="3000" indicator-color="#1989fa">
        <van-swipe-item v-for="img in 1" :key="img">
          <img :src="this.Detail.photo" height="250" width="250" style="padding-top: 40px;">
        </van-swipe-item>
      </van-swipe>
    </div>
    <div class="main">
      <div class="name">
        <p>{{ this.Detail.productName }}</p>
        <p>Brand: {{ this.Detail.brand }}</p>
      </div>
      <div class="price">
        <h1>${{this.Detail.price}}</h1>
      </div>
      
    </div>
    <div class="prop">
      <van-tabs v-model:active="active" swipeable>
        <van-tab title="Color">
          {{ this.Detail.property }}
        </van-tab>
        <van-tab title="Screen Size">
          {{ this.Detail.propertySecond }}
        </van-tab>
      </van-tabs>
    </div>
    <div class="footer">
      <van-action-bar placeholder>
        <van-action-bar-icon icon="chat-o" text="客服" />
        <van-action-bar-icon icon="cart-o" text="Cart" @click="goToCart" :badge="!Cart.CartTotal ? '' : Cart.CartTotal"/>
        <van-action-bar-button color="#be99ff" type="warning" text="Add to Cart" @click="addCartItem(Detail.productId)"/>
        <van-action-bar-button color="#7232dd" type="danger" text="Buy Now" @click="onClickButtonBuy(Detail.productId)"/>
      </van-action-bar>
    </div>
    <!-- <div>{{ Cart.Cart[0].productId }}</div> -->
    <!-- <div>{{ Detail.productId }}</div> -->
    <!-- <div>product {{ Detail }}</div>
    <div>Cart: {{ Cart.Cart }}</div> -->
    <div class="block"></div>
  </div>
</template>

<style lang="less" scoped>
.bg{
    .my-swipe .van-swipe-item {
    font-size: 20px;
    line-height: 150px;
    text-align: center;
    background-color: #ff0000a8;
  }
  .main{
    border-radius: 15px;
    background-color: rgb(255, 255, 255);
    .name{
      margin: 0px;
    }
    .price{
      color: red;
      margin: 0px;
      font-size: 15px;
    }
    .desc{
      font-size: 15px;
    }
  }
  .block{
    height: 50px;
  }
}

</style>