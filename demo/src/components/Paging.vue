<script>
import { ref } from 'vue';

export default {
  setup() {
    const currentPage = ref(1);
    return { currentPage };
  },
};
</script>

<template>
    <van-pagination v-model="currentPage" :total-items="50" 
    :show-page-size="5" :items-per-page="5" @change="pagechange">
    <template #prev-text>
        <van-icon name="arrow-left" />
    </template>
    <template #next-text>
        <van-icon name="arrow" />
    </template>
    <template #page="{ text }">{{ text }}</template>
    </van-pagination>
</template>

<style lang="less" scoped>

</style>