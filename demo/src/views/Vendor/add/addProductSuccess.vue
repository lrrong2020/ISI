<template>
    <div style="margin: 16px">
      <van-button @click="navigateBack" round block type="primary" native-type="submit"> Go Back </van-button>
    </div>

</template>

<script>
import { mapState } from "vuex";


export default {
  setup() {


    return {

    };
  },

  computed: {
    ...mapState(["Product"]),
  },

  methods: {
    navigateBack(){
      this.$router.push({ name: "VendorHome", params: { } });
    },
  },
};
</script>
