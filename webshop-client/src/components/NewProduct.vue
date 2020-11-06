<template>
  <v-card
      class="mx-8 my-8"
      width="300"
      min-height="400"
      height="min-content">
    <router-link :to="{name: 'createProduct'}">
      <v-btn color="secondary"
             flat
             width="100%"
             height="300">
        <v-icon x-large>
          library_add
        </v-icon>
      </v-btn>
    </router-link>
    <v-card-title style="color: black;">
      Create new product
    </v-card-title>
  </v-card>
</template>

<script>
export default {
  name: "NewProduct"
}
</script>

<style scoped>

</style>