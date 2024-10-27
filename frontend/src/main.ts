import {createApp} from 'vue'
import './style.css'
import App from './App.vue'
import router from './router/router'
import vuetify from "./plugins/vuetify.ts";

createApp(App)
    .use(router)
    .use(vuetify)
    .mount('#app')
