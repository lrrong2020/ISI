<script>
import { ref } from 'vue';
export default {
  name: "Login",
  components: {},
  data: () => ({
    username: '',
    password: '',
  }),
  methods: {
    login() {
      this.$store.dispatch('login', {
        username: this.username,
        password: this.password,
      })
      .then(success => {
        if (success) {
          this.$router.push('/user');
        }
      })
      .catch(error => {
        this.$toast.fail(error);
      });
    },
    onClickLeft() {
      this.$router.push('/account')
    }
  },
  setup() {
    const username = ref('');
    const password = ref('');
    const onSubmit = (values) => {
      console.log('submit', values);
    };
    return {
      username,
      password,
      onSubmit,
    };
  },
};
</script>

<template>
  <div class="login">
    <van-nav-bar
      :border="false"
      title="Login"
      left-arrow
      @click-left="onClickLeft"
      class="header"
    />
    <!--Icon-->
    <van-row justify="center">
      <van-icon name="user-circle-o" color="#9819fa" size="80"/>
    </van-row>
    <!--Title-->
    <van-row justify="center">
      <van-col>
        <h1 style="font-size: 40px;">Login</h1>
      </van-col>
    </van-row>
    <!--Input Form-->
    <div class="login-form">
      <van-form @submit="onSubmit">
        <van-cell-group inset>
        <van-field
          v-model="username"
          name="Username"
          label="Username"
          right-icon="contact"
          placeholder="Username"
          :rules="[{ required: true, message: 'Please enter username' }]"
        />
        <van-field
          v-model="password"
          type="password"
          name="Password"
          label="Password"
          right-icon="closed-eye"
          placeholder="Password"
          :rules="[{ required: true, message: 'Please enter password' }]"
        />
        </van-cell-group>
        <div style="margin: 16px;">
          <van-button round block type="primary" native-type="submit">
            Login
          </van-button>
        </div>
        <div style="margin: 16px;">
          <van-button round block type="primary" native-type="submit" to="/signup">
            Sign Up
          </van-button>
        </div>
      </van-form>
    </div>
  </div>
</template>

<style scoped>
</style>