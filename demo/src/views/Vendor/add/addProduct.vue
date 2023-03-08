<template>
<van-cell-group inset>
  <van-field v-model="value" label="Label" placeholder="Text" />
</van-cell-group>
</template>

<script>import { ref } from 'vue';

export default {
  setup() {
    const value = ref('');
    return { value };
  },
};
</script>