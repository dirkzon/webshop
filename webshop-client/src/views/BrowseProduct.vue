<template>
  <div>
  <TopBar></TopBar>
    <v-main>
      <v-row>
        <v-card style="height:min-content; width: 15%;
                      padding: 20px;
                      margin: 30px;
                      position:fixed"
                class="primary">
          <v-card-title>Search values:</v-card-title>
          <v-divider></v-divider>
          <v-card-subtitle class="font-weight-bold">min price:</v-card-subtitle>
            <v-text-field v-on:change="browse"
                        v-on:click="browse"
                        type="number"
                        min="1"
                        :max="maxPrice - 1"
                        color="warning"
                        v-model="minPrice"
                        data-cy="minPrice">
          </v-text-field>

          <v-card-subtitle class="font-weight-bold">max price:</v-card-subtitle>
          <v-text-field color="warning"
                        type="number"
                        v-on:change="browse"
                        v-on:click="browse"
                        :min="minPrice"
                        v-model="maxPrice"
                        data-cy="maxPrice">
          </v-text-field>

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
                    data-cy="minRating"
          ></v-rating>

          <v-card-subtitle class="font-weight-bold">sort by:</v-card-subtitle>
          <v-select
              v-model="sortBy"
              :items="sortTypes">
          </v-select>
        </v-card>
        <v-row style="margin-left: 320px"
               align="center"
               justify="center">
          <v-card-title v-if="products.length == 0">No products found </v-card-title>
          <div v-bind:key="product.id" v-for="product in products">
            <ProductThumbnail v-bind:product="product"></ProductThumbnail>
          </div>
          <NewProduct></NewProduct>
        </v-row>
      </v-row>
    </v-main>
  </div>
</template>

<script>
import TopBar from "@/components/TopBar";
import ProductThumbnail from "@/components/ProductThumbnail";
import productService from "@/services/product-service"
import NewProduct from "@/components/NewProduct";

export default {
  name: "BrowseProduct",
  components: {
    NewProduct,
    TopBar, ProductThumbnail
      },
  data(){
    return{
      products: [],
      minPrice: 0,
      maxPrice: 0,
      minRating:0,
      query:"",
      sortTypes:[
          'price',
          'rating',
      ],
      sortBy:'price',
    }
  },
  async mounted() {
    await this.browse()
  },
  methods:{
    browse: async function(){
      this.query = this.$route.query.query;
      let browseVars = JSON.stringify({
        minPrice: this.minPrice,
        maxPrice: this.maxPrice,
        query: this.query,
        minRating: this.minRating,
      })
      this.products = await productService.browseProducts(browseVars)
      this.sortArray();
    },
    sortArray: function(){
      if(this.products != null) {
        if (this.sortBy == "price") {
          this.products.sort((a, b) => {
            return a.price - b.price
          })
        }
        if (this.sortBy == "rating") {
          this.products.sort((a, b) => {
            return b.rating - a.rating
          })
        }
      }
    }
  },
  watch: {
    sortBy: function() {
      this.sortArray()
    },
    '$route.query.query': {
      handler: function() {
        this.browse()
      },
      deep: true,
      immediate: true
    }
  }
}
</script>

<style scoped>
</style>
