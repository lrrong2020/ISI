<script>
import { reactive } from 'vue';
import { mapState } from 'vuex';
import Apple from "@/assets/Brands/Apple.png";
import Huawei from "@/assets/Brands/Huawei.png";
import Xiaomi from "@/assets/Brands/Xiaomi.png";
import Samsung from "@/assets/Brands/Samsung.png";
import vivo from "@/assets/Brands/vivo.png";
import OPPO from "@/assets/Brands/OPPO.png";
import realme from "@/assets/Brands/realme.png";
import All from "@/assets/Brands/All.png";

export default {
  name: "SearchPage",
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
      itemsPerPage: 5,
      page: null,
      //search
      searchValue: '',
      isFiltering: false,
      filterValue:'',
      activeIndex: '',
      scrollTop: 0,
    }
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
    this.setProductListEmpty();
  },
  computed: {
    ...mapState(['Product']),
  },
  methods: {
    //Set ProductList Empty
    setProductListEmpty() {
      this.$store.commit('Product/SetProductListEmpty');
    },
    //getProductList from Store
    getProductList() {
      this.$store.dispatch('Product/getProductList');
    },
    //add to ProductList
    //search-bar
    goBack() {
      this.$router.push({ name: 'Home' });
    },
    //searchProduct
    searchProduct() {
      // clearInterval(this.timer);
      // console.log("clear timer");

      this.currentPage = 1;
      var params = [];

      params.push(this.searchValue);
      params.push(this.filterValue);

      if(this.isFiltering == true && this.searchValue.length != 0) {
        this.$store.dispatch('Product/searchProductAndFilterByBrand', params);
      } else if (this.isFiltering == true && this.searchValue.length == 0) {
        this.$store.dispatch('Product/filterProductByBrand', this.filterValue);
      } else {
        this.$store.dispatch('Product/searchProduct', this.searchValue);
        // this.$store.dispatch('Product/getProductList');
        console.log(`search Product: ${this.searchValue}`);
      }
      // if (this.searchValue.length == 0 && this.isFiltering == false) {
      //   this.setProductListEmpty();
      // }
    },
    //Filter by Brands
    filterByBrand(id){

      // clearInterval(this.timer);
      // console.log("clear timer");

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
    //Reset Filter
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
      } else {
        this.activeIndex = '';
        if (this.searchValue.length == 0) {
          this.getProductList();
        } else {
          this.searchProduct();
        }
      }
    },
    //Go to Detail Page
    async toDetail(item) {
      await this.$store.dispatch('Product/getProductDetail', item);
      this.$router.push({ name: 'Detail', params: { id: item } });
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
    //Show Toast
    // onSearch(val) {
    //   showToast(val);
    // },
  },
  activated() {
    //进入路由时，滚动条回到上次离开时的位置
    document.documentElement.scrollTop = this.scrollTop;
  },
  beforeRouteLeave(to, from, next) {
    this.scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
    next();
  },
  //定时器获取后端数据, 1s一次，销毁时清除定时器
  // timer: null,
  // mounted() {
  //   this.timer = setInterval(() => {
  //     this.getProductList();
  //   }, 1000);
  // },
  // beforeUnmount() {
  //   clearInterval(this.timer);
  //   console.log("clear timer");
  // },
}
</script>

<template>
  <div class="bg">
    <!-- Header Search -->
    <div class="search-bar">
      <van-search
        v-model="searchValue"
        show-action
        shape="round"
        placeholder="Type to search"
        
        @clear="showList"
        @update:modelValue="searchProduct"
      >
        <template #left>
          <van-icon name="arrow-left" color="#1989fa" size="25" @click="goBack" class="back-icon"/>
        </template>
        <template #action>
          <div @click="searchProduct" class="search">Search</div>
        </template>
      </van-search>
    </div>
    <!-- Block -->
    <div class="block"></div>
    <!-- Brands -->
    <div class="brands">
      <van-grid :column-num="4" square :border="false" :gutter="15" clickable>
        <!-- 7 brands -->
        <van-grid-item v-for="brand in brands" :key="brand" :id="brand.id" class="grid-item" @click="filterByBrand(brand.id)">
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
    <!-- Empty -->
    <div class="empty" v-if="Product.productList.length === 0">
      <van-empty description="Search for Products" />
    </div>
    <!-- Product List -->
    <div class="product">
      <van-config-provider :theme-vars="themeVars" v-if="Product.productList.length !== 0">
      <!--全局样式-->
        <van-card
          v-for="item in Product.productList.slice((currentPage-1) * itemsPerPage, currentPage * itemsPerPage)"
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
    </div>
  </div>
</template>

<style lang="less" scoped>
.bg {
  height: 100%;
  font-family: "Comic Sans MS";
  .search-bar{
    position: fixed;
    top: 0;
    z-index: 9999;
    width: 100%;
    .back-icon {
    margin: 0px 10px 0px 0px;
    }
    .search {
      background-color: #1989fa;
      color: #ffffff;
      font-size: 15px;
      margin: 0px 5px 0px 5px;
      padding: 0px 5px 0px 5px;
      border-radius: 10px;
    }
  }
  .block {
    height: 60px;
  }
  .card {
    border-bottom: 1px solid #e5e5e5;
  }
  .brands {
    margin: 0px 10px 20px 10px;
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
}
</style>