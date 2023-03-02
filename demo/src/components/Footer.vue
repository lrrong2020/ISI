<script>
import { ref,reactive } from 'vue';

export default {
  name: "Footer",
  setup() {
    const active = ref(0);
    const themeVars = reactive({
      tabbarHeight: '58px',
      tabbarItemIconSize: '35px',
      tabbarItemFontSize: '15px',
      tabbarItemActiveColor: '#7232dd',
    });
    return { 
      active,
      themeVars,
    };
  }
}    
</script>

<template>
  <div>
    <van-config-provider :theme-vars="themeVars">
      <van-tabbar placeholder height: v-model="active" route>
        <van-tabbar-item replace to="/" icon="home-o">Home</van-tabbar-item>
        <van-tabbar-item replace to="/cart" icon="shopping-cart-o">Cart</van-tabbar-item>
        <van-tabbar-item replace to="/order" icon="orders-o">Order</van-tabbar-item>
        <van-tabbar-item replace to="/account" icon="user-circle-o">Account</van-tabbar-item>
      </van-tabbar>
    </van-config-provider>
  </div>
</template>

<style scoped>

</style>