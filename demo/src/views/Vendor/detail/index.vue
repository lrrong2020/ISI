<script>
import { ref } from 'vue';
import { onMounted } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';
import { mapState } from 'vuex';

export default {
  name: "Detail",
  setup() {
    const active = ref(0);
    return { active };
  },
  data() {
    return {
      // id: '',
      Detail: {},
    }
  },
  created() {
    // this.id = this.$route.params.id;
    this.getDetail();
    // this.getCartItems();
  },
  // mounted() {
  //   console.log(this.Detail);
  // },
  computed: {
    // ...mapState([]),
  },
  methods: {
    getDetail() {
      axios.get(`http://127.0.0.1:8080/product/${this.$route.params.id}`).then((response) => {
        this.Detail = response.data;
      })
    },
    //Cart
    // getCartItems() {
    //   this.$store.dispatch('Cart/getCartItems');
    // },
    // addCartItem() {
    //   this.$store.dispatch('Cart/addCartItem', this.Detail);
    // },
    // goToCart() {
    //   this.$router.push({ name: 'Cart' });
    // },
  }
}
</script>

<template>
  <div class="bg">
    <div class="img">
      <van-image
        width= "100%"
        height="300px"
        :src="this.Detail.photo"
        fit="contain"
      />
    </div>
    <div class="main-card">
      <div class="name">
        <p style="margin: 5px 0px; font-size: 30px;">{{ this.Detail.productName }}</p>
        <p style="margin: 5px 0px;font-size: 20px; color: #b4b4b4;">Brand: {{ this.Detail.brand }}</p>
        <p style="margin: 5px 0px;font-size: 20px; color: #00aaff;">ID: {{ this.Detail.productId }}</p>
      </div>
      <div class="price">
        <h1 style="margin: 5px 0px">${{this.Detail.price}}</h1>
      </div> 
    </div>
    <div class="prop">
      <p style="margin: 5px 0px">Color: {{ this.Detail.property }}</p>
      <p style="margin: 5px 0px">Screen Size: {{ this.Detail.propertySecond }}</p>
    </div>
  </div>
</template>

<style lang="less" scoped>
.img{
  width: 100%;
  height: 300px;
}
//main是卡片样式，卡片背景色为白色，圆角为15px，宽度为90%，有阴影
.main-card{
  border-radius: 10px;
  box-shadow: 0 0 5px #bdbdbd;
  margin: 20px 10px 0px 10px;
  padding: 10px;
  .name{
    margin: 0px;
  }
  .price{
    color: red;
    margin: 0px;
    font-size: 10px;
  }
  .desc{
    margin: 0px;
  }
}
.prop{
  border-radius: 10px;
  box-shadow: 0 0 5px #bdbdbd;
  margin: 10px 10px;
  padding: 10px;
  font-size: 20px;
}

</style>