<script>
import { mapState } from 'vuex';
import { showSuccessToast, showFailToast } from 'vant';

export default {
  name: 'AccountManagement',
  data() {
    return {
      shippingAddress: '',
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
      this.shippingAddress = this.User.currentUser.shippingAddress;
    }, 
    onClickLeft() {
      this.$router.go(-1);
    },
    Save() {
      const payload = {
        customerId: this.User.currentUser.customerId,
        shippingAddress: this.shippingAddress,
      };
      this.$store.dispatch('User/updateAddress', payload)
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
        title="Address Management"
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
            v-model="shippingAddress"
            type="textarea"
            rows="1"
            clearable
            autosize
            size="large"
            name="shippingAddress"
            label="Address"
            right-icon="location-o"
            placeholder="Your address"
            :rules="[
              { required: true, message: 'Please enter address' },
            ]"
          />
        </van-cell-group>
        <div style="margin: 30px 16px;">
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