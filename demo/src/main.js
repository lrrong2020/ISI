import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import store from '@/store'
import 'amfe-flexible'
import './assets/reset.css'
import '@vant/touch-emulator';


const app = createApp(App)

//Vant global reference
// import 'vant/lib/index.css';
// import { Button,Icon,Swipe,SwipeItem,Search,ConfigProvider,Card,Tag,Pagination,CellGroup,Field,NavBar,Tabbar,TabbarItem,ActionBar,
// ActionBarIcon,ActionBarButton,Grid,GridItem,Tabs,Tab,Empty,SwipeCell,NumberKeyboard,Stepper,SubmitBar,Checkbox,Row,Col } from 'vant';

// app.use(Button).use(Icon).use(Swipe).use(SwipeItem).use(Search).use(ConfigProvider).use(Card).use(Tag)
// .use(Pagination).use(CellGroup).use(Field).use(NavBar).use(Tabbar).use(TabbarItem).use(ActionBar).use(ActionBarIcon)
// .use(ActionBarButton).use(Grid).use(GridItem).use(Tabs).use(Tab).use(Empty).use(SwipeCell).use(NumberKeyboard)
// .use(Stepper).use(SubmitBar).use(Checkbox).use(Row).use(Col);


app.use(router)
app.use(store)
app.use(VueAxios, axios)

app.mount('#app')
