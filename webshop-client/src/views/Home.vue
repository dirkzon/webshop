<template>
  <v-app>
    <TopBar></TopBar>
    <ProductList v-bind:products="products"></ProductList>
  </v-app>
</template>

<script>
import ProductList from '@/components/ProductList'
import TopBar from "@/components/TopBar";

import axios from "axios";
export default {
  components: {
    ProductList,
    TopBar
  },
  data(){
    return{
      products: Object
    }
  },
  mounted() {
    let token = localStorage.getItem("token")
    alert(token)
    axios.get('http://localhost:4545/v1/retailers/retailer:1/catalog',
        {headers:{Authorization: 'Bearer ' + token}})
        .then(response => (this.products = response.data))
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