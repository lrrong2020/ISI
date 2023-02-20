import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import store from '@/store'

import 'vant/lib/index.css';
import 'amfe-flexible'
import '@vant/touch-emulator';

import { Button } from 'vant';

const app = createApp(App)

app.use(router)
app.config.globalProperties.axios = axios
app.use(store)
app.use(Button);

app.mount('#app')
