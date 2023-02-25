<script>
export default {
  name: "Account",
  components: {},
  setup() {
  },
};
</script>

<template>
<div>
  <van-button plain size="large" to="/login"
  icon="https://fastly.jsdelivr.net/npm/@vant/assets/user-active.png"
  type="primary">
    Login
  </van-button>

  <van-button plain size="large" to="/signup"
  icon="https://fastly.jsdelivr.net/npm/@vant/assets/user-active.png"
  type="primary">
    Sign Up
  </van-button>
</div>
</template>

<style>

</style>
