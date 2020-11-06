<template>
  <div>
    <TopBar></TopBar>
    <ProductDetails v-on:ReturnProduct="CreateNewProduct($event)"></ProductDetails>
  </div>
</template>

<script>
import TopBar from "@/components/TopBar";
import ProductDetails from "@/components/ProductDetails";

import axios from "axios";

export default {
  components:{TopBar, ProductDetails},
  name: "CreateProduct",
  methods:{
    CreateNewProduct: function (product){
      axios.post("http://localhost:4545/v1/retailers/" + localStorage.id + "/catalog", product)
      .then(alert("success"))
      .catch(error => alert(error))
    }
  }
}
</script>

<style scoped>

</style>