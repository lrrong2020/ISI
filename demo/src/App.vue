<script>
import { ref } from 'vue';
import { RouterLink, RouterView } from 'vue-router'


export default {
  name: "App",
  components: {},
};
</script>

<template>
<!--Top NavBar-->
  <router-view class="topNav view" name="TopNavBar"></router-view>

<!--RouterView-->
  <router-view ></router-view>

<!--Tabbar-->
  <router-view class="bottomNav view" name="BottomNavBar"></router-view>
</template>

<style scoped>

</style>
