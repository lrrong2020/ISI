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
    this.getCartItems();
  },
  // mounted() {
  //   console.log(this.Detail);
  // },
  computed: {
    ...mapState(['Cart']),
  },
  methods: {
    getDetail() {
      axios.get(`http://127.0.0.1:8080/product/${this.$route.params.id}`).then((response) => {
        this.Detail = response.data;
      })
    },
    //Cart
    getCartItems() {
      this.$store.dispatch('Cart/getCartItems');
    },
    addCartItem() {
      this.$store.dispatch('Cart/addCartItem', this.Detail);
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
        <van-swipe-item v-for="img in 3" :key="img">
          <img :src="this.Detail.url" height="250" width="250" style="padding-top: 40px;">
        </van-swipe-item>
      </van-swipe>
    </div>
    <div class="main">
      <div class="price">
        <p><van-tag plain type="danger">Price</van-tag> ${{this.Detail.price}}</p>
      </div>
      <div class="name">
        <p>{{ this.Detail.productName }}</p>
      </div>
      <div class="desc">
        <p><van-tag plain type="primary">{{ this.Detail.brand }}</van-tag>{{ this.Detail.productName }}</p>
      </div>
    </div>
    <div class="prop">
      <van-tabs v-model:active="active" swipeable>
        <van-tab v-for="index in 3" :title="'Prop' + index">
          {{ this.Detail.property }} {{ index }}
        </van-tab>
      </van-tabs>
    </div>
    <div class="footer">
      <van-action-bar>
        <van-action-bar-icon icon="chat-o" text="客服" />
        <van-action-bar-icon icon="cart-o" text="Cart" @click="goToCart" :badge="!Cart.CartTotalQuantity ? '' : Cart.CartTotalQuantity"/>
        <van-action-bar-button color="#be99ff" type="warning" text="Add to Cart" @click="addCartItem"/>
        <van-action-bar-button color="#7232dd" type="danger" text="Buy Now" @click="onClickButtonBuy"/>
      </van-action-bar>
    </div>
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
    background-color: aqua;
    .name{
      font-size: 20px;
    }
    .desc{
      font-size: 15px;
    }
  }
}

</style>