import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import 'lib-flexible/flexible'
import 'vant/lib/index.css';
import '@vant/touch-emulator';

import './assets/main.css'

const app = createApp(App)

//Vant
import { Button } from 'vant';
app.use(Button);

app.use(createPinia())
app.use(VueAxios, axios)
app.use(router)

app.mount('#app')
