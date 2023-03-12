<script>
export default {
  name: "Header",
  methods: {
    onClickLeft() {
      this.$router.push('/');
    },
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