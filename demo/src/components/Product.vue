<script>
import { reactive } from 'vue';
import { ref } from 'vue';
import { toRaw } from 'vue';
import { mapState } from 'vuex';
//Brand
import Brand from '@/components/Brand.vue';

export default {
  name: "Product",
  //全局样式
  setup() {
    // themeVars 内的值会被转换成对应 CSS 变量
    // 比如 sliderBarHeight 会转换成 `--van-slider-bar-height`
    const themeVars = reactive({
      cardRadius: '20px',
      cardThumbSize: '150px',
      cardThumbRadius: '0px',
      cardBackground: '#ffffff',
      cardPriceColor: '#ee0a24',
      cardFontSize: '15px',
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
  //Brand
  components: {
      Brand,
  },
  //业务逻辑
  data() {
    return {
      totalItems: 0,
      currentPage: 1,
      itemsPerPage: 5,
      page: null,
      //search
      searchValue: '',
    }
  },
  created() {
    this.getProductList();
  },
  mounted() {
 
  },
  computed: {
    ...mapState(['Product']),
  },
  methods: {
    

    //getProductList from Store
    getProductList() {
       this.$store.dispatch('Product/getProductList');
       console.log("1");
    },
    //searchProduct
    searchProduct() {
      this.$store.dispatch('Product/searchProduct', this.searchValue);
      // this.$store.dispatch('Product/getProductList');
      console.log(`search Product: ${this.searchValue}`);
    },

    toDetail() {
      this.$router.push('/detail')
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
}
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
    >
    <template #action>
      <div @click="searchProduct" class="button">Search</div>
    </template>
  </van-search>
  <!--Brand-->
  <Brand />   
  <van-config-provider :theme-vars="themeVars">
  <!--全局样式-->
    <van-card
      v-for="item in Product.productList.slice((currentPage-1)*itemsPerPage,currentPage*itemsPerPage)"
      :key="item.id"
      :num="5"
      :price="item.price"
      :desc="item.brand"
      :title="item.productName"
      :thumb="item.url"
      @click="toDetail"
      class="card"
      >
      <template #tags>
        <van-tag plain type="primary">tag1</van-tag>
        <van-tag plain type="primary">tag2</van-tag>
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

  <p>
  {{ Product.errorMsg }}
  </p>
</template>

<style lang="less" scoped>
.card {
  border-radius: 20px;
}
.input{
  margin: 0px;
  border-radius: 0%;
} 
</style>