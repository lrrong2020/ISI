import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import store from '@/store'

import 'vant/lib/index.css';
import 'amfe-flexible'
import '@vant/touch-emulator';

//Vant
import { ConfigProvider,Button,Tabbar,TabbarItem,NavBar,Icon,Card } from 'vant';

const app = createApp(App)

app.use(VueAxios, axios)
app.use(router)
app.config.globalProperties.axios = axios
app.use(store)

//Vant
app.use(ConfigProvider);
app.use(Button);
app.use(Tabbar);
app.use(TabbarItem);
app.use(NavBar);
app.use(Icon);
app.use(Card);

app.mount('#app')
