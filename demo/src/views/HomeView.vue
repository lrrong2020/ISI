<script>
import product from "@/product.js"
import EmployeeList from '@/components/EmployeeList.vue';

export default {
  name: "Home",
  components: {
    EmployeeList,
  },
  data () {
    return {
      items: product.items,
    }
  },
}
</script>

<template>
  <div>
    <employee-list />
    <van-card
      v-for="(item,index) in items"
      :key="index"
      :num="item.num"
      :price="item.price"
      :desc="item.desc"
      :title="item.title"
      :thumb="item.thumb"
      class="product_list"
    >
  <template #tags>
    <van-tag plain type="primary">标签</van-tag>
    <van-tag plain type="primary">标签</van-tag>
  </template>
  <template #footer>
    <van-button size="mini">按钮</van-button>
    <van-button size="mini">按钮</van-button>
  </template>
</van-card>
    </div>
</template>

<style scoped>
</style>