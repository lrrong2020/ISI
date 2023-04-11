<script>
import { reactive } from "vue";
import { mapState } from "vuex";
import Apple from "@/assets/Apple.png";
import Huawei from "@/assets/Huawei.png";
import Xiaomi from "@/assets/Xiaomi.png";
import Samsung from "@/assets/Samsung.png";
import vivo from "@/assets/vivo.png";
import OPPO from "@/assets/OPPO.png";
import realme from "@/assets/realme.png";
import All from "@/assets/All.png";

export default {
  name: "HomeBrands",
  data() {
    return {
      brands: [
        {
          id: "Apple",
          url: Apple,
          name: "Apple",
        },
        {
          id: "Huawei",
          url: Huawei,
          name: "HuaWei",
        },
        {
          id: "Xiaomi",
          url: Xiaomi,
          name: "XiaoMi",
        },
        {
          id: "Samsung",
          url: Samsung,
          name: "Samsung",
        },
        {
          id: "vivo",
          url: vivo,
          name: "vivo",
        },
        {
          id: "OPPO",
          url: OPPO,
          name: "OPPO",
        },
        {
          id: "realme",
          url: realme,
          name: "realme",
        },
      ],
      all: {
        url: All,
        name: "All",
      },
      currentPage: 1,
      itemsPerPage: 4,
      page: null,
      //search
      searchValue: "",
      isFiltering: false,
      filterValue: "",
      activeIndex: "",
    };
  },
  setup() {
    // themeVars 内的值会被转换成对应 CSS 变量
    // 比如 sliderBarHeight 会转换成 `--van-slider-bar-height`
    const themeVars = reactive({
      cardThumbSize: '120px',
      cardThumbRadius: '0px',
      cardBackground: '#ffffff',
      cardPriceColor: '#ee0a24',
      cardFontSize: '17px',
      cardTitleLineHeight: '30px',
      cardDescLineHeight: '20px',
      cardPriceFontSize: '15px',
      cardPriceIntegerFontSize: '25px',
      gridItemContentPadding: '0px',
    });
    return {
      themeVars,
    };
  },
  created() {
    this.getProductListPaging();
  },
  computed: {
    ...mapState(["Product"]),
  },
  methods: {
    //getProductList from Store
    getProductList() {
      this.$store.dispatch("Product/getProductList");
    },

    //getProductList from Store with paging
    getProductListPaging() {
      var params = [];
      params.push(this.currentPage - 1);
      params.push(this.itemsPerPage);
      this.$store.dispatch("Product/getProductListPaging", params);
    },

    //Filter by Brands
    filterByBrand(id) {
      //clear timer
      clearInterval(this.timer);
      console.log("clear timer");

      this.currentPage = 1;
      this.isFiltering = true;
      this.filterValue = id;
      this.activeIndex = id;

      var params = [];
      params.push(id);
      params.push(this.currentPage - 1);
      params.push(this.itemsPerPage);

      this.$store.dispatch("Product/filterProductByBrand", params);
    },
    resetFilter() {
      if (this.isFiltering === true) {
        this.currentPage = 1;
        // console.log("reset filter");
        this.isFiltering = false;
        this.activeIndex = "";

        console.log("this.isFiltering after reset: ");
        console.log(this.isFiltering);

        this.getProductListPaging();
        this.timer = setInterval(() => {
          this.getProductListPaging();
        }, 10000);
      }
    },
    async toDetail(item) {
      await this.$store.dispatch("Product/getProductDetail", item);
      this.$router.push({ name: "Detail", params: { id: item } });
    },
    pagechange(page) {
      console.log("PageChange", page);
      this.currentPage = page;

      if (this.isFiltering) {
        var params = [];
        params.push(this.filterValue);
        params.push(page - 1);
        params.push(this.itemsPerPage);
        this.$store.dispatch("Product/filterProductByBrand", params);
      } else {
        var params = [];
        params.push(page - 1);
        params.push(this.itemsPerPage);
        this.$store.dispatch("Product/getProductListPaging", params);
      }

      // this.getProductList();
    },
    toPage(page, max) {
      if (page > max) {
        page = max;
      }
      if (page == 0) {
        page = 1;
      }

      this.pagechange(page);
      this.page = null;
    },
    showList() {
      this.getProductLisPaging();
    },
  },
  timer: null,
  mounted() {
    this.timer = setInterval(() => {
      this.getProductListPaging();
    }, 10000);
  },
  beforeUnmount() {
    clearInterval(this.timer);
    console.log("clear timer");
  },
};
</script>

<template>
  <div class="brands">
    <van-grid :column-num="4" square :border="false" :gutter="15" clickable>
      <!-- 7 brands -->
      <van-grid-item
        v-for="brand in brands"
        :key="brand.id"
        class="grid-item"
        @click="filterByBrand(brand.id)"
      >
        <van-image class="image" fit="cover" :src="brand.url" />
        <p class="text" :style="{ color: activeIndex === brand.id ? '#03abff' : '' }">
          {{ brand.name }}
        </p>
      </van-grid-item>
      <!-- All reset -->
      <van-grid-item class="grid-item" @click="resetFilter">
        <van-image class="image" fit="cover" :src="all.url" />
        <p class="text">{{ all.name }}</p>
      </van-grid-item>
    </van-grid>
  </div>
  <!-- Empty -->
  <div class="empty" v-if="Product.productListPaging.length === 0">
    <van-empty description="No Product!" />
  </div>


  <van-divider
    :style="{
      color: '#1989fa',
      borderColor: '#1989fa',
      padding: '30px 16px 15px 16px',
      fontSize: '20px',
    }"
    v-if="Product.productListPaging.length !== 0"
  >
    Hot Products
  </van-divider>
  <!-- Product List -->
  <div class="product">
    <van-config-provider :theme-vars="themeVars" v-if="Product.productListPaging.length !== 0">
      <!--全局样式-->

      <van-card
        v-for="item in Product.productListPaging"
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
        <!-- <template #tags>
        <van-tag plain type="primary">tag1</van-tag>
        <van-tag plain type="primary">tag2</van-tag>
      </template> -->
      </van-card>

      <!--Paging-->
      <van-pagination
        v-model="this.currentPage"
        :total-items="Product.noOfTotalElements"
        :show-page-size="5"
        :items-per-page="this.itemsPerPage"
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
            <van-button type="primary" @click="toPage(page, Product.noOfPages)">
              GO!
            </van-button>
          </template>
        </van-field>
      </van-cell-group>
      <!--全局样式-->
    </van-config-provider>
  </div>
</template>

<style lang="less" scoped>
//brands
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
.title {
  background-color: rgb(247, 247, 247);
  margin: 10px 0px 20px 0px;
  font-size: 30px;
  font-weight: bold;
  color: rgb(0, 136, 255);
  text-align: center;
  padding: 1px 0px 1px 0px;
}
</style>
