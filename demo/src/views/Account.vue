<script>
import { showToast } from 'vant';

export default {
  name: "Account",
  components: {},
  setup() {
    const onAdd = () => showToast('新增');
    return {
      onAdd,
    };
  },
};
</script>

<template>
  <div style="background-color: aqua;">
    <h1>Account</h1>
  </div>
  <van-contact-card type="add" @click="onAdd" />
</template>

<style>

</style>
