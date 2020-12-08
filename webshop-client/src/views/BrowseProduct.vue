<template>
  <v-main>
    <TopBar></TopBar>
    <v-row>
      <v-card style="height:min-content; width: 15%;
                    padding: 20px;
                    margin: 30px;
                    position:fixed"
              class="primary">
        <v-card-title>Search values:</v-card-title>
        <v-divider></v-divider>
        <v-card-subtitle class="font-weight-bold">min price:</v-card-subtitle>
        <v-slider color="warning"
                  v-model="minPrice"
                  :label="minPrice">
        </v-slider>
        <v-divider></v-divider>
        <v-card-subtitle class="font-weight-bold">max price:</v-card-subtitle>
        <v-slider color="warning"
                  v-model="maxPrice"
                  :label="maxPrice">
        </v-slider>
        <v-divider></v-divider>
        <v-card-subtitle class="font-weight-bold">min rating:</v-card-subtitle>
        <v-rating v-model="minRating"
                  :size="25"
                  color="warning"
                  background-color="warning"
                  half-increments
                  empty-icon="star_border"
                  full-icon="star"
                  half-icon="star_half"
                  length="5"
        ></v-rating>
      </v-card>
      <v-row style="margin-left: 320px">
        <div v-bind:key="product.id" v-for="product in products">
          <ProductThumbnail v-bind:product="product"></ProductThumbnail>
        </div>
      </v-row>
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
      minPrice: 1,
      maxPrice: 100,
      minRating:0.5,
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