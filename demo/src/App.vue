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
  <van-nav-bar fixed placeholder title="标题" left-text="返回" left-arrow @click-left="onClickLeft" @click-right="onClickRight">
    <template #right>
      <van-icon name="search" size="18" />
    </template>
  </van-nav-bar>

<!--RouterView-->
  <p style="background-color: aqua; margin: 0 auto;">aaaaa</p>
  <router-view></router-view>
  <p style="background-color: red; margin: 0 auto;">aaaaa</p>
  
<!--Tabbar-->
  <van-tabbar placeholder height: v-model="active" @change="onChange" route>
    <van-tabbar-item replace to="/" icon="home-o">Home</van-tabbar-item>
    <van-tabbar-item replace to="/about" icon="shopping-cart-o">Cart</van-tabbar-item>
    <van-tabbar-item replace to="/account" icon="user-circle-o">Account</van-tabbar-item>
  </van-tabbar>
</template>

<style scoped>

</style>
