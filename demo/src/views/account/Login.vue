<script>
import { ref } from 'vue';
import { mapState } from 'vuex';

import { showSuccessToast, showFailToast } from 'vant';
import 'vant/es/toast/style';

export default {
  name: "Login",
  components: {},
  data: () => ({
    customerEmail: '',
    customerPassword: '',
  }),
  created() {
    this.getUser();
  },
  computed: {
    ...mapState(['User']),
  },
  methods: {
    getUser() {
      this.$store.dispatch('User/getUser');
    },    
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
      this.$router.push('/')
    },
    onSubmit(value) {
      console.log(value);
      if (!this.User.user.length) {
        showFailToast('User not found');
        console.log("User not found 1");
        return;
      } else {
        for (let i = 0; i < this.User.user.length; i++) {
          if (this.User.user[i].customerEmail === value.customerEmail) {
            if (this.User.user[i].customerPassword === value.customerPassword) {
              showSuccessToast('Login Success');
              console.log("Login Success");

              // Set current user
              let currentUser = this.User.user[i];
              this.$store.commit('User/setCurrentUser', currentUser);
              console.log(currentUser);

              localStorage.setItem("isLogin", "login");
              this.$router.push({ name: 'Account', params: { id: currentUser.customerId } });
            } else {
              showFailToast('Password or email is incorrect');
              this.customerEmail = '';
              this.customerPassword = '';
              console.log("Password is incorrect");
            }
            return;
          }
        }
        showFailToast('User not found');
        console.log("User not found 2");
      }
      // if (!localStorage.userInfo) {
      //   console.log("User not found");
      //   return;
      // } else {
      //   let userInfo = JSON.parse(localStorage.userInfo);
      //   if (userInfo['email'] === value['email']) {
      //     if (userInfo['password'] === value['password']) {
      //       console.log("Login Success");
      //       localStorage.setItem("isLogin", "login");
      //       this.$router.push('/account');
      //     } else {
      //       console.log("Password is incorrect");
      //     }
      //   } else {
      //     console.log("User not found");
      //   }
      // }

      //clear input
      this.customerEmail = '';
      this.customerPassword = '';
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
    <div>
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
    </div>
    <!--Input Form-->
    <div class="login-form">
      <van-form @submit="onSubmit">
        <van-cell-group inset>
        <van-field
          v-model="customerEmail"
          name="customerEmail"
          label="Email"
          right-icon="contact"
          placeholder="Email"
          :rules="[
            { required: true, message: 'Please enter e-mail' },
            { pattern: /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/, message: 'Please enter valid email' },
          ]"
        />

        <van-field
          v-model="customerPassword"
          type="password"
          name="customerPassword"
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
    <div v-for="user in User.user" :key="user.customerId">User: {{ user.customerName }} | {{ user.customerPassword }} </div>
  </div>
</template>

<style>
</style>