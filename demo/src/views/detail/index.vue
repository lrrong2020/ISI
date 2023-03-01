<script>
export default {
    name: "Detail",
    setup() {
        const images = [
            'https://fastly.jsdelivr.net/npm/@vant/assets/apple-1.jpeg',
            'https://fastly.jsdelivr.net/npm/@vant/assets/apple-2.jpeg',
        ];
        return { images };
    },
}
</script>

<template>
    <div class="swipe">
        <van-swipe class="my-swipe" :autoplay="3000" indicator-color="white">
            <van-swipe-item>1</van-swipe-item>
            <van-swipe-item>2</van-swipe-item>
            <van-swipe-item>3</van-swipe-item>
            <van-swipe-item>4</van-swipe-item>
        </van-swipe>
    </div>
    <div class="main">
        <p>aaaaaaaaaaa</p>
    </div>
</template>

<style>
 .my-swipe .van-swipe-item {
    color: #fff;
    font-size: 20px;
    line-height: 150px;
    text-align: center;
    background-color: #39a9ed;
  }
  .image{
    width: 100%;
    height: 100%;
  }
  .main{
    margin: 20px 10px 0px 10px;
    border-radius: 20px;
    background-color: aqua;
  }
</style>