<script>
import { reactive } from "vue";
import { ref } from "vue";
import { toRaw } from "vue";
import { mapState } from "vuex";
//Brand
import Brand from "@/components/Brand.vue";

export default {
  name: "Product",
  //全局样式
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
    Brand,
  },
  //业务逻辑
  data() {
    return {
      currentPage: 1,
      itemsPerPage: 5,
      page: null,
      //search
      searchValue: "",
      isFiltering: false,
      filterValue: "",
    };
  },
  created() {
    // this.getProductList();
    this.getProductListPaging();
  },
  mounted() {},
  computed: {
    ...mapState(["Product"]),
  },
  methods: {
    //getProductList from Store
    getProductList() {
      this.$store.dispatch("Product/getProductList");
    },

    //getProductList from Store
    getProductListPaging() {
      var params = [];
      params.push(0);
      params.push(3);
      this.$store.dispatch("Product/getProductListPaging", params);
    },
    //searchProduct
    searchProduct() {
      clearInterval(this.timer);
      console.log("clear timer");

      this.currentPage = 1;
      var params = [];

      params.push(this.searchValue);
      params.push(this.filterValue);

      if (this.isFiltering) {
        this.$store.dispatch("Product/searchProductAndFilterByBrand", params);
      } else {
        this.$store.dispatch("Product/searchProduct", this.searchValue);
        // this.$store.dispatch('Product/getProductList');
        console.log(`search Product: ${this.searchValue}`);
      }
      //当searchValue为空时，并且isFiltering为false时，重新获取数据
      if (this.searchValue.length == 0 && this.isFiltering == false) {
        this.timer = setInterval(() => {
          // this.getProductList();
          this.getProductListPaging();
        }, 1000);
      }
    },

    filterByBrand(event) {
      clearInterval(this.timer);
      console.log("clear timer");

      this.currentPage = 1;
      this.isFiltering = true;
      this.filterValue = event.target.id;

      console.log("this.searchValue", this.searchValue.lengtgith);

      var params = [];

      params.push(this.searchValue);
      params.push(event.target.id);

      //filter based on searching
      if (this.searchValue.length > 0) {
        console.log("this.searchValue.length > 0");

        this.$store.dispatch("Product/searchProductAndFilterByBrand", params);
      } else {
        console.log("this.searchValue.length <= 0");
        console.log("this.isFiltering after filter: ");
        console.log(this.isFiltering);

        console.log(event.target.id);

        params.push(false);
        this.$store.dispatch("Product/filterProductByBrand", event.target.id);
      }
    },

    resetFilter() {
      this.currentPage = 1;
      console.log("reset filter");
      this.isFiltering = false;

      console.log("this.isFiltering after reset: ");
      console.log(this.isFiltering);
      // 判断search是否为空,不为空则不清空
      if (this.searchValue.length == 0) {
        // this.getProductList();
        this.getProductListPaging();
        this.timer = setInterval(() => {
          // this.getProductList();
          this.getProductListPaging();
        }, 1000);
      } else {
        this.searchProduct();
      }

      // this.searchProduct();
      // this.getProductList();
    },

    async toDetail(item) {
      await this.$store.dispatch("Product/getProductDetail", item);
      this.$router.push({ name: "Detail", params: { id: item } });
    },

    pagechange(page) {
      console.log("PageChange", page);
      this.currentPage = page;
      // this.getProductList();
    },

    toPage(page) {
      console.log("ToPage", page);
      console.log();

      this.currentPage = page;
      // this.getProductList();
      console.log(this.page);
      this.page = null;
    },
    //After clear
    showList() {
      //判断isFiltering是否为true,是则不清空
      if (this.isFiltering) {
        this.searchProduct();
      } else {
        // this.getProductList();
        this.getProductListPaging();
      }
    },
    //search
    onSearch(val) {
      showToast(val);
    },

    // getProductList() {
    //   this.axios.get("http://localhost:3000/fakeBackendProducts").then((response) => {
    //     this.products = response.data;
    //     console.log(response.data)
    //   })
    // },

    // onClickButton() {
    //   console.log(this.searchValue);
    //   this.axios.get("http://localhost:8080/product/search?productName=" + this.searchValue)
    //   .then((response) => {
    //     this.products = response.data;
    //     console.log(response.data)
    //   })
    // },
  },
  //定时器获取后端数据, 1s一次，销毁时清除定时器
  timer: null,
  mounted() {
    // temporarily disable for paging debug
    // this.timer = setInterval(() => {
    //   this.getProductList();
    // }, 1000);
  },
  beforeUnmount() {
    clearInterval(this.timer);
    console.log("clear timer");
  },
};
</script>

<template>
  <!--Search-->
  <van-search
    v-model="searchValue"
    show-action
    shape="round"
    background="#ffffff"
    placeholder="Type to search"
    @search="onSearch"
    @clear="showList"
    @update:modelValue="searchProduct"
  >
    <template #action>
      <div @click="searchProduct" class="button">Search</div>
    </template>
  </van-search>
  <!--Brand-->
  <!-- <Brand />    -->

  <van-button size="small" type="primary" id="Xiaomi" @click="filterByBrand">
    Xiaomi
  </van-button>

  <van-button size="small" type="primary" id="Huawei" @click="filterByBrand">
    Huawei
  </van-button>

  <van-button size="small" type="primary" id="Apple" @click="filterByBrand">
    Apple
  </van-button>

  <van-button
    plain
    hairline
    size="small"
    type="primary"
    id="Reset"
    @click="resetFilter"
    v-if="isFiltering"
  >
    Reset
  </van-button>

  <!-- Empty -->
  <div class="empty" v-if="Product.productListPaging.length === 0">
    <van-empty description="No Product" />
  </div>

  <van-config-provider :theme-vars="themeVars" v-if="Product.productListPaging.length !== 0">
    <!--全局样式-->

    <!-- <van-card
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
    </van-card> -->

    <van-card
      v-for="item in Product.productListPaging"
      :key="item.productId"
      :num="item.quantity"
      :price="item.price"
      :desc="item.brand"
      :title="item.productName"
      :thumb="item.photo"
      @click="toDetail(item.productId)"
      class="card"
    >
    </van-card>

    <!--Paging-->
    <van-pagination
      v-model="Product.page"
      :total-items="Product.noOfTotalElements"
      :show-page-size="5"
      :items-per-page="5"
      @change="pagechange"
    >
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
          <van-button size="small" type="primary" @click="toPage(page)"> GO! </van-button>
        </template>
      </van-field>
    </van-cell-group>
    <!--全局样式-->
  </van-config-provider>

  <p>
    {{ Product.errorMsg }}
  </p>
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
