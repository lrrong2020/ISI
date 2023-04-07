<script>
import { RouterLink, RouterView } from 'vue-router'

export default {
  name: 'App',
  components: {
    RouterLink,
    RouterView,
  }
}
</script>

<template>
  <div class="bg">
    <div>
      <!--Header-->
      <router-view class="view header" name="Header"></router-view>
    </div>
    <div>
      <!--Main-->
      <router-view class="main view"></router-view>
    </div>
    <div>
      <!--Footer-->
      <router-view class="view footer" name="Footer"></router-view>
    </div> 
  </div>
</template>

<style scoped>
.bg {
  font-family: "Comic Sans MS";
}
</style>
