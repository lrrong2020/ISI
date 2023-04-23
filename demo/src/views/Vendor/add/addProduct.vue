<template>
  <div>
  <van-nav-bar
    :border="false"
    title="Add New Product"
    left-arrow
    @click-left="onClickLeft"
    class="header"
  />
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
      label="Color " 
      placeholder="What color?" 
      :rules="[{ required: true, message: 'Property is required' }]"
      />

      <van-field 
      v-model="propertySecond" 
      name="propertySecond"
      label="Size" 
      placeholder="What size?" 
      :rules="[{ required: true, message: 'Property Second is required' }]"
      />

      <van-field
        v-model="result"
        is-link
        readonly
        name="brand"
        label="Brand"
        placeholder="Select Brand"
        @click="showPicker = true"
      />
      <van-popup v-model:show="showPicker" position="bottom">
        <van-picker
          :columns="columns"
          @confirm="onConfirm"
          @cancel="showPicker = false"
        />
      </van-popup>

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
      name="photo"
      type="file" 
      label="Image" 
      placeholder="photo" 
      :rules="[{ required: true, message: 'photo is required' }]"
      @change="onFileChange"
      />
    </van-cell-group>

    <div style="margin: 16px">
      <van-button round block type="primary" native-type="submit"> Submit </van-button>
    </div>
  </van-form>
</div>
</template>

<script>
import { ref } from "vue";
import { mapState } from "vuex";


export default {
  setup() {
    const productName = ref("");
    const property = ref("");
    const propertySecond = ref("");
    const brand = ref("");
    const price = ref("");
    const image = ref("");

    const that = this;
    const onSubmit = (value) => {
        console.log("onSubmit");
        console.log("value", value);
        addProduct(value);
    };
    const result = ref('');
    const showPicker = ref(false);
    const columns = [
      { text: 'Huawei', value: 'Huawei' },
      { text: 'Apple', value: 'Apple' },
      { text: 'Xiaomi', value: 'Xiaomi' },
      { text: 'Samsung', value: 'Samsung' },
      { text: 'vivo', value: 'vivo' },
      { text: 'OPPO', value: 'OPPO' },
      { text: 'realme', value: 'realme' },
    ];

    const onConfirm = ({ selectedOptions }) => {
      result.value = selectedOptions[0]?.text;
      showPicker.value = false;
    };
    const onClickLeft = () => history.back();
    return {
      productName,
      brand,
      price,
      image,
      property,
      propertySecond,
      onSubmit,
      result,
      columns,
      onConfirm,
      showPicker,
      onClickLeft,
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

    onFileChange(e) {
      var files = e.target.files || e.dataTransfer.files;
      if (!files.length)
        return;
      this.createImage(files[0]);
    },
    
    createImage(file) {
      var image = new Image();
      var reader = new FileReader();
      var vm = this;

      reader.onload = (e) => {
        vm.image = e.target.result;
      };
      reader.readAsDataURL(file);
    },

  },
};
</script>
