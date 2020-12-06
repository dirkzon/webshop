<template>
  <v-card
      class="mx-8 my-8"
      width="600px">
    <v-responsive :aspect-ratio="16/7" >
      <UserCard v-bind:user="review.customer"></UserCard>
    </v-responsive>
  </v-card>
</template>

<script>
import UserCard from "@/components/UserCard";
export default {
  name: "Review",
  components: {UserCard},
  props:["review"]
}
</script>

<style scoped>

</style>