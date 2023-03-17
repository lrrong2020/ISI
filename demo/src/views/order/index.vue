<script>
import { showSuccessToast, showFailToast } from 'vant';
import { ref } from 'vue';
import { mapState } from 'vuex';

export default {
  name: "OrderList", 
  data() {
    return {
      value1: 0,
      option1: [
        { text: 'All orders', value: 0 },
        { text: 'Current Purchases', value: 1 },
        { text: 'Past Purchases', value: 2 },
      ],
    }
  },
  created() {
    this.getOrderList();
  },
  computed: {
    ...mapState(['Cart', 'User', 'Order']),
    reverseOrderList() {
      return this.Order.OrderList.reverse();
    }
  },
  methods: {
    getOrderList() {
      const customerId = this.User.currentUser.customerId;
      this.$store.dispatch('Order/getOrderList', customerId);
    },
    toOrderDetail(orderId) {
      this.$router.push({ name: 'OrderDetail', params: { id: orderId } });
      const payload = {
        customerId: this.User.currentUser.customerId,
        orderId: orderId,
      }
      this.$store.dispatch('Order/getOrderDetail', payload);
    }
  }
}
</script>

<template>
  <div class="bg">
    <div class="header">
      <van-nav-bar
        :border="false"
        title="Order List"
        fixed
        placeholder
      />
    </div>
    <div class="orderList">
      <van-dropdown-menu>
        <van-dropdown-item v-model="value1" :options="option1" />
      </van-dropdown-menu>
    </div>
    <!-- All order list -->
    <div class="allorders" v-if="this.value1 == 0">
      <van-cell v-for="order in reverseOrderList" :key="order" 
        :value="'$' + order.totalAmount" 
        :label="order.purchaseDate"
        center 
        size="large"
        is-link 
        @click="toOrderDetail(order.purchaseOrderNumber)">
        <!-- 使用 title 插槽来自定义标题 -->
        <template #title>
          <span class="custom-title">P.O.number: {{ order.purchaseOrderNumber }}</span>
          <br/>
          <van-tag type="primary">{{ order.status }}</van-tag>
        </template>
      </van-cell>
    </div>
    <!-- Current purchases -->
    <div class="current" v-if="this.value1 == 1">
      <van-cell v-for="order in reverseOrderList.filter(obj => {return obj.status === 'pending' || obj.status === 'hold'})" :key="order" 
        :value="'$' + order.totalAmount" 
        :label="order.purchaseDate"
        center 
        size="large"
        is-link 
        @click="toOrderDetail(order.purchaseOrderNumber)">
        <!-- 使用 title 插槽来自定义标题 -->
        <template #title>
          <span class="custom-title">P.O.number: {{ order.purchaseOrderNumber }}</span>
          <br/>
          <van-tag type="primary">{{ order.status }}</van-tag>
        </template>
      </van-cell>
    </div>
    <!-- Past purchases -->
    <div class="past" v-if="this.value1 == 2">
      <van-cell v-for="order in reverseOrderList.filter(obj => {return obj.status === 'shipped' || obj.status === 'cancelled'})" :key="order" 
        :value="'$' + order.totalAmount" 
        :label="order.purchaseDate"
        center 
        size="large"
        is-link 
        @click="toOrderDetail(order.purchaseOrderNumber)">
        <!-- 使用 title 插槽来自定义标题 -->
        <template #title>
          <span class="custom-title">P.O.number: {{ order.purchaseOrderNumber }}</span>
          <br/>
          <van-tag type="primary">{{ order.status }}</van-tag>
        </template>
      </van-cell>
    </div>
  </div>
</template>

<style lang="less" scoped>
.custom-title {
    margin-right: 10px;
    vertical-align: middle;
}
.allorders {
    margin-top: 10px;
}
.current {
    margin-top: 10px;
}
.past {
    margin-top: 10px;
}
</style>