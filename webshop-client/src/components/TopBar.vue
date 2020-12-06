<template>
  <v-app-bar
      app
      color="secondary"
      height="80">
    <div class="d-flex align-center">
      <v-img
          alt="WebShop-logo"
          class="shrink mr-2"
          contain
          src="@/assets/shoppingcart-logo.svg"
          transition="scale-transition"
          width="75"
      />
    </div>
    <router-link :to="{name:'home'}">
      <v-toolbar-title class="text-uppercase white--text">
        <span class="font-weight-thin">web</span>
        <span>shop</span>
      </v-toolbar-title>
    </router-link>
    <v-spacer></v-spacer>
    <v-text-field
        @keydown.enter="Search"
        label="Search for products"
        dark
        bottom
        v-model="searchQuery"
    ></v-text-field>
      <v-btn v-on:click="Search"
             icon
             fab
             small>
        <v-icon color="white">
          search
        </v-icon>
      </v-btn>
    <v-spacer></v-spacer>
      <v-btn v-on:click="redirect"
            small
             class="mx-2 my-2"
             fab
             color="secondary">
        <v-badge content="2"
                 color="warning">
          <v-icon large>
            account_circle
          </v-icon>
        </v-badge>
      </v-btn>

      <v-btn small
             class="mx-2 my-2"
             fab
             color="secondary">
        <v-icon large>
          shopping_cart
        </v-icon>
      </v-btn>

  </v-app-bar>
</template>

<script>
import router from "@/router";

export default {
  name: "TopBar",
  data() {
    return {
      searchQuery: ""
    }
  },
  methods:{
    Search: function () {
      router.push({name: 'search' , query: {query : this.searchQuery}})
    },
    redirect: function (){
      let token = localStorage.getItem('token')
      if(token != null){
        router.push('/account/me')
      }
      router.push('/login')
    }
  }
}
</script>

<style scoped>

</style>
