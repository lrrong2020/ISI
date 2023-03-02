<script>
import { ref } from 'vue';

export default {
  name: "Detail",
  setup() {
    const active = ref(0);

    const text = 'Vant is lightweight, customizable and customizable, 2017 at present. . . Official official official official vue 2 vue 2 vue 3 vue 3 vue 3 version version version version version WeChat WeChat applet';
    return { text,active };
  },
}
</script>

<template>
  <div class="bg">
    <div class="swipe">
      <van-swipe class="my-swipe" :autoplay="3000" indicator-color="white">
        <van-swipe-item>1</van-swipe-item>
        <van-swipe-item>2</van-swipe-item>
        <van-swipe-item>3</van-swipe-item>
        <van-swipe-item>4</van-swipe-item>
      </van-swipe>
    </div>
    <div class="main">
      <div class="price">
        <p><van-tag plain type="danger">Price</van-tag> $1000</p>
      </div>
      <div class="name">
        <p> Product Name</p>
      </div>
      <div class="desc">
        <p><van-tag plain type="primary">Brand</van-tag>{{ text }}</p>
      </div>
    </div>
    <div class="prop">
      <van-tabs v-model:active="active" swipeable>
        <van-tab v-for="index in 3" :title="'Prop' + index">
          Prop {{ index }}
        </van-tab>
      </van-tabs>
    </div>
  </div>
</template>

<style lang="less" scoped>
.bg{
    .my-swipe .van-swipe-item {
    color: #fff;
    font-size: 20px;
    line-height: 150px;
    text-align: center;
    background-color: #39a9ed;
  }
  .main{
    border-radius: 15px;
    background-color: aqua;
    .name{
      font-size: 20px;
    }
    .desc{
      font-size: 15px;
    }
  }
}

</style>