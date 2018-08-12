<template>
    <!-- header -->
  <header class="app-header" id="mainnav">
    <!-- top-bar -->
    <div class="top-bar">
      <!-- top-bar-brand -->
      <div class="top-bar-brand">
        <h4 class="text-uppercase">Smile</h4>
      </div>
      <!-- end top-bar-brand -->
      <!-- top-bar-list menu items when login -->
      <div class="top-bar-list">
        <!-- top-bar-item-full search box-->
        <div class="top-bar-item top-bar-item-full">
          <div class="top-bar-search">
            <div class="input-group input-group-search">
              <div class="input-group-prepend">
                <span class="input-group-text">
                  <i class="fas fa-caret-right"></i>
                </span>
              </div>
              <input type="text" class="form-control" aria-label="Search" placeholder="Search" v-model="query" @keyup="query_onUpdated">
            </div>
          </div>
          <!-- Nav bar -->
          <ul class="header-nav nav">
            <li class="nav-item">
              <a class="nav-link" href="/admin/">{{ $t("header.menu.admin") }}</a>
            </li>
          </ul>
        </div>
        <!-- End top-bar-item-full search box-->
        <!-- top-bar-item-right search box-->
        <div class="top-bar-item top-bar-item-right">
          <!-- Dropdown -->
          <div class="dropdown">
            <button class="btn-account d-none d-md-flex dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              <span class="account-summary">
                <span class="account-name">{{ user.name }}</span>
              </span>
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
              <a class="dropdown-item" href="#">
                <i class="dropdown-icon fa fa-user"></i>
                <span>{{ $t("header.account.profile") }}</span>
              </a>
              <a class="dropdown-item" href="" v-on:click="logout">
                <i class="dropdown-icon fas fa-sign-out-alt"></i>
                <span>{{ $t("header.account.signout") }}</span>
              </a>
            </div>
          </div>
          <!-- End Dropdown -->
        </div>
      </div>
    </div>
    <!-- end top-bar -->
  </header>
  <!-- end header -->
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator';
import Cookies from 'js-cookie'
import { User } from '../models/user'

@Component
export default class Nav extends Vue {
  el = "#mainnav"
  user: User = this.$store.state.user
  query: string = ""

  logout() {
    $.ajax({
      type: 'POST',
      url: "/logout",
      headers: {
        "X-XSRF-TOKEN": Cookies.get('XSRF-TOKEN')
      }
    });
  }

  query_onUpdated() {
    console.log(this.query)
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
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
