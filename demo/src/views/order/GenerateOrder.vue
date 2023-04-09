<script>
import { mapState } from 'vuex';
import { showSuccessToast,showLoadingToast, closeToast } from 'vant';

export default {
  name: "GenerateOrder",
  data() {
    return {
      showPay: false,
    };
  },
  computed: {
    ...mapState(['Cart', 'User', 'Order']),
  },
  methods: {
    onClickLeft() {
      //router回到上一个页面
      this.$router.go(-1);
    },
    closePopup() {
      this.showPay = false;
    },
    async handleCreateOrder () {
      this.showPay = true;
      // const customerId = await this.User.currentUser.customerId;
      // //checkout 生成订单，删除购物车
      // await this.$store.dispatch('Order/checkOut', customerId);
      // //获取订单列表
      // await this.$store.dispatch('Order/getOrderList', customerId);
      // //获取订单详情
      // await this.getOrderDetail();
    },
    async handlePayOrder(){

      const customerId = await this.User.currentUser.customerId;
      //checkout 生成订单，删除购物车
      await this.$store.dispatch('Order/checkOut', customerId);
      //获取订单列表
      await this.$store.dispatch('Order/getOrderList', customerId);
      //获取订单详情
      await this.getOrderDetail();
      
      const toast = showLoadingToast({
        duration: 0,
        forbidClick: true,
        message: 'Paying...',
      });
      let second = 2;
      const timer = setInterval(() => {
        second--;
        if (second) {
          // do nothing
        } else {
          clearInterval(timer);
          closeToast();
          this.showPay = false;
          showSuccessToast('Payment Success');
          //跳转到订单详情页面
          this.$router.push({ name: 'OrderDetail', params: { id: this.Order.OrderDetail[0].order.purchaseOrderNumber } });
        }
      }, 1000);
      



      // const customerId = await this.User.currentUser.customerId;
      // await this.$store.dispatch('Order/checkOut', customerId);
      
      // const payload = {
      //   customerId: this.User.currentUser.customerId,
      // };
      // await this.$store.dispatch('Order/createOrder', payload);
      // this.orderNo = this.$store.state.Order.orderNo;
      // this.showPay = true;
    },
    async getOrderDetail() {
      const currentOrder = await this.Order.OrderList[this.Order.OrderList.length - 1];
      console.log('currentOrder:' + currentOrder);
      console.log('currentOrder.purchaseOrderNumber:' + currentOrder.purchaseOrderNumber);
      const payload = {
        customerId: this.User.currentUser.customerId,
        orderId: currentOrder.purchaseOrderNumber,
      };
      await this.$store.dispatch('Order/getOrderDetail', payload);
    }
  }
}
</script>

<template>
  <div class="bg">
    <div class="header">
      <van-nav-bar
        title="Generate Order"
        left-arrow
        fixed
        placeholder
        @click-left="onClickLeft"
      />
    </div>
    <div class="address-wrap">
      <van-icon name="location-o" class="arrow" />
      <div class="name" @click="goTo">
        <span>{{ User.currentUser.customerName }}</span>
        <span style="padding-left: 15px;">{{ User.currentUser.customerEmail }}</span>
      </div>
      <div class="address">
        {{ User.currentUser.shippingAddress }}
      </div>
    </div>
    <div class="orderList">
      <van-swipe-cell v-for="item in Cart.Cart" :key="item">
        <van-card
          :num="item.quantity"
          :price="item.product.price"
          :desc="item.product.brand"
          :title="item.product.productName"
          :thumb="item.product.photo"
          class="goods-card"
          
        />
      </van-swipe-cell>
    </div>
    <div class="block" style="height: 100px;"></div>
    <div class="footer">
      <div class="pay-wrap">
      <div class="price">
        <span style="font-size: 20px;">Total Amount</span>
        <span>${{ Cart.CartTotalPrice }}</span>
      </div>
      <van-button @click="handleCreateOrder" class="pay-btn" color="#0392ff" type="primary" block>Conform</van-button>
    </div>
    <van-popup
      closeable
      :close-on-click-overlay="false"
      v-model:show="showPay"
      position="bottom"
      :style="{ height: '30%' }"
    >
      <div :style="{ width: '90%', margin: '0 auto', padding: '50px 0' }">
        <van-button :style="{ marginBottom: '10px' }" color="#1989fa" block @click="handlePayOrder">Alipay</van-button>
        <van-button color="#4fc08d" block @click="handlePayOrder">WeChat Pay</van-button>
      </div>
    </van-popup>
    </div>
  </div>
</template>

<style lang="less" scoped>
.address-wrap {
  margin-bottom: 20px;
  background: #ffffff;
  position: relative;
  font-size: 17px;
  padding: 15px;
  color: #222333;
  .address {
    margin: 8px 0px;
    padding-left: 20px;
  }
  .name {
    margin: 8px 0px;
    padding-left: 20px;
  }
  .arrow {
    position: absolute;
    right: 10px;
    top: 50%;
    transform: translateY(-50%);
    font-size: 30px;
  }
  &::before {
    position: absolute;
    right: 0;
    bottom: 0;
    left: 0;
    height: 2px;
    background: -webkit-repeating-linear-gradient(135deg, #ff6c6c 0, #ff6c6c 20%, transparent 0, transparent 25%, #1989fa 0, #1989fa 45%, transparent 0, transparent 50%);
    background: repeating-linear-gradient(-45deg, #ff6c6c 0, #ff6c6c 20%, transparent 0, transparent 25%, #1989fa 0, #1989fa 45%, transparent 0, transparent 50%);
    background-size: 80px;
    content: '';
  }
}
.orderList {
  margin-bottom: 20px;
  .goods-card {
    margin: 5px 0px;
    width: 100%;
    font-size: 15px;
  }
}
.pay-wrap {
  position: fixed;
  bottom: 0;
  left: 0;
  width: 100%;
  background: #fff;
  padding: 10px 0;
  padding-bottom: 50px;
  border-top: 1px solid #e9e9e9;
  >div {
    display: flex;
    justify-content: space-between;
    padding: 0 5%;
    margin: 10px 0;
    font-size: 14px;
    span:nth-child(2) {
      color: red;
      font-size: 18px;
    }
  }
  .pay-btn {
    position: fixed;
    bottom: 7px;
    right: 0;
    left: 0;
    width: 90%;
    margin: 0 auto;
    font-size: 20px;
  }
}
</style>