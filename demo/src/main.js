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
import { ConfigProvider,Button,Tabbar,TabbarItem,NavBar,Icon,Card,ContactCard,Row,Form, 
Field,CellGroup,Tag } from 'vant';

const app = createApp(App)

app.use(VueAxios, axios)
app.use(router)
app.config.globalProperties.axios = axios
app.use(store)

//Vant
app.use(ConfigProvider).use(Button).use(Tabbar).use(TabbarItem).use(NavBar).use(Icon)
.use(Card).use(ContactCard).use(Row).use(Form).use(Field).use(CellGroup).use(Tag);

app.mount('#app')
