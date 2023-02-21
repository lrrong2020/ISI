import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import store from '@/store'

import 'vant/lib/index.css';
import 'amfe-flexible'
import '@vant/touch-emulator';

//Vant
import { Button,Tabbar,TabbarItem,NavBar,Icon } from 'vant';

const app = createApp(App)

app.use(router)
app.config.globalProperties.axios = axios
app.use(store)

//Vant
app.use(Button);
app.use(Tabbar);
app.use(TabbarItem);
app.use(NavBar);
app.use(Icon);

app.mount('#app')
