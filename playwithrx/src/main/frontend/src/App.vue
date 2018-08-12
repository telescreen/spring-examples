<template>
<div id="app">
  <Nav></Nav>
  <router-view/>
</div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import Nav from './views/Nav.vue'

@Component({
  components: {
    'Nav': Nav
  }
})
export default class App extends Vue {
  el = '#app'
}
</script>


<style>
body, html {
  height: 100%;
  margin: 0;
  padding: 0;
  border: 0;
  background: #f5f5f5;
  font-family: -apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Helvetica Neue,Arial,sans-serif;
  font-weight: 400;
  line-height: 1.5;
  color: #28313b;
  background: #fff;
}

.app {
  width: 100%;
  min-height: 100%;
}
</style>
