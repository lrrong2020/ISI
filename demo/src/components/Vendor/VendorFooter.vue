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
      tabbarItemActiveColor: '#1989fa',
    });
    return { 
      active,
      themeVars,
    };
  },
  computed: {
    click() {
      if (this.$route.name == 'VendorHome') {
        return 'vendorHome';
      } else if (this.$route.name == 'VendorOrderList') {
        return 'vendorOrder';
      } else {
        return '';
      }
    }
  },
  methods: {
    toHome() {
      this.$router.push({ path: '/Vendor' });
    },
    toOrderList() {
      this.$store.dispatch('Vendor/getVendorOrderList');
      this.$router.push({ path: '/Vendor/OrderList' });
    },
  },
}    
</script>

<template>
  <div>
    <van-config-provider :theme-vars="themeVars">
      <van-tabbar :placeholder="true" :fixed="true" :z-index="1000" v-model="active" route>
        <van-tabbar-item replace icon="home-o" @click="toHome" :style="{ color: click === 'vendorHome' ? '#0392ff' : '' }">VendorHome</van-tabbar-item>
        <van-tabbar-item replace icon="orders-o" @click="toOrderList" :style="{ color: click === 'vendorOrder' ? '#0392ff' : '' }">VendorOrder</van-tabbar-item>
      </van-tabbar>
    </van-config-provider>
  </div>
</template>

<style scoped>

</style>