<template>
  <v-card
      v-if="scope == 'Retailer'"
      class="mx-8 my-8"
      width="300"
      min-height="400"
      height="min-content">
    <v-responsive :aspect-ratio="4/6">
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
      <div style="padding: 10px">
        <v-card-title style="color: black;">
          Create new product
        </v-card-title>
        <v-divider></v-divider>
      </div>
    </v-responsive>
  </v-card>
</template>

<script>
export default {
  name: "NewProduct",
  data(){
    return {
      scope: '',
    }
  },
  mounted() {
    this.scope = this.$cookies.get("scope");
  }
}
</script>


