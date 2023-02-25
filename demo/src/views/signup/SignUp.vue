<script>
import { ref } from 'vue';

export default {
  name: "SignUp",
  components: {},
  setup() {
    const username = ref('');
    const email = ref('');
    const password = ref('');
    const address = ref('');
    const onSubmit = (values) => {
      console.log('submit', values);
    };

    return {
      username,
      password,
      email,
      address,
      onSubmit,
    };
  },
  methods: {
    async signUp() {
      let result = await this.axios.post('http://localhost:3000/users', {
        username: this.username,
        email: this.email,
        password: this.password,
        address: this.address,
      });
      console.log(result);
      if (result.status === 201) {
        alert("Sign Up Success");
        localStorage.setItem("user-info", JSON.stringify(result.data));
      }
    },
  },
};
</script>

<template>
  <div class="signup">
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
          :rules="[{ required: true, message: 'Please enter username' }]"
        />
        <van-field
          v-model="email"
          name="E-mail"
          label="E-mail"
          right-icon="envelop-o"
          placeholder="Your e-mail"
          :rules="[{ required: true, message: 'Please enter E-mail' }]"
        />
        <van-field
          v-model="password"
          type="password"
          name="Password"
          label="Password"
          right-icon="closed-eye"
          placeholder="Your password"
          :rules="[{ required: true, message: 'Please enter password' }]"
        />
        <van-field
          v-model="address"
          name="Address"
          label="Address"
          right-icon="location-o"
          placeholder="Your address"
          :rules="[{ required: true, message: 'Please enter address' }]"
        />
        </van-cell-group>
        <div style="margin: 16px;">
          <van-button round block type="primary" native-type="submit" @click="signUp">
            Sign Up
          </van-button>
        </div>
        <div style="margin: 16px;">
          <van-button round block type="primary" native-type="submit" to="/login">
            Login
          </van-button>
        </div>
      </van-form>
    </div>
  </div>
</template>

<style scoped>

</style>