<script>
import { ref } from 'vue';
export default {
  name: "SignUp",
  components: {},
  data() {
    return {
      username: '',
      email: '',
      password: '',
      address: '',
    }
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
    onClickLeft() {
      this.$router.push('/account')
    },
    onSubmit(value) {
      console.log(value);
    },
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
    <!--Icon-->
    <van-row justify="center">
      <van-icon name="add-o" color="#9819fa" size="80"/>
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
          v-model="username"
          name="Username"
          label="Username"
          right-icon="contact"
          placeholder="Your username"
          :rules="[
            { required: true, message: 'Please enter username' },
          ]"
        />
        <van-field
          v-model="email"
          name="E-mail"
          label="E-mail"
          right-icon="envelop-o"
          placeholder="Your e-mail"
          :rules="[
            { required: true, message: 'Please enter E-mail' },
            { pattern: /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/, message: 'Please enter valid email' },  
          ]"
        />
        <van-field
          v-model="password"
          type="password"
          name="Password"
          label="Password"
          right-icon="closed-eye"
          placeholder="Your password"
          :rules="[
            { required: true, message: 'Please enter password' },
            { validator, message: 'Password must be at least 6 characters' },
          ]"
        />
        <van-field
          v-model="address"
          name="Address"
          label="Address"
          right-icon="location-o"
          placeholder="Your address"
          :rules="[
            { required: true, message: 'Please enter address' },
          ]"
        />
        </van-cell-group>
        <div style="margin: 16px;">
          <van-button round block type="primary" native-type="submit">
            Sign Up
          </van-button>
        </div>
        <div style="margin: 16px; text-align: center; font-size: medium;">
          <router-link to="/login">Login</router-link>
        </div>
      </van-form>
    </div>
  </div>
</template>

<style scoped>
</style>