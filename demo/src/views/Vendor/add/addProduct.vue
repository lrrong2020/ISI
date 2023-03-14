<template>
<van-cell-group inset>
  <van-field v-model="productName" ref="productName" label="productName" placeholder="The name of the product" />
  <van-field v-model="brand" ref="brand" label="brand" placeholder="Which brand?" />
  <van-field v-model="price" ref="price" label="price" placeholder="Set price to 2dp" />
  <van-field v-model="image" ref="image" label="image" placeholder="imageeeeee" />
</van-cell-group>
</template>

<script>import { ref } from 'vue';

export default {
  setup() {
    const productName = ref('productName');
    const brand = ref('brand');
    const price = ref('price');
    const image = ref('image');

    return { 
      productName,
      brand,
      price,
      image
    };
  },
};
</script>