<script>
export default {
  name: "VendorHeader",
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
  title="Product Detail"
  left-arrow
  @click-left="onClickLeft"
  class="header"
  />
</template>

<style lang="less" scoped>

</style>