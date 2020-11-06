<template>
  <v-row no-gutters>
    <div v-bind:key="product.id" v-for="product in products">
      <ProductThumbnail v-bind:product="product"></ProductThumbnail>
    </div>
    <NewProduct></NewProduct>
  </v-row>
</template>

<script>
import ProductThumbnail from "@/components/ProductThumbnail";
import NewProduct from "@/components/NewProduct";

export default {
  name: "Products",
  components: {NewProduct, ProductThumbnail},
  props: ["products"]
}
</script>