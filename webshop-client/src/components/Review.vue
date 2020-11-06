<template>
  <v-card
          class="mx-8 my-8"
          width="600px">
    <v-responsive :aspect-ratio="16/7" >
      <v-subheader style="margin:15px">
        <v-avatar>
          <img v-bind:src="review.customer.avatar.url"/>
        </v-avatar>
        <v-tooltip top>
          <template v-slot:activator="{ on, attrs }">
          <v-card-title v-bind="attrs"
                        v-on="on"> {{review.customer.name}}
          </v-card-title>
          </template>
          <span>
            joined: {{review.customer.joined.day}}-{{review.customer.joined.month}}-{{review.customer.joined.year}}
          </span>
        </v-tooltip>
      </v-subheader>
      <v-divider></v-divider>
      <v-subheader>
        <Rating v-bind:size="14" :value="review.rating"></Rating>
        <v-card-text class="text-right">
          {{review.created.day}}-{{review.created.month}}-{{review.created.year}}
        </v-card-text>
      </v-subheader>
      <v-card-text class="subtitle-2">
        {{review.body}}
      </v-card-text>
    </v-responsive>
  </v-card>
</template>

<script>
import Rating from "@/components/Rating";

export default {
  components: {Rating},
  name: "Review",
  props:["review"]
}
</script>

<style scoped>

</style>