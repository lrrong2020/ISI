import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import 'amfe-flexible'
import './assets/reset.css'
import '@vant/touch-emulator';


//Vant global reference
// import 'vant/lib/index.css';
// import { Button,Icon } from 'vant';
// app.use(Button).use(Icon);

const app = createApp(App)

app.use(router)
app.use(VueAxios, axios)

app.mount('#app')
