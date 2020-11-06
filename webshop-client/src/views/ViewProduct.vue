<template>
  <v-app>
    <TopBar></TopBar>
    <Product v-bind:product="product"></Product>
  </v-app>
</template>

<script>
import Product from "@/components/Product";
import TopBar from "@/components/TopBar";

import axios from "axios";
export default {
  components: {
    Product,
    TopBar
  },
  data(){
    return{
      product: Object
    }
  },
  props:{
    productid: String
  },
  mounted() {
    axios
        .get('http://localhost:4545/v1/products/' + this.$route.params.id)
        .then(response => (this.product = response.data))
        .catch(error => alert(error))
  }
}
</script>

<style scoped>
/deep/ .v-text-field{
  align-self: auto;
  width: 400px;
}
</style>