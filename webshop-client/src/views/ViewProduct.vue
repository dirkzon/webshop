<template>
  <v-app>
    <TopBar></TopBar>
    <v-row>
      <v-card width="40%" height="min-content">
          <v-img :src="product.image.url"
                  data-cy="image">
          </v-img>
      </v-card>

      <v-card style="height:min-content"
          class="mx-12 my-12">
          <v-btn v-if="product.canEdit"
                 @click="editProduct"
                 small
                 class="mx-2 my-2"
                 fab
                 color="secondary">
            <v-icon dark>
              build
            </v-icon>
          </v-btn>

        <v-card-title>{{product.name}}</v-card-title>
        <v-card-subtitle>{{product.created}}</v-card-subtitle>
        <v-card-subtitle style="max-width: 250px">{{product.description}}</v-card-subtitle>
        <v-rating v-if="product.rating > 0"
                  :value="product.rating"
                  :size="15"
                  color="warning"
                  background-color="warning"
                  readonly
                  half-increments
                  empty-icon="star_border"
                  full-icon="star"
                  half-icon="star_half"
                  length="5"
        ></v-rating>
        <v-card-title>${{product.price.toFixed(2)}}</v-card-title>
        <UserCard v-bind:user="product.retailer"></UserCard>
        <v-btn :disabled="!canAddToCart"
               @click="addToCart"
               color="secondary"
               style="margin: 10px"
               data-cy="addToCart">
          <v-icon>
            shop
          </v-icon>
          Add to cart
        </v-btn>
      </v-card>
    </v-row>
    <div v-bind:key="review.id" v-for="review in product.reviews">
      <Review v-bind:review="review" @report="report"></Review>
    </div>
    <v-card
        :disabled="!product.canReview"
        class="mx-8 my-8"
        width="600px">
      <v-responsive :aspect-ratio="16/7" >
        <v-row>
        <v-card-title style="margin-left: 10px"
        >Write a review:</v-card-title>
          <v-spacer></v-spacer>
          <v-rating :size="20"
                    style="margin:15px"
                    v-model="rating"
                    color="warning"
                    background-color="warning"
                    half-increments
                    empty-icon="star_border"
                    full-icon="star"
                    half-icon="star_half"
                    length="5"
          ></v-rating>
        </v-row>
        <v-form
            v-model="valid">
          <v-divider></v-divider>
          <v-textarea
              :rules="reviewBodyRules"
              style="margin:10px"
              single-line
              full-width
              counter="200"
              v-model="body"
              data-cy="reviewBody"
          ></v-textarea>
          <v-btn @click="createReview"
                 :disabled="!valid"
                 color="secondary"
                 style="margin: 10px"
                 data-cy="postReview">
            <v-icon>
              add_comment
            </v-icon>
            Post review
          </v-btn>
        </v-form>
      </v-responsive>
    </v-card>
  </v-app>
</template>

<script>
import TopBar from "@/components/TopBar";
import productService from "@/services/product-service";
import UserCard from "@/components/UserCard";
import Review from "@/components/Review";

import reportService from "@/services/report-service"

export default {
  name: "ViewProduct",
  components: {
    UserCard,
    TopBar,
    Review
  },
  data(){
    return{
      product: '',
      valid: false,
      body: '',
      canAddToCart: false,
      reviewBodyRules: [
        v => !!v || 'review is required', v => v.length <= 200 || 'too many characters'
      ],
      rating: 2.5,
    }
  },
  async mounted() {
    this.product = await productService.getProductById(this.$route.params.id);
    this.canAddProductToCart();
  },
   methods:{
    createReview: async function(){
      let newReview = JSON.stringify({
        rating: this.rating,
        body: this.body,
        product:{
          id: this.product.id
        },
        customer:{},
      })
      await productService.createReviewOnProductById(this.product.id, newReview)
      this.product = await productService.getProductById(this.$route.params.id);
    },
     addToCart: function(){
      let cart =  JSON.parse(this.$cookies.get("cart"));
      if(cart == null){
        cart = [];
      }
      if(cart.includes(this.product.id)){
        alert("cart already contains product")
      }else{
        cart.push(this.product.id);
        this.canAddToCart = false;
      }
      this.$cookies.set("cart", JSON.stringify(cart))
     },
     canAddProductToCart: function(){
      let cart = JSON.parse(this.$cookies.get("cart"));
       if(cart == null){
         cart = [];
       }
       if(!cart.includes(this.product.id)){
         if(this.$cookies.get("scope") == "CUSTOMER"){
           this.canAddToCart = true;
         }
       }
     },
     editProduct: function(){
      this.$router.push({name: 'editProduct' , params: {id:this.product.id}})
     },
     report: async function(review_id){
      alert("Review has been reported");
      let report = JSON.stringify({
        retailer: {
          id: this.product.retailer.id,
        },
        review:{
          id: review_id
        }
      })
      await reportService.reportReview(report);
     }
  }
}
</script>

<style scoped>
.v-card{
  margin: 50px
}
</style>
