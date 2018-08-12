<template>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { User } from '../models/user'

@Component
export default class Home extends Vue {
  user: User = this.$store.state.user
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

/* Header */
.app-header {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  height: 3rem;
  background-color: #24292e;
  color: rgba(255,255,255,0.75);
  box-shadow: 0 0 0 1px rgba(61,70,79,.05), 0 1px 3px 0 rgba(61,70,79,.15);
  z-index: 1030;
}

.top-bar {
  display: flex;
  width: 100%;
  height: 3rem;
  align-items: stretch;
}

.top-bar-brand {
  margin: 0;
  align-items: center;
  height: 3rem;
  width: 14rem;
  padding: 0 .5rem 0 1rem;
}

.top-bar-list, .top-bar-item {
  display: flex;
  align-items: center;
  flex: 1;
}

.top-bar-item-full {
  flex: 1;
}

.top-bar-item-right {
  margin-right: auto;
}

.top-bar-item {
  padding: 0;
}

.top-bar-search {
  width: 600px;
  margin-right: auto;
  background: rgba(255,255,255,0.75);
  border-radius: 2px;
  box-shadow: 0 2px 2px 0 rgba(0,0,0,0.16), 0 0 0 1px rgba(0,0,0,0.08);
  transition: box-shadow 200ms cubic-bezier(0.4, 0.0, 0.2, 1);
}

.top-bar-search .input-group-text {
  background: rgba(255,255,255,0.75);
  border: none;
}

.top-bar-search input {
  padding-left: 5px;
  border: none;
}

.top-bar-item .nav .nav-item a {
  color: rgba(255,255,255,0.75);
}

.app-header .btn-account {
  padding: 0 1rem;
  min-width: 3.5rem;
  min-height: 3.5rem;
  border-left: 1px solid rgba(0,0,0,.12);
}

.btn-account {
  position: relative;
  margin: 0;
  padding: 0;
  border: 0;
  align-items: center;
  background: none;
  color: inherit;
  cursor: pointer;
  outline: 0;
  transition: background-color .15s;
}

.dropdown-menu {
  font-size: 0.875rem;
  line-height: 1.6;
}

.dropdown-icon {
  margin-right: .25rem;
  color: #a9acb0;
  width: 1.25rem;
}

/* Hack for spring logout link */
.dropdown-item input {
  background-color: inherit;
  vertical-align:bottom;
  overflow:visible;
  font-size:1em;
  display:inline;
  margin:0;
  padding:0;
  border:0;
  cursor:pointer;
}
</style>
