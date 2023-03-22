<script>
import { reactive } from "vue";

import { mapState } from "vuex";
//Brand
// import Brand from '@/components/Brand.vue';

export default {
  name: "Product",
  //global style
  setup() {
    // themeVars 内的值会被转换成对应 CSS 变量
    // 比如 sliderBarHeight 会转换成 `--van-slider-bar-height`
    const themeVars = reactive({
      cardRadius: "20px",
      cardThumbSize: "150px",
      cardThumbRadius: "0px",
      cardBackground: "#ffffff",
      cardPriceColor: "#ee0a24",
      cardFontSize: "15px",
      cardTitleLineHeight: "50px",
      cardDescLineHeight: "50px",
      cardPriceFontSize: "15px",
      cardPriceIntegerFontSize: "25px",
      gridItemContentPadding: "0px",
    });

    return {
      themeVars,
    };
  },

  //Brand
  components: {
    // Brand,
  },

  //business logic
  data() {
    return {
      totalItems: 0,
      currentPage: 1,
      itemsPerPage: 5,
      page: null,

      //search
      searchValue: "",
    };
  },

  created() {
    this.getProductList();
  },

  mounted() {},

  computed: {
    ...mapState(["Product"]),
  },

  methods: {
    //getProductList from Store
    getProductList() {
      this.$store.dispatch("Product/getProductList");
      console.log("1");
    },

    //searchProduct
    searchProduct() {
      if (this.isNumeric(this.searchValue)){
        this.$store.dispatch("Product/searchProductById", this.searchValue);
      }
      else {
        this.$store.dispatch("Product/searchProduct", this.searchValue);
      }

      // this.$store.dispatch('Product/getProductList');
      console.log(`search Product: ${this.searchValue}`);
    },

    isNumeric(str) {
      if (typeof str != "string") return false;
      return (
        !isNaN(str) &&
        !isNaN(parseFloat(str))
      );
    },

    toDetail(item) {
      this.$router.push({ name: "ProductDetail", params: { id: item } });
    },

    pagechange(page) {
      this.currentPage = page;
      this.getProductList();
    },

    toPage(page) {
      this.currentPage = page;
      this.getProductList();
      console.log(this.page);
      this.page = null;
    },

    //After clear
    showList() {
      this.getProductList();
    },

    //search
    onSearch(val) {
      showToast(val);
    },

    //clear
    showList() {
      this.getProductList();
    },

    toAddProductPage() {
      console.log("add product");
      this.$router.push({ name: "VendorProductAdd", params: {} });
    },
  },
};
</script>

<template>
  <h1>[Product Catelog Vendor]</h1>

  <van-search
    v-model="searchValue"
    show-action
    shape="round"
    background="#ffffff"
    placeholder="Type to search"
    @search="onSearch"
    @clear="showList"
    @update:model-value="searchProduct"
  >
    <template #action>
      <div @click="searchProduct" class="button">Search</div>
    </template>
  </van-search>

  <van-button
    plain
    hairline
    size="large"
    type="primary"
    id="Add"
    @click="toAddProductPage"
  >
    Add A New Item
  </van-button>

  <van-config-provider :theme-vars="themeVars">
    <!--全局样式-->

    <van-card
      v-for="item in Product.productList.slice(
        (currentPage - 1) * itemsPerPage,
        currentPage * itemsPerPage
      )"
      :key="item.productId"
      :num="item.quantity"
      :price="item.price"
      :desc="item.brand"
      :title="item.productName"
      :thumb="item.photo"
      @click="toDetail(item.productId)"
      class="card"
    >
      <template #tags>
        <van-tag plain type="primary">{{ item.productId }}</van-tag>
      </template>
    </van-card>
  </van-config-provider>
</template>

<style lang="less" scoped>
.card {
  border-radius: 20px;
}
.input {
  margin: 0px;
  border-radius: 0%;
}
</style>
