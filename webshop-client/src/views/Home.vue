<template>
  <v-app>
    <TopBar></TopBar>
      <v-row align="center"
             justify="center">
        <div v-bind:key="product.id" v-for="product in products">
          <ProductThumbnail v-bind:product="product"></ProductThumbnail>
        </div>
      </v-row>
  </v-app>
</template>
<script>

import TopBar from "@/components/TopBar";
import productService from "@/services/product-service"
import ProductThumbnail from "@/components/ProductThumbnail";

export default {
  components: {
    ProductThumbnail,
    TopBar
  },
  data(){
    return{
      products: []
    }
  },
  async mounted() {
    this.products = await productService.browseProducts("{}")
  }
}
</script>

<style scoped>
/deep/ .v-text-field{
  align-self: auto;
  width: 400px;
}
</style>
