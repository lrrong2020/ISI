<script>
import { ref } from 'vue';
import { RouterLink, RouterView } from 'vue-router'
import { showToast } from 'vant';

export default {
  name: "App",
  components: {},
  setup() {
    const active = ref(0);
    const onClickLeft = () => history.back();
    const onClickRight = () => showToast('Search');
    return { 
      active,
      onClickLeft,
      onClickRight,
     };
  },
};
</script>

<template>
<!--Top NavBar-->
  <van-nav-bar fixed placeholder title="Title" left-text="Back" left-arrow @click-left="onClickLeft" @click-right="onClickRight">
    <template #right>
      <van-icon name="search" size="18" />
    </template>
  </van-nav-bar>

<!--RouterView-->
  <router-view></router-view>
 
<!--Tabbar-->
  <van-tabbar placeholder height: v-model="active" @change="onChange" route>
    <van-tabbar-item replace to="/" icon="home-o">Home</van-tabbar-item>
    <van-tabbar-item replace to="/cart" icon="shopping-cart-o">Cart</van-tabbar-item>
    <van-tabbar-item replace to="/account" icon="user-circle-o">Account</van-tabbar-item>
  </van-tabbar>
</template>

<style scoped>

</style>
