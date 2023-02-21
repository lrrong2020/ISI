<script>
import { ref } from 'vue';
import { RouterLink, RouterView } from 'vue-router'
import { showToast } from 'vant';

export default {
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
  <van-nav-bar fixed title="标题" left-text="返回" left-arrow @click-left="onClickLeft" @click-right="onClickRight">
    <template #right>
      <van-icon name="search" size="18" />
    </template>
  </van-nav-bar>

<!--Blank Space-->
  <div style="height: 1rem;"></div>

<!--RouterView-->
  <RouterView />

<!--Blank Space-->
  <div style="height: 2rem;"></div>

<!--Tabbar-->
  <van-tabbar v-model="active" @change="onChange" route>
    <van-tabbar-item replace to="/" icon="home-o">Home</van-tabbar-item>
    <van-tabbar-item replace to="/about" icon="shopping-cart-o">Cart</van-tabbar-item>
    <van-tabbar-item replace to="/account" icon="user-circle-o">Account</van-tabbar-item>
  </van-tabbar>

</template>

<style scoped>

</style>
