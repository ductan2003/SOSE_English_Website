import { createApp } from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import store from './store'
import './assets/main.css'
import 'bootstrap'


// const cors = require('cors');
// const corsOptions ={
//     origin:'http://localhost:8080',
//     credentials:true,            //access-control-allow-credentials:true
//     optionSuccessStatus:200
// }
// App.use(cors(corsOptions));

createApp(App).use(store).use(router).mount('#app')
