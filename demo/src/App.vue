<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <div>
    <RouterLink to="/">Home</RouterLink>
    <RouterLink to="/cart">Cart</RouterLink>
    <RouterLink to="/order">Order</RouterLink>
    <RouterLink to="/account">Account</RouterLink>
  </div>
  <RouterView />
  

  <div class="aaa">
    <p>AAA</p>
  </div>
  <svg class="icon" aria-hidden="true">
    <use xlink:href="#icon-adduser"></use>
  </svg>
  <svg class="icon" aria-hidden="true">
    <use xlink:href="#icon-home"></use>
  </svg>
  <svg class="icon" aria-hidden="true">
    <use xlink:href="#icon-cart"></use>
  </svg>
</template>

<style scoped>
.wapper {
  align-items: center;
  background-color: red;
  width: 160px;
  height: 160px;
}
.aaa {
  background-color: blue;
  width: 160px;
  height: 160px;
}
</style>
