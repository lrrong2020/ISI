import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import 'amfe-flexible'
import './assets/reset.css'
import '@vant/touch-emulator';


//Vant global reference
// import 'vant/lib/index.css';
// import { Button,Icon } from 'vant';
// app.use(Button).use(Icon);

const app = createApp(App)

app.use(router)


app.mount('#app')
