<script>
export default {
  name: "GenerateOrder",
  methods: {
    onClickLeft() {
      //router回到上一个页面
      this.$router.go(-1);
    }
  }
}
</script>

<template>
  <div class="bg">
    <div class="header">
      <van-nav-bar
        title="Generate Order"
        left-arrow
        @click-left="onClickLeft"
      />
    </div>
    <div class="address">
      <van-contact-card lang="en" type="edit" icon="location-o" name="Grant" list="Macao" :editable="false" />
    </div>
  </div>
</template>

<style lang="less" scoped>

</style>