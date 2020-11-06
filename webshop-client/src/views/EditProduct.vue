<template>
  <div>
    <TopBar></TopBar>
    <ProductDetails v-on:ReturnProduct="UpdateProduct($event)" v-bind:product="product"></ProductDetails>
  </div>
</template>

<script>
import TopBar from "@/components/TopBar";
import ProductDetails from "@/components/ProductDetails";
import axios from "axios";

export default {
  components: {
    TopBar,
    ProductDetails
  },
  data(){
    return{
      product: Object
    }
  },
  mounted(){
    axios.get('http://localhost:4545/v1/products/'+ this.$route.params.id)
        .then(response => (this.product = response.data))
        .catch(error => alert("wasn't able to get product data " + error))
  },
  methods:{
    UpdateProduct:function (updatedProduct){
      axios.put('http://localhost:4545/v1/products/' + this.$route.params.id, updatedProduct)
          .then(response => alert(response))
          .catch(error => alert(error))
    }
  }
}
</script>

<style scoped>

</style>