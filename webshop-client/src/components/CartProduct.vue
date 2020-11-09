<template>
  <v-card
      class="mx-8 my-8"
      width="600"
      height="150">
      <v-img v-bind:src='product.images == null? "@/assets/data-not-found.svg": product.images[0].url'
             :aspect-ratio="1/1"
             height="100px"
             class="grey lighten-3"
             style="filter: blur(0px)"
      />
      <v-card-title> {{this.product.name}} </v-card-title>
  </v-card>
</template>

<script>
export default {
  name: "CartProduct",
  props: {
    product: Object
  },
}
</script>