<script>
export default {
  name: "Header",
  methods: {
    onClickLeft() {
      this.$router.go(-1);
    },
  },
}
</script>

<template>
  <van-nav-bar
  :border="false"
  title="Product Detail"
  left-arrow
  fixed
  placeholder
  @click-left="onClickLeft"
  class="header"
  />
</template>

<style lang="less" scoped>

</style>