<script>
import { ref } from 'vue';
import { mapState } from 'vuex';

export default {
  name: "Cart",
  data() {
      return {
        
      };
  },
  created() {
    // console.log("cart current user:")
    // console.log(this.User.currentUser);
    this.$store.dispatch('Cart/getCartItems', this.User.currentUser.customerId);
    // console.log("get cart items")
  },
  computed: {
    ...mapState(['Cart', 'User']),
  },
  methods: {
    // deleteCartItem(item) {
    //   this.$store.dispatch('Cart/deleteCartItem', item);
    // },
    toHome() {
        this.$router.push({ path: "/" });
    },
    onChange(value,detail) {
      event.stopPropagation();
      const payload = {
        customerId: this.User.currentUser.customerId,
        productId: detail.name,
        quantity: value,
      };
      this.$store.dispatch('Cart/updateCartItems', payload);
    },
    deleteCartItem(productId) {
      const payload = {
        customerId: this.User.currentUser.customerId,
        productId: productId,
        quantity: 0,
      };
      this.$store.dispatch('Cart/updateCartItems', payload);
    },
    toDetail(productId) {
    this.$router.push({ name: 'Detail', params: { id: productId } });
    },
  },
}
</script>

<template>
  <div class="bg">
    <!--TopTitle-->
    <van-nav-bar title="Cart" />
    <!--NumberofItems-->
    <div>{{ Cart.CartTotal }}</div>
    <!--IfEmptyCart-->
    <div class="empty" v-if="Cart.CartTotalQuantity == 0">
      <van-empty description="Your cart is empty!">
        <van-button type="success" @click="toHome">Go to shop</van-button>
      </van-empty>
    </div>
    <!--IfNotEmptyCart-->
    <div class="non-empty" >
      <van-swipe-cell v-for="item in Cart.Cart" :key="item">
        <van-card
        class="goods-card"
        :price="item.product.price"
        :desc="item.product.brand"
        :title="item.product.productName"
        :thumb="item.product.url"
        @click="toDetail(item.product.productId)"
        >
          <template #footer>
            <van-stepper
              integer
              :min="1"
              :model-value="item.quantity"
              :name="item.product.productId"
              async-change
              @change="onChange"
            />
          </template>
        </van-card>
        <template class="button" #right>
          <van-button square icon="delete-o" type="danger" class="delete-button" @click="deleteCartItem(item.product.productId)"/>
        </template>
      </van-swipe-cell>
    </div>
    <div class="block"></div>
    <!--CheckOut-->
    <div v-if="Cart.CartTotalQuantity !== 0 ">
      <van-submit-bar currency="$" :price="Cart.CartTotalPrice * 100" button-text="Submit Order" @submit="onSubmit" class="footer">
      </van-submit-bar>
    </div>
    <!-- <div>{{ User.currentUser }}</div>
    <div>{{ Cart.Cart }}</div>
    <div>{{ Cart.CartTotal }}</div>
    <div>{{ Cart.CartTotalPrice }}</div>
    <div>{{ Cart.CartTotalQuantity }}</div>
    <div v-for="item in Cart.Cart" :key="item">{{ item.product }}</div> -->
    <div class="block"></div>
  </div>
</template>

<style scoped>
.goods-card {
    margin: 0;
    background-color: #ffffff;
    width: 100%;
  }
.button{
  width: auto;
}
.delete-button {
  height: 100%;
}
.block{
  position: relative;
  height: 50px;
}
.footer{
  position: fixed;
  bottom: 7%;
  width: 100%;
}
</style>