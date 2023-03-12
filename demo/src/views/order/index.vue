<script>
import { showSuccessToast, showFailToast } from 'vant';
import 'vant/es/toast/style';
import 'vant/es/notify/style';

export default {
  name: "Order", 
  setup() {
    const onAdd = () => showToast('新增');
    return {
      onAdd,
    };
  },
  methods: {
    aaa() {
      showFailToast('失败文案');
    },
  }
}
</script>

<template>
  <div>
    <div>
      <van-nav-bar
        :border="false"
        title="Order"
        left-arrow
        @click-left="onClickLeft"
        class="header"
      />
    </div>
    <div>
      <van-contact-card add-text="Change your address" type="edit" @click="onAdd" />
    </div>
    <div>
      <van-button type="primary" round block @click="aaa">Submit</van-button>
    </div>
  </div>
</template>

<style>

</style>