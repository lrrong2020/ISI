<script>
import { ref } from 'vue';
import { showToast } from 'vant';

export default {
  name: "Search",
  setup() {
    const value = ref('');
    const onSearch = (val) => showToast(val);
    const onClickButton = () => { console.log(value.value) };
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
    background="#ffffff"
    placeholder="Type to search"
    @search="onSearch"
    >
        <template #action>
            <div @click="onClickButton" class="button">Search</div>
        </template>
    </van-search>
</template>
<style lang="less" scoped>
</style>