<script>
import { ref } from 'vue';
import { onMounted } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';
import { mapState } from 'vuex';

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
    // this.getCartItems();
  },
  // mounted() {
  //   console.log(this.Detail);
  // },
  computed: {
    // ...mapState([]),
  },
  methods: {
    getDetail() {
      axios.get(`http://127.0.0.1:8080/product/${this.$route.params.id}`).then((response) => {
        this.Detail = response.data;
      })
    },
    //Cart
    // getCartItems() {
    //   this.$store.dispatch('Cart/getCartItems');
    // },
    // addCartItem() {
    //   this.$store.dispatch('Cart/addCartItem', this.Detail);
    // },
    // goToCart() {
    //   this.$router.push({ name: 'Cart' });
    // },
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
    <!-- <div class="footer">
      <van-action-bar placeholder>
        <van-action-bar-icon icon="chat-o" text="客服" />
        <van-action-bar-icon icon="cart-o" text="Cart" @click="goToCart" :badge="!Cart.CartTotal ? '' : Cart.CartTotal"/>
        <van-action-bar-button color="#be99ff" type="warning" text="Add to Cart" @click="addCartItem(Detail.productId)"/>
        <van-action-bar-button color="#7232dd" type="danger" text="Buy Now" @click="onClickButtonBuy(Detail.productId)"/>
      </van-action-bar>
    </div> -->
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