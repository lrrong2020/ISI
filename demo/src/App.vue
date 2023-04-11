App.vue
<script>
import { mapState } from 'vuex';

export default {
  name: 'App',
  computed: {
    ...mapState(['Include']),
  },
};

</script>

<template>
  <div class="bg">
    <div>
      <!--Header-->
      <router-view class="view header" name="Header"></router-view>
    </div>
    <div>
      <router-view class="main view" v-slot="{ Component }">
        <transition name="fade" mode="out-in">
        <keep-alive :max="10" :include="Include.include">
          <component :is="Component" />
        </keep-alive>
      </transition>
      </router-view>
      <!-- <router-view class="main view" v-slot="{Component}">
        <transition name="fade" mode="out-in">
          <component :is="Component" />
        </transition>
      </router-view> -->
    </div>
    <div>
      <!--Footer-->
      <router-view class="view footer" name="Footer"></router-view>
    </div>
  </div>
</template>

<style scoped>
html, body {
  height: 100%;
  width: 100%;
  overflow-x: hidden;
  overflow-y: scroll;
}
.bg {
  font-family: "Comic Sans MS";
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.1s;
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
