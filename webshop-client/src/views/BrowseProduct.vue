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
        <v-slider v-on:mouseup="browse"
                  min="1"
                  :max="this.maxPrice"
                  color="warning"
                  v-model="minPrice"
                  :label="minPrice">
        </v-slider>
        <v-divider></v-divider>
        <v-card-subtitle class="font-weight-bold">max price:</v-card-subtitle>
        <v-slider color="warning"
                  v-on:mouseup="browse"
                  :min="this.minPrice"
                  :max="this.maxlimit"
                  v-model="maxPrice"
                  :label="maxPrice">
        </v-slider>
        <v-divider></v-divider>
        <v-card-subtitle class="font-weight-bold">min rating:</v-card-subtitle>
        <v-rating v-model="minRating"
                  v-on:input="browse"
                  :size="25"
                  color="warning"
                  background-color="warning"
                  half-increments
                  empty-icon="star_border"
                  full-icon="star"
                  half-icon="star_half"
                  length="5"
        ></v-rating>
        <v-divider></v-divider>
        <v-card-subtitle class="font-weight-bold">sort by:</v-card-subtitle>
        <v-select
            outlined
            v-model="this.sortBy"
            :items="sortTypes"
            v-on:mousedown="browse">
        </v-select>
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
      products: [],
      minPrice: 0,
      maxPrice: 0,
      minRating:0,
      maxlimit:0,
      sortTypes:[
          'price low - high',
          'price high - low'
      ],
      sortBy:'price high - low',
    }
  },
  async mounted() {
    this.minPrice = this.$route.query.min_price
    this.minRating = this.$route.query.min_rating
    this.maxPrice = this.$route.query.max_price
    await this.browse()
  },
  methods:{
    browse: async function(){
      let browseVars = JSON.stringify({
        minPrice: this.minPrice,
        maxPrice: this.maxPrice,
        query: this.$route.query.query,
        minRating: this.minRating,
      })
      this.products = await productService.browseProducts(browseVars)
      this.getMaxPrice();
      this.sortArray();
    },
    getMaxPrice: function(){
      this.maxlimit = Math.max.apply(Math, this.products.map(function(product) { return product.price; })) * 2
    },
    sortArray: function(){
      if(this.sortBy == "price low - high"){
        this.products.sort((a, b) =>{ return a.price - b.price})
      }
      if(this.sortBy == "price high - low"){
        this.products.sort((a, b) =>{ return b.price - a.price})
      }

    }
  }
}
</script>

<style scoped>
</style>