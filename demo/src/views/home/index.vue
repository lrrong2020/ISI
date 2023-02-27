<script>
import Search from '@/components/Search.vue';

export default {
    name: "Home",
    components: {
        Search,
    },
    setup() {
      
    },
};
</script>
<template>
    <div class="home">
        <Search />
        <div>
            Home
            <van-button type="primary">submit</van-button>
            <svg class="icon" aria-hidden="true">
                <use xlink:href="#icon-adduser"></use>
            </svg>
        </div>
    </div>
</template>

<style lang="less" scoped>
.home {
    div{
        color: red
    }
}
</style>