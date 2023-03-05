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
      add() {
          this.number++;
      },
      remove() {
          if (this.number > 0) {
              this.number--;
          }
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
        <template #right>
          <van-button square text="删除" type="danger" class="delete-button" @click="deleteCartItem(item.id)"/>
        </template>
      </van-swipe-cell>
    </div>
    <!--AddandRemove-->
    <van-button type="success" @click="add">Add 1 item</van-button>
    <van-button type="danger" @click="remove">Remove 1 item</van-button>
    <!--CheckOut-->
    <div v-if="Cart.CartTotal">
      <van-submit-bar :price="3050" button-text="Submit Order" @submit="onSubmit">
        <van-checkbox>Select All</van-checkbox>
      </van-submit-bar>
    </div>
  </div>
</template>

<style scoped>
.goods-card {
    margin: 0;
    background-color: #ffffff;
  }

  .delete-button {
    height: 100%;
  }
</style>