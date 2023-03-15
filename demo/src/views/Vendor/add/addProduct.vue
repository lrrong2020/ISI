<template>
  <van-form @submit="addProduct">
    <van-cell-group inset>
      <van-field
        v-model="productName"
        name="productName"
        label="Product"
        placeholder="The name of the product"
        :rules="[{ required: true, message: 'Product Name is required' }]"
      />

      <van-field 
      v-model="property" 
      name="property"
      label="Property" 
      placeholder="What color?" 
      :rules="[{ required: true, message: 'Property is required' }]"
      />

      <van-field 
      v-model="brand" 
      name="brand"
      label="Brand" 
      placeholder="Which brand?" 
      :rules="[{ required: true, message: 'Brand is required' }]"
      />

      <van-field
        v-model="price"
        name="price"
        type="number"
        label="Price"
        placeholder="Set price to 2dp"
        :rules="[{ required: true, message: 'Price is required' }]"
      />

      <van-field 
      v-model="image" 
      name="url"
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

</template>

<script>
import { ref } from "vue";
import { mapState } from "vuex";


export default {
  setup() {
    const productName = ref("");
    const property = ref("");
    const brand = ref("");
    const price = ref("");
    const image = ref("");

    const that = this;
    const onSubmit = (value) => {
        console.log("onSubmit");
        console.log("value", value);
        addProduct(value);
    };

    return {
      productName,
      brand,
      price,
      image,
      property,
      onSubmit,
    };
  },

  computed: {
    ...mapState(["Product"]),
  },

  methods: {
    addProduct(value){
      console.log("add product");
      console.log(value);
      this.$store.dispatch("Product/vendorAddProduct", value);
    },

  },
};
</script>
