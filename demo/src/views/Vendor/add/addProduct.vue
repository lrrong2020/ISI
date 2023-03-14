<template>
  <van-form @submit="onSubmit">
    <van-cell-group inset>
      <van-field
        v-model="productName"
        name="Product"
        label="Product"
        placeholder="The name of the product"
        :rules="[{ required: true, message: 'Product Name is required' }]"
      />

      <van-field 
      v-model="brand" 
      name="Brnad"
      label="Brand" 
      placeholder="Which brand?" 
      :rules="[{ required: true, message: 'Brand is required' }]"
      />

      <van-field
        v-model="price"
        name="Price"
        type="number"
        label="Price"
        placeholder="Set price to 2dp"
        :rules="[{ required: true, message: 'Price is required' }]"
      />

      <van-field 
      v-model="image" 
      name="Image"
      type="url" 
      label="Image" 
      placeholder="imageeeeee" 
      :rules="[{ required: true, message: 'Image is required' }]"
      />
    </van-cell-group>

    <div style="margin: 16px">
      <van-button round block type="primary" native-type="submit"> Submit </van-button>
    </div>
  </van-form>


  <van-form @submit="onSubmit">
  <van-cell-group inset>
    <van-field
      v-model="username"
      name="Username"
      label="Username"
      placeholder="Username"
      :rules="[{ required: true, message: 'Username is required' }]"
    />
    <van-field
      v-model="password"
      type="password"
      name="Password"
      label="Password"
      placeholder="Password"
      :rules="[{ required: true, message: 'Password is required' }]"
    />
  </van-cell-group>
  <div style="margin: 16px;">
    <van-button round block type="primary" native-type="submit">
      Submit
    </van-button>
  </div>
</van-form>

</template>

<script>
import { ref } from "vue";

export default {
  setup() {
    const productName = ref("");
    const brand = ref("");
    const price = ref("");
    const image = ref("");

    const username = ref('');
    const password = ref('');

    const onSubmit = (values) => {
      console.log("submit", values);
    };

    return {
      productName,
      brand,
      price,
      image,
      onSubmit,
      username,
      password,
    };
  },
};
</script>
