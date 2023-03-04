<script>
import { showToast } from 'vant';

export default {
  setup() {
    const onSubmit = () => showToast('点击按钮');
    const onClickLink = () => showToast('修改地址');
    return {
      onSubmit,
      onClickLink,
    };
  },
};
</script>

<template>
  <div>
    <!--CkeckOut-->
    <van-submit-bar :price="3050" button-text="Submit Order" @submit="onSubmit">
      <van-checkbox>Select All</van-checkbox>
    </van-submit-bar>
  </div>
</template>

<style lang="less" scoped>

</style>