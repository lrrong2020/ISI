<script>
import { mapState } from "vuex";
import Apple from "@/assets/Brands/Apple.jpg"
import Huawei from "@/assets/Brands/Huawei.jpg"
import Xiaomi from "@/assets/Brands/Xiaomi.jpg"
import Samsung from "@/assets/Brands/Samsung.jpg"
import vivo from "@/assets/Brands/vivo.jpg"
import OPPO from "@/assets/Brands/OPPO.jpg"
import realme from "@/assets/Brands/realme.jpg"

export default {
  name: "HomeSwipe",
  data() {
    return {
      Swipe: [
        {
          id: 11,
          url: Apple,
        },
        {
          id:33,
          url: Huawei,
        },
        {
          id: 34,
          url: Xiaomi,
        },
        {
          id: 35,
          url: Samsung,
        },
        {
          id: 36,
          url: vivo,
        },
        {
          id: 37,
          url: OPPO,
        },
        {
          id:38,
          url: realme,
        },
      ],
    }
  },
  computed: {
    ...mapState(["Product"]),
  },
  methods: {
    async toDetail(id) {
      await this.$store.dispatch("Product/getProductDetail", id);
      this.$router.push({ name: "Detail", params: { id: id } });
    },
  },
}
</script>
<template>
  <div>
    <van-swipe :autoplay="3000" class="swipe">
      <van-swipe-item v-for="item in Swipe" :key="item">
        <img :src="item.url" alt="" class="img" @click="toDetail(item.id)">
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