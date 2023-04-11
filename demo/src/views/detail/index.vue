<script>
import { mapState } from 'vuex';
import { reactive } from 'vue';
import axios from 'axios';

import { showSuccessToast, showFailToast } from 'vant';
// import 'vant/es/toast/style';

export default {
  name: "Detail",
  data() {
    return {
      // id: '',
      Detail: {},
      like: false,
      notPreferred: null,
    }
  },
  setup() {
    // themeVars 内的值会被转换成对应 CSS 变量
    // 比如 sliderBarHeight 会转换成 `--van-slider-bar-height`
    const themeVars = reactive({
      actionBarIconSize: '25px',
      actionBarIconFontSize: '15px',
    });
    return {
      themeVars,
    };
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
          message: 'Please log in!',
          wordBreak: 'break-word',
        });
      } else {
        this.$store.dispatch('Cart/getCartItems', this.User.currentUser.customerId);

        const that = this;
        axios.get(`http://127.0.0.1:8080/preference/get?customerId=${this.User.currentUser.customerId}&productId=${this.Detail.productId}`)
        .then((response) => {
          console.log("response.data");
          console.log(response.data)
          if(response.data.customer != null){
            that.like = response.data.like;
            that.setPreferred(false);
            console.log(that.like);
          }
          else{
            console.log("response.data.customer is null");
            that.setPreferred(true);
            console.log("notPreferred?");
            console.log(that.notPreferred)
          }
        }).catch((error) => {
          console.log(error);
        });
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
          message: 'Please login first!',
          wordBreak: 'break-word',
        });
        this.$router.push({ name: 'Login' });
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
          message: 'Please login first!',
          wordBreak: 'break-word',
        });
        this.$router.push({ name: 'Login' });
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
      if (this.User.currentUser.customerId == null) {
        showFailToast({
          message: 'Please login first!',
          wordBreak: 'break-word',
        });
        this.$router.push({ name: 'Login' });
        return;
      } else {
        this.$router.push({ name: 'Cart' });
      }
    },

    setPreferred(value){
      this.notPreferred = value;
    },

    async toggleLike(){
      if(this.notPreferred == null){
        alert("fail to toggle");
        return;
      }
      const that = this;
      this.like = !this.like;



      if(this.notPreferred){
        console.log("this.notPreferred: ", this.notPreferred);
        await axios.post(`http://127.0.0.1:8080/preference/add?customerId=${this.User.currentUser.customerId}&productId=${this.Detail.productId}&like=${this.like}`)
        .then((response) => {
          console.log("response.data");
          console.log(response.data)
          if(response.data.customer.customerId != null){
            that.like = response.data.like;
            console.log(that.like);
          }
        }).catch((error) => {
          console.log(error);
        });
      }
      else{
        console.log("this.notPreferred: ", this.notPreferred);
        await axios.post(`http://127.0.0.1:8080/preference/update?customerId=${this.User.currentUser.customerId}&productId=${this.Detail.productId}&like=${this.like}`)
        .then((response) => {
          console.log("response.data");
          console.log(response.data)
          if(response.data.customer.customerId != null){
            that.like = response.data.like;
            console.log(that.like);
          }
        }).catch((error) => {
          console.log(error);
        });
      }
    },

    isLike(){
      return this.like;
    }
  }
}
</script>

<template>
  <div class="bg">
    <div class="img">
      <van-image
        width= "100%"
        height="300px"
        :src="this.Detail.photo"
        fit="contain"
      />
    </div>
    <!-- <div class="swipe">
      <van-swipe class="my-swipe" :autoplay="3000" indicator-color="#1989fa">
        <van-swipe-item v-for="img in 1" :key="img">
          <img :src="this.Detail.photo" height="250" width="250" style="padding-top: 40px;">
        </van-swipe-item>
      </van-swipe>
    </div> -->
    <div class="main-card">
      <div class="name">
        <p style="margin: 5px 0px; font-size: 30px;">{{ this.Detail.productName }}</p>
        <p style="margin: 5px 0px;font-size: 20px; color: #b4b4b4;">Brand: {{ this.Detail.brand }}</p>
      </div>
      <div class="price">
        <h1 style="margin: 5px 0px">${{this.Detail.price}}</h1>
      </div> 
    </div>
    <div class="prop">
      <p style="margin: 5px 0px">Color: {{ this.Detail.property }}</p>
      <p style="margin: 5px 0px">Screen Size: {{ this.Detail.propertySecond }}</p>
    </div>
    <div class="footer">
      <van-config-provider :theme-vars="themeVars">
        <van-action-bar placeholder>
          <van-action-bar-icon icon="cart-o" text="Cart" @click="goToCart" :badge="!Cart.CartTotal ? '' : Cart.CartTotal" style="margin: 0px 15px;"/>
          <van-action-bar-button color="#54c9ff" type="warning" text="Add to Cart" @click="addCartItem(Detail.productId)"/>
          <van-action-bar-button color="#0392ff" type="danger" text="Buy Now" @click="onClickButtonBuy(Detail.productId)"/>
        </van-action-bar>
      </van-config-provider>
    </div>
  </div>

  <div v-if="User.currentUser.customerId !== null">
    <van-button v-if="!isLike()" type="success" id="like" @click="toggleLike"></van-button>
    <van-button v-if="isLike()" type="danger" id="like" @click="toggleLike"></van-button>
  </div>
</template>

<style lang="less" scoped>
//用less写样式
.img{
  width: 100%;
  height: 300px;
}
//main是卡片样式，卡片背景色为白色，圆角为15px，宽度为90%，有阴影
.main-card{
  border-radius: 10px;
  box-shadow: 0 0 5px #bdbdbd;
  margin: 20px 10px 0px 10px;
  padding: 10px;
  .name{
    margin: 0px;
  }
  .price{
    color: red;
    margin: 0px;
    font-size: 10px;
  }
  .desc{
    margin: 0px;
  }
}
.prop{
  border-radius: 10px;
  box-shadow: 0 0 5px #bdbdbd;
  margin: 10px 10px;
  padding: 10px;
  font-size: 20px;
}
.footer{
  position: fixed;
  bottom: 0;
  width: 100%;
}
</style>