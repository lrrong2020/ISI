<script>
import { reactive } from 'vue';
import { mapState } from 'vuex';

export default {
  name: "HomeBrands",
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
  created() {
    this.getProductList();
  },
  computed: {
    ...mapState(['Product']),
  },
  methods: {
    //getProductList from Store
    getProductList() {
       this.$store.dispatch('Product/getProductList');
    },
    //Filter by Brands
    filterByBrand(id){
      //clear timer
      clearInterval(this.timer);
      console.log("clear timer");

      this.currentPage = 1;
      this.isFiltering = true;
      this.filterValue = id;
      this.activeIndex = id;

      this.$store.dispatch('Product/filterProductByBrand', id);
    },
    resetFilter(){
      if (this.isFiltering === true) {
        this.currentPage = 1;
        // console.log("reset filter");
        this.isFiltering = false;
        this.activeIndex = '';
        
        console.log("this.isFiltering after reset: ");
        console.log(this.isFiltering);

        this.getProductList();
        this.timer = setInterval(() => {
          this.getProductList();
        }, 1000);
      }
    },
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
    showList() {
      this.getProductList();
    },
  },
  timer: null,
  mounted() {
    this.timer = setInterval(() => {
      this.getProductList();
    }, 1000);
  },
  beforeUnmount() {
    clearInterval(this.timer);
    console.log("clear timer");
  },
}
</script>

<template>
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
<!-- Empty -->
<div class="empty" v-if="Product.productList.length === 0">
  <van-empty description="No Product!" />
</div>
<van-divider
  :style="{ color: '#1989fa', borderColor: '#1989fa', padding: '30px 16px 15px 16px', fontSize: '20px' }"
  v-if="Product.productList.length !== 0"
>
  Hot Products
</van-divider>
<!-- Product List -->
<div class="product">
  <van-config-provider :theme-vars="themeVars" v-if="Product.productList.length !== 0">
  <!--全局样式-->
    <van-card
      v-for="item in Product.productList.slice((currentPage-1)*itemsPerPage,currentPage*itemsPerPage)"
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