<script>
import { mapState } from 'vuex';
import { showSuccessToast, showFailToast } from 'vant';

export default {
  name: 'AccountManagement',
  data() {
    return {
      customerName: '',
      customerPassword: '',
      Password: false,
      type: 'password',
    };
  },
  created() {
    this.getCurrentUser();
  },
  computed: {
    ...mapState(['User']),
  },
  methods: {
    getCurrentUser() {
      this.customerName = this.User.currentUser.customerName;
      this.customerPassword = this.User.currentUser.customerPassword;
    }, 
    onClickLeft() {
      this.$router.go(-1);
    },
    validator(val) {
      return /^.{6,20}$/.test(val);
    },
    showPassword() {
      this.Password = !this.Password;
      this.type = this.Password ? 'text' : 'password';
    },
    Save() {
      const payload = {
        customerId: this.User.currentUser.customerId,
        customerName: this.customerName,
        customerPassword: this.customerPassword,
      };
      console.log("Change Account");
      console.log(payload);
      this.$store.dispatch('User/updateUser', payload)
        .then(() => {
          showSuccessToast({
            message: 'Update Success',
            wordBreak: 'break-word',
          });
          this.$router.go(-1);
        })
        .catch(() => {
          showFailToast({
            message: 'Update Fail',
            wordBreak: 'break-word',
          });
        });
    },
  },
};
</script>

<template>
  <div class="bg">
    <div class="header">
      <van-nav-bar
        title="Account Management"
        left-arrow
        fixed
        placeholder
        @click-left="onClickLeft"
      />
    </div>
    <div class="edit">
      <van-form @submit="Save">
        <van-cell-group inset>
          <van-field
            v-model="customerName"
            clearable
            size="large"
            name="customerName"
            label="Username"
            right-icon="contact"
            placeholder="Your username"
            :rules="[
              { required: true, message: 'Please enter username' },
            ]"
          />
          <van-field
            v-model="customerPassword"
            clearable
            size="large"
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
        <div style="margin-top: 30px; margin-left: 16px; margin-right: 16px;">
          <van-button round block type="primary" native-type="submit" style="font-size: 20px;">
            Save
          </van-button>
        </div>
      </van-form>
    </div>
  </div>
</template>

<style lang="less" scoped>

</style>