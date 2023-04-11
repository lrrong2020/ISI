<script>
import ProductCatelog from '../../../components/Vendor/ProductCatelog.vue';
import { mapState } from 'vuex';

export default {
  name: "VendorHome",
  components: {
    // Search,
    // Brand,
    ProductCatelog,
  },
  data() {
    return {
      scrollTop: 0,
    };
  },
  mounted() {
    this.$store.commit('Include/AddInclude');
  },
  computed: {
    ...mapState(['Include',])
  },
  activated() {
    //进入路由时，滚动条回到上次离开时的位置
    document.documentElement.scrollTop = this.scrollTop;
  },
  beforeRouteLeave(to, from, next) {
    //离开路由时，记录滚动条位置
    this.scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
    next();
  },
};
</script>
<template>
  <div class="home">
    <div class="content">
    <!--Header-->
      <div class="header">
        <div class="title">Product Catelog Vendor</div>
      </div>
    <!--Main-->
      <div class="main">
        <div class="bg">
          <div class="search">
            <!-- <Search /> -->
          </div>
          <div class="sort">
            <!-- <Brand /> -->
          </div>
          <div class="product">
            <ProductCatelog />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="less" scoped>
.home {
  display: flex;
  flex-flow: column;
  height: 100%;
  .content {
    flex: 1;
    overflow-y: auto;
    .header {
      background-image: linear-gradient(#1989fa, #ffffff);
      // display: flex;
      // justify-content: space-between;
      // align-items: center;
      text-align: center;
      padding: 20px 0px 40px 0px;
      .title {
        font-size: 29px;
        font-weight: 600;
        font-family: "Comic Sans MS"
      }
    }
    .main{
      margin-top: -20px;
      width: 100%;
      .bg{
        background-color: #ffffff;
        padding: 10px 0px 0px 0px;
        border-radius: 30px 30px 0px 0px;
        .product{
          margin: 0px 10px 0px 10px;
        }
      }
    }
  }
}
</style>