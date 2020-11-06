<template>
  <v-card
      class="mx-8 my-8"
      width="600px">
      <v-responsive :aspect-ratio="16/7" >
          <v-subheader class="mx-2 my-2">
            <v-toolbar-title>
              your rating:
            </v-toolbar-title>
            <v-rating color="warning"
                      background-color="warning"
                      half-increments
                      empty-icon="star_border"
                      full-icon="star"
                      half-icon="star_half"
                      length="5"
                      size="18"
                      v-model="rating"
            ></v-rating>
            <v-toolbar-title class="shrink">
              ({{rating}})
            </v-toolbar-title>
          </v-subheader>
        <div class="mx-5 my-5">
            <v-textarea
                style="width: 100%"
                single-line
                full-width
                counter="400"
                label="Write a review"
                v-model="reviewBody"
            ></v-textarea>
            <v-btn @click="SendReview()"
                    color="secondary"
                    right>
              <v-icon>
                add_comment
              </v-icon>
               Post review
            </v-btn>
          </div>
      </v-responsive>
  </v-card>
</template>

<script>
import axios from "axios";

export default {
  name: "WriteReview",
  data: function () {
    return {
      rating: 0,
      reviewBody:''
    }
  },
  props:{
    productId:String
  },
  methods:{
    SendReview(){
      axios
          .post("http://localhost:4545/v1/products/" + this.productId + "/reviews",
              {
                rating: this.rating,
                body: this.reviewBody,
                customer:{
                  id: localStorage.id
                },
                product:{
                  id: this.productId
                }
              })
          .then(alert("Success"))
          .catch(error => alert(error))
    }
  }
}
</script>