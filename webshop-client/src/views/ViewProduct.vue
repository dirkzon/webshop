<template>
  <v-app>
    <TopBar></TopBar>
    <v-row>

      <v-card width="40%">
        <v-responsive :aspect-ratio="16/9">
          <v-img :src="product.image.url">
          </v-img>
        </v-responsive>
      </v-card>

      <v-card style="height:min-content"
          class="mx-12 my-12">
        <v-btn v-if="product.canEdit"
            small
            class="mx-2 my-2"
            fab
            color="#cdc9c3">
          <v-icon dark>
            build
          </v-icon>
        </v-btn>

        <v-card-title>{{product.name}}</v-card-title>
        <v-card-subtitle>{{product.created}}</v-card-subtitle>
        <v-card-subtitle>{{product.description}}</v-card-subtitle>
        <v-rating :value="product.rating"
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
        <v-card-title>${{product.price}}</v-card-title>
        <UserCard v-bind:user="product.retailer"></UserCard>
      </v-card>
    </v-row>
    <div v-bind:key="review.id" v-for="review in product.reviews">
      <Review v-bind:review="review"></Review>
    </div>
    <v-card
        disabled="product.canReview"
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
          ></v-textarea>
          <v-btn @click="createReview"
                 :disabled="!valid"
                 color="secondary"
                style="margin: 10px">
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
      reviewBodyRules: [
        v => !!v || 'review is required', v => v.length <= 200 || 'too many characters'
      ],
      rating: 2.5,
    }
  },
  async mounted() {
    this.product = await productService.getProductById(this.$route.params.id);
  },
   methods:{
    createReview: function(){
      let newReview = JSON.stringify({
        rating: this.rating,
        body: this.body,
        product:{
          id: this.product.id
        },
        customer:{},
      })
      productService.createReviewOnProductById(this.product.id, newReview)
    }
  }
}
</script>

<style scoped>
.v-card{
  margin: 50px
}
</style>