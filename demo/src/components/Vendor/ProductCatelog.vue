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
      searchValue: '',
      isFiltering: false,
      filterValue:'',
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

    
    filterByBrand(event){
      this.isFiltering = true;
      this.filterValue = event.target.id;

      console.log("this.searchValue", this.searchValue.length);

      var params = [];

      params.push(this.searchValue);
      params.push(event.target.id);
    


      //filter based on searching
      if(this.searchValue.length > 0){
        console.log("this.searchValue.length > 0");

        this.$store.dispatch('Product/searchProductAndFilterByBrand', params);
      }

      else{
      console.log("this.searchValue.length <= 0");
      console.log("this.isFiltering after filter: ");
      console.log(this.isFiltering);

      console.log(event.target.id);

      params.push(false);
      this.$store.dispatch('Product/filterProductByBrand', event.target.id);
      }
    },

    resetFilter(){
      console.log("reset filter");
      this.isFiltering = false;

      console.log("this.isFiltering after reset: ");
      console.log(this.isFiltering);

      this.searchProduct();
      // this.getProductList();
    },

    //searchProduct
    searchProduct() {
      if(this.searchValue.length == 0) this.showList();

      if (this.isNumeric(this.searchValue) && this.searchValue.length > 3){
        this.$store.dispatch("Product/searchProductById", this.searchValue.substring(3));
      }
      else {
        var params = [];

params.push(this.searchValue);
params.push(this.filterValue);

if(this.isFiltering) {
  this.$store.dispatch('Product/searchProductAndFilterByBrand', params);
}

else{
  this.$store.dispatch('Product/searchProduct', this.searchValue);
// this.$store.dispatch('Product/getProductList');
console.log(`search Product: ${this.searchValue}`);
}
      }

      // this.$store.dispatch('Product/getProductList');
      console.log(`search Product: ${this.searchValue}`);
    },

    isNumeric(str) {
      // if (typeof str != "string") return false;
      // return (
      //   !isNaN(str) &&
      //   !isNaN(parseFloat(str))
      // );
      console.log("isNumeric ")
      console.log(str);
      console.log(str.substring(0, 3) == "id:");

      return str.substring(0, 3) == "id:";

    },

    toDetail(item) {
      this.$router.push({ name: "VendorProductDetail", params: { id: item } });
    },

    pagechange(page) {
      this.currentPage = page;
      this.getProductList();
    },

    toPage(page) {
      this.currentPage = page;
      // this.getProductList();
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

  <van-button size="small" type="primary" id="Xiaomi" @click="filterByBrand">
          Xiaomi
  </van-button>

  <van-button size="small" type="primary" id="Huawei" @click="filterByBrand">
          Huawei
  </van-button>

  <van-button size="small" type="primary" id="Apple" @click="filterByBrand">
          Apple
  </van-button>


  <van-button plain hairline size="small" type="primary" id="Reset" @click="resetFilter" v-if="isFiltering">
          Reset
  </van-button>


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


  <!-- <van-config-provider :theme-vars="themeVars">
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
  </van-config-provider> -->

  <van-config-provider :theme-vars="themeVars" v-if="Product.productList.length !== 0">
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
    <!--Paging-->
    <van-pagination v-model="currentPage" :total-items="Product.productList.length" 
    :show-page-size="5" :items-per-page="itemsPerPage" @change="pagechange">
      <template #prev-text>
          <van-icon name="arrow-left" />
      </template>
      <template #next-text>
          <van-icon name="arrow" />
      </template>
      <template #page="{ text }">{{ text }}</template>
    </van-pagination>
    <!--Input Location-->
    <van-cell-group inset class="input">
      <van-field
        v-model.number="page"
        center
        clearable
        type="digit"
        placeholder="Go to page"
      >
        <template #button>
        <van-button size="small" type="primary" @click="toPage(page)">
          GO!
        </van-button>
        </template>
      </van-field>
    </van-cell-group>
  <!--全局样式-->
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
