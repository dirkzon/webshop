<template>
  <v-col class="mx-12 my-12">
    <div v-bind:key="review.id" v-for="review in reviews">
      <Review v-bind:review="review">
      </Review>
    </div>
    <WriteReview v-if="!reviewed"
                  v-bind:productId="productId">
    </WriteReview>
  </v-col>
</template>

<script>
import Review from "@/components/Review";
import WriteReview from "@/components/WriteReview";

export default {
  name: "reviews",
  components: {WriteReview, Review},
  props: {
    reviews: Array,
    reviewed: Boolean,
    productId: String
  }
}
</script>