<script>
import { ref } from 'vue';
import { mapState } from 'vuex';

export default {
    name: "Cart",
    data() {
        return {
            number: 0,
            numItem: 1,
        };
    },
    created() {
      this.$store.dispatch('Cart/getCartItems');
    },
    computed: {
      ...mapState(['Cart']),
    },
    methods: {
      deleteCartItem(item) {
        this.$store.dispatch('Cart/deleteCartItem', item);
      },
      toHome() {
          this.$router.push({ path: "/" });
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
    <div class="empty" v-if="!Cart.CartTotal">
      <van-empty description="Your cart is empty!">
        <van-button type="success" @click="toHome">Go to shop</van-button>
      </van-empty>
    </div>
    <!--IfNotEmptyCart-->
    <div class="non-empty">
      <van-swipe-cell v-for="item in Cart.Cart" :key="item">
        <van-card
        class="goods-card"
        :num="item.quantity"
        :price="item.price"
        :desc="item.brand"
        :title="item.productName"
        :thumb="item.url[0]"
        >
          <template #footer>
            <van-stepper v-model="numItem" />
          </template>
        </van-card>
        <template class="button" #right>
          <van-button square text="Delete" type="danger" class="delete-button" @click="deleteCartItem(item.id)"/>
        </template>
      </van-swipe-cell>
    </div>
    <div class="block"></div>
    <!--CheckOut-->
    <div v-if="Cart.CartTotal">
      <van-submit-bar currency="$" :price="Cart.CartTotalPrice * 100" button-text="Submit Order" @submit="onSubmit" class="footer">
        <van-checkbox>Select All</van-checkbox>
      </van-submit-bar>
    </div>
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
  height: 45px;
}
.footer{
  position: fixed;
  bottom: 7%;
  width: 100%;
}
</style>