<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <header>
    <img alt="Vue logo" class="logo" src="@/assets/logo.svg" width="125" height="125" />

    <div class="wrapper">

      <nav>
        <RouterLink to="/">Home</RouterLink>
        <RouterLink to="/about">About</RouterLink>
      </nav>
    </div>
  </header>

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
