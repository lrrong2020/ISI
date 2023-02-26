<script>
import product from "@/product.js"
import EmployeeList from '@/components/EmployeeList.vue';
import data from '@/product.json'

export default {
  name: "Home",
  components: {
    EmployeeList,
  },
  data () {
    return {
      items: product.items,
      dataJson: [],
    }
  },
  mounted() {

    //pretend to load data from the api, may rewrite later on   
    const loadData = () => Promise.resolve(Object.values(data));

    //setTimeOut may be added to simulate network congestion

    loadData()
      .then((res) =>{
          console.log("Successfully fetched the data: ");
          console.log(res);

          //store the data to the local variable
          this.dataJson = res;

          //if(res.status.code == “404”)

          //
      });

  },
}
</script>

<template>
  <div>
    <!-- Get data from .json-->
    <van-card
      v-for="item in dataJson"
      :num="item.num"
      :price="item.price"
      :desc="item.desc"
      :title="item.title"
      :thumb="item.thumb"
    >
      <template #tags>
        <van-tag plain type="primary">Tag1</van-tag>
        <van-tag plain type="primary">Tag2</van-tag>
      </template>
      <template #footer>
        <van-button size="mini">Cart</van-button>
        <van-button size="mini">Buy</van-button>
      </template>
    </van-card>

    <!--Test-->
    <employee-list />

    <!--Get data from .js-->
    <van-card
      v-for="(item,index) in items"
      :key="index"
      :num="item.num"
      :price="item.price"
      :desc="item.desc"
      :title="item.title"
      :thumb="item.thumb"
    >
  <template #tags>
    <van-tag plain type="primary">Tag1</van-tag>
    <van-tag plain type="primary">Tag2</van-tag>
  </template>
  <template #footer>
    <van-button size="mini">Cart</van-button>
    <van-button size="mini">Buy</van-button>
  </template>
</van-card>
    </div>
</template>

<style scoped>
</style>