<script>
import { showSuccessToast, showFailToast } from 'vant';
import { nextTick, ref } from 'vue';
import { mapState } from 'vuex';

export default {
  name: "OrderList", 
  data() {
    return {
      value1: 0,
      option1: [
        { text: 'All Orders', value: 0 },
        { text: 'Current Purchases', value: 1 },
        { text: 'Past Purchases', value: 2 },
      ],
      scrollTop: 0,
    }
  },
  created() {
    this.getOrderList();
    // this.$store.dispatch('Cart/getCartItems', this.User.currentUser.customerId);
  },
  mounted() {
    this.$store.commit('Include/AddInclude');
  },
  computed: {
    ...mapState(['Cart', 'User', 'Order', 'Include']),
    // reverseOrderList() {
    //   return this.Order.OrderList.reverse();
    // }
  },
  methods: {
    getOrderList() {
      const customerId = this.User.currentUser.customerId;
      this.$store.dispatch('Order/getOrderList', customerId);
    },
    async toOrderDetail(orderId) {
      const payload = {
        customerId: this.User.currentUser.customerId,
        orderId: orderId,
      }
      await this.$store.dispatch('Order/getOrderDetail', payload);
      this.$router.push({ name: 'OrderDetail', params: { id: orderId } });
    }
  },
  //定时器获取后端数据, 1s一次，销毁时清除定时器
  timer: null,
  // mounted() {
  //   this.timer = setInterval(() => {
  //     this.getOrderList();
  //   }, 1000);
  // },
  // beforeUnmount() {
  //   clearInterval(this.timer);
  //   console.log("clear timer");
  // },

  //keep-alive hooks
  activated() {
    this.timer = setInterval(() => {
      this.getOrderList();
    }, 10000);
    //进入路由时，滚动条回到上次离开时的位置
    document.documentElement.scrollTop = this.scrollTop;
  },
  deactivated() {
    clearInterval(this.timer);
    console.log("clear timer");
    this.scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
  },
  
  // beforeRouteLeave(to, from, next) {
  //   this.scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
  //   next();
  // },
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
    <!-- Empty -->
    <div class="empty" v-if="Order.OrderList.length == 0">
      <van-empty description="No order yet" />
    </div>
    <!-- All order list -->
    <div class="allorders" v-if="this.value1 == 0">
      <van-cell v-for="order in Order.OrderList" :key="order" 
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
          <van-tag plain type="primary" v-if="order.status == 'pending'">{{ order.status }}</van-tag>
          <van-tag plain type="warning" v-if="order.status == 'hold'">{{ order.status }}</van-tag>
          <van-tag plain type="success" v-if="order.status == 'shipped'">{{ order.status }}</van-tag>
          <van-tag plain type="danger" v-if="order.status == 'cancelled'">{{ order.status }}</van-tag>
        </template>
      </van-cell>
    </div>
    <!-- Current purchases -->
    <div class="current" v-if="this.value1 == 1">
      <van-cell v-for="order in Order.OrderList.filter(obj => {return obj.status === 'pending' || obj.status === 'hold'})" :key="order" 
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
          <van-tag plain type="primary" v-if="order.status == 'pending'">{{ order.status }}</van-tag>
          <van-tag plain type="warning" v-if="order.status == 'hold'">{{ order.status }}</van-tag>
        </template>
      </van-cell>
    </div>
    <!-- Past purchases -->
    <div class="past" v-if="this.value1 == 2">
      <van-cell v-for="order in Order.OrderList.filter(obj => {return obj.status === 'shipped' || obj.status === 'cancelled'})" :key="order" 
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
          <van-tag plain type="success" v-if="order.status == 'shipped'">{{ order.status }}</van-tag>
          <van-tag plain type="danger" v-if="order.status == 'cancelled'">{{ order.status }}</van-tag>
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