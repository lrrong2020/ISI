<script>
export default {
  name: "HomeSwipe",
  data() {
    return {
      Swipe: [
        {
          url: 'https://i.ytimg.com/vi/TppeEN7gnsA/maxresdefault.jpg',
        },
        {
          url: 'https://consumer.huawei.com/content/dam/huawei-cbg-site/common/mkt/pdp/phones/p60/images/design/huawei-p60-colours-01.jpg',
        },
        {
          url: 'https://i.teknolojioku.com/2/1280/720/storage/files/images/2023/03/11/adwed-1-TBbt_cover.jpg',
        },
        {
          url: 'https://akm-img-a-in.tosshub.com/indiatoday/images/story/202302/14649_samsung_s23-ultra_1-2-2023_1200x675-sixteen_nine.jpg?VersionId=11lWDaf3FVEq35iFvPeCQOod65Aj0cjD',
        },
        {
          url: 'https://fdn.gsmarena.com/imgroot/news/23/01/vivo-x90-series-malaysia-launch-soon/inline/-1200/gsmarena_001.jpg',
        },
        {
          url: 'https://technave.com/data/files/article/202205180627059647.jpeg',
        },
      ],
    }
  },
}
</script>
<template>
  <div>
    <van-swipe :autoplay="3000" class="swipe">
      <van-swipe-item v-for="item in Swipe" :key="item">
        <img :src="item.url" alt="" class="img">
      </van-swipe-item>
    </van-swipe>
  </div>
</template>

<style lang="less" scoped>
.swipe {
  width: 100%;
  height: 200px;
  .img {
    width: 100%;
    height: 100%;
  }
}
</style>