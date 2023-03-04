<script>
import { ref } from 'vue';
import CheckOut from '../../components/CheckOut.vue';
export default {
    name: "Cart",
    setup() {
        const value = ref(1);
        return { value };
    },
    data() {
        return {
            number: 0,
        };
    },
    methods: {
        add() {
            this.number++;
        },
        remove() {
            if (this.number > 0) {
                this.number--;
            }
        },
        toHome() {
            this.$router.push({ path: "/" });
        },
    },
    components: { 
      CheckOut,
    }
}
</script>

<template>
  <div class="bg">
    <!--TopTitle-->
    <van-nav-bar title="Cart" />
    <!--NumberofItems-->
    <div>{{ number }}</div>
    <!--IfEmptyCart-->
    <div class="empty" v-if="!number">
      <van-empty description="Your cart is empty!">
        <van-button type="success" @click="toHome">Go to shop</van-button>
      </van-empty>
    </div>
    <!--IfNotEmptyCart-->
    <div class="non-empty">
      <van-swipe-cell v-for="i in number" :key="i">
        <van-card
        num="2"
        price="2.00"
        desc="描述信息"
        title="商品标题"
        class="goods-card"
        thumb="https://fastly.jsdelivr.net/npm/@vant/assets/cat.jpeg"
        >
          <template #footer>
            <van-stepper v-model="value" />
          </template>
        </van-card>
        <template #right>
          <van-button square text="删除" type="danger" class="delete-button" @click="remove"/>
        </template>
      </van-swipe-cell>
    </div>
    <!--AddandRemove-->
    <van-button type="success" @click="add">Add 1 item</van-button>
    <van-button type="danger" @click="remove">Remove 1 item</van-button>
    <!--CheckOut-->
    <div>
      <CheckOut v-if="number"/>
    </div>
  </div>
</template>

<style scoped>
.goods-card {
    margin: 0;
    background-color: #ffffff;
  }

  .delete-button {
    height: 100%;
  }
</style>