<script>
import { ref } from 'vue';
import { showToast } from 'vant';

export default {
  setup() {
    const value = ref('');
    const onSearch = (val) => showToast(val);
    const onClickButton = () => showToast(value.value);
    return {
      value,
      onSearch,
      onClickButton,
    };
  },
};
</script>

<template>
    <van-search
    v-model="value"
    show-action
    shape="round"
    background="#9e0aee"
    placeholder="Type to search"
    @search="onSearch"
    >
        <template #action>
            <div @click="onClickButton">Search</div>
        </template>
    </van-search>
</template>