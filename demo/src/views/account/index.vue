<script>
import CountVuex from "@/components/CountVuex.vue";
import { mapState } from "vuex";
export default {
  name: "Account",
  components: {
    CountVuex,
  },
  computed: {
    ...mapState(["Product"]),
  },
}

</script>

<template>
  <div>
    Account
    <CountVuex />
    <div>{{ Product.productList.length }}</div>
  </div>
</template>

<style lang="less" scoped>

</style>