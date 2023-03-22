<script>
import { mapState } from 'vuex';

export default {
  name: "OrderList", 
  data() {
    return {
      value1: 0,
      option1: [
        { text: 'All orders', value: 0 },
        { text: 'Pending orders', value: 1 },
        { text: 'Orders on hold', value: 2 },
        { text: 'Past orders', value: 3 },
      ],
    }
  },
  created() {
    this.getOrderList();
  },
  computed: {
    ...mapState(['Vendor']),
    reverseOrderList() {
      return this.Vendor.VendorOrderList.reverse();
    }
  },
  methods: {
    getOrderList() {
      this.$store.dispatch('Vendor/getVendorOrderList');
    },
    toOrderDetail(orderId, customerId) {
      const payload = {
        customerId: customerId,
        orderId: orderId,
      }
      this.$store.dispatch('Vendor/getVendorOrderDetail', payload);
      this.$router.push({ name: 'VendorOrderDetail', params: { id: orderId } });
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
        @click="toOrderDetail(order.purchaseOrderNumber, order.customer.customerId)">
        <!-- 使用 title 插槽来自定义标题 -->
        <template #title>
          <span class="custom-title">P.O.number: {{ order.purchaseOrderNumber }}</span>
          <br/>
          <span class="custom-title">Customer: {{ order.customer.customerName }}</span>
          <br/>
          <van-tag type="primary">{{ order.status }}</van-tag>
        </template>
      </van-cell>
    </div>
    <!-- Pending -->
    <div class="pending" v-if="this.value1 == 1">
      <van-cell v-for="order in reverseOrderList.filter(obj => {return obj.status === 'pending'})" :key="order" 
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
          <span class="custom-title">Customer: {{ order.customer.customerName }}</span>
          <br/>
          <van-tag type="primary">{{ order.status }}</van-tag>
        </template>
      </van-cell>
    </div>
    <!-- Hold -->
    <div class="hold" v-if="this.value1 == 2">
      <van-cell v-for="order in reverseOrderList.filter(obj => {return obj.status === 'hold'})" :key="order" 
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
          <span class="custom-title">Customer: {{ order.customer.customerName }}</span>
          <br/>
          <van-tag type="primary">{{ order.status }}</van-tag>
        </template>
      </van-cell>
    </div>

    <!-- Current purchases -->
    <!-- <div class="current" v-if="this.value1 == 1">
      <van-cell v-for="order in reverseOrderList.filter(obj => {return obj.status === 'pending' || obj.status === 'hold'})" :key="order" 
        :value="'$' + order.totalAmount" 
        :label="order.purchaseDate"
        center 
        size="large"
        is-link 
        @click="toOrderDetail(order.purchaseOrderNumber)">
        <template #title>
          <span class="custom-title">P.O.number: {{ order.purchaseOrderNumber }}</span>
          <br/>
          <van-tag type="primary">{{ order.status }}</van-tag>
        </template>
      </van-cell>
    </div> -->

    <!-- Past purchases -->
    <div class="past" v-if="this.value1 == 3">
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
          <span class="custom-title">Customer: {{ order.customer.customerName }}</span>
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
.pending {
    margin-top: 10px;
}
.hold {
    margin-top: 10px;
}
.current {
    margin-top: 10px;
}
.past {
    margin-top: 10px;
}
</style>