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
    <span v-if="product.canReview"> I can review AAAAAAAAAAAAAAAAAAAAAAAAAA</span>
  </v-app>
</template>

<script>
import TopBar from "@/components/TopBar";
import productService from "@/services/product-service";
import UserCard from "@/components/UserCard";
export default {
  name: "ViewProduct",
  components: {
    UserCard,
    TopBar
  },
  data(){
    return{
      product: ''
    }
  },
  async mounted() {
    this.product = await productService.getProductById(this.$route.params.id);
  }
}
</script>

<style scoped>
.v-card{
  margin: 50px
}
</style>