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
    <!--Header-->
    <router-view class="view header" name="Header"></router-view>

    <!--Main-->
    <router-view class="main view"></router-view>

    <!--Footer-->
    <router-view class="view footer" name="Footer"></router-view>

    <!--DetailFooter-->
    <router-view class="view detail-footer" name="DetailFooter"></router-view>
  </div>
</template>

<style scoped>

</style>
