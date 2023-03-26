<script>
import { mapState } from 'vuex';

export default {
  name: "OrderDetail",
  data() {
    return {
      currentOrder: [],
      purchaseDate: '',
      cancelDate: '',
    }
  },
  created() {
    this.getOrderDetail();
  },
  computed: {
    ...mapState(['User','Order']),
  },
  methods: {
    async getOrderDetail() {
      const payload = {
        customerId: this.User.currentUser.customerId,
        orderId: this.$route.params.id,
      };
      await this.$store.dispatch('Order/getOrderDetail', payload);
    },
    async handleCancelOrder() {
      const payload = {
        customerId: this.User.currentUser.customerId,
        customerName: this.User.currentUser.customerName,
        orderId: this.$route.params.id,
      };
      await this.$store.dispatch('Order/cancelOrder', payload);
    },
    onClickLeft() {
      //router go to product list page
      this.$router.push({ name: 'OrderList' });
    },
  },
  //定时器获取后端数据, 1s一次，销毁时清除定时器
  timer: null,
  mounted() {
    this.timer = setInterval(() => {
      this.getOrderDetail();
    }, 1000);
  },
  beforeUnmount() {
    clearInterval(this.timer);
    console.log("clear timer");
  },
}
</script>

<template>
  <div class="bg">
    <div class="header">
      <van-nav-bar
        title="Order Detail"
        left-arrow
        fixed
        placeholder
        @click-left="onClickLeft"
      />
    </div>
    <div class="order-status">
      <div class="status-item">
        <label>Order Status:</label>
        <span>{{ Order.OrderDetail[0].order.status }}</span>
      </div>
      <div class="status-item">
        <label>P.O.number: </label>
        <span>{{ Order.OrderDetail[0].order.purchaseOrderNumber }}</span>
      </div>
      <div class="status-item">
        <label>Purchase Date: </label>
        <span>{{ Order.OrderDetail[0].order.purchaseDate }}</span>
      </div>
      <div class="status-item" v-if="Order.OrderDetail[0].order.status == 'cancelled'">
        <label>Cancel Date: </label>
        <span>{{ Order.OrderDetail[0].order.cancelDate }}</span>
      </div>
      <div class="status-item" v-if="Order.OrderDetail[0].order.status == 'cancelled'">
        <label>Canceled Person: </label>
        <span>{{Order.OrderDetail[0].order.cancelPerson  }}</span>
      </div>
      <div class="status-item" v-if="Order.OrderDetail[0].order.status == 'shipped'">
        <label>Shipment Date: </label>
        <span>{{ Order.OrderDetail[0].order.shipmentDate }}</span>
      </div>
      <van-button v-if="Order.OrderDetail[0].order.status == 'pending' || Order.OrderDetail[0].order.status == 'hold'" block @click="handleCancelOrder()">Cancelled Order</van-button>
    </div>
    <div class="order-price">
      <div class="price-item">
        <label>Total Amount: </label>
        <span>${{ Order.OrderDetail[0].order.totalAmount }}</span>
      </div>
      <div class="price-item">
        <label>Username: </label>
        <span>{{ Order.OrderDetail[0].order.customer.customerName }}</span>
      </div>
      <div class="price-item">
        <label>Shipping Address: </label>
        <span>{{ Order.OrderDetail[0].order.customer.shippingAddress }}</span>
      </div>
    </div>
    <van-card
      v-for="item in Order.OrderDetail"
      :key="item"
      style="background: #fff"
      :num="item.quantity"
      :price="item.product.price"
      :desc="item.product.brand"
      :title="item.product.productName"
      :thumb="item.product.photo"
    >
    <template #footer>
      <div style="font-size: 15px; color: crimson;">Subtotal: ${{ item.totalPrice }}</div>
    </template>
    </van-card>
  </div>
  <!-- <div>
    {{ currentOrder[0].order.status }}
  </div> -->
</template>

<style lang="less" scoped>
.bg {
  background: #f7f7f7;
  height: 100%;
  .order-status {
  background: #fff;
  padding: 20px;
  font-size: 15px;
  .status-item {
    margin-bottom: 10px;
    label {
      color: #999;
    }
  }
}
.order-price {
  background: #fff;
  margin: 20px 0;
  padding: 20px;
  font-size: 15px;
  .price-item {
    margin-bottom: 10px;
    label {
      color: #999;
    }
  }
}
}

</style>