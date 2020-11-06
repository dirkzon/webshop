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
        label="Search for products"
        dark
        bottom
        v-model="searchQuery"
    ></v-text-field>
    <router-link :to="{name: 'search' , query: {query : searchQuery}}">
      <v-btn icon
             fab
             small>
        <v-icon color="white">
          search
        </v-icon>
      </v-btn>
    </router-link>
    <v-spacer></v-spacer>
    <router-link :to="{name: 'viewAccount'}">
      <v-btn small
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
    </router-link>
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
export default {
  name: "TopBar",
  data() {
    return {
      searchQuery: ""
    }
  }
}
</script>

<style scoped>

</style>
