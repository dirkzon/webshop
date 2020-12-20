<template>
  <div>
    <TopBar></TopBar>
    <span>{{error}}</span>
    <v-row>
      <div v-bind:key="product.id" v-for="product in products">
        <ProductThumbnail v-bind:product="product"></ProductThumbnail>
      </div>
    </v-row>
  </div>
</template>

<script>
import TopBar from "@/components/TopBar";
import productService from "@/services/product-service";
import ProductThumbnail from "@/components/ProductThumbnail";

export default {
  name: "ShoppingCart",
  components: {TopBar, ProductThumbnail},
  data(){
    return{
      products:[],
      error:'',
    }
  },
  async mounted() {
    let cart = this.$cookies.get("cart");
    if(cart == null || cart.length < 0){
      this.error = "There are no products in your cart."
    }else{
      Object.keys(cart).forEach(await this.getProduct)
    }
  },
  methods:{
    async getProduct(id){
      alert(id);
      if(id > 0){
        let product = await productService.getProductById(id);
        this.products.push(product);
      }
    }
  },
}
</script>

<style scoped>

</style>