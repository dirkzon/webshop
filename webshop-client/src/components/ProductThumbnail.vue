<template>
  <v-card width="300"
          class="mx-8 my-8">
    <v-responsive :aspect-ratio="4/6">
      <v-img contain
             aspect-ratio="1/1"
             class="grey lighten-3"
             max-height="300"
             min-height="270"
             :src='product.image.url'>
      </v-img>
      <div style="padding: 10px">
        <router-link :to="{name: 'product' , params: {id:this.product.id}}">
          <v-card-title style="color: black;">
            {{product.name}}</v-card-title>
        </router-link>
        <v-rating v-if="product.rating > 0"
                  :value="product.rating"
                  :size="18"
                  color="warning"
                  background-color="warning"
                  readonly
                  half-increments
                  empty-icon="star_border"
                  full-icon="star"
                  half-icon="star_half"
                  length="5"
        ></v-rating>
        <v-divider></v-divider>
        <v-card-subtitle style="font-size: 20px"
                         data-cy="price"> $ {{product.price.toFixed(2)}}</v-card-subtitle>
      </div>
    </v-responsive>
  </v-card>
</template>

<script>
export default {
  name: "ProductThumbnail",
  props: ["product"]
}
</script>

<style scoped>

</style>
