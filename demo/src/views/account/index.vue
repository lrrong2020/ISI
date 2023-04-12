<script>
import { mapState } from "vuex";
import { reactive } from 'vue'
import { useRouter } from 'vue-router'

import { showSuccessToast, showFailToast } from 'vant';
// import 'vant/es/toast/style';

const state = reactive({
  user: {},
  loading: true
})
export default {
  name: "Account",
  components: {
  },
  data() {
    return {
      loading: true,
      user: {},
    }
  },
  created() {
    this.getUser();
    this.$store.dispatch('Cart/getCartItems', this.User.currentUser.customerId);
  },
  mounted() {
    this.loading = false;
  },
  computed: {
    ...mapState(['User', 'Cart', 'Order', 'Include', 'Product']),
  },
  methods: {
    getUser() {
      this.$store.dispatch('User/getUser');
    }, 
    onClickLeft() {
      this.$router.push({ name: 'Home' });
    },
    async toOrder() {
      const customerId = this.User.currentUser.customerId;
      await this.$store.dispatch('Order/getOrderList', customerId);
      await this.$router.push({ name: 'OrderList' });
    },
    toAccountManagement() {
      this.$router.push({ name: 'AccountManagement', params: { id: this.User.currentUser.customerId } });
    },
    toAddressManagement() {
      this.$router.push({ name: 'AddressManagement', params: { id: this.User.currentUser.customerId } });
    },
    Logout() {
      localStorage.removeItem("isLogin");
      this.$store.commit('User/deleteCurrentUser');
      this.$store.commit('Cart/clearCart')
      this.$store.commit('Include/ClearInclude');
      this.$store.commit('Product/ClearTop3');
      this.$router.push({ name: 'Login' });

      showSuccessToast({
        message: 'Logout Success',
        wordBreak: 'break-word',
      });
    }
  }
}

</script>

<template>
  <div class="bg">
    <div class="TopBar">
      <van-nav-bar
        fixed
        placeholder
        title="My Account"
        left-arrow
        @click-left="onClickLeft"
      />
    </div>
    <div class="Account">
      <van-skeleton title :avatar="true" :row="3" :loading="this.loading">
        <div class="user-info">
          <div class="user-avatar">
            <van-image
              src="https://png.pngtree.com/png-vector/20190805/ourlarge/pngtree-account-avatar-user-abstract-circle-background-flat-color-icon-png-image_1650938.jpg"
              width="100px"
              height="100px"
              fit="cover"
            />
          </div>
          <div class="user-desc">
            <div class="user-name">
              <span> User: {{ User.currentUser.customerName }}</span>
            </div>
            <div class="user-email">
              <span> E-mail: {{ User.currentUser.customerEmail }}</span>
            </div>
          </div>
        </div>
      </van-skeleton>
    </div>
    <ul class="user-list">
      <li class="van-hairline--bottom" @click="toOrder">
        <span>My Orders</span>
        <van-icon name="arrow" />
      </li>
      <li class="van-hairline--bottom" @click="toAccountManagement">
        <span>Account Management</span>
        <van-icon name="arrow" />
      </li>
      <li class="van-hairline--bottom" @click="toAddressManagement">
        <span>Address Management</span>
        <van-icon name="arrow" />
      </li>
    </ul>
    <div class="Logout">
      <van-button plain size="large" round block
        type="primary"
        @click="Logout">
          Log Out
      </van-button>
    </div>
  </div>
</template>

<style lang="less" scoped>
//用less写样式

.bg{
  background-color: rgb(255, 255, 255);
  height: 100%;
  .Account{
    height: 20%;
    width: 90%;
    background-color: white;
    border-radius: 10px;
    box-shadow: 0 0 5px #888888;
    margin: 20px auto;
    padding: 10px;
    font-size: 20px;
    .user-info{
      display: flex;
      flex-direction: row;
      .user-avatar{
        margin-left: 20px;
        margin-right: 20px;
      }
      .user-desc{
        display: flex;
        flex-direction: column;
        justify-content: center;
        .user-name{
          font-size: 20px;
          font-weight: bold;
        }
        .user-email{
          font-size: 15px;
          color: #999;
        }
      }
    }
  }
  .user-list{
    margin-top: 20px;
    li{
      display: flex;
      flex-direction: row;
      justify-content: space-between;
      align-items: center;
      padding: 10px 20px;
      font-size: 16px;
      color: #333;
      span{
        font-size: 16px;
        color: #333;
      }
    }
  }
  .Logout{
    position: absolute;
    bottom: 8%;
    width: 90%;
    font-size: 20px;
    left: 5%;
  }
}


</style>