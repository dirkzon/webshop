<template>
  <div>
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
        style="margin: 80px"
        @keydown.enter="Search"
        label="Search for products"
        dark
        bottom
        append-icon="search"
        v-model="searchQuery"
    ></v-text-field>
    <v-spacer></v-spacer>
      <v-btn v-if="loggedIn"
             icon
             class="mx-2 my-2"
             color="white"
             @click.stop="drawer = !drawer">
        <v-icon large>
          menu
        </v-icon>
      </v-btn>
  </v-app-bar>
    <v-alert v-if="!loggedIn"
             type="warning"
             icon="error_outline"
             style="margin: 0px">
      You are not logged in.
      <v-btn v-on:click="$router.push('/login')" small>Log in</v-btn>
    </v-alert>
  <v-navigation-drawer
      v-model="drawer"
      style="position: fixed"
      absolute
      right
      temporary>
    <v-list
        nav
        dense>
      <v-list-item-group active-class="warning--text">
        <v-list-item v-on:click="drawer = !drawer">
          <v-spacer></v-spacer>
            <v-icon x-large
                    color="gray">
              menu
            </v-icon>
        </v-list-item>
        <br>
        <v-divider></v-divider>
        <v-list-item>
          <v-icon large>person</v-icon>
          <v-list-item-title v-on:click="viewAccount">account</v-list-item-title>
        </v-list-item>
        <v-divider></v-divider>
        <v-list-item v-if="scope == 'CUSTOMER'" v-on:click="goToCart">
          <v-badge
              bordered
              color="warning"
              overlap
              :content="cartSize"
              :value="cartSize">
            <v-icon large>shopping_cart</v-icon>
          </v-badge>
          <v-list-item-title>shopping cart</v-list-item-title>
        </v-list-item>
        <v-divider></v-divider>
        <v-list-item v-if="scope == 'RETAILER'" v-on:click="goToReports">
          <v-icon large>report</v-icon>
          <v-list-item-title>reports</v-list-item-title>
        </v-list-item>
        <v-divider></v-divider>
        <v-list-item>
          <v-icon large>exit_to_app</v-icon>
          <v-list-item-title v-on:click="logOut">log out</v-list-item-title>
        </v-list-item>
      </v-list-item-group>
    </v-list>
  </v-navigation-drawer>
  </div>
</template>

<script>
import router from "@/router";

export default {
  name: "TopBar",
  data() {
    return {
      searchQuery: "",
      drawer: false,
      loggedIn: false,
      scope:"",
      cartSize: 0,
    }
  },
  async mounted() {
    let token = await this.$cookies.get("access_token");
    this.loggedIn = (token != null);
    this.scope = this.$cookies.get("scope");
    this.cartSize = JSON.parse(this.$cookies.get("cart")).length;
  },
  methods:{
    Search: function () {
      router.push({name: 'broseProducts' , query: {query : this.searchQuery}})
    },
    logOut: function(){
      this.$cookies.remove("access_token")
      this.$cookies.remove("scope")
      this.$cookies.remove("cart")
      router.push('/login')
    },
    viewAccount: function(){
      if(this.scope == "CUSTOMER"){
        router.push({name: 'customerAccount'})
      }
      if(this.scope == "RETAILER") {
        router.push({name: 'retailerAccount'})
      }
    },
    goToCart: function(){
      router.push({name: 'shoppingCart'})
    },
    goToReports: function(){
      router.push({name: 'viewReports'})
    }
  }
}
</script>

<style scoped>

</style>
