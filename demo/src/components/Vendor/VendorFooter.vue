<script>
import { ref,reactive } from 'vue';

export default {
  name: "VendorFooter",
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
  }
}    
</script>

<template>
  <div>
    <van-config-provider :theme-vars="themeVars">
      <van-tabbar :placeholder="true" :fixed="true" :z-index="1000" v-model="active" route>
        <van-tabbar-item replace to="/Vendor/" icon="home-o">VendorHome</van-tabbar-item>
        <van-tabbar-item replace to="/Vendor/order" icon="orders-o">VendorOrder</van-tabbar-item>
        <van-tabbar-item replace to="/Vendor/account" icon="user-circle-o">VendorAccount</van-tabbar-item>
      </van-tabbar>
    </van-config-provider>
  </div>
</template>

<style scoped>

</style>