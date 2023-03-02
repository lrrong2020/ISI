<script>
import { showToast } from 'vant';

export default {
  setup() {
    const onClickIcon = () => showToast('点击图标');
    const onClickButtonCart = () => showToast('Add to cart');
    const onClickButtonBuy = () => showToast('Add to cart');
    return {
      onClickIcon,
      onClickButtonCart,
      onClickButtonBuy,
    };
  },
};
</script>

<template>
  <van-action-bar>
    <van-action-bar-icon icon="chat-o" text="客服" @click="onClickIcon" />
    <van-action-bar-icon icon="shop-o" text="店铺" @click="onClickIcon" />
    <van-action-bar-button color="#be99ff" type="warning" text="加入购物车" @click="onClickButtonCart"/>
    <van-action-bar-button color="#7232dd" type="danger" text="立即购买" @click="onClickButtonBuy"/>
  </van-action-bar>
</template>

<style lang="less" scoped>

</style>