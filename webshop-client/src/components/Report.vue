<template>
  <v-row style="padding: 20px">
    <Review v-bind:review="report.review"></Review>
    <div>
      <v-row style="padding: 30px">
        <div>
          <v-btn @click="removeReview"
                 color="#ec5858"
                 style="margin: 10px">
            <v-icon>
              delete_forever
            </v-icon>
            Remove review
          </v-btn>
        </div>
        <br>
        <div>
          <v-btn @click="dismissReport"
              color="secondary"
              style="margin: 10px">
            <v-icon>
              thumb_up
            </v-icon>
            Dismiss
          </v-btn>
        </div>
      </v-row>
    </div>
  </v-row>
</template>

<script>
import Review from "@/components/Review";

import reportService from "@/services/report-service"

export default {
  name: "Report",
  components: {Review},
  props:["report"],
  methods:{
    removeReview: async function(){
      await reportService.removeReview(this.report.id);
      await window.location.reload()
    },
    dismissReport: async function(){
      await reportService.dismissReport(this.report.id);
      await window.location.reload()
    }
  }
}
</script>

<style scoped>

</style>
