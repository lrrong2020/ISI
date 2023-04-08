<script>
import { ref,reactive } from 'vue';
import { mapState } from 'vuex';

export default {
  name: "Footer",
  setup() {
    const active = ref(0);
    const themeVars = reactive({
      tabbarHeight: '7%' ,
      tabbarItemIconSize: '30px',
      tabbarItemFontSize: '15px',
      tabbarItemActiveColor: '#0392ff',
    });
    return { 
      active,
      themeVars,
    };
  },
  computed: {
    ...mapState(['User', 'Cart', 'Order']),
    //判断router的name，来确定当前点击的tabbar，从而改变颜色
    click() {
      if (this.$route.name == 'Home') {
        return 'home';
      } else if (this.$route.name == 'Cart') {
        return 'cart';
      } else if (this.$route.name == 'OrderList') {
        return 'order';
      } else if (this.$route.name == 'Account') {
        return 'account';
      } else {
        return '';
      }
    },
  },
  methods: {
    toHome() {
      this.$router.push({ name: 'Home' });
    },
    toCart() {
      if (this.User.currentUser.customerId == null) {
        this.$router.push({ name: 'Login' });
        return;
      } else {
        this.$router.push({ name: 'Cart'});
      }  
    },
    async toOrder() {
      if (this.User.currentUser.customerId == null) {
        this.$router.push({ name: 'Login' });
        return;
      } else {
        const customerId = this.User.currentUser.customerId;
        await this.$store.dispatch('Order/getOrderList', customerId);
        await this.$router.push({ name: 'OrderList' });
      }  
    },
    toAccount() {
      if (this.User.currentUser.customerId == null) {
        this.$router.push({ name: 'Login' });
        return;
      } else {
        this.$router.push({ name: 'Account', params: { id: this.User.currentUser.customerId } });
      }  
    },
  },
}    
</script>

<template>
  <div>
    <van-config-provider :theme-vars="themeVars">
      <van-tabbar :placeholder="true" :fixed="true" :z-index="1000" v-model="active" route>
        <van-tabbar-item replace icon="home-o" @click="toHome" :style="{ color: click === 'home' ? '#0392ff' : '' }">Home</van-tabbar-item>
        <van-tabbar-item replace icon="shopping-cart-o" @click="toCart" :badge="!Cart.CartTotal ? '' : Cart.CartTotal" :style="{ color: click === 'cart' ? '#0392ff' : '' }">Cart</van-tabbar-item>
        <van-tabbar-item replace icon="orders-o" @click="toOrder" :style="{ color: click === 'order' ? '#0392ff' : '' }">Order</van-tabbar-item>
        <van-tabbar-item replace icon="user-circle-o" @click="toAccount" :style="{ color: click === 'account' ? '#0392ff' : '' }">Account</van-tabbar-item>
      </van-tabbar>
    </van-config-provider>
  </div>
</template>

<style scoped>

</style>