<template>
  <v-navigation-drawer
      class="primary"
      :mini-variant.sync="mini"
      permanent>
    <v-btn
        icon
        @click.stop="mini = !mini">
      <v-icon color="white"
              large>list</v-icon>
    </v-btn>
    <v-divider dark></v-divider>
    <v-card-title class="text-no-wrap"> Minimal price</v-card-title>
    <v-slider
        dark
        max="50"
        min="-50"
    ></v-slider>
    <v-container fluid></v-container>
  </v-navigation-drawer>
</template>

<script>
export default {
  data () {
    return {
      mini: true,
    }
  },
}
</script>

<style scoped>
.v-card-title{
  color: white;
}
.v-navigation-drawer{
  padding: 10px;
}
</style>
