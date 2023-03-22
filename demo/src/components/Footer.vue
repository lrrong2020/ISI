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
      tabbarItemActiveColor: '#7232dd',
    });
    return { 
      active,
      themeVars,
    };
  },
  computed: {
    ...mapState(['User']),
  },
  methods: {
    toCart() {
      if (this.User.currentUser.customerId == null) {
        this.$router.push({ name: 'Login' });
        return;
      } else {
        this.$router.push({ name: 'Cart'});
      }  
    },
    toOrder() {
      if (this.User.currentUser.customerId == null) {
        this.$router.push({ name: 'Login' });
        return;
      } else {
        this.$router.push({ name: 'OrderList'});
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
        <van-tabbar-item replace to="/" icon="home-o">Home</van-tabbar-item>
        <van-tabbar-item replace icon="shopping-cart-o" @click="toCart">Cart</van-tabbar-item>
        <van-tabbar-item replace icon="orders-o" @click="toOrder">Order</van-tabbar-item>
        <van-tabbar-item replace icon="user-circle-o" @click="toAccount">Account</van-tabbar-item>
      </van-tabbar>
    </van-config-provider>
  </div>
</template>

<style scoped>

</style>