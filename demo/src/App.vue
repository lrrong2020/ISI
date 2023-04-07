<script setup>

import { useRouter, RouterView } from 'vue-router'
import { reactive, toRefs } from 'vue'
const router = useRouter()
const state = reactive({
  transitionName: 'slide-left'
})
router.beforeEach((to, from) => {
  if (to.meta.index > from.meta.index) {
    state.transitionName = 'slide-left' // 向左滑动
  } else if (to.meta.index < from.meta.index) {
    // 由次级到主级
    state.transitionName = 'slide-right'
  } else {
    state.transitionName = ''   // 同级无过渡效果
  }
})

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
.slide-right-enter-active,
.slide-right-leave-active,
.slide-left-enter-active,
.slide-left-leave-active{
    height: 100%;
    will-change: transform;
    transition: all 500ms;
    position: absolute;
    backface-visibility: hidden;
}
.slide-right-enter{
    opacity: 0;
    transform: translate3d(-100%, 0, 0);
}
.slide-right-leave-active{
    opacity: 0;
    transform: translate3d(100%, 0, 0);
}
.slide-left-enter{
    opacity: 0;
    transform: translate3d(100%, 0, 0);
}
.slide-left-leave-active{
    opacity: 0;
    transform: translate3d(-100%, 0, 0);
}
</style>
