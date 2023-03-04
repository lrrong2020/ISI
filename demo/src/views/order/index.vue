<script>
import { showToast } from 'vant';

export default {
  name: "Order", 
  setup() {
    const onAdd = () => showToast('新增');
    return {
      onAdd,
    };
  },
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

    </div>
  </div>
</template>

<style lang="less" scoped>

</style>