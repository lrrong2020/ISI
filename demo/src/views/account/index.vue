<script>
import { mapState } from "vuex";
import { reactive, onMounted, toRefs } from 'vue'
import { useRouter } from 'vue-router'

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
  mounted() {
    this.loading = false;
  },
  computed: {
    
  },
  methods: {
    onClickLeft() {
      this.$router.push({ name: 'Home' });
    },
  }
}

</script>

<template>
  <div class="main">
    <div class="TopBar">
      <van-nav-bar
        title="Account"
        left-arrow
        @click-left="onClickLeft"
      />
    </div>
    <div class="Account">
      <van-skeleton title :avatar="true" :row="3" :loading="this.loading">
        <div class="user-info">
          <div class="user-avatar">
            <van-image
              src="https://fastly.jsdelivr.net/npm/@vant/assets/user-active.png"
              width="80px"
              height="80px"
              fit="cover"
            />
          </div>
          <div class="user-desc">
            <span>Username: aaaaa</span>
          </div>
        </div>
      </van-skeleton>
    </div>
    <ul class="user-list">
      <li class="van-hairline--bottom" @click="goTo()">
        <span>我的订单</span>
        <van-icon name="arrow" />
      </li>
      <li class="van-hairline--bottom" @click="goTo()">
        <span>账号管理</span>
        <van-icon name="arrow" />
      </li>
      <li class="van-hairline--bottom" @click="goTo()">
        <span>地址管理</span>
        <van-icon name="arrow" />
      </li>
      <li @click="goTo()">
        <span>关于我们</span>
        <van-icon name="arrow" />
      </li>
    </ul>
  </div>
  <div>
    <van-button plain size="large" to="/login"
    icon="https://fastly.jsdelivr.net/npm/@vant/assets/user-active.png"
    type="primary">
      Login
    </van-button>

    <van-button plain size="large" to="/signup"
    icon="https://fastly.jsdelivr.net/npm/@vant/assets/user-active.png"
    type="primary">
      Sign Up
    </van-button>
</div>
</template>

<style lang="less" scoped>

</style>