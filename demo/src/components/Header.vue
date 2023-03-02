<script>
export default {
  name: "Header",
  setup() {
    const onClickLeft = () => history.back();

    return {
      onClickLeft,
    };
  },
}
</script>

<template>
  <van-nav-bar
  :border="false"
  title="Title"
  left-arrow
  @click-left="onClickLeft"
  class="header"
  />
</template>

<style lang="less" scoped>
.header {
  background-image: linear-gradient(#7232dd, #ffffff);
}
</style>