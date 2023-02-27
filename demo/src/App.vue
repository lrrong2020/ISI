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
  <div>
    <router-view class="main view"></router-view>

    <!--Footer-->
    <router-view class="view footer" name="Footer"></router-view>
  </div>
</template>

<style scoped>

</style>
