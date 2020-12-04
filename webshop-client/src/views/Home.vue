<template>
  <v-app>
    <TopBar></TopBar>
  </v-app>
</template>

<script>

import TopBar from "@/components/TopBar";

export default {
  components: {
    TopBar
  },
  data(){
    return{
      products: Object
    }
  },
}
</script>

<style scoped>
/deep/ .v-text-field{
  align-self: auto;
  width: 400px;
}
</style>