<template>
  <div>
    <div class="title">CountVuex</div>
    <div class="count">
      <div class="1">{{ $store.state.C.sum }}</div>
      <!-- <div class="1_getter">{{ $store.getters.C.bigSum }}</div>  -->
      <div class="1">I'm in {{ $store.state.C.COD }}, I'm in {{ $store.state.C.mazra }}</div>

      <div class="mapState">{{ C.sum }}</div>
      <!-- <div class="mapGetter">{{ bigSum }}</div> -->
      <div class="mapState">I'm in {{ C.COD }}, I'm in {{ C.mazra }}</div>

      <div class="count__btn">
        <select v-model="n">
          <option v-for="i in 10" :key="i" :value="i">{{ i }}</option>
        </select>
        <button @click="increment">+</button>
        <button @click="decrement">-</button>
        |
        <!--mapMputation and mapAction-->
        <!-- <button @click="Increment(n)">+</button>
        <button @click="Decrement(n)">-</button> -->
      </div>
    </div>
  </div>      
</template>

<script>
//mapState and mapGetter
import { mapState, mapGetters, mapMutations, mapActions } from 'vuex';

export default {
  name: "CountVuex",
  data() {
    return {
      n: 1,
    };
  },
  computed: {
    //mapState and mapGetter
    ...mapState(['C']), //mapState({sum:'sum', COD:'COD', mazra:'mazra'}), //mapState(['sum', 'COD', 'mazra']),
    // ...mapGetters(['bigSum']),//这种写法，<template>里{{ }}中的必须可Store里的state和getter的名字一致
  },
  mounted() {
    console.log('CountVuex', this)
    // mapState and mapGetter
    const x = mapState(['sum', 'COD', 'mazra']); //mapState({sum:'sum', COD:'COD', mazra:'mazra'}); //mapState(['sum', 'COD', 'mazra']);
    console.log('mapState', x);
  },

  methods: {
    increment() {
      this.$store.commit('Increase', this.n);
      console.log("n="+this.n);
      //连续调用两个action
      // this.$store.dispatch('demo1');
    },
    decrement() {
      this.$store.commit('Decrease', this.n);
    },
    //mapMputation and mapAction
    ...mapMutations({Increase:'Increase', Decrease:'Decrease'}),//mapMutations(['Increase', 'Decrease']),
    //上面的click事件中的方法必须和Store中的mutation的名字一致
  }
};
</script>

<style lang="less" scoped>

</style>