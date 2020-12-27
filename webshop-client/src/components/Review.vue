<template>
  <v-card
      class="mx-8 my-8"
      width="600px">
    <v-responsive :aspect-ratio="16/7" >
      <v-row>
        <UserCard v-bind:user="review.customer"></UserCard>
        <v-spacer></v-spacer>
        <v-btn v-if="review.canReport"
               @click="report"
               style="margin:20px"
               flat
               icon>
          <v-icon >report</v-icon>
        </v-btn>
      </v-row>
      <v-divider></v-divider>
      <v-subheader>
        <v-rating :value="review.rating"
                  :size="15"
                  color="warning"
                  background-color="warning"
                  readonly
                  half-increments
                  empty-icon="star_border"
                  full-icon="star"
                  half-icon="star_half"
                  length="5"
        ></v-rating>
        <v-card-text class="text-right">
          {{review.created}}
        </v-card-text>
      </v-subheader>
      <v-card-text class="subtitle-2">
        {{review.body}}
      </v-card-text>
    </v-responsive>
  </v-card>
</template>

<script>
import UserCard from "@/components/UserCard";
export default {
  name: "Review",
  components: {UserCard},
  props:["review"],
  methods:{
    report: function(){
      this.$emit('report', this.review.id);
    }
  }
}
</script>

<style scoped>

</style>
