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
  fixed
  placeholder
  @click-left="onClickLeft"
  class="header"
  />
</template>

<style lang="less" scoped>

</style>