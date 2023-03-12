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
      onChange(productId, quantity) {
        const payload = {
          customerId: this.User.currentUser.customerId,
          productId: productId,
          quantity: quantity,
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
      }
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
    <div class="empty" v-if="Cart.CartTotal == 0">
      <van-empty description="Your cart is empty!">
        <van-button type="success" @click="toHome">Go to shop</van-button>
      </van-empty>
    </div>
    <!--IfNotEmptyCart-->
    <div class="non-empty" v-if="Cart.CartTotal !== 0">
      <van-swipe-cell v-for="item in Cart.Cart" :key="item">
        <van-card
        class="goods-card"
        :price="item.price"
        :desc="item.brand"
        :title="item.productName"
        :thumb="item.url"
        >
          <template #footer>
            <van-stepper
              integer
              :min="1"
              :model-value="Cart.CartTotal"
              :name="item.productId"
              async-change
              @change="onChange(item.productId, $event)"
            />
          </template>
        </van-card>
        <template class="button" #right>
          <van-button square icon="delete-o" type="danger" class="delete-button" @click="deleteCartItem(item.productId)"/>
        </template>
      </van-swipe-cell>
    </div>
    <div class="block"></div>
    <!--CheckOut-->
    <div v-if="Cart.CartTotal !== 0 ">
      <van-submit-bar currency="$" :price="Cart.CartTotalPrice * 100" button-text="Submit Order" @submit="onSubmit" class="footer">
      </van-submit-bar>
    </div>
    <div>{{ User.currentUser }}</div>
    <div>{{ Cart.Cart }}</div>
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