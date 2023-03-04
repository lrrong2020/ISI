<script>
import { mapState } from "vuex";
export default {
  name: "Account",
  components: {
  },
  computed: {
    ...mapState(["Product"]),
  },
}

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

<style lang="less" scoped>

</style>