import $ from 'jquery'
import Vue from 'vue'
import VueI18n from 'vue-i18n'
import Vuex from 'vuex'
import App from './App.vue'

import { messages } from './messages'
import { UserRepository, User } from './models/user'

function main() {
  // Initialize Vue modules
  Vue.use(VueI18n)
  Vue.use(Vuex)

  const i18n = new VueI18n({
    locale: 'en',
    messages
  })
  Vue.config.productionTip = false

  // Initialize injectable objects
  let userRepository: UserRepository = new UserRepository()

  $.when(
    userRepository.fetchUser()
  ).done(function(userInfo) {

    const store = new Vuex.Store({
      state: {
        user: new User(userInfo)
      }
    })

    let app = new Vue({
      i18n,
      store,
      provide: {
        userRepository: userRepository
      },
      render: h => h(App)
    }).$mount('#app')
  });
}

main()