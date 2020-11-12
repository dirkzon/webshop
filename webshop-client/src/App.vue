<template>
  <v-app>
    <v-content>
      <router-view></router-view>
    </v-content>
  </v-app>
</template>

<script>
export default {
  name: 'App',
  mounted() {
    localStorage.id = 'retailer:1'
    localStorage.token = 'aGVuazp3YWNodHdvb3Jk'
    localStorage.cart = ''
  }
}
</script>

<style>
a {
  text-decoration: none;
}
</style>