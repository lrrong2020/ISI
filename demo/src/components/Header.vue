<script>
export default {
    name: "Header",
    setup() {
        const onClickLeft = () => history.back();

        return {
            onClickLeft,
        };
    },
}
</script>

<template>
    <van-config-provider :theme-vars="themeVars">
        <van-nav-bar
        :border="false"
        title="Title"
        left-arrow
        @click-left="onClickLeft"
        class="header"
        />
    </van-config-provider> 
</template>

<style lang="less" scoped>
.header {
    background-image: linear-gradient(#7232dd, #ffffff);
}
</style>