<template>
  <v-app>
    <TopBar></TopBar>
    <span>{{products}}</span>
  </v-app>
</template>
<script>

import TopBar from "@/components/TopBar";
import productService from "@/services/product-service";

export default {
  components: {
    TopBar
  },
  data(){
    return{
      products: ''
    }
  },
  async mounted() {
    this.products = await productService.getProductById(1);
  }
}
</script>

<style scoped>
/deep/ .v-text-field{
  align-self: auto;
  width: 400px;
}
</style>