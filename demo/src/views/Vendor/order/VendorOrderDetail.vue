<script>
import { mapState } from 'vuex';

export default {
  name: "OrderDetail",
  data() {
    return {
    }
  },
  created() {

  },
  computed: {
    ...mapState(['Vendor']),
  },
  methods: {
    async handleCancelOrder() {
      const payload = {
        customerId: this.Vendor.VendorOrderDetail[0].order.customer.customerId,
        orderId: this.$route.params.id,
      };
      await this.$store.dispatch('Vendor/cancelOrder', payload);
    },
    async handleShipOrder() {
      const payload = {
        customerId: this.Vendor.VendorOrderDetail[0].order.customer.customerId,
        orderId: this.$route.params.id,
      };
      await this.$store.dispatch('Vendor/shipOrder', payload);
    },
    async handleHoldOrder() {
      const payload = {
        customerId: this.Vendor.VendorOrderDetail[0].order.customer.customerId,
        orderId: this.$route.params.id,
      };
      await this.$store.dispatch('Vendor/holdOrder', payload);
    },
    onClickLeft() {
      //router go to product list page
      this.$router.push({ name: 'VendorOrderList' });
    },
    //用于定时器获取后端数据的方法
    getVendorOrderDetail() {
      const payload = {
        customerId: this.Vendor.VendorOrderDetail[0].order.customer.customerId,
        orderId: this.$route.params.id,
      };
      this.$store.dispatch('Vendor/getVendorOrderDetail', payload);
    }
  },
  //定时器获取后端数据, 1s一次，销毁时清除定时器
  timer: null,
  mounted() {
    this.timer = setInterval(() => {
      this.getVendorOrderDetail();
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
        <label>Order Status: </label>
        <span style="color: #0392ff" v-if="Vendor.VendorOrderDetail[0].order.status == 'pending'">{{ Vendor.VendorOrderDetail[0].order.status }}</span>
        <span style="color: #ffa303" v-if="Vendor.VendorOrderDetail[0].order.status == 'hold'">{{ Vendor.VendorOrderDetail[0].order.status }}</span>
        <span style="color: #1dbd00" v-if="Vendor.VendorOrderDetail[0].order.status == 'shipped'">{{ Vendor.VendorOrderDetail[0].order.status }}</span>
        <span style="color: #ff0303" v-if="Vendor.VendorOrderDetail[0].order.status == 'cancelled'">{{ Vendor.VendorOrderDetail[0].order.status }}</span>
      </div>
      <div class="status-item">
        <label>P.O.number: </label>
        <span>{{ Vendor.VendorOrderDetail[0].order.purchaseOrderNumber }}</span>
      </div>
      <div class="status-item">
        <label>Purchase Date: </label>
        <span>{{ Vendor.VendorOrderDetail[0].order.purchaseDate }}</span>
      </div>
      <div class="status-item" v-if="Vendor.VendorOrderDetail[0].order.status == 'cancelled'">
        <label>Cancel Date: </label>
        <span>{{ Vendor.VendorOrderDetail[0].order.cancelDate }}</span>
      </div>
      <div class="status-item" v-if="Vendor.VendorOrderDetail[0].order.status == 'cancelled'">
        <label>Canceled Person: </label>
        <span>{{Vendor.VendorOrderDetail[0].order.cancelPerson  }}</span>
      </div>
      <div class="status-item" v-if="Vendor.VendorOrderDetail[0].order.status == 'shipped'">
        <label>Shipment Date: </label>
        <span>{{ Vendor.VendorOrderDetail[0].order.shipmentDate }}</span>
      </div>
      <van-button plain type="warning" style="margin-bottom: 15px; margin-top: 20px;" v-if="Vendor.VendorOrderDetail[0].order.status == 'pending'" block @click="handleHoldOrder()">Hold Order</van-button>
      <van-button plain type="success" style="margin-bottom: 15px;" v-if="Vendor.VendorOrderDetail[0].order.status == 'pending' || Vendor.VendorOrderDetail[0].order.status == 'hold'" block @click="handleShipOrder()">Ship Order</van-button>
      <van-button plain type="danger" v-if="Vendor.VendorOrderDetail[0].order.status == 'pending' || Vendor.VendorOrderDetail[0].order.status == 'hold'" block @click="handleCancelOrder()">Cancel Order</van-button>
    </div>
    <div class="order-price">
      <div class="price-item">
        <label>Total Amount: </label>
        <span style="color: red">${{ Vendor.VendorOrderDetail[0].order.totalAmount }}</span>
      </div>
      <div class="price-item">
        <label>Customer Name: </label>
        <span>{{ Vendor.VendorOrderDetail[0].order.customer.customerName }}</span>
      </div>
      <div class="price-item">
        <label>Shipping Address: </label>
        <span>{{ Vendor.VendorOrderDetail[0].order.customer.shippingAddress }}</span>
      </div>
    </div>
    <van-card
      v-for="item in Vendor.VendorOrderDetail"
      currency="$"
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