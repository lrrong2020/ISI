<script>
import { ref } from 'vue';

export default {
    name: "BottomNavBar",
    setup() {
        const active = ref(0);
        return { 
            active,
        };
    }
}    
</script>

<template>
    <van-tabbar placeholder height: v-model="active" route>
        <van-tabbar-item replace to="/" icon="home-o">Home</van-tabbar-item>
        <van-tabbar-item replace to="/cart" icon="shopping-cart-o">Cart</van-tabbar-item>
        <van-tabbar-item replace to="/account" icon="user-circle-o">Account</van-tabbar-item>
    </van-tabbar>
</template>

<style scoped>

</style>