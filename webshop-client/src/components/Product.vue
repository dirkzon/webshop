<template>
  <div style="margin-top: 85px">
    <v-container>
      <v-row>
        <v-carousel class="mx-12 my-12 grey lighten-3"
                    :cycle=true
                    show-arrows-on-hover
                    prev-icon="navigate_before"
                    next-icon="navigate_next"
                    delimiter-icon="lens">
          <v-carousel-item
              v-for="(item,i) in product.images"
              :key="i"
              :src="item.url">
          </v-carousel-item>
        </v-carousel>
          <v-card
                  class="mx-12 my-12">
            <v-card-title>{{product.name}}</v-card-title>
            <v-card-text>{{product.created.day}}-{{product.created.month}}-{{product.created.year}}</v-card-text>
            <v-card-title>$ {{product.price}}</v-card-title>
            <v-card-subtitle>{{product.description}}</v-card-subtitle>
            <Rating v-bind:size="18" :value="product.rating"></Rating>
            <router-link :to="{name: 'editProduct' , params: {id:this.product.id}}">
              <v-btn
                  small
                  class="mx-2 my-2"
                  fab
                  color="secondary">
                <v-icon dark>
                  build
                </v-icon>
              </v-btn>
            </router-link>
            <v-subheader style="margin:15px">
              <v-avatar>
                <img :src="product.retailer.avatar.url"/>
              </v-avatar>
              <v-tooltip top>
                <template v-slot:activator="{ on, attrs }">
                  <v-card-title v-bind="attrs"
                                v-on="on">
                    {{product.retailer.name}}
                  </v-card-title>
                </template>
                <span>
                  joined: {{product.retailer.joined.day}}-{{product.retailer.joined.month}}-{{product.retailer.joined.year}}
                  <Rating v-bind:value="product.retailer.rating" :size="15"></Rating>
                </span>
              </v-tooltip>
            </v-subheader>
            <v-btn
                @click="AddProductToCart"
                class="mx-2 my-2"
                color="secondary">
              <v-icon dark>
                add_shopping_cart
              </v-icon>
              Add to shopping cart
            </v-btn>
          </v-card>
      </v-row>
      <v-card class="mx-12 my-12">
        category: {{product.category.name}}
      </v-card>
    </v-container>
    <Reviews v-bind:reviews="product.reviews" :reviewed="product.reviewed" :productId="product.id"></Reviews>
  </div>
</template>

<script>
import Reviews from "@/components/Reviews";
import Rating from "@/components/Rating"
export default {
  name: "Product",
  components: {Reviews, Rating},
  props: {
    product: Object
  },
  methods:{
    AddProductToCart:function () {
      var cart = localStorage.getItem("cart");
      cart = (cart) ? JSON.parse(cart): [];
      if(cart.includes(this.product.id)){
        alert("product is already in cart")
      }else{
        cart.push(this.product.id);
      }
      localStorage.setItem("cart",JSON.stringify(cart));
      console.log(localStorage.getItem("cart"));
    }
  }
}
</script>

<style scoped>
.v-carousel{
  width: fit-content !important;
  max-width: 1000px;
  min-width: 500px;
  height: auto!important;
}
</style>