<script>
import { showToast } from 'vant';

export default {
  name: "App",
  components: {},
  setup() {
    const onClickLeft = () => history.back();
    const onClickRight = () => showToast('Search');
    return { 
      onClickLeft,
      onClickRight,
     };
  },
};
</script>

<template>
<div>
<!--Top NavBar-->
  <van-nav-bar fixed placeholder title="Title" left-text="Back" left-arrow @click-left="onClickLeft" @click-right="onClickRight">
    <template #right>
      <van-icon name="search" size="18" />
    </template>
  </van-nav-bar>
</div>
</template>

<style scoped>

</style>