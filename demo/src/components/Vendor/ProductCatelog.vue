<script>
import { reactive } from "vue";
import { mapState } from "vuex";

export default {
  name: "Product",
  //business logic
  data() {
    return {
      brands: [
        {
          id: 'Apple',
          url: 'https://cdn3.iconfinder.com/data/icons/social-media-logos-glyph/2048/5315_-_Apple-512.png',
          name: 'Apple',
        },
        {
          id: 'Huawei',
          url: 'https://cdn.icon-icons.com/icons2/2699/PNG/512/huawei_logo_icon_169026.png',
          name: 'HuaWei',
        },
        {
          id: 'Xiaomi',
          url: 'https://cdn4.iconfinder.com/data/icons/flat-brand-logo-2/512/xiaomi-512.png',
          name: 'XiaoMi',
        },
        {
          id: 'Samsung',
          url: 'https://cdn-icons-png.flaticon.com/512/882/882747.png',
          name: 'Samsung',
        },
        {
          id: 'vivo',
          url: 'https://cdn-icons-png.flaticon.com/512/882/882813.png',
          name: 'vivo',
        },
        {
          id: 'OPPO',
          url: 'https://cdn-icons-png.flaticon.com/512/882/882745.png',
          name: 'OPPO',
        },
        {
          id: 'realme',
          url: 'https://egyptian-gazette.com/wp-content/uploads/2021/10/realme-logo.png',
          name: 'realme',
        },
      ],
      all: {
        url: 'https://cdn-icons-png.flaticon.com/128/5996/5996290.png',
        name: 'All',
      },
      currentPage: 1,
      itemsPerPage: 5,
      page: null,
      //search
      searchValue: '',
      isFiltering: false,
      filterValue:'',
      activeIndex: '',
    };
  },
  //global style
  setup() {
    // themeVars 内的值会被转换成对应 CSS 变量
    // 比如 sliderBarHeight 会转换成 `--van-slider-bar-height`
    const themeVars = reactive({
      cardThumbSize: '120px',
      cardThumbRadius: '0px',
      cardBackground: '#ffffff',
      cardPriceColor: '#ee0a24',
      cardFontSize: '17px',
      cardTitleLineHeight: '50px',
      cardDescLineHeight: '50px',
      cardPriceFontSize: '15px',
      cardPriceIntegerFontSize: '25px',
      gridItemContentPadding: '0px',
    });
    return {
      themeVars,
    };
  },

  components: {
    // Brand,
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
    },

    filterByBrand(id){
      this.currentPage = 1;
      this.isFiltering = true;
      this.filterValue = id;
      this.activeIndex = id;

      console.log("this.searchValue", this.searchValue.length);

      var params = [];

      params.push(this.searchValue);
      params.push(id);
    


      //filter based on searching
      if(this.searchValue.length > 0){
        console.log("this.searchValue.length > 0");
        this.$store.dispatch('Product/searchProductAndFilterByBrand', params);
      }

      else{
      console.log("this.searchValue.length <= 0");
      console.log("this.isFiltering after filter: ");
      console.log(this.isFiltering);

      console.log(id);

      params.push(false);
      this.$store.dispatch('Product/filterProductByBrand', id);
      }
    },

    resetFilter(){
      if (this.isFiltering == true) {
        this.currentPage = 1;
        console.log("reset filter");
        this.isFiltering = false;
        this.activeIndex = '';

        console.log("this.isFiltering after reset: ");
        console.log(this.isFiltering);

        // 判断search是否为空,不为空则不清空
        if (this.searchValue.length == 0) {
          this.getProductList();
        } else {
          this.searchProduct();
        }
        // this.searchProduct();
        // this.getProductList();
      } else {
        this.activeIndex = '';
        this.getProductList();
      }
    },

    //searchProduct
    searchProduct() {
      this.currentPage = 1;
      if (this.isNumeric(this.searchValue) && this.searchValue.length > 3){
        this.$store.dispatch("Product/searchProductById", this.searchValue.substring(3));
      }
      else {
        var params = [];

        params.push(this.searchValue);
        params.push(this.filterValue);

        if(this.isFiltering) {
          this.$store.dispatch('Product/searchProductAndFilterByBrand', params);
        } else {
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
      // this.getProductList();
    },

    toPage(page) {
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
        this.getProductList();
      }
    },
    toAddProductPage() {
      console.log("add product");
      this.$router.push({ name: "VendorProductAdd", params: {} });
    },
  },
};
</script>

<template>
  <van-search
    v-model="searchValue"
    show-action
    shape="round"
    background="#ffffff"
    placeholder="Type to search"
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
    Add A New Product
  </van-button>

  <div class="brands">
    <van-grid :column-num="4" square :border="false" :gutter="15" clickable>
      <!-- 7 brands -->
      <van-grid-item v-for="brand in brands" :key="brand.id" class="grid-item" @click="filterByBrand(brand.id)" >
        <van-image class="image" fit="cover"
          :src="brand.url"
          
        />
        <p class="text" :style="{ color: activeIndex === brand.id ? '#03abff' : '' }">{{brand.name}}</p>
      </van-grid-item>
      <!-- All reset -->
      <van-grid-item class="grid-item" @click="resetFilter">
        <van-image class="image" fit="cover"
          :src="all.url"
        />
        <p class="text">{{all.name}}</p>
      </van-grid-item>
    </van-grid>
  </div>

  <!-- <van-button size="small" type="primary" id="Xiaomi" @click="filterByBrand">
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
  </van-button> -->

  <!-- Empty -->
  <div class="empty" v-if="Product.productList.length === 0">
    <van-empty description="No Product!" />
  </div>

  <van-config-provider :theme-vars="themeVars" v-if="Product.productList.length !== 0">
  <!--全局样式-->

  <van-card
      v-for="item in Product.productList.slice(
        (currentPage - 1) * itemsPerPage,
        currentPage * itemsPerPage
      )"
      currency="$"
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
        <van-button type="primary" @click="toPage(page)">
          GO!
        </van-button>
        </template>
      </van-field>
    </van-cell-group>
  <!--全局样式-->
  </van-config-provider>
</template>

<style lang="less" scoped>
.brands {
  margin: 20px 10px 20px 10px;
  .grid-item {
    .image {
    width: 50px;
    height: 50px;
    margin: 0 auto;
    }
    .text {
      font-size: 13px;
      margin: 0px;
    }
  }
}
.card {
  border-bottom: 1px solid #e5e5e5;
}
.input {
  margin: 0px;
  border-radius: 0%;
}
</style>
