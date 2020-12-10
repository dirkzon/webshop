<template>
  <v-app>
    <v-content>
      <v-alert v-if="!loggedIn"
               type="warning"
               icon="error_outline">
        You are not logged in.
        <v-btn v-on:click="$router.push('login')" small>Log in</v-btn>
      </v-alert>
      <router-view></router-view>
    </v-content>
  </v-app>
</template>

<script>

export default {
  name: 'App',
  data() {
    return {
      loggedIn: false,
    }
  },
  mounted() {
    let token = this.$cookies.get("access_token")
    if(token != null){
      this.loggedIn = true;
    }
  }
}
</script>

<style>
a {
  text-decoration: none;
}
</style>
