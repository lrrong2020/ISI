<script>
export default {
  name: 'App',
  
};
</script>

<template>
  <div class="bg">
    <div>
      <!--Header-->
      <router-view class="view header" name="Header"></router-view>
    </div>
    <div>
      <!-- <router-view class="main view" /> -->
      <router-view class="main view" v-slot="{Component}">
        <transition name="fade" mode="out-in">
          <component :is="Component" />
        </transition>
      </router-view>
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

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.2s;
  /* transition: opacty 0.5s ease; */
}
.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
.page{
  position: absolute;
  top: 0;
  bottom: 0;
  width: 100%;
  margin: 0 auto;
}
</style>
