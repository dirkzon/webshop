<template>
  <v-main>
    <TopBar></TopBar>
    <v-row>
      <div v-bind:key="product.id" v-for="product in products">
        <ProductThumbnail v-bind:product="product"></ProductThumbnail>
      </div>
    </v-row>
  </v-main>
</template>

<script>
import TopBar from "@/components/TopBar";
import ProductThumbnail from "@/components/ProductThumbnail";
import productService from "@/services/product-service"

export default {
  name: "BrowseProduct",
  components: {
    TopBar, ProductThumbnail
      },
  data(){
    return{
      products: '',
    }
  },
  async mounted() {
    let browseVars = JSON.stringify({
      minPrice: this.$route.query.min_price,
      maxPrice: this.$route.query.max_price,
      query: this.$route.query.query,
      targetRating: this.$route.query.target_rating,
    })
      this.products = await productService.browseProducts(browseVars)
  }
}
</script>

<style scoped>

</style>