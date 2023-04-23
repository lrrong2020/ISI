<script>
import { ref } from 'vue';
import { mapState } from 'vuex';
import loginImg from '@/assets/Login.png';
import { showSuccessToast, showFailToast } from 'vant';
// import 'vant/es/toast/style';

export default {
  name: "Login",
  components: {},
  data: () => ({
    customerEmail: '',
    customerPassword: '',
    loginImg,
    Password: false,
    type: 'password',
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
      this.$router.push({ name: 'Home' });
    },
    onSubmit(value) {
      console.log(value);
      if (!this.User.user.length) {
        showFailToast({
          message: 'User not found',
          wordBreak: 'break-word',
        });
        this.customerEmail = '';
        this.customerPassword = '';
        console.log("User not found 1");
        return;
      } else {
        for (let i = 0; i < this.User.user.length; i++) {
          if (this.User.user[i].customerEmail === value.customerEmail) {
            if (this.User.user[i].customerPassword === value.customerPassword) {
              showSuccessToast({
                message: 'Login Success',
                wordBreak: 'break-word',
              });
              console.log("Login Success");

              // Set current user
              let currentUser = this.User.user[i];
              this.$store.commit('User/setCurrentUser', currentUser);
              console.log(currentUser);

              localStorage.setItem("isLogin", "login");
              this.$router.push({ name: 'Account', params: { id: currentUser.customerId } });
            } else {
              showFailToast({
                message: 'Password is incorrect',
                wordBreak: 'break-word',
              });
              this.customerPassword = '';
              console.log("Password is incorrect");
            }
            return;
          }
        }
        showFailToast({
          message: 'User not found',
          wordBreak: 'break-word',
        });
        this.customerEmail = '';
        this.customerPassword = '';
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
    showPassword() {
      this.Password = !this.Password;
      this.type = this.Password ? 'text' : 'password';
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
      <div class="mainbody">
        <!--Icon-->
        <van-row justify="center">
          <van-image
            width="130"
            height="130"
            :src="loginImg"
          />
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
              v-model="customerEmail"
              clearable
              autocomplete
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
              clearable
              autocomplete
              :type="type"
              name="customerPassword"
              label="Password"
              placeholder="Password"
              :right-icon="Password ? 'eye-o' : 'eye'"
              @click-right-icon="showPassword"
              :rules="[
                { required: true, message: 'Please enter password' },
                { validator, message: 'Password must be at least 6 characters' },
              ]"
            />
            </van-cell-group>
            <div style="margin: 16px;">
              <van-button round block type="primary" native-type="submit" style="font-size: 20px;">
                Login
              </van-button>
            </div>
            <div style="font-size: 20px;text-align: center;">or</div>
            <div style="margin: 16px; text-align: center; font-size: medium;">
              <router-link to="/signup" style="color: #1989fa;font-size: 20px; text-decoration:underline">Sign Up</router-link>
            </div>
          </van-form>
        </div>
    </div>
  </div>
</template>

<style lang="less" scoped>
.mainbody{
  margin-top: 25%;
}
</style>