<template>
  <v-app>
    <v-content>
      <router-view></router-view>
    </v-content>
  </v-app>
</template>

<script>

import axios from "axios";

export default {
  name: 'App',
  mounted() {
    localStorage.id = 'retailer:1'
    localStorage.cart = ''
    axios.defaults.headers.common["Authorization"] = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huIiwianRpIjoicmV0YWlsZXI6MSIsInJvbGUiOiJSZXRhaWxlciIsImlhdCI6MTYwNTgxODU3OX0.-EjR8C3ykcL5fu75sowILPNIH1sIlDWdrnPQqNQSEVI"
  }
}
</script>

<style>
a {
  text-decoration: none;
}
</style>