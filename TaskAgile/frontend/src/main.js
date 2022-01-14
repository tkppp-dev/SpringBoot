import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'

createApp(App).use(store).use(router).mount('#app')
axios.defaults.baseURL = '/api'
axios.defaults.headers.common.Accept = 'appliction/json'