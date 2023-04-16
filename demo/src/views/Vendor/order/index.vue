<script>
import { mapState } from 'vuex';

export default {
  name: "VendorOrderList", 
  data() {
    return {
      value1: 0,
      option1: [
        { text: 'All Orders', value: 0 },
        { text: 'Pending Orders', value: 1 },
        { text: 'Orders on Hold', value: 2 },
        { text: 'Past Orders', value: 3 },
      ],
      searchValue:'',
      scrollTop: 0,
    }
  },
  created() {
    this.getOrderList();
  },
  computed: {
    ...mapState(['Vendor', 'Include']),
    reverseOrderList() {
      return this.Vendor.VendorOrderList.reverse();
    },
  },
  methods: {
    getOrderList() {
      this.$store.dispatch('Vendor/getVendorOrderList');
    },
    async toOrderDetail(orderId, customerId) {
      const payload = {
        customerId: customerId,
        orderId: orderId,
      }
      await this.$store.dispatch('Vendor/getVendorOrderDetail', payload);
      clearInterval(this.timer);
      console.log("clear timer");
      this.$router.push({ name: 'VendorOrderDetail', params: { id: orderId } });
    },

    searchOrderById(){
      if(this.searchValue > 0){
        this.$store.dispatch('Vendor/searchOrderById', this.searchValue);
      }
      else {
        this.getOrderList();
      }
    },

    clearSearch(){
      this.searchValue='';
      this.getOrderList();
    }
  },
  //定时器获取后端数据, 1s一次，销毁时清除定时器
  timer: null,
  mounted() {
    this.$store.commit('Include/AddInclude');
  },
  // beforeUnmount() {
  //   clearInterval(this.timer);
  //   console.log("clear timer");
  // },
  activated() {
    const that = this;
    this.timer = setInterval(() => {

      if(that.searchValue.length == 0){
        that.getOrderList();
      }
      else{
        that.searchOrderById();
      }
    }, 1000);
    //进入路由时，滚动条回到上次离开时的位置
    document.documentElement.scrollTop = this.scrollTop;
  },
  beforeRouteLeave(to, from, next) {
    clearInterval(this.timer);
    console.log("clear timer");
    //离开路由时，记录滚动条位置
    this.scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
    next();
  },
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

    
    <!--Search-->
    <van-search
      v-model="searchValue"
      show-action
      shape="round"
      background="#ffffff"
      placeholder="Type to search"
      @clear="clearSearch"
      class="search"
      >
      <template #action>
        <div @click="searchOrderById" class="button">Search</div>
      </template>
    </van-search>

    <!-- empty -->
    <div class="empty" v-if="Vendor.VendorOrderList[0].length == 0">
      <van-empty description="No Order Found!" />
    </div>
    
    <!-- All order list -->
    <div class="allorders" v-if="this.value1 == 0 && Vendor.VendorOrderList[0].length != 0">
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
          <van-tag plain type="primary" v-if="order.status == 'pending'">{{ order.status }}</van-tag>
          <van-tag plain type="warning" v-if="order.status == 'hold'">{{ order.status }}</van-tag>
          <van-tag plain type="success" v-if="order.status == 'shipped'">{{ order.status }}</van-tag>
          <van-tag plain type="danger" v-if="order.status == 'cancelled'">{{ order.status }}</van-tag>
        </template>
      </van-cell>
    </div>
    <!-- Pending -->
    <div class="pending" v-if="this.value1 == 1 && Vendor.VendorOrderList[0].length != 0">
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
          <van-tag plain type="primary" v-if="order.status == 'pending'">{{ order.status }}</van-tag>
        </template>
      </van-cell>
    </div>
    <!-- Hold -->
    <div class="hold" v-if="this.value1 == 2 && Vendor.VendorOrderList[0].length != 0">
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
          <van-tag plain type="warning" v-if="order.status == 'hold'">{{ order.status }}</van-tag>
        </template>
      </van-cell>
    </div>
    <!-- Past purchases -->
    <div class="past" v-if="this.value1 == 3 && Vendor.VendorOrderList[0].length != 0">
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
          <van-tag plain type="success" v-if="order.status == 'shipped'">{{ order.status }}</van-tag>
          <van-tag plain type="danger" v-if="order.status == 'cancelled'">{{ order.status }}</van-tag>
        </template>
      </van-cell>
    </div>
  </div>
</template>

<style lang="less" scoped>
.search {
    margin-top: 10px;
}
.custom-title {
    margin-right: 10px;
    vertical-align: middle;
    white-space: nowrap;
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