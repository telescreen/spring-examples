import Vue from 'vue'
import { StompService, StompState } from './services'

let stompConfig = {
  host: "localhost",
  port: 8080,
  endpoint: "facts",
  ssl: false,
  username: "",
  password: "",
  publish: ["/app/hello"],
  subscribe: ["/topic/hello"],
  heartbeat_in: 20000,
  heartbeat_out: 20000,
  debug: true
}

let stompService = new StompService();
stompService.configure(stompConfig);

let app = new Vue({
  el: "#app",
  provide: {
    stompService
  },
  mounted: function() {
    stompService.try_connect();
  },
  data: {
    query: ''
  },
  methods: {
    query_OnUpdated: function() {

    }
  }
})