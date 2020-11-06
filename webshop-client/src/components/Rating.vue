<template>
  <v-rating v-if="value > 0"
            :value="value"
            :size="size"
            color="warning"
            background-color="warning"
            readonly
            half-increments
            empty-icon="star_border"
            full-icon="star"
            half-icon="star_half"
            length="5"
  ></v-rating>
</template>

<script>
export default {
  name: "Rating",
  props:{
    value: Number,
    size: Number
  }
}
</script>