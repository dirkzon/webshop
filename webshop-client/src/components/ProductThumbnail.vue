<template>
  <v-card
          class="mx-8 my-8"
          width="300"
          min-height="400"
          height="min-content">
    <v-img contain
           class="grey lighten-3"
           max-height="300"
           min-height="270"
           v-bind:src='product.images == null? "" : product.images[0].url'>
        <router-link :to="{name: 'editProduct' , params: {id:this.product.id}}">
          <v-btn icon>
            <v-icon>
              build
            </v-icon>
          </v-btn>
        </router-link>
    </v-img>
    <router-link :to="{name: 'product' , params: {id:this.product.id}}">
      <v-card-title style="color: black;">
        {{product.name}}
      </v-card-title>
    </router-link>
    <v-card-text >
     <Rating v-bind:size="18" :value="product.rating"></Rating>
      <v-divider></v-divider>
      <v-card-subtitle style="font-size: 25px"> $ {{product.price}}</v-card-subtitle>
    </v-card-text>
  </v-card>
</template>

<script>
import Rating from "@/components/Rating";

export default {
  components:{Rating},
  name: "ProductThumbnail",
  props: ["product"]
}
</script>

<style scoped>

</style>