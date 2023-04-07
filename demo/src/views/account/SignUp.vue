<script>
import { ref } from 'vue';
import router from '../../router';
import { mapState } from 'vuex';
import signupImg from '@/assets/Signup.png';
import { showSuccessToast, showFailToast } from 'vant';
// import 'vant/es/toast/style';

export default {
  name: "SignUp",
  components: {},
  data() {
    return {
      customerName: '',
      customerEmail: '',
      customerPassword: '',
      shippingAddress: '',
      signupImg,
    }
  },
  created() {
    this.getUser();
  },
  computed: {
    ...mapState(['User']),
  },
  methods: {
    // async signUp() {
    //   let result = await this.axios.post('http://localhost:3000/users', {
    //     username: this.username,
    //     email: this.email,
    //     password: this.password,
    //     address: this.address,
    //   });
    //   console.log(result);
    //   if (result.status === 201) {
    //     alert("Sign Up Success");
    //     localStorage.setItem("user-info", JSON.stringify(result.data));
    //   }
    // },
    getUser() {
      this.$store.dispatch('User/getUser');
    },    
    onClickLeft() {
      this.$router.push('/')
    },
    onSubmit(value) {
      // console.log(value)
      if (this.User.user.length > 0) {
        //遍历用户列表，如果用户已存在，则不添加
        for (let i = 0; i < this.User.user.length; i++) {
          if (this.User.user[i].customerEmail === value.customerEmail) {
            showFailToast({
              message: 'Email already exists',
              wordBreak: 'break-word',
            });
            this.customerEmail = '';
            console.log("User already exists");
            return; //退出函数
          }
        }
        //如果用户不存在，则添加
        this.SignUp(value);
        console.log("调用SingUp 1");
      } else {
        //如果用户列表为空，则添加
        this.SignUp(value);
        console.log("调用SingUp 2");
      }
        
      // if (localStorage.userInfo) {
      //   let userInfo = JSON.parse(localStorage.getItem("userInfo"));
      //   if (userInfo['email'] === value['email']) {
      //     console.log("Username already exists");
      //     return;
      //   }
      // } else {
      //   this.SignUp(value);
      // }

      // clear input
      this.customerName = '';
      this.customerEmail = '';
      this.customerPassword = '';
      this.shippingAddress = '';
    },
    async SignUp(value) {
      await this.$store.dispatch('User/signup', value);
      showSuccessToast({
        message: 'Sign Up Success',
        wordBreak: 'break-word',
      });
      console.log("添加用户成功");
      await this.$store.dispatch('User/getUser');
      console.log("获取用户列表成功");

      for (let i = 0; i < this.User.user.length; i++) {
        if (this.User.user[i].customerEmail === value.customerEmail) {
          // Set current user
          let currentUser = this.User.user[i];
          this.$store.commit('User/setCurrentUser', currentUser);
          console.log("current user:");
          console.log(currentUser);
          //set isLogin
          localStorage.setItem("isLogin", "login");
          //跳转到Account页面
          this.$router.push({ name: 'Account', params: { id: currentUser.customerId } });
        }
      }

    },
      // // Set current user
      // let currentUser = await this.getCurrentUser();  
      // console.log("current user:");
      // console.log(currentUser);
      // //set isLogin
      // localStorage.setItem("isLogin", "login");
      // //跳转到Account页面
      // this.$router.push({ name: 'Account', params: { id: currentUser.customerId } });
    getCurrentUser() {
      return this.$store.state.User.currentUser;
    },
    // SignUp(value) {
    //   this.$store.dispatch('User/signup', value);
    //   // localStorage.setItem("userInfo", JSON.stringify(value));
    //   console.log("添加用户成功");

    //   // Set current user
    //   let currentUser = value;
    //   this.$store.commit('User/setCurrentUser', currentUser);
    //   console.log("current user:")
    //   console.log(currentUser);

    //   localStorage.setItem("isLogin", "login");
    //   // this.$router.push('/account');
    // },
    validator(val) {
      return /^.{6,20}$/.test(val);
    },
  },
};
</script>

<template>
  <div class="signup">
    <van-nav-bar
      :border="false"
      title="Sign Up"
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
          :src="signupImg"
        />
      </van-row>
      <!--Title-->
      <van-row justify="center">
        <van-col>
          <h1 style="font-size: 40px;">Sign Up</h1>
        </van-col>
      </van-row>
      <!--Input Form-->
      <div class="signup-form">
        <van-form @submit="onSubmit">
          <van-cell-group inset>
          <van-field
            v-model="customerName"
            clearable
            name="customerName"
            label="Username"
            right-icon="contact"
            placeholder="Your username"
            :rules="[
              { required: true, message: 'Please enter username' },
            ]"
          />
          <van-field
            v-model="customerEmail"
            clearable
            name="customerEmail"
            label="E-mail"
            right-icon="envelop-o"
            placeholder="Your e-mail"
            :rules="[
              { required: true, message: 'Please enter E-mail' },
              { pattern: /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/, message: 'Please enter valid email' },  
            ]"
          />
          <van-field
            v-model="customerPassword"
            clearable
            type="password"
            name="customerPassword"
            label="Password"
            right-icon="closed-eye"
            placeholder="Your password"
            :rules="[
              { required: true, message: 'Please enter password' },
              { validator, message: 'Password must be at least 6 characters' },
            ]"
          />
          <van-field
            v-model="shippingAddress"
            clearable
            name="shippingAddress"
            label="Address"
            right-icon="location-o"
            placeholder="Your address"
            :rules="[
              { required: true, message: 'Please enter address' },
            ]"
          />
          </van-cell-group>
          <div style="margin: 16px;">
            <van-button round block type="primary" native-type="submit" style="font-size: 20px;">
              Sign Up
            </van-button>
          </div>
          <div style="font-size: 20px;text-align: center;">or</div>
          <div style="margin: 16px; text-align: center; font-size: medium;">
            <router-link to="/login" style="color: #1989fa;font-size: 20px; text-decoration:underline">Login</router-link>
          </div>
        </van-form>
      </div>
    </div>
  </div>
  
</template>

<style scoped>
.mainbody {
  margin-top: 20%;
}
</style>