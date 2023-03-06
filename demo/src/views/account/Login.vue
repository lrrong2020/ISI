<script>
import { ref } from 'vue';
import { mapState } from 'vuex';
export default {
  name: "Login",
  components: {},
  data: () => ({
    email: '',
    password: '',
  }),
  computed: {
    ...mapState(['User']),
  },
  methods: {
    // login() {
    //   this.$store.dispatch('login', {
    //     username: this.username,
    //     password: this.password,
    //   })
    //   .then(success => {
    //     if (success) {
    //       this.$router.push('/user');
    //     }
    //   })
    //   .catch(error => {
    //     this.$toast.fail(error);
    //   });
    // },
    onClickLeft() {
      this.$router.push('/account')
    },
    onSubmit(value) {
      console.log(value);
    },
    // Password length function check
    validator(val) {
      return /^.{6,20}$/.test(val);
    },
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
          v-model="email"
          name="Email"
          label="Email"
          right-icon="contact"
          placeholder="Email"
          :rules="[
            { required: true, message: 'Please enter e-mail' },
            { pattern: /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/, message: 'Please enter valid email' },
          ]"
        />

        <van-field
          v-model="password"
          type="password"
          name="Password"
          label="Password"
          right-icon="closed-eye"
          placeholder="Password"
          :rules="[
            { required: true, message: 'Please enter password' },
            { validator, message: 'Password must be at least 6 characters' },
          ]"
        />
        </van-cell-group>
        <div style="margin: 16px;">
          <van-button round block type="primary" native-type="submit">
            Login
          </van-button>
        </div>
        <div style="margin: 16px; text-align: center; font-size: medium;">
          <router-link to="/signup">Sign Up</router-link>
        </div>
      </van-form>
    </div>
  </div>
</template>

<style scoped>
</style>