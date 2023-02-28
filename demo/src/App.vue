<script>
import { ref } from 'vue';
import { RouterLink, RouterView } from 'vue-router'


export default {
  name: "App",
  components: {},
};
</script>

<template>
<div>
<!--Top NavBar-->
  <router-view class="topNav view" name="TopNavBar"></router-view>

<!--RouterView-->
  <router-view v-slot="{Component}">
    <transition name="slide" mode="out-in">
      <component :is="Component" />
    </transition>
  </router-view>

<!--Tabbar-->
  <router-view class="bottomNav view" name="BottomNavBar"></router-view>
</div>
</template>

<style lang="css">
.slide-enter-active,
.slide-leave-active {
  transition: opacity 1s, transform 1s;
}
.slide-enter-from,
.slide-leave-to {
  opacity: 0;
  transform: translateX(-30%);
}
</style>
